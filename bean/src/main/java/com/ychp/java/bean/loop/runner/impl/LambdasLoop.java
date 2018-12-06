package com.ychp.java.bean.loop.runner.impl;

import com.ychp.java.bean.loop.runner.BaseLoop;

import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018/12/6
 */
public class LambdasLoop extends BaseLoop {

    @Override
    public void loop(List<String> testDatas) {
        testDatas.forEach(data -> {
            String newData = data + "1";
        });
    }
}
