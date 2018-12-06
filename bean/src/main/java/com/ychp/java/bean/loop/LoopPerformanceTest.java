package com.ychp.java.bean.loop;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ychp.java.bean.ResultPrinter;
import com.ychp.java.bean.loop.runner.impl.ForeachLoop;
import com.ychp.java.bean.loop.runner.impl.IteratorLoop;
import com.ychp.java.bean.loop.runner.impl.LambdasLoop;
import com.ychp.java.bean.loop.runner.impl.NativeLoop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yingchengpeng
 * @date 2018/11/28
 */
public class LoopPerformanceTest {

    private final static String NATIVE_KEY = "native";
    private final static String FOREACH_KEY = "foreach";
    private final static String ITERATOR_KEY = "iterator";
    private final static String LAMBDAS_KEY = "lambdas";

    public static void main(String[] args) {
        Map<String, List<Long>> resultMap = Maps.newTreeMap();
        resultMap.put(NATIVE_KEY, new ArrayList<>());
        resultMap.put(FOREACH_KEY, new ArrayList<>());
        resultMap.put(ITERATOR_KEY, new ArrayList<>());
        resultMap.put(LAMBDAS_KEY, new ArrayList<>());
        int loopTimes = 10;
        int testTimes = 10;

        List<String> testData;

        for(; loopTimes < 10001; loopTimes *= 10) {

            testData = Lists.newArrayListWithCapacity(loopTimes);
            for(int i = 0; i < loopTimes; i ++) {
                testData.add("test" + i);
            }

            long nativeResult = 0;
            long foreachResult = 0;
            long iteratorResult = 0;
            long lambdasResult = 0;
            for (int j = 0; j < testTimes; j++) {
                nativeResult += new NativeLoop().testLoop(loopTimes, testData);
                foreachResult += new ForeachLoop().testLoop(loopTimes, testData);
                iteratorResult += new IteratorLoop().testLoop(loopTimes, testData);
                lambdasResult += new LambdasLoop().testLoop(loopTimes, testData);
            }

            System.out.println(loopTimes + " 量级");
            System.out.println(NATIVE_KEY + ": " + nativeResult + " ns");
            System.out.println(FOREACH_KEY + ": " + foreachResult + " ns");
            System.out.println(ITERATOR_KEY + ": " + iteratorResult + " ns");
            System.out.println(LAMBDAS_KEY + ": " + lambdasResult + " ns");
            System.out.println();

            resultMap.get(NATIVE_KEY).add(BigDecimal.valueOf(nativeResult).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(FOREACH_KEY).add(BigDecimal.valueOf(foreachResult).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(ITERATOR_KEY).add(BigDecimal.valueOf(iteratorResult).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(LAMBDAS_KEY).add(BigDecimal.valueOf(lambdasResult).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
        }

        new ResultPrinter().print("| 方式 | 10 | 100 | 1000 | 10000 |",
                "| --- | --- | --- | --- | --- |", resultMap);

    }

}
