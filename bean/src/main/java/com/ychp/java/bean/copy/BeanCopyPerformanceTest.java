package com.ychp.java.bean.copy;

import com.google.common.collect.Maps;
import com.ychp.java.bean.ResultPrinter;
import com.ychp.java.bean.copy.copier.impl.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yingchengpeng
 * @date 2018/11/28
 */
public class BeanCopyPerformanceTest {

    private final static String JAVA_KEY = "java get/set";
    private final static String SPRING_KEY = "spring BeanUtils";
    private final static String CGLIB1_KEY = "cglib BeanCopier(不需要new copier)";
    private final static String CGLIB2_KEY = "cglib BeanCopier(BeanCopyUtils)";
    private final static String APACHE_KEY = "apache BeanUtils";

    public static void main(String[] args) {
        Map<String, List<Long>> resultMap = Maps.newTreeMap();
        resultMap.put(JAVA_KEY, new ArrayList<>());
        resultMap.put(SPRING_KEY, new ArrayList<>());
        resultMap.put(CGLIB1_KEY, new ArrayList<>());
        resultMap.put(CGLIB2_KEY, new ArrayList<>());
        resultMap.put(APACHE_KEY, new ArrayList<>());
        int loopTimes = 1;
        int testTimes = 10;

        for(; loopTimes < 1000001; loopTimes *= 10) {
            System.gc();
            long javaResult = 0;
            long springResult = 0;
            long cglibResult1 = 0;
            long cglibResult2 = 0;
            long apacheResult = 0;
            for (int j = 0; j < testTimes; j++) {
                javaResult += new NativeBeanCopier().testCopy(loopTimes);
                springResult += new SpringBeanCopier().testCopy(loopTimes);
                cglibResult1 += new CglibBeanCopier().testCopy(loopTimes);
                cglibResult2 += new CglibWithUtilsBeanCopier().testCopy(loopTimes);
                apacheResult += new ApacheBeanCopier().testCopy(loopTimes);
            }

            System.out.println(loopTimes + " 量级");
            System.out.println(JAVA_KEY + ": " + javaResult + " ns");
            System.out.println(SPRING_KEY + ": " + springResult + " ns");
            System.out.println(CGLIB1_KEY + ": " + cglibResult1 + " ns");
            System.out.println(CGLIB2_KEY + ": " + cglibResult2 + " ns");
            System.out.println(APACHE_KEY + ": " + apacheResult + " ns");
            System.out.println();

            resultMap.get(JAVA_KEY).add(BigDecimal.valueOf(javaResult).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(SPRING_KEY).add(BigDecimal.valueOf(springResult).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(CGLIB1_KEY).add(BigDecimal.valueOf(cglibResult1).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(CGLIB2_KEY).add(BigDecimal.valueOf(cglibResult2).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(APACHE_KEY).add(BigDecimal.valueOf(apacheResult).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
        }

        new ResultPrinter().print("| 类库 | 1 | 10 | 100 | 1000 | 10000 | 100000 | 1000000 |",
                "| --- | --- | --- | --- | --- | --- | --- | --- |", resultMap);

    }

}
