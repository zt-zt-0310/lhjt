package com.example.data.analysis.dto;

import com.example.data.analysis.entity.FinanceDate;
import lombok.Data;

import java.util.List;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/12/28 14:26
 */
@Data
public class FinanceList extends FinanceDate {
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
     * 公司地址
     */
    private String companyAddress;

    /**
     * 0是一级，1是二级
     */
    private String level;

    /**
     * 子公司
     */
    private List<FinanceList> subCompany;
}
