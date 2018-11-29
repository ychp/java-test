package com.ychp.java.bean.copy;

import com.google.common.collect.Maps;
import com.ychp.java.bean.copy.copier.impl.CglibBeanCopier;
import com.ychp.java.bean.copy.copier.impl.CglibWithUtilsBeanCopier;
import com.ychp.java.bean.copy.copier.impl.NativeBeanCopier;
import com.ychp.java.bean.copy.copier.impl.SpringBeanCopier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author yingchengpeng
 * @date 2018/11/28
 */
public class BeanCopyPerformanceTest {

    private final static String JAVA_KEY = "java get/set";
    private final static String SPRING_KEY = "spring BeanUtils";
    private final static String CGLIB1_KEY = "cglib BeanCopier(不需要new copier)";
    private final static String CGLIB2_KEY = "cglib BeanCopier(BeanCopyUtils)";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Map<String, List<Long>> resultMap = Maps.newTreeMap();
        resultMap.put(JAVA_KEY, new ArrayList<>());
        resultMap.put(SPRING_KEY, new ArrayList<>());
        resultMap.put(CGLIB1_KEY, new ArrayList<>());
        resultMap.put(CGLIB2_KEY, new ArrayList<>());
        int loopTimes = 1;
        int testTimes = 10;

        for(; loopTimes < 1000001; loopTimes *= 10) {
            System.gc();
            long javaResult = 0;
            long springResult = 0;
            long cglibResult1 = 0;
            long cglibResult2 = 0;
            for (int j = 0; j < testTimes; j++) {
                javaResult += new NativeBeanCopier().testCopy(loopTimes);
                springResult += new SpringBeanCopier().testCopy(loopTimes);
                cglibResult1 += new CglibBeanCopier().testCopy(loopTimes);
                cglibResult2 += new CglibWithUtilsBeanCopier().testCopy(loopTimes);
            }

            System.out.println(loopTimes + " 量级");
            System.out.println(JAVA_KEY + ": " + javaResult + " ns");
            System.out.println(SPRING_KEY + ": " + springResult + " ns");
            System.out.println(CGLIB1_KEY + ": " + cglibResult1 + " ns");
            System.out.println(CGLIB2_KEY + ": " + cglibResult2 + " ns");
            System.out.println();

            resultMap.get(JAVA_KEY).add(BigDecimal.valueOf(javaResult).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(SPRING_KEY).add(BigDecimal.valueOf(springResult).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(CGLIB1_KEY).add(BigDecimal.valueOf(cglibResult1).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(CGLIB2_KEY).add(BigDecimal.valueOf(cglibResult2).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
        }

        System.out.println("| 类库 | 1 | 10 | 100 | 1000 | 10000 | 100000 | 1000000 |");
        System.out.println("| --- | --- | --- | --- | --- | --- | --- | --- |");

        for(Map.Entry<String, List<Long>> entry : resultMap.entrySet()) {
            StringBuilder outStr = new StringBuilder("| " + entry.getKey() + " | ");
            for (Long result : entry.getValue()) {
                outStr.append(BigDecimal.valueOf(result).divide(BigDecimal.valueOf(1000000), 6, RoundingMode.HALF_UP)).append(" ms | ");
            }
            System.out.println(outStr.toString());
        }

    }

}
