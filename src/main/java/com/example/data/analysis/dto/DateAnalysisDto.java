package com.example.data.analysis.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/2/8 13:44
 */
@Data
public class DateAnalysisDto {
    private String analysis;
    private List<FinanceList> financeLists;
}
