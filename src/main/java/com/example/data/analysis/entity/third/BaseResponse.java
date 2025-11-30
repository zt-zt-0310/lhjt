package com.example.data.analysis.entity.third;


import lombok.Data;
import java.util.List;

/**
 * 统一 JSON 响应格式实体
 */
@Data
public class BaseResponse<T> {
    private Integer code; // 响应码
    private String msg;  // 响应信息
    private Data<T> data; // 响应数据（分页+列表）

    /**
     * 分页数据实体
     */
    @lombok.Data
    public static class Data<T> {
        private List<PolicyItem> items; // 数据列表
        private Integer page; // 当前页码
        private Integer page_size; // 每页条数
        private Integer total; // 总条数
        private Object stats; // 统计信息（可为 null）
    }
}
