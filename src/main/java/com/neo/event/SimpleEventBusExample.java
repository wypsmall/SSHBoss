package com.neo.event;

import com.google.common.eventbus.EventBus;

/**
 * Created by neowyp on 2016/3/8.
 */
public class SimpleEventBusExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        System.out.println("Post Simple EventBus Example");
        eventBus.post("Simple EventBus Example");

    }
}
