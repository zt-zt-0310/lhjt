package com.example.data.analysis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.data.analysis.entity.Company;
import com.example.data.analysis.mapper.CompanyMapper;
import com.example.data.analysis.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/12/13 13:01
 */
@Service
@Slf4j
@AllArgsConstructor
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
}
