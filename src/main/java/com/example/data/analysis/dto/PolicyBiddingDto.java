package com.example.data.analysis.dto;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 招标政策数据表实体类（对应 JSON 结构）
 */
@Data
public class PolicyBiddingDto  {

    /**
     * 唯一标识（对应 JSON 的 id）
     */
//    @TableId(type = IdType.INPUT)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;


    /**
     * 政策标题
     */
    @TableField("title")
    private String title;

    /**
     * 翻译后标题
     */
    @TableField("translated_title")
    private String translatedTitle;

    /**
     * 政策摘要
     */
    @TableField("summary")
    private String summary;

    /**
     * 发布时间（对应 JSON 的 publish_time，ISO 格式转 LocalDateTime）
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @TableField("publish_time")
    private Date publishTime;

    /**
     * 数据来源名称
     */
    @TableField("source_name")
    private String sourceName;

    /**
     * 分类（如 bidding）
     */
    @TableField("category")
    private String category;

    /**
     * 状态（如 national_tenders）
     */
    @TableField("status")
    private String status;

    /**
     * 标签（JSON 数组转字符串存储，查询时可转回 List）
     */
    @TableField("tags")
    private String tags;

    /**
     * 来源链接
     */
    @TableField("source_url")
    private String sourceUrl;

    /**
     * 是否正向政策（0：否，1：是）
     */
    @TableField("is_positive_policy")
    private Boolean isPositivePolicy;

    /**
     * 公司名
     */
    @TableField("company_name")
    private String companyName;


}
