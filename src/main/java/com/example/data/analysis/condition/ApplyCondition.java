package com.example.data.analysis.condition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/11/26 23:40
 */
@Data
public class ApplyCondition extends BeanPage{
    @ApiModelProperty(value = "pending:带申报    ,apply  审核中,   complete 已获取")
    public String applyStatus;
}
