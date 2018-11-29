package com.ychp.java.bean.copy.copier.impl;

import com.ychp.java.bean.copy.copier.BaseBeanCopier;
import com.ychp.java.bean.copy.model.FromBean;
import com.ychp.java.bean.copy.model.ToBean;
import org.springframework.beans.BeanUtils;

/**
 * @author yingchengpeng
 * @date 2018/11/29
 */
public class NativeBeanCopier extends BaseBeanCopier {
    @Override
    public void copyProperties(FromBean fromBean, ToBean toBean) {
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
}
