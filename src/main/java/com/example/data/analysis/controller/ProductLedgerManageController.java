package com.example.data.analysis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.data.analysis.entity.ProductLedger;
import com.example.data.analysis.service.ProductLedgerService;
import com.example.data.analysis.utils.response.IResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 产品台账管理控制器
 */
@Api(description = "产品台账管理")
@AllArgsConstructor
@RestController
@RequestMapping("/productLedger")
public class ProductLedgerManageController {

    private final ProductLedgerService productLedgerService;

    @PostMapping("/add")
    @ApiOperation(value = "新增台账数据", httpMethod = "POST")
    public IResponse addProductLedger(@RequestBody ProductLedger productLedger) {
        return IResponse.success(productLedgerService.save(productLedger));
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新台账数据", httpMethod = "PUT")
    public IResponse updateProductLedger(@RequestBody ProductLedger productLedger) {
        return IResponse.success(productLedgerService.updateById(productLedger));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除台账数据", httpMethod = "DELETE")
    public IResponse deleteProductLedger(@PathVariable Long id) {
        return IResponse.success(productLedgerService.removeById(id));
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "获取台账详情", httpMethod = "GET")
    public IResponse<ProductLedger> getProductLedgerById(@PathVariable Long id) {
        return IResponse.success(productLedgerService.getById(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询台账列表", httpMethod = "GET")
    public IResponse<IPage<ProductLedger>> getProductLedgerList(
            @RequestParam(defaultValue = "1") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String productName) {
        IPage<ProductLedger> page = new Page<>(pageIndex, pageSize);
        QueryWrapper<ProductLedger> queryWrapper = new QueryWrapper<>();
        if (productName != null && !productName.isEmpty()) {
            queryWrapper.lambda().like(ProductLedger::getProductName, productName);
        }
        return IResponse.success(productLedgerService.page(page, queryWrapper));
    }
}
