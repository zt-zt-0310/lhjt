package com.example.data.analysis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.data.analysis.condition.AssessmentIndicatorsCondition;
import com.example.data.analysis.dto.AssessmentIndicatorsDto;
import com.example.data.analysis.entity.AssessmentIndicators;
import com.example.data.analysis.mapper.AssessmentIndicatorsMapper;
import com.example.data.analysis.service.AssessmentIndicatorsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/1/17 17:32
 */
@Service
@Slf4j
@AllArgsConstructor
public class AssessmentIndicatorsServiceImpl extends ServiceImpl<AssessmentIndicatorsMapper, AssessmentIndicators> implements AssessmentIndicatorsService {

    private final AssessmentIndicatorsMapper assessmentIndicatorsMapper;

    @Override
    public List<AssessmentIndicatorsDto> selectAssessmentCompany(AssessmentIndicatorsCondition assessmentIndicatorsCondition) {

        return assessmentIndicatorsMapper.selectAssessmentCompany(assessmentIndicatorsCondition);
    }
}
