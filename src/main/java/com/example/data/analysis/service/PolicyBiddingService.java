package com.example.data.analysis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.data.analysis.dto.PolicyBiddingDto;
import com.example.data.analysis.entity.PolicyBidding;
import org.apache.ibatis.annotations.Param;

/**
 * @Author zt
 * @Description TODO
 * @Time 2025/11/27 23:44
 */
public interface PolicyBiddingService extends IService<PolicyBidding> {

    /**
     * 分页查询用户未阅读的政策
     * @param page 分页对象（页码、每页条数）
     * @param userId 用户ID
     * @param category 分类（可选，可为 null）
     * @return 分页后的未阅读政策
     */
    IPage<PolicyBiddingDto> selectUnreadPolicy(
            Page<PolicyBidding> page,
            @Param("userId") Long userId,
            @Param("category") String category
    );
}
