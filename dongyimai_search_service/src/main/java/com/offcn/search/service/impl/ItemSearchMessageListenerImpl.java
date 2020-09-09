package com.offcn.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.offcn.pojo.TbItem;
import com.offcn.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;

/*接收消息队列的消息，新增商品同步到solr中*/
@Component
public class ItemSearchMessageListenerImpl implements MessageListener {

    @Autowired
    private ItemSearchService itemSearchService;

    public void onMessage(Message message) {
        //1.接收消息进行类型转换
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage)message;
            try {
                List<TbItem> itemList = JSON.parseArray(textMessage.getText(), TbItem.class);
                //2.调用搜索服务，进行同步solr处理
                itemSearchService.importItem(itemList);
                System.out.println("成功接收消息队列的信息，商品导入solr成功");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }


}