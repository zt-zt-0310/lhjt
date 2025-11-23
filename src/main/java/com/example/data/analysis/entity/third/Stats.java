package com.example.data.analysis.entity.third;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 统计信息实体（根据分类动态返回）
 */
public class Stats {
    @JsonProperty("total_count")
    private Integer totalCount;  // FDA/EMA/PMDA：总政策数
    @JsonProperty("year_count")
    private Integer yearCount;   // FDA/EMA/PMDA：当年发布数
    @JsonProperty("positive_count")
    private Integer positiveCount;  // FDA/EMA/PMDA：利好政策数

    @JsonProperty("pending_total")
    private Integer pendingTotal;  // 项目申报：未申报总数
    @JsonProperty("pending_year")
    private Integer pendingYear;   // 项目申报：当年未申报数
    @JsonProperty("submitted_total")
    private Integer submittedTotal;  // 项目申报：已申报总数
    @JsonProperty("submitted_year")
    private Integer submittedYear;   // 项目申报：当年已申报数

}
