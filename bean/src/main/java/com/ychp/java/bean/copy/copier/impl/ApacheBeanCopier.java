package com.ychp.java.bean.copy.copier.impl;

import com.ychp.java.bean.copy.copier.BaseBeanCopier;
import com.ychp.java.bean.copy.model.FromBean;
import com.ychp.java.bean.copy.model.ToBean;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author yingchengpeng
 * @date 2018/11/29
 */
public class ApacheBeanCopier extends BaseBeanCopier {
    @Override
    public void copyProperties(FromBean fromBean, ToBean toBean) {
        try {
            BeanUtils.copyProperties(fromBean, toBean);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
