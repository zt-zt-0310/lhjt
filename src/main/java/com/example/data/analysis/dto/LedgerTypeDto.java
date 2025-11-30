package com.example.data.analysis.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.data.analysis.entity.LedgerType;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "报表类别列表",description="报表类别列表")
@TableName("ledger_type")
public class LedgerTypeDto {
    /**
     * 台账分类名字
     */
    private String ledgerName;
    /**
     * 台账分类编号
     */
    private String ledgerNo;
    /**
     * 台账分类二级
     */
    private List<LedgerType> ledgerTypeList;

}
