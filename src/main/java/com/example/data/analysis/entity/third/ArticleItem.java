package com.example.data.analysis.entity.third;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 文章列表项实体
 */
@Data
public class ArticleItem {
    private String id;
    private String title;
    @JsonProperty("translated_title")
    private String translatedTitle;
    private String summary;
    @JsonProperty("publish_time")
    private Date publishTime;  // 假设接口返回ISO格式时间（如yyyy-MM-dd HH:mm:ss）
    @JsonProperty("source_name")
    private String sourceName;
    private String category;
    @JsonProperty("sub_category")
    private String subCategory;
    private List<String> tags;
    @JsonProperty("source_url")
    private String sourceUrl;
    @JsonProperty("apply_status")
    private String applyStatus;
    @JsonProperty("is_positive_policy")
    private Boolean isPositivePolicy;

}
