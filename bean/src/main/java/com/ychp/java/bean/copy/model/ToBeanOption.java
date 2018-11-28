package com.ychp.java.bean.copy.model;

import com.tuyang.beanutils.annotation.BeanCopySource;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yingchengpeng
 * @date 2018/11/28
 */
@Data
@BeanCopySource(source = FromBean.class)
public class ToBeanOption implements Serializable {

    private static final long serialVersionUID = -3245359209464611395L;
    private String id;
    private String name;
    private String desc;
    private String subId;
    private String bizId;
    private Integer bizType;
    private List<String> subBizIds;
    private Integer status;
    private Boolean isDeleted;
    private Long quantity;
    private Map<String, Object> extra;
    private String entityId;
    private Integer entityType;
    private Long channelInventoryId;
    private Long inventoryEventId;
    private Long realQuantityDelta;
    private Long safeQuantityDelta;
    private Long preorderQuantityDelta;
    private Long withholdQuantityDelta;
    private Long occupyQuantityDelta;
    private Long expectQuantity;
    private Long realQuantityResult;
    private Long safeQuantityResult;
    private Long preorderQuantityResult;
    private Long withholdQuantityResult;
    private Long occupyQuantityResult;
    private Integer version;
    private Date createdAt;
    private Date updatedAt;

}
