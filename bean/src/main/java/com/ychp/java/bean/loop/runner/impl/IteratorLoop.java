package com.ychp.java.bean.loop.runner.impl;

import com.ychp.java.bean.loop.runner.BaseLoop;

import java.util.Iterator;
import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018/12/6
 */
public class IteratorLoop extends BaseLoop {

    @Override
    public void loop(List<String> testDatas) {
        Iterator<String> iterator = testDatas.iterator();
        while (iterator.hasNext()) {
            String newData = iterator.next() + "1";
        }
    }
}
