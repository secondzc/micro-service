package com.tongyuan.b;

import com.tongyuan.event.Event;
import com.tongyuan.event.EventManager;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by zhangcy on 2018/5/31
 */
@Service
public class BarService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EventManager eventManager;

    @Transactional
    @RabbitListener(queues = "foo-success-queue")
    public void handleFooSuccess(Event event){
        try {
            String barId = UUID.randomUUID().toString();
            jdbcTemplate.update(
                    "INSERT INTO bar (id,name) VALUES (?,?)",
                    barId,"bar"
            );
            throw new RuntimeException();
        } catch (RuntimeException e) {
            eventManager.sendEventQueue("bar-failure-queue",event);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
