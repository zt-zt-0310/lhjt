package com.example.data.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.data.analysis.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "报表数据表",description="报表数据表")
@TableName("finance_date")
public class FinanceDate extends BaseEntity<FinanceDate> implements Serializable {

    /**
     * 公司编号
     */
    @ApiModelProperty(value = "公司编号")
    private String companyNo;

    /**
     * 公司id
     */
    @ApiModelProperty(value = "公司id")
    private Long companyId;

    /**
     * 指标类别
     */
    @ApiModelProperty(value = "指标类别：营业收入\t01\n" +
            "利润总额\t02\n" +
            "实现税金\t03\n" +
            "入库税金\t04\n" +
            "所得税\t05\n" +
            "净利润\t06\n" +
            "实现税金(扬州地区)\t07\n" +
            "入库税金(扬州地区)\t08")
    private String typeNo;

    /**
     * 当前金额
     */
    @ApiModelProperty(value = "当前金额")
    private BigDecimal currentAmt;

    /**
     * 去年同期金额
     */
    @ApiModelProperty(value = "去年同期金额")
    private BigDecimal lastYearAmt;

    /**
     * 去年累计金额
     */
    @ApiModelProperty(value = "去年累计金额")
    private BigDecimal lastYearTotalAmt;

    /**
     * 今年累计金额
     */
    @ApiModelProperty(value = "今年累计金额")
    private BigDecimal thisYearTotalAmt;

    /**
     * 月增减额
     */
    @ApiModelProperty(value = "月增减额")
    private BigDecimal addAmt;

    /**
     * 月增减率
     */
    @ApiModelProperty(value = "月增减率")
    private BigDecimal addRate;

    /**
     * 年增减额
     */
    @ApiModelProperty(value = "年增减额")
    private BigDecimal yearAddAmt;

    /**
     * 年增减率
     */
    @ApiModelProperty(value = "年增减率")
    private BigDecimal yearAddRate;



    /**
     * 记账日期
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd"
    )
    @ApiModelProperty(value = "记账日期")
    private Date keepDate;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

}
