package com.example.data.analysis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.data.analysis.condition.AssessmentIndicatorsCondition;
import com.example.data.analysis.entity.ProductLedger;
import com.example.data.analysis.service.impl.ProductLedgerServiceImpl;
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
 * @Time 2025/10/22 20:58
 */
@Api(description = "产品台账")
@AllArgsConstructor
@RestController
@RequestMapping("/ledger")
public class ProductLedgerController {

    private final ProductLedgerServiceImpl productLedgerService;

    @PostMapping("/getFinanceType")
    @ApiOperation(value = "获取报表列表",httpMethod = "POST")
    public IResponse<?> getFinanceType(@RequestBody AssessmentIndicatorsCondition condition){
        IPage<ProductLedger> page = new Page<>(condition.getPageIndex(),condition.getPageSize());


        return IResponse.success(productLedgerService.page(page, new QueryWrapper<ProductLedger>().lambda().eq(ProductLedger::getTransformationDate,condition.getKeepDate())));
    }

}
