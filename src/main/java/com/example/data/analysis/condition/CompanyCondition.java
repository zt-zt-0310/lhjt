package com.example.data.analysis.condition;

import lombok.Data;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/1/17 18:15
 */
@Data
public class CompanyCondition {
    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司编号
     */
    private String companyNo;

    /**
     * 上级公司编号
     */
    private String highCompanyNo;

    /**
     * 当前页数
     */
    private Integer pageIndex;
    /**
     * 每页显示数量
     */
    private Integer pageSize;

    /**
     * 0是一级，1是二级
     */
    private String level;
}
