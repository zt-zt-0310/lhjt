package com.example.data.analysis.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/11/19 22:27
 */
@Api(tags = "自用-第三方接口")
//@ApiIgnore
@AllArgsConstructor
@RestController
@RequestMapping("/productLedger")
public class ThirdPartyAPIController {

//    @PostMapping("/get")
//    @ApiOperation(value = "新增台账数据", httpMethod = "POST")
//    public IResponse addProductLedger(@RequestBody ProductLedger productLedger) {
//        return IResponse.success(productLedgerService.save(productLedger));
//    }

}
