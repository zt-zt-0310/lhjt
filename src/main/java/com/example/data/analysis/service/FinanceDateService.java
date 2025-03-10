package com.example.data.analysis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.data.analysis.dto.DateAnalysisDto;
import com.example.data.analysis.dto.FinanceList;
import com.example.data.analysis.entity.FinanceDate;

import java.util.List;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/12/13 13:19
 */
public interface FinanceDateService extends IService<FinanceDate> {
    List<FinanceList> getFinanceDate(String financeType,List<String> companyNo,String keepDate);

    List<FinanceList> getFinanceDateGraph(String financeType,String keepDate);

    DateAnalysisDto getFinanceDateCompare(String compareType, List<String> companyNos, List<String> year, List<String> month);
}
