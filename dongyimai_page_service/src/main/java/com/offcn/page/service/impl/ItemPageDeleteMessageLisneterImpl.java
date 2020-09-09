package com.offcn.page.service.impl;

import com.offcn.page.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

//接收消息队列的消息，删除SKU商品详情页
@Component
public class ItemPageDeleteMessageLisneterImpl implements MessageListener {

    @Autowired
    private ItemPageService itemPageService;

    public void onMessage(Message message) {
        if(message instanceof ObjectMessage){
            ObjectMessage objectMessage = (ObjectMessage)message;
            try {
                Long[] ids = (Long[])objectMessage.getObject();

                itemPageService.deleteItemHtml(ids);

                System.out.println("接收到消息队列的消息，删除商品详情页成功");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }


}