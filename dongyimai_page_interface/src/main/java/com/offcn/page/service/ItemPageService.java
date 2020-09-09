package com.offcn.page.service;

public interface ItemPageService {

    /**
     * 生成具体SKU商品详情页
     * @param goodsId
     * @return
     */
    public boolean genItemHtml(Long goodsId);

    /**
     * 批量删除SKU商品详情页
     * @param ids
     * @return
     */
    public boolean deleteItemHtml(Long[] ids);

}
