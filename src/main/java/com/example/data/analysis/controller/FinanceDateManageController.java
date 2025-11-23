package com.example.data.analysis.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.data.analysis.dto.FinanceList;
import com.example.data.analysis.entity.FinanceDate;
import com.example.data.analysis.mapper.FinanceDateMapper;
import com.example.data.analysis.service.FinanceDateService;
import com.example.data.analysis.utils.response.IResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 财务数据管理控制器
 */
@Api(tags = "app-财务数据管理")
@AllArgsConstructor
@RestController
@RequestMapping("/financeDate")
public class FinanceDateManageController {

    private final FinanceDateService financeDateService;
    private final FinanceDateMapper financeDateMapper;
    // 获取数据
    @PostMapping("/dataList")
    @ApiOperation(value = "获取财务全量数据数据", httpMethod = "POST")
    public IResponse<List<FinanceList>> getData(@RequestParam(required = false) String keepDate) {
        List<String> companyNos = new ArrayList<>();
        List<FinanceList> list = financeDateMapper.selectListFinanceDate("",companyNos, keepDate);
        return IResponse.success(list);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增财务数据", httpMethod = "POST")
    public IResponse addFinanceDate(@RequestBody FinanceDate financeDate) {
        return IResponse.success(financeDateService.save(financeDate));
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新财务数据", httpMethod = "PUT")
    public IResponse updateFinanceDate(@RequestBody FinanceDate financeDate) {
        return IResponse.success(financeDateService.updateById(financeDate));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除财务数据", httpMethod = "DELETE")
    public IResponse deleteFinanceDate(@PathVariable Long id) {
        return IResponse.success(financeDateService.removeById(id));
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "获取财务数据详情", httpMethod = "GET")
    public IResponse<FinanceDate> getFinanceDateById(@PathVariable Long id) {
        return IResponse.success(financeDateService.getById(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询财务数据列表", httpMethod = "GET")
    public IResponse<IPage<FinanceDate>> getFinanceDateList(
            @RequestParam(defaultValue = "1") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String companyNo) {
        IPage<FinanceDate> page = new Page<>(pageIndex, pageSize);
        return IResponse.success(financeDateService.page(page,
                Wrappers.<FinanceDate>query().lambda()
                        .eq(companyNo != null, FinanceDate::getCompanyNo, companyNo)));
    }
}
