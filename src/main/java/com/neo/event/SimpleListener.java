package com.neo.event;

import com.google.common.eventbus.Subscribe;

/**
 * Created by neowyp on 2016/3/8.
 */
public class SimpleListener {
    @Subscribe
    public void task(String s) {
        System.out.println("do task(" + s + ")");
    }
}
