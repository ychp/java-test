package com.ychp.java.bean.copy;

import com.google.common.collect.Maps;
import com.tuyang.beanutils.BeanCopyUtils;
import com.ychp.java.bean.copy.model.FromBean;
import com.ychp.java.bean.copy.model.ToBean;
import com.ychp.java.bean.copy.model.ToBeanOption;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

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
    private final static String YT1_KEY = "yangtu BeanCopyUtils(未指定source)";
    private final static String YT2_KEY = "yangtu BeanCopyUtils(指定source)";
    private final static String CGLIB1_KEY = "cglib BeanCopier(每次new copier)";
    private final static String CGLIB2_KEY = "cglib BeanCopier(不需要new copier)";
    private static BeanCopier copier = BeanCopier.create(FromBean.class, ToBean.class, false);


    public static void main(String[] args) {
        Map<String, List<Long>> resultMap = Maps.newTreeMap();
        resultMap.put(JAVA_KEY, new ArrayList<>());
        resultMap.put(SPRING_KEY, new ArrayList<>());
        resultMap.put(YT1_KEY, new ArrayList<>());
        resultMap.put(YT2_KEY, new ArrayList<>());
        resultMap.put(CGLIB1_KEY, new ArrayList<>());
        resultMap.put(CGLIB2_KEY, new ArrayList<>());
        int loopTimes = 1;
        int testTimes = 10;

        long setPre = System.currentTimeMillis();
        long tmp = System.currentTimeMillis() - setPre;
        long setNext = System.currentTimeMillis();
        long mathCost = setNext - setPre;

        for(; loopTimes < 1000001; loopTimes *= 10) {
            long javaResult = 0;
            long springResult = 0;
            long yt1Result = 0;
            long yt2Result = 0;
            long cglibResult1 = 0;
            long cglibResult2 = 0;
            for (int j = 0; j < testTimes; j++) {
                javaResult += testJava(loopTimes);
                springResult += testSpringBeanUtils(loopTimes);
                yt1Result += testYangtu222BeanCopyUtilsWithoutOption(loopTimes);
                yt2Result += testYangtu222BeanCopyUtilsWithOption(loopTimes);
                cglibResult1 += testCglibBeanCopier(loopTimes);
                cglibResult2 += testCglibBeanCopierOnce(loopTimes);
            }

            javaResult -= mathCost * loopTimes;
            springResult -= mathCost * loopTimes;
            yt1Result -= mathCost * loopTimes;
            yt2Result -= mathCost * loopTimes;
            cglibResult1 -= mathCost * loopTimes;
            cglibResult2 -= mathCost * loopTimes;

            System.out.println(loopTimes + " 量级");
            System.out.println(JAVA_KEY + ": " + javaResult + " ms");
            System.out.println(SPRING_KEY + ": " + springResult + " ms");
            System.out.println(YT1_KEY + ": " + yt1Result + " ms");
            System.out.println(YT2_KEY + ": " + yt2Result + " ms");
            System.out.println(CGLIB1_KEY + ": " + cglibResult1 + " ms");
            System.out.println(CGLIB2_KEY + ": " + cglibResult2 + " ms");
            System.out.println();

            resultMap.get(JAVA_KEY).add(BigDecimal.valueOf(javaResult).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(SPRING_KEY).add(BigDecimal.valueOf(springResult).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(YT1_KEY).add(BigDecimal.valueOf(yt1Result).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(YT2_KEY).add(BigDecimal.valueOf(yt2Result).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(CGLIB1_KEY).add(BigDecimal.valueOf(cglibResult1).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
            resultMap.get(CGLIB2_KEY).add(BigDecimal.valueOf(cglibResult2).divide(BigDecimal.valueOf(testTimes), 0, RoundingMode.HALF_UP).longValue());
        }

        System.out.println("| 类库 | 1 | 10 | 100 | 1000 | 10000 | 100000 | 1000000 |");
        System.out.println("| --- | --- | --- | --- | --- | --- | --- | --- |");

        for(Map.Entry<String, List<Long>> entry : resultMap.entrySet()) {
            StringBuilder outStr = new StringBuilder("| " + entry.getKey() + " | ");
            for (Long result : entry.getValue()) {
                outStr.append(result).append(" | ");
            }
            System.out.println(outStr.toString());
        }

    }

    private static long testJava(Integer loopTimes) {
        FromBean fromBean;

        ToBean toBean;

        long pre = System.currentTimeMillis();
        long setCost = 0;
        for(int i = 1; i< loopTimes; i++) {
            long setPre = System.currentTimeMillis();
            fromBean = new FromBean(i);
            setCost += System.currentTimeMillis() - setPre;

            toBean = new ToBean();
            toBean.setId(fromBean.getId());
            toBean.setName(fromBean.getName());
            toBean.setDesc(fromBean.getDesc());
            toBean.setSubId(fromBean.getSubId());
            toBean.setBizId(fromBean.getBizId());
            toBean.setBizType(fromBean.getBizType());
            toBean.setSubBizIds(fromBean.getSubBizIds());
            toBean.setStatus(fromBean.getStatus());
            toBean.setIsDeleted(fromBean.getIsDeleted());
            toBean.setQuantity(fromBean.getQuantity());
            toBean.setExtra(fromBean.getExtra());
            toBean.setEntityId(fromBean.getEntityId());
            toBean.setEntityType(fromBean.getEntityType());
            toBean.setTargetId(fromBean.getTargetId());
            toBean.setEventId(fromBean.getEventId());
            toBean.setRealQuantityDelta(fromBean.getRealQuantityDelta());
            toBean.setSafeQuantityDelta(fromBean.getSafeQuantityDelta());
            toBean.setPreOrderQuantityDelta(fromBean.getPreOrderQuantityDelta());
            toBean.setWithholdQuantityDelta(fromBean.getWithholdQuantityDelta());
            toBean.setOccupyQuantityDelta(fromBean.getOccupyQuantityDelta());
            toBean.setExpectQuantity(fromBean.getExpectQuantity());
            toBean.setRealQuantityResult(fromBean.getRealQuantityResult());
            toBean.setSafeQuantityResult(fromBean.getSafeQuantityResult());
            toBean.setPreOrderQuantityResult(fromBean.getPreOrderQuantityResult());
            toBean.setWithholdQuantityResult(fromBean.getWithholdQuantityResult());
            toBean.setOccupyQuantityResult(fromBean.getOccupyQuantityResult());
            toBean.setVersion(fromBean.getVersion());
            toBean.setCreatedAt(fromBean.getCreatedAt());
            toBean.setUpdatedAt(fromBean.getUpdatedAt());

        }
        return System.currentTimeMillis() - pre - setCost;
    }

    private static long testSpringBeanUtils(Integer loopTimes) {

        ToBean toBean;

        long pre = System.currentTimeMillis();
        long setCost = 0;
        for(int i = 1; i< loopTimes; i++) {
            long setPre = System.currentTimeMillis();
            FromBean fromBean = new FromBean(i);
            setCost += System.currentTimeMillis() - setPre;

            toBean = new ToBean();
            BeanUtils.copyProperties(fromBean, toBean);
        }
        return System.currentTimeMillis() - pre - setCost;
    }

    private static long testYangtu222BeanCopyUtilsWithoutOption(Integer loopTimes) {
        FromBean fromBean;

        ToBean toBean;

        long pre = System.currentTimeMillis();
        long setCost = 0;
        for(int i = 1; i< loopTimes; i++) {
            long setPre = System.currentTimeMillis();
            fromBean = new FromBean(i);
            setCost += System.currentTimeMillis() - setPre;

            toBean = new ToBean();
            BeanCopyUtils.copyBean(fromBean, toBean);
        }
        return System.currentTimeMillis() - pre - setCost;
    }

    private static long testYangtu222BeanCopyUtilsWithOption(Integer loopTimes) {
        FromBean fromBean;

        ToBean toBean;

        long pre = System.currentTimeMillis();
        long setCost = 0;

        for(int i = 1; i< loopTimes; i++) {
            long setPre = System.currentTimeMillis();
            fromBean = new FromBean(i);
            setCost += System.currentTimeMillis() - setPre;

            toBean = new ToBean();
            BeanCopyUtils.copyBean(fromBean, toBean, ToBeanOption.class);
        }
        return System.currentTimeMillis() - pre - setCost;
    }

    private static long testCglibBeanCopier(Integer loopTimes) {
        FromBean fromBean;

        ToBean toBean;

        long pre = System.currentTimeMillis();
        long setCost = 0;
        for(int i = 1; i< loopTimes; i++) {
            long setPre = System.currentTimeMillis();
            fromBean = new FromBean(i);
            setCost += System.currentTimeMillis() - setPre;

            BeanCopier copier = BeanCopier.create(FromBean.class, ToBean.class, false);
            toBean = new ToBean();
            copier.copy(fromBean, toBean, null);
        }
        return System.currentTimeMillis() - pre - setCost;
    }

    private static long testCglibBeanCopierOnce(Integer loopTimes) {
        FromBean fromBean;

        ToBean toBean;

        long pre = System.currentTimeMillis();
        long setCost = 0;
        for(int i = 1; i< loopTimes; i++) {
            long setPre = System.currentTimeMillis();
            fromBean = new FromBean(i);
            setCost += System.currentTimeMillis() - setPre;

            toBean = new ToBean();
            copier.copy(fromBean, toBean, null);
        }
        return System.currentTimeMillis() - pre - setCost;
    }
}
