package com.example.data.analysis.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.data.analysis.condition.CompanyCondition;
import com.example.data.analysis.entity.Company;
import com.example.data.analysis.service.CompanyService;
import com.example.data.analysis.utils.response.IResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/12/13 15:09
 */
@Api(tags = "sys-公司信息")
@AllArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/insertCompany")
    @ApiOperation(value = "新增公司数据",httpMethod = "POST",response = List.class)
    public IResponse insertFinanceDate(@RequestBody List<Company> companyList){

        return IResponse.success(companyService.saveBatch(companyList));
    }

    @PostMapping("/selectCompany")
    @ApiOperation(value = "查询公司数据",httpMethod = "POST",response = CompanyCondition.class)
    public IResponse selectCompany(@RequestBody CompanyCondition company){

        return IResponse.success(companyService.list(Wrappers.<Company>query().lambda().eq(company.getLevel()!=null,Company::getLevel,company.getLevel()).eq(company.getCompanyNo()!=null,Company::getCompanyNo,company.getCompanyNo())));
    }

    @GetMapping("/selectCompanyAll")
    @ApiOperation(value = "分页查询公司数据")
    public IResponse selectCompanyAll(@RequestParam Integer pageIndex,@RequestParam  Integer pageSize){
        Page<Company> page = companyService.page(new Page<>(pageIndex, pageSize));
        return IResponse.success(page);
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "分页查询公司数据")
    public IResponse selectCompanyById(@PathVariable Long id){
        return IResponse.success(companyService.getById(id));
    }
}
