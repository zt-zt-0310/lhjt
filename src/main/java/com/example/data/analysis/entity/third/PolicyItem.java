package com.example.data.analysis.entity.third;


import lombok.Data;
import java.util.List;

/**
 * 具体业务数据实体（对应 JSON 中的 items 元素）
 */
@Data
public class PolicyItem {
    private String id; // 唯一标识
    private String title; // 标题
    private String translated_title; // 翻译后标题
    private String summary; // 摘要
    private String publish_time; // 发布时间（ISO格式）
    private String source_name; // 来源名称
    private String category; // 分类
    private String status; // 状态
    private List<String> tags; // 标签
    private String source_url; // 来源链接
    private Boolean is_positive_policy; // 是否正向政策
}
