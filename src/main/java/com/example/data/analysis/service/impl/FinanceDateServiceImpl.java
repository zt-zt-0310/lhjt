package com.example.data.analysis.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.data.analysis.dto.DateAnalysisDto;
import com.example.data.analysis.dto.FinanceList;
import com.example.data.analysis.entity.Company;
import com.example.data.analysis.entity.FinanceDate;
import com.example.data.analysis.mapper.FinanceDateMapper;
import com.example.data.analysis.service.CompanyService;
import com.example.data.analysis.service.FinanceDateService;
import com.example.data.analysis.utils.ChatCompletionsExample;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/12/13 13:19
 */
@Service
@Slf4j
@AllArgsConstructor
public class FinanceDateServiceImpl extends ServiceImpl<FinanceDateMapper, FinanceDate> implements FinanceDateService {
    private final FinanceDateMapper financeDateMapper;
    private final CompanyService companyService;
    @Resource
    ChatCompletionsExample chatCompletions;

    @Override
    public List<FinanceList> getFinanceDate(String financeType,List<String> companyNo,String keepDate) {
        List<String> companyNos = new ArrayList<>();
        if(null != companyNo && !companyNo.isEmpty()){
            Company company = companyService.getOne(Wrappers.<Company>query().lambda().in(Company::getCompanyNo, companyNo));
            if (null== company){
                return null;
            }
            List<Company> list = companyService.list(Wrappers.<Company>query().lambda().eq(Company::getHighCompanyId, company.getId()));
            companyNos = list.stream().filter(str -> str.getCompanyNo()!=null).map(Company::getCompanyNo).collect(Collectors.toList());
        }
        List<FinanceList> financeDates = financeDateMapper.selectListFinanceDate(financeType,companyNos,keepDate);
        if (financeDates.size()>0) {
            for (FinanceList dto: financeDates){
                if (!dto.getLevel().equals("0")) {
                    Company company = companyService.getOne(Wrappers.<Company>query().lambda().eq(Company::getCompanyNo, dto.getCompanyNo()));
                    List<Company> list = companyService.list(Wrappers.<Company>query().lambda().eq(Company::getHighCompanyId, company.getId()));
                    companyNos = list.stream().filter(str -> str.getCompanyNo() != null).map(Company::getCompanyNo).collect(Collectors.toList());
                    if (companyNos.size()>0){
                        List<FinanceList> subCompany = financeDateMapper.selectListFinanceDate(financeType, companyNos, keepDate);
                        dto.setSubCompany(subCompany);
                    }

                }
            }
        }
        return financeDates;
    }

    @Override
    public List<FinanceList> getFinanceDateGraph(String financeType, String keepDate) {
        List<String> companyNos = new ArrayList<>();
//        联博 lbyyhb
//        普林斯 plshb
//        圣氏化学 sshx
//        康和药业 khyy
//        其他
//        联环股份 lhjthb
//        国药  gykg
        companyNos.add("lbyyhb");
        companyNos.add("plshb");
        companyNos.add("sshx");
        companyNos.add("khyy");
        companyNos.add("lhjthb");
        companyNos.add("gykg");
        List<FinanceList> financeDateGraph = financeDateMapper.getFinanceDateGraph(financeType, companyNos, keepDate);
        companyNos.add("lhjt");
        List<FinanceList> financeListQts = financeDateMapper.getFinanceDateGraphQt(financeType,companyNos,keepDate);
        if (financeListQts.get(0) == null){
            return financeDateGraph;
        }
        for (FinanceList list:financeListQts) {
            list.setCompanyName("其它");
            financeDateGraph.add(list);
        }
        return financeDateGraph;
    }

    @Override
    public DateAnalysisDto getFinanceDateCompare(String compareType, List<String> companyNos, List<String> year, List<String> month) {
        List<String> years = new ArrayList<>();
        if(!year.isEmpty()){
            for (String str:year) {
                if (!month.isEmpty()) {
                    for (String mon:month){
                        String yearList =str+"-"+mon+"-01";
                        years.add(yearList);
                    }
                }else {
                    String yearList =str+"-01-01";
                    years.add(yearList);
                }
            }
        }
        //A公司的营业收入是xx，（同比）（增长，下降）xx%，利润总额是xx，（同比）（增长，下降）xx%，原因是xx，请帮我进行数据分析
        //A公司的营业收入是xx，（同比）（增长，下降）xx%，利润总额是xx，（同比）（增长，下降）xx%，原因是xx。B公司...请帮我进行数据分析
        List<FinanceList> financeLists = financeDateMapper.selectListFinanceDateCompare(companyNos, years, month);
        String text = "";
        for (FinanceList list:financeLists) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            String keepDate = format.format(list.getKeepDate());
            text = text + list.getCompanyName()+"公司的"+keepDate+"营业收入是"+list.getCurrentAmt()+"万元,(同比)(增长，下降)"+list.getAddRate()+"%,";
            if (list.getRemarks()!=null){
                text = text+"原因是"+list.getRemarks();
            }
        }
        text = text+"。请帮我进行数据分析,只需要告诉我分析结果不要分析过程。";
        log.info(text);
        String chatText = chatCompletions.chatCompletions(text);
        DateAnalysisDto dto = new DateAnalysisDto();
        dto.setFinanceLists(financeLists);
        dto.setAnalysis(chatText);
        return  dto;
    }
}
