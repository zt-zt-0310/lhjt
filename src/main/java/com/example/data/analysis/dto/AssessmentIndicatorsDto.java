package com.example.data.analysis.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AssessmentIndicatorsDto implements Serializable {
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

}
