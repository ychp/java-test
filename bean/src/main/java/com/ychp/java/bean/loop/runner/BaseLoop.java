package com.ychp.java.bean.loop.runner;

import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018/12/6
 */
public abstract class BaseLoop {

    public Long testLoop(long loopTimes, List<String> testDatas) {
        long cost = 0;
        for (int i = 1; i < loopTimes; i++) {
            cost += testOne(testDatas);
        }
        return cost;
    }

    private long testOne(List<String> testDatas) {
        long pre = System.nanoTime();

        loop(testDatas);
        return System.nanoTime() - pre;
    }

    public abstract void loop(List<String> testDatas);
}
