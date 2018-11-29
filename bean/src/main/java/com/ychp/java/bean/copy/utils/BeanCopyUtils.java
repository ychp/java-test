package com.ychp.java.bean.copy.utils;

import org.springframework.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yingchengpeng
 * @date 2018/11/29
 */
public class BeanCopyUtils {

    private static Map<String, BeanCopier> copierByClass = new ConcurrentHashMap<>();

    public static void copyProperties(Object source, Object target) {
        getCopier(source.getClass(), target.getClass()).copy(source, target, null);
    }

    private static BeanCopier getCopier(Class source, Class target) {
        String key = generateKey(source, target);
        BeanCopier copier = copierByClass.get(key);
        if(copier == null) {
            System.out.println(key);
            copier = BeanCopier.create(source, target, false);
            copierByClass.put(key, copier);
        }
        return copier;
    }

    private static String generateKey(Class source, Class target) {
        return source.getName() + "&" + target.getName();
    }
}
