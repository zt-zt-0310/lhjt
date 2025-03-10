package com.example.data.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.data.analysis.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "报表类别列表",description="报表类别列表")
@TableName("finance_type")
public class FinanceType extends BaseEntity<FinanceType> implements Serializable {

    /**
     * 类别名称
     */
    private String typeName;

    /**
     * 类别编号
     */
    private String typeNo;

    /**
     * 是否启用 1是0否
     */
    private String typeStatus;






}
