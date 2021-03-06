package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.entity.PageResult;
import com.offcn.mapper.TbItemCatMapper;
import com.offcn.pojo.TbItemCat;
import com.offcn.pojo.TbItemCatExample;
import com.offcn.pojo.TbItemCatExample.Criteria;
import com.offcn.sellergoods.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    //findAll
    public List<TbItemCat> findAll() {
        return itemCatMapper.selectByExample(null);
    }

    //findPage
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbItemCat> page = (Page<TbItemCat>) itemCatMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    //add
    public void add(TbItemCat itemCat) {
        itemCatMapper.insert(itemCat);
    }

    //update
    public void update(TbItemCat itemCat) {
        itemCatMapper.updateByPrimaryKey(itemCat);
    }

    //findOne
    public TbItemCat findOne(Long id) {
        return itemCatMapper.selectByPrimaryKey(id);
    }

    //delete
    public void delete(Long[] ids) {
        for (Long id : ids) {
            itemCatMapper.deleteByPrimaryKey(id);
        }
    }

    //条件、模糊查询+分页
    public PageResult findPage(TbItemCat itemCat, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();

        if (itemCat != null) {
            if (itemCat.getName() != null && itemCat.getName().length() > 0) {
                criteria.andNameLike("%" + itemCat.getName() + "%");
            }
        }

        Page<TbItemCat> page = (Page<TbItemCat>) itemCatMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 通过父级ID查询本级商品分类列表（下钻法，内关联表）
     * @param parentId 父级ID
     * @return
     */
    public List<TbItemCat> findByParentId(Long parentId) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        Criteria criteria = tbItemCatExample.createCriteria();
        //设置父级ID为查询条件
        criteria.andParentIdEqualTo(parentId);

        //将分类对象放入缓存   {三级分类名称：模板ID}
        List<TbItemCat> itemCatList = this.findAll();
        for(TbItemCat itemCat:itemCatList){
            //执行放入缓存
            redisTemplate.boundHashOps("itemCat").put(itemCat.getName(),itemCat.getTypeId());
        }
        System.out.println("分类存入缓存成功");

        return itemCatMapper.selectByExample(tbItemCatExample);
    }

}
