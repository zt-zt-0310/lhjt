package com.example.data.analysis.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.data.analysis.condition.AssessmentIndicatorsCondition;
import com.example.data.analysis.entity.AssessmentIndicators;
import com.example.data.analysis.service.AssessmentIndicatorsService;
import com.example.data.analysis.utils.response.IResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/1/17 18:59
 */
@Api(tags = "app-指标考核")
@AllArgsConstructor
@RestController
@RequestMapping("/assessmentIndicators")
public class AssessmentIndicatorsController {

    private final AssessmentIndicatorsService assessmentIndicatorsService;

    @PostMapping("/selectAssessmentIndicators")
    @ApiOperation(value = "查询指标考核公司数据",httpMethod = "POST",response = AssessmentIndicatorsCondition.class)
    public IResponse selectCompany(@RequestBody AssessmentIndicatorsCondition assessmentIndicatorsCondition){

        return IResponse.success(assessmentIndicatorsService.selectAssessmentCompany(assessmentIndicatorsCondition));
    }

    @PostMapping("/selectTarget")
    @ApiOperation(value = "根据公司编号查询查询指标考核数据",httpMethod = "POST",response = AssessmentIndicatorsCondition.class)
    public IResponse selectTarget(@RequestBody AssessmentIndicatorsCondition assessmentIndicatorsCondition){

        return IResponse.success(assessmentIndicatorsService.list(Wrappers.<AssessmentIndicators>query().lambda()
                .eq(assessmentIndicatorsCondition.getCompanyNo()!=null, AssessmentIndicators::getCompanyNo,assessmentIndicatorsCondition.getCompanyNo())
                .last("and DATE_FORMAT(keep_date,'%Y') = DATE_FORMAT('"+assessmentIndicatorsCondition.getKeepDate()+"','%Y')")));
    }
}

