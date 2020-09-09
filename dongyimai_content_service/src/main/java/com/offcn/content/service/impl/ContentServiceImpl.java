package com.offcn.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.content.service.ContentService;
import com.offcn.entity.PageResult;
import com.offcn.mapper.TbContentMapper;
import com.offcn.pojo.TbContent;
import com.offcn.pojo.TbContentExample;
import com.offcn.pojo.TbContentExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    //findALL
    public List<TbContent> findAll() {
        return contentMapper.selectByExample(null);
    }

    //findPage
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbContent> page = (Page<TbContent>) contentMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    //add（已修改，需求：数据库增加操作时，需清空redis缓存）
    public void add(TbContent content) {
        contentMapper.insert(content);
        //清空缓存
        redisTemplate.boundHashOps("content").delete(content.getCategoryId());
    }

    //update（已修改，需求：数据库修改操作时，需清空redis缓存）
    public void update(TbContent content) {
        //1.获得修改之前的分类ID
        Long categoryId = contentMapper.selectByPrimaryKey(content.getId()).getCategoryId();
        //2.根据分类ID在缓存中删除相应记录
        redisTemplate.boundHashOps("content").delete(categoryId);

        contentMapper.updateByPrimaryKey(content);

        //3.清空修改之后的分类的记录
        if (content.getCategoryId().longValue() != categoryId.longValue()) {
            redisTemplate.boundHashOps("content").delete(content.getCategoryId());
        }
    }

    //findOne
    public TbContent findOne(Long id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    //delete（已修改，需求：数据库删除操作时，需清空redis缓存）
    public void delete(Long[] ids) {
        for (Long id : ids) {
            Long categoryId = contentMapper.selectByPrimaryKey(id).getCategoryId();
            //清空缓存
            redisTemplate.boundHashOps("content").delete(categoryId);

            contentMapper.deleteByPrimaryKey(id);
        }
    }

    //条件、模糊查询+分页
    public PageResult findPage(TbContent content, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();

        if (content != null) {
            if (content.getTitle() != null && content.getTitle().length() > 0) {
                criteria.andTitleLike("%" + content.getTitle() + "%");
            }
            if (content.getUrl() != null && content.getUrl().length() > 0) {
                criteria.andUrlLike("%" + content.getUrl() + "%");
            }
            if (content.getPic() != null && content.getPic().length() > 0) {
                criteria.andPicLike("%" + content.getPic() + "%");
            }
            if (content.getStatus() != null && content.getStatus().length() > 0) {
                criteria.andStatusLike("%" + content.getStatus() + "%");
            }
        }

        Page<TbContent> page = (Page<TbContent>) contentMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 通过广告分类ID查询本类广告列表（网站前台→首页广告轮播）
     * @param categoryId
     * @return
     */
    public List<TbContent> findContentByCategoryId(Long categoryId) {
        //查询缓存，如果缓存中没有数据，则查询数据库，并同步回缓存
        List<TbContent> contentList = (List<TbContent>) redisTemplate.boundHashOps("content").get(categoryId);

        if (CollectionUtils.isEmpty(contentList)) {
            TbContentExample tbContentExample = new TbContentExample();
            Criteria criteria = tbContentExample.createCriteria();
            criteria.andCategoryIdEqualTo(categoryId);   //分类ID
            criteria.andStatusEqualTo("1"); //状态 有效
            tbContentExample.setOrderByClause("sort_order");   //排序

            contentList = contentMapper.selectByExample(tbContentExample);
            System.out.println("从数据库查询到的数据");

            //同步缓存
            redisTemplate.boundHashOps("content").put(categoryId, contentList);
        } else {
            System.out.println("从缓存中查询到的数据");
        }
        return contentList;
    }

}