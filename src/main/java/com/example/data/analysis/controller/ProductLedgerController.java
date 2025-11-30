package com.example.data.analysis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.data.analysis.condition.ApplyCondition;
import com.example.data.analysis.condition.AssessmentIndicatorsCondition;
import com.example.data.analysis.condition.BeanPage;
import com.example.data.analysis.dto.LedgerTypeDto;
import com.example.data.analysis.dto.PolicyBiddingDto;
import com.example.data.analysis.entity.LedgerType;
import com.example.data.analysis.entity.PolicyBidding;
import com.example.data.analysis.entity.ProductLedger;
import com.example.data.analysis.entity.ReadBiddingLog;
import com.example.data.analysis.service.impl.LedgerTypeServiceImpl;
import com.example.data.analysis.service.impl.PolicyBiddingServiceImpl;
import com.example.data.analysis.service.impl.ProductLedgerServiceImpl;
import com.example.data.analysis.utils.JwtUtil;
import com.example.data.analysis.utils.response.IResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/10/22 20:58
 */
@Api(tags = "app-产品台账")
@AllArgsConstructor
@RestController
@RequestMapping("/ledger")
public class ProductLedgerController {

    private final ProductLedgerServiceImpl productLedgerService;
    private final PolicyBiddingServiceImpl policyBiddingService;
    private final LedgerTypeServiceImpl ledgerTypeService;
    private final JwtUtil jwtUtil;

    @PostMapping("/getLedgerList")
    @ApiOperation(value = "获取产品台账列表",httpMethod = "POST")
    public IResponse<?> getLedgerList(@RequestBody AssessmentIndicatorsCondition condition){
        IPage<ProductLedger> page = new Page<>(condition.getPageIndex(),condition.getPageSize());
        return IResponse.success(productLedgerService.page(page, new QueryWrapper<ProductLedger>().lambda()
                .eq(condition.getKeepDate()!=null && !condition.getKeepDate().isEmpty(),ProductLedger::getTransformationDate,condition.getKeepDate())
                .eq(condition.getCompanyNo()!=null && !condition.getCompanyNo().isEmpty(),ProductLedger::getCompanyNo,condition.getCompanyNo())));
    }

    @PostMapping("/getBiddingList")
    @ApiOperation(value = "获取最新政策动态",httpMethod = "POST")
    public IResponse getBiddingList(@RequestBody BeanPage beanPage, HttpServletRequest httpServletRequest) throws IOException {
        String userId = jwtUtil.getUserIdFromToken(httpServletRequest.getHeader("token"));

        // 2. 远程获取招标列表数据
//        BaseResponse bidding = ArticleApiService.fetchArticles("bidding",null, beanPage.getPageIndex(), 70);
//        if (bidding != null) {
//            List<PolicyBidding> list = new ArrayList<>();
//            if (list != null) {
//                for (Object item : bidding.getData().getItems()){
//                    PolicyBidding policyBidding = new PolicyBidding();
//                    policyBidding.setBiddingId(((PolicyItem) item).getId());
//                    policyBidding.setTranslatedTitle(((PolicyItem) item).getTranslated_title());
//                    policyBidding.setTitle(((PolicyItem) item).getTitle());
//                    policyBidding.setSummary(((PolicyItem) item).getSummary());
//                    policyBidding.setPublishTime(IsoTimeConverter.toDate(((PolicyItem) item).getPublish_time()));
//                    policyBidding.setSourceName(((PolicyItem) item).getSource_name());
//                    policyBidding.setCategory(((PolicyItem) item).getCategory());
//                    policyBidding.setStatus(((PolicyItem) item).getStatus());
//                    policyBidding.setTags(((PolicyItem) item).getTags().toString());
//                    policyBidding.setSourceUrl(((PolicyItem) item).getSource_url());
//                    policyBidding.setIsPositivePolicy(((PolicyItem) item).getIs_positive_policy());
//                    list.add(policyBidding);
//                }
//            }
//            policyBiddingService.saveBatch(list);
//        }
        IPage<PolicyBiddingDto> policyBiddingDtoIPage = policyBiddingService.selectUnreadPolicy(new Page<>(beanPage.getPageIndex(), beanPage.getPageSize()), Long.valueOf(userId), "bidding");

        return IResponse.success(policyBiddingDtoIPage);
    }

    @PostMapping("/readBidding")
    @ApiOperation(value = "阅读当前政策",httpMethod = "POST")
    public IResponse readBidding(@RequestBody ReadBiddingLog readBidding, HttpServletRequest httpServletRequest) throws IOException {
        String userId = jwtUtil.getUserIdFromToken(httpServletRequest.getHeader("token"));
        ReadBiddingLog read = new ReadBiddingLog();
        read.setUserId(Long.valueOf(userId));
        read.setBidingId(readBidding.getBidingId());
        read.insert();
        return IResponse.success(readBidding.insert());
    }

    @PostMapping("/apply")
    @ApiOperation(value = "申报内容",httpMethod = "POST")
    public IResponse apply(@RequestBody ApplyCondition applyCondition, HttpServletRequest httpServletRequest) throws IOException {
//        if (applyCondition.applyStatus.equals("pending")) {
//            BaseResponse bidding = ArticleApiService.fetchArticles("project_apply", applyCondition.applyStatus, applyCondition.getPageIndex(), applyCondition.getPageSize());
//            return IResponse.success(bidding.getData());
//        }else {
            List<String> status = new ArrayList<>();
            status.add(applyCondition.applyStatus);
            if (applyCondition.applyStatus.equals("apply")) {
                status.add("pass");
                status.add("refuse");
            }
            return IResponse.success( policyBiddingService.page(new Page<>(applyCondition.getPageIndex(),
                            applyCondition.getPageSize()),
                    new QueryWrapper<PolicyBidding>().lambda().in(PolicyBidding::getStatus, status)
                            .eq(PolicyBidding::getCategory, "project_apply")));
//        }
    }


    @PostMapping("/getLedgerType")
    @ApiOperation(value = "获取产品台账列表type",httpMethod = "POST")
    public IResponse getLedgerType() {
        List<LedgerType> list = ledgerTypeService.list(Wrappers.<LedgerType>lambdaQuery().eq(LedgerType::getDelFlag, 0).eq(LedgerType::getLevel, "0"));
        List<LedgerTypeDto> ledgerTypeDto = new ArrayList<>();
        for (LedgerType ledgerType : list) {
            LedgerTypeDto ledgerTypeDto1 = new LedgerTypeDto();
            ledgerTypeDto1.setLedgerName(ledgerType.getLedgerName());
            ledgerTypeDto1.setLedgerNo(ledgerType.getLedgerNo());
            ledgerTypeDto.add(ledgerTypeDto1);
            List<LedgerType> typeList = ledgerTypeService.list(Wrappers.<LedgerType>lambdaQuery()
                    .eq(LedgerType::getDelFlag, 0)
                    .eq(LedgerType::getUpLedgerNo, ledgerType.getLedgerNo()));
            ledgerTypeDto1.setLedgerTypeList(typeList);
        }
        return IResponse.success(ledgerTypeDto);
    }

}
