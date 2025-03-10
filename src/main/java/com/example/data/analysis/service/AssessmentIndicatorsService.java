package com.example.data.analysis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.data.analysis.condition.AssessmentIndicatorsCondition;
import com.example.data.analysis.dto.AssessmentIndicatorsDto;
import com.example.data.analysis.entity.AssessmentIndicators;

import java.util.List;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/1/17 17:32
 */
public interface AssessmentIndicatorsService extends IService<AssessmentIndicators> {

    /**
     * 根据日期查询当前有指标的公司
     * @param assessmentIndicatorsCondition
     * @return
     */
    public List<AssessmentIndicatorsDto> selectAssessmentCompany(AssessmentIndicatorsCondition assessmentIndicatorsCondition);

}
