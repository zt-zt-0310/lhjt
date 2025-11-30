package com.example.data.analysis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.data.analysis.dto.PolicyBiddingDto;
import com.example.data.analysis.entity.PolicyBidding;
import com.example.data.analysis.mapper.PolicyBiddingMapper;
import com.example.data.analysis.service.PolicyBiddingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/11/27 23:45
 */
@Service
@AllArgsConstructor
public class PolicyBiddingServiceImpl extends ServiceImpl<PolicyBiddingMapper, PolicyBidding> implements PolicyBiddingService {


    @Override
    public IPage<PolicyBiddingDto> selectUnreadPolicy(Page<PolicyBidding> page, Long userId, String category) {
        return this.baseMapper.selectUnreadPolicy(page, userId, category);
    }
}
