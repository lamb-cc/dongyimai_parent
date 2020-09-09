package com.offcn.page.service.impl;

import com.offcn.page.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

//接收消息队列的消息，生成SKU商品详情页
@Component
public class ItemPageMessageLisneterImpl implements MessageListener {

    @Autowired
    private ItemPageService itemPageService;

    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage)message;
            try {
                Long goodsId = Long.parseLong(textMessage.getText());
                itemPageService.genItemHtml(goodsId);
                System.out.println("从消息队列中得到消息："+goodsId+"，生成商品详情页成功");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }


}