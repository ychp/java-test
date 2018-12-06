package com.ychp.java.bean.loop.runner.impl;

import com.ychp.java.bean.loop.runner.BaseLoop;

import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018/12/6
 */
public class ForeachLoop extends BaseLoop {

    @Override
    public void loop(List<String> testDatas) {
        for(String data: testDatas) {
            String newData = data + "1";
        }
    }
}
