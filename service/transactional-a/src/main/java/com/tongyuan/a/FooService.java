package com.tongyuan.a;

import com.tongyuan.event.Event;
import com.tongyuan.event.EventManager;
import com.tongyuan.event.EventType;
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
public class FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EventManager eventManager;

    @Transactional
    public void insertFoo(String name){
        String fooId = UUID.randomUUID().toString();
        try {
            jdbcTemplate.update(
                   "INSERT INTO foo (id,name) VALUES (?,?)",
                   fooId,name
            );
        } finally {
            Event event = new Event(EventType.INSERT,"Foo",fooId);
            eventManager.insertEvent(event);
            eventManager.sendEventQueue("foo-success-queue",event);
        }
    }

    @Transactional
    @RabbitListener(queues = "bar-failure-queue")
    public void handleBarFailure(Event event){
        String fooId = eventManager.queryModelId(event.getId());
        jdbcTemplate.update(
                "DELETE FROM foo WHERE id = ?",fooId
        );
    }

}
