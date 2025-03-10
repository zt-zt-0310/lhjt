package com.example.data.analysis.condition;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/1/17 19:08
 */
@Data
public class AssessmentIndicatorsCondition {
    /**
     * 当前页数
     */
    private Integer pageIndex;
    /**
     * 每页显示数量
     */
    private Integer pageSize;

    /**
     * 考核日期
     */
//    @JsonFormat(
//            pattern = "yyyy-MM-dd HH:mm:ss"
//    )
//    @ApiModelProperty(value = "哪一年的考核目标，默认传当前日期")
    private String keepDate;

    /**
     * 公司编号
     */
    @ApiModelProperty(value = "查询考核指标公司详情数据使用")
    private String companyNo;
}
