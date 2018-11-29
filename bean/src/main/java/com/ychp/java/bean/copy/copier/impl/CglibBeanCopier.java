package com.ychp.java.bean.copy.copier.impl;

import com.ychp.java.bean.copy.copier.BaseBeanCopier;
import com.ychp.java.bean.copy.model.FromBean;
import com.ychp.java.bean.copy.model.ToBean;
import org.springframework.cglib.beans.BeanCopier;

/**
 * @author yingchengpeng
 * @date 2018/11/29
 */
public class CglibBeanCopier extends BaseBeanCopier {

    private BeanCopier copier = BeanCopier.create(FromBean.class, ToBean.class, false);

    @Override
    public void copyProperties(FromBean fromBean, ToBean toBean) {
        copier.copy(fromBean, toBean, null);
    }
}
