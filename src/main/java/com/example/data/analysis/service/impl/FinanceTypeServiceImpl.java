package com.example.data.analysis.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.data.analysis.entity.FinanceType;
import com.example.data.analysis.mapper.FinanceTypeMapper;
import com.example.data.analysis.service.FinanceTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/12/13 13:11
 */
@Service
@Slf4j
@AllArgsConstructor
public class FinanceTypeServiceImpl extends ServiceImpl<FinanceTypeMapper, FinanceType>  implements FinanceTypeService {


    @Override
    public List<FinanceType> getFinanceType() {
        return this.baseMapper.selectList(Wrappers.emptyWrapper());
    }
}
