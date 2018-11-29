package com.ychp.java.bean.copy.copier.impl;

import com.ychp.java.bean.copy.copier.BaseBeanCopier;
import com.ychp.java.bean.copy.model.FromBean;
import com.ychp.java.bean.copy.model.ToBean;
import org.springframework.beans.BeanUtils;

/**
 * @author yingchengpeng
 * @date 2018/11/29
 */
public class SpringBeanCopier extends BaseBeanCopier {
    @Override
    public void copyProperties(FromBean fromBean, ToBean toBean) {
        BeanUtils.copyProperties(fromBean, toBean);
    }
}
