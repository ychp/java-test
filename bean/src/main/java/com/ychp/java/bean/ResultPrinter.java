package com.ychp.java.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

/**
 * @author yingchengpeng
 * @date 2018/12/6
 */
public class ResultPrinter {

    public void print(String title, String column, Map<String, List<Long>> resultMap) {
        System.out.println(title);
        System.out.println(column);

        for(Map.Entry<String, List<Long>> entry : resultMap.entrySet()) {
            StringBuilder outStr = new StringBuilder("| " + entry.getKey() + " | ");
            for (Long result : entry.getValue()) {
                outStr.append(BigDecimal.valueOf(result).divide(BigDecimal.valueOf(1000000),  3, RoundingMode.HALF_UP)).append(" ms | ");
            }
            System.out.println(outStr.toString());
        }
    }
}
