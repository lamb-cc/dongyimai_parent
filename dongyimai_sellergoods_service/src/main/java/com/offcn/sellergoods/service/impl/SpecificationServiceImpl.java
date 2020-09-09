package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.entity.PageResult;
import com.offcn.group.Specification;
import com.offcn.mapper.TbSpecificationMapper;
import com.offcn.mapper.TbSpecificationOptionMapper;
import com.offcn.pojo.TbSpecification;
import com.offcn.pojo.TbSpecificationExample;
import com.offcn.pojo.TbSpecificationExample.Criteria;
import com.offcn.pojo.TbSpecificationOption;
import com.offcn.pojo.TbSpecificationOptionExample;
import com.offcn.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional  //注解式事务管理
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper specificationMapper;

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;


    //findAll
    public List<TbSpecification> findAll() {
        return specificationMapper.selectByExample(null);
    }

    //findPage
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    //add（已修改，需求：新增规格名称的同时，要新增对应的规格选项）（复合实体类Specification）
    public void add(Specification specification) {
        //1.保存规格名称
        specificationMapper.insert(specification.getSpecification());
        //2.得到规格名称ID
        if (null != specification.getSpecificationOptionList() && specification.getSpecificationOptionList().size() > 0) {
            for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
                //3.向规格选项中设置规格ID
                specificationOption.setSpecId(specification.getSpecification().getId());
                //4.保存规格选项
                specificationOptionMapper.insert(specificationOption);
            }
        }
    }

    //update（已修改，需求：修改规格名称的同时，要修改对应的规格选项）（复合实体类Specification）
    public void update(Specification specification) {
        //1.修改规格名称对象
        specificationMapper.updateByPrimaryKey(specification.getSpecification());
        //2.根据ID删除规格选项集合
        TbSpecificationOptionExample specificationOptionExample = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = specificationOptionExample.createCriteria();
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());
        //执行删除
        specificationOptionMapper.deleteByExample(specificationOptionExample);
        //3.重新插入规格选项
        if (!CollectionUtils.isEmpty(specification.getSpecificationOptionList())) {
            for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
                //先设置规格名称的ID
                specificationOption.setSpecId(specification.getSpecification().getId());
                specificationOptionMapper.insert(specificationOption);
            }
        }


    }

    //findOne（已修改，需求：根据ID查询规格名称时，也要同时查询对应的规格选项）（复合实体类Specification）
    public Specification findOne(Long id) {
        //1.根据ID查询规格名称
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        //2.根据ID关联查询规格选项
        TbSpecificationOptionExample specificationOptionExample = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = specificationOptionExample.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> specificationOptionList = specificationOptionMapper.selectByExample(specificationOptionExample);
        //3.将规格名称和规格选项集合设置到复合实体类中
        Specification specification = new Specification();
        specification.setSpecification(tbSpecification);
        specification.setSpecificationOptionList(specificationOptionList);

        return specification;
    }

    //delete（已修改，需求：根据IDS批量删除规格名称时，也要同时删除对应的规格选项）
    public void delete(Long[] ids) {
        for (Long id : ids) {
            //1.删除规格名称对象
            specificationMapper.deleteByPrimaryKey(id);
            //2.关联删除规格选项集合
            TbSpecificationOptionExample specificationOptionExample = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = specificationOptionExample.createCriteria();
            criteria.andSpecIdEqualTo(id);
            //执行删除
            specificationOptionMapper.deleteByExample(specificationOptionExample);

        }
    }

    //条件、模糊查询+分页
    public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbSpecificationExample example = new TbSpecificationExample();
        Criteria criteria = example.createCriteria();

        if (specification != null) {
            if (specification.getSpecName() != null && specification.getSpecName().length() > 0) {
                criteria.andSpecNameLike("%" + specification.getSpecName() + "%");
            }
        }

        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 查询规格名称下拉列表
     * @return
     */
    public List<Map> selectOptionList() {
        return specificationMapper.selectOptionList();
    }

}
