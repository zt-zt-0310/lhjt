package com.example.data.analysis.controller;

import com.example.data.analysis.entity.FinanceType;
import com.example.data.analysis.service.FinanceDateService;
import com.example.data.analysis.service.FinanceTypeService;
import com.example.data.analysis.utils.response.IResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/11/15 13:58
 */
@Api(description = "报表数据")
@AllArgsConstructor
@RestController
@RequestMapping("/finance")
public class FinanceController {
    private final FinanceTypeService financeTypeService;
    private final FinanceDateService financeDateService;

    @PostMapping("/getFinanceType")
    @ApiOperation(value = "获取报表列表",httpMethod = "POST")
    public IResponse getFinanceType(){

        return IResponse.success(financeTypeService.getFinanceType());
    }

    @PostMapping("/getFinanceDate")
    @ApiOperation(value = "根据类型获取报表数据",httpMethod = "POST",response = String.class)
    public IResponse getFinanceDate(@RequestParam("financeType") String financeType,
                                    @RequestParam("keepDate") String keepDate,
                                    @RequestParam(value = "companyNo",required = false) List<String> companyNo){

        return IResponse.success(financeDateService.getFinanceDate(financeType,companyNo,keepDate));
    }

    @PostMapping("/getFinanceDateGraph")
    @ApiOperation(value = "根据类型获取报表图形数据",httpMethod = "POST",response = String.class)
    public IResponse getFinanceDateGraph(@RequestParam("financeType") String financeType,
                                    @RequestParam("keepDate") String keepDate){

        return IResponse.success(financeDateService.getFinanceDateGraph(financeType,keepDate));
    }

    @PostMapping("/getFinanceDateCompare")
    @ApiOperation(value = "获取数据分析数据",httpMethod = "POST",response = String.class)
    public IResponse getFinanceDateCompare(@RequestParam("compareType") String compareType,
                                           @RequestParam(value = "companyNos") List<String> companyNos,
                                           @RequestParam(value = "year") List<String> year,
                                           @RequestParam(value = "month") List<String> month){

        return IResponse.success(financeDateService.getFinanceDateCompare(compareType, companyNos, year, month));
    }

    @PostMapping("/insertFinanceType")
    @ApiOperation(value = "新增报表列表",httpMethod = "POST",response = FinanceType.class)
    public IResponse insertFinanceType(@RequestBody List<FinanceType> financeTypeList){

        return IResponse.success(financeTypeService.saveBatch(financeTypeList));
    }

    @PostMapping("/insertFinanceDate")
    @ApiOperation(value = "新增报表数据",httpMethod = "POST",response = List.class)
    public IResponse insertFinanceDate(@RequestBody List<FinanceType> financeTypeList){

        return IResponse.success(financeTypeService.saveBatch(financeTypeList));
    }

}
