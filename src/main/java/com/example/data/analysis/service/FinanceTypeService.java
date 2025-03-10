package com.example.data.analysis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.data.analysis.entity.FinanceType;

import java.util.List;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/12/13 13:10
 */
public interface FinanceTypeService extends IService<FinanceType> {
    List<FinanceType> getFinanceType();
}
