package com.example.data.analysis.condition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/11/23 23:11
 */
@Data
public class BeanPage {
    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数")
    private Integer pageIndex;
    /**
     * 每页显示数量
     */
    @ApiModelProperty(value = "每页显示数量")
    private Integer pageSize;
}
