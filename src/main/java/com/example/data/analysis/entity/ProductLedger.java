package com.example.data.analysis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.data.analysis.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 公司台账实体类
 *
 * @author auto generated
 * @since 2025-10-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("product_ledger")
public class ProductLedger extends BaseEntity<FinanceType> implements Serializable  {

    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 批件日期（结题日期）
     */
    private String approvalDate;

    /**
     * 公司id
     */
    private Long compayId;

    /**
     * 转化公司
     */
    private String compayName;

    /**
     * 生产车间
     */
    private String produceWorkshop;

    /**
     * 转化时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date transformationDate;

    /**
     * 投产情况（排产、批量情况）
     */
    private String productionStatus;

    /**
     * 销售数量（月度、季度）
     */
    private BigDecimal salesNumber;

    /**
     * 累计销售额
     */
    private BigDecimal totalAmount;

    /**
     * 年度总销售额
     */
    private BigDecimal yearSalesAmount;

    /**
     * 销售金额（月度、季度）
     */
    private BigDecimal salesAmount;

    /**
     * 首次销售（起始月，销售额）
     */
    private BigDecimal firstSalesAmount;

}
