/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.myself.project.impl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author wb-zb452365
 * @version $Id: KafkaSender.java, v 0.1 2019年10月17日 16:19 wb-zb452365 Exp $
 */
@Component
public class KafkaSender {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send(String str) {
        log.info("+++++++++++++++++++++  message = {}", str);
        kafkaTemplate.send("zhangbao", str);
    }
}