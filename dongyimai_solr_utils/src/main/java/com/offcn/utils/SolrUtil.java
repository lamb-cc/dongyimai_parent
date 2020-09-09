package com.offcn.utils;

import com.alibaba.fastjson.JSON;
import com.github.promeg.pinyinhelper.Pinyin;
import com.offcn.mapper.TbItemMapper;
import com.offcn.pojo.TbItem;
import com.offcn.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: cc
 * @Date: 2020/8/21 14:20
 * @Description: 数据库数据存入solr
 */
@Component
public class SolrUtil {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private SolrTemplate solrTemplate;


    public void importItemData() {
        //创建查询数据库对象tbItemExample
        TbItemExample tbItemExample = new TbItemExample();
        //创建查询条件对象criteria
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        //添加条件：审核通过状态
        criteria.andStatusEqualTo("1");
        //执行查询
        List<TbItem> itemList = itemMapper.selectByExample(tbItemExample);

        //将tb_item表中spec字段中的记录：键值对中的键，全部中文转英文
        for (TbItem item : itemList) {
            //从数据库中遍历查询spec数据，并进行数据类型转换，然后存入specMap
            Map<String, String> specMap = JSON.parseObject(item.getSpec(), Map.class);
            //创建一个pinyinMap，用于接收key中文转拼音后的specMap
            Map<String, String> pinyinMap = new HashMap<String, String>();
            //遍历key值
            for (String key : specMap.keySet()) {
                //将KEY值进行拼音转换，并重新将数据保存的到新的pinyinMap
                pinyinMap.put(Pinyin.toPinyin(key, "").toLowerCase(), specMap.get(key));
            }
            //重新设置到动态域
            item.setSpecMap(pinyinMap);
        }
        //将tb_item表中的数据存入solr（以集合形式存）
        solrTemplate.saveBeans(itemList);
        solrTemplate.commit();
        System.out.println("导入成功");

    }

    public void deleteSolrAll(){
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
        System.out.println("删除成功");
    }

    //主方法，运行上述方法，完成清空solr，再数据库表数据存入solr
    public static void main(String[] args) {
        //获取spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
        //从容器中获得solrUtil对象
        SolrUtil solrUtil = (SolrUtil) context.getBean("solrUtil");
        //先删除solr中所有数据
        solrUtil.deleteSolrAll();
        //将数据库中的表数据存入solr
        solrUtil.importItemData();

    }

}