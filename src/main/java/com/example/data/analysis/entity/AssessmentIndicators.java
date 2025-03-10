package com.example.data.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.data.analysis.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "考核指标",description="考核指标表")
@TableName("assessment_indicators")
public class AssessmentIndicators extends BaseEntity<AssessmentIndicators> implements Serializable {
    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 公司id
     */
    private String companyName;

    /**
     * 公司编号
     */
    private String companyNo;

    /**
     * 考核内容
     */
    private String assessmentContent;

    /**
     * 考核目标(万)
     */
    private String appraisalTarget;

    /**
     * 分数
     */
    private int score;

    /**
     * 得分分数
     */
    private BigDecimal actualScore;

    /**
     * 实际金额(万)
     */
    private String actualAmount;

    /**
     * 完成率(%)
     */
    private BigDecimal rate;

    /**
     * 考核日期
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date keepDate;
}
