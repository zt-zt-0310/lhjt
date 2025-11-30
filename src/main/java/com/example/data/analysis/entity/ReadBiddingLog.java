package com.example.data.analysis.entity;

import com.example.data.analysis.utils.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/11/26 0:27
 */
@Data
public class ReadBiddingLog extends BaseEntity<ReadBiddingLog> implements Serializable {
    String bidingId;
    Long userId;
}
