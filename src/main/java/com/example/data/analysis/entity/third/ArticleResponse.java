package com.example.data.analysis.entity.third;

import lombok.Data;

import java.util.List;

/**
 * 文章列表响应实体（顶层）
 */
@Data
public class ArticleResponse {
    // 列表数据
    private List<ArticleItem> items;
    // 统计信息（部分分类返回）
    private Stats stats;
    // 分页信息（通常接口会返回，假设包含）
//    private Pagination pagination;

}



