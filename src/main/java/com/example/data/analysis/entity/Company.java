package com.example.data.analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.data.analysis.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "公司信息",description="公司信息表")
@TableName("company")
public class Company extends BaseEntity<Company> implements Serializable {

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司编号
     */
    private String companyNo;

    /**
     * 上级公司id
     */
    private Long highCompanyId;

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




}
