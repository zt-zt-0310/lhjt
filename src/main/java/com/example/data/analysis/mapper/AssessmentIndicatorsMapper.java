package com.example.data.analysis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.data.analysis.condition.AssessmentIndicatorsCondition;
import com.example.data.analysis.dto.AssessmentIndicatorsDto;
import com.example.data.analysis.entity.AssessmentIndicators;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/1/17 17:33
 */
public interface AssessmentIndicatorsMapper extends BaseMapper<AssessmentIndicators> {
    /**
     * 根据日期查询当前有指标的公司
     * @param assessmentIndicatorsCondition
     * @return
     */
    public List<AssessmentIndicatorsDto> selectAssessmentCompany(@Param("assessmentIndicatorsCondition") AssessmentIndicatorsCondition assessmentIndicatorsCondition);

}
