package com.offcn.search.service;

import com.offcn.pojo.TbItem;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {

    /**
     * 搜索商品信息
     * @param searchMap
     * @return
     */
    public Map search(Map searchMap);

    /**
     * 增量数据更新到solr（新增商品数据）（在运营商管理后台中审核模块，审核通过按钮）
     * @param itemList
     */
    public void importItem(List<TbItem> itemList);

    /**
     * 增量数据更新到solr（删除商品数据）（在运营商管理后台中审核模块，删除按钮）
     * @param ids
     */
    public void deleteByGoodsIds(List ids);
}
