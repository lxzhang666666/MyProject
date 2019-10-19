/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.myself.project.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 * @author wb-zb452365
 * @version $Id: KafkaReceiver.java, v 0.1 2019年10月17日 16:18 wb-zb452365 Exp $
 */
@Component
public class KafkaReceiver {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = {"zhangbao"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

            log.info("----------------- record =" + record);
            log.info("------------------ message =" + message);
        }

    }
}