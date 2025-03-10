package com.example.data.analysis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.data.analysis.dto.FinanceList;
import com.example.data.analysis.entity.FinanceDate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/12/13 13:18
 */
public interface FinanceDateMapper extends BaseMapper<FinanceDate> {

    List<FinanceList> selectListFinanceDate(@Param("typeNo") String typeNo,@Param("companyNos")List<String> companyNos,@Param("keepDate") String keepDate);

    List<FinanceList> getFinanceDateGraph(@Param("typeNo") String typeNo,@Param("companyNos")List<String> companyNos,@Param("keepDate")String keepDate);

    List<FinanceList> getFinanceDateGraphQt(@Param("typeNo") String typeNo,@Param("companyNos")List<String> companyNos,@Param("keepDate")String keepDate);

    List<FinanceList> selectListFinanceDateCompare(@Param("companyNos")List<String> companyNos,@Param("years") List<String> year,@Param("months") List<String> month);

}
