package com.ychp.java.bean.copy.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
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
public class FromBean implements Serializable {

    private static final long serialVersionUID = 295004058569864848L;
    public FromBean(Integer loopNum) {
        this.id = "id" + loopNum;
        this.name = "name" + loopNum;
        this.desc = "desc" + loopNum;
        this.subId = "subId" + loopNum;
        this.bizId = "bizId" + loopNum;
        this.bizType = 1 + loopNum;
        this.subBizIds = Lists.newArrayList("111", "2222", "3333", ""  + loopNum);
        this.status = 1 + loopNum;
        this.isDeleted = true;
        this.quantity = 100L + loopNum;
        this.extra = ImmutableMap.of("key1", "value" + loopNum);
        this.entityId = "entityId" + loopNum;
        this.entityType = 2222 + loopNum;
        this.targetId = 42342L + loopNum;
        this.eventId = 3214213L + loopNum;
        this.realQuantityDelta = 1000L + loopNum;
        this.safeQuantityDelta = 1000L + loopNum;
        this.preOrderQuantityDelta = 1000L + loopNum;
        this.withholdQuantityDelta = 1000L + loopNum;
        this.occupyQuantityDelta = 1000L + loopNum;
        this.expectQuantity = 1000L + loopNum;
        this.realQuantityResult = 1000L + loopNum;
        this.safeQuantityResult = 1000L + loopNum;
        this.preOrderQuantityResult = 1000L + loopNum;
        this.withholdQuantityResult = 1000L + loopNum;
        this.occupyQuantityResult = 1000L + loopNum;
        this.version = 111 + loopNum;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

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
    private Map<String, String> extra;
    private String entityId;
    private Integer entityType;
    private Long targetId;
    private Long eventId;
    private Long realQuantityDelta;
    private Long safeQuantityDelta;
    private Long preOrderQuantityDelta;
    private Long withholdQuantityDelta;
    private Long occupyQuantityDelta;
    private Long expectQuantity;
    private Long realQuantityResult;
    private Long safeQuantityResult;
    private Long preOrderQuantityResult;
    private Long withholdQuantityResult;
    private Long occupyQuantityResult;
    private Integer version;
    private Date createdAt;
    private Date updatedAt;

}
