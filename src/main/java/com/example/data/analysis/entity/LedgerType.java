package com.example.data.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.data.analysis.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "报表类别列表",description="报表类别列表")
@TableName("ledger_type")
public class LedgerType extends BaseEntity<LedgerType> implements Serializable {
    /**
     * 台账分类名字
     */
    private String ledgerName;
    /**
     * 台账分类编号
     */
    private String ledgerNo;
    /**
     * 上级分类编号
     */
    private String upLedgerNo;
    /**
     * 分类级别 0一级，1二级
     * */
    private String level;

}
