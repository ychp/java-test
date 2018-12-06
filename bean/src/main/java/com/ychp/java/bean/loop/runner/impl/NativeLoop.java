package com.ychp.java.bean.loop.runner.impl;

import com.ychp.java.bean.loop.runner.BaseLoop;

import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018/12/6
 */
public class NativeLoop extends BaseLoop {

    @Override
    public void loop(List<String> testDatas) {
        int length = testDatas.size();
        for(int i = 0; i < length; i++) {
            String data = testDatas.get(i) + "1";
        }
    }
}
