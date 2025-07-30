package com.example.data.analysis.controller;

import com.example.data.analysis.dto.FinanceList;
import com.example.data.analysis.entity.FinanceDate;
import com.example.data.analysis.entity.FinanceType;
import com.example.data.analysis.mapper.FinanceDateMapper;
import com.example.data.analysis.service.FinanceDateService;
import com.example.data.analysis.service.FinanceTypeService;
import com.example.data.analysis.utils.response.IResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author zt
 * @Description TODO
 * @Time 2024/11/15 13:58
 */
@Api(description = "报表数据")
@AllArgsConstructor
@RestController
@RequestMapping("/finance")
public class FinanceController {
    private final FinanceTypeService financeTypeService;
    private final FinanceDateService financeDateService;
    private final FinanceDateMapper financeDateMapper;
    @PostMapping("/getFinanceType")
    @ApiOperation(value = "获取报表列表",httpMethod = "POST")
    public IResponse getFinanceType(){

        return IResponse.success(financeTypeService.getFinanceType());
    }

    @PostMapping("/getFinanceDate")
    @ApiOperation(value = "根据类型获取报表数据",httpMethod = "POST",response = String.class)
    public IResponse getFinanceDate(@RequestParam("financeType") String financeType,
                                    @RequestParam("keepDate") String keepDate,
                                    @RequestParam(value = "companyNo",required = false) List<String> companyNo){

        return IResponse.success(financeDateService.getFinanceDate(financeType,companyNo,keepDate));
    }

    @PostMapping("/getFinanceDateGraph")
    @ApiOperation(value = "根据类型获取报表图形数据",httpMethod = "POST",response = String.class)
    public IResponse getFinanceDateGraph(@RequestParam("financeType") String financeType,
                                    @RequestParam("keepDate") String keepDate){

        return IResponse.success(financeDateService.getFinanceDateGraph(financeType,keepDate));
    }

    @PostMapping("/getFinanceDateCompare")
    @ApiOperation(value = "获取数据分析数据",httpMethod = "POST",response = String.class)
    public IResponse getFinanceDateCompare(@RequestParam("compareType") String compareType,
                                           @RequestParam(value = "companyNos") List<String> companyNos,
                                           @RequestParam(value = "year") List<String> year,
                                           @RequestParam(value = "month") List<String> month){

        return IResponse.success(financeDateService.getFinanceDateCompare(compareType, companyNos, year, month));
    }

    @PostMapping("/insertFinanceType")
    @ApiOperation(value = "新增报表列表",httpMethod = "POST",response = FinanceType.class)
    public IResponse insertFinanceType(@RequestBody List<FinanceType> financeTypeList){

        return IResponse.success(financeTypeService.saveBatch(financeTypeList));
    }

    @PostMapping("/insertFinanceDate")
    @ApiOperation(value = "新增报表数据",httpMethod = "POST",response = List.class)
    public IResponse insertFinanceDate(@RequestBody List<FinanceType> financeTypeList){

        return IResponse.success(financeTypeService.saveBatch(financeTypeList));
    }



    // 处理文件上传
    @PostMapping("/upload")
    public IResponse handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return IResponse.fail("请选择execl上传！");
        }
        try {
            // 创建工作簿对象
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 用于存储解析后的数据

            List<FinanceDate> dateList = new ArrayList<>();
            // 从第二行开始遍历（跳过第一行表头）
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                System.out.println("当前行数-----"+i);
                Row row = sheet.getRow(i);
                FinanceDate financeDate = new FinanceDate();
                financeDate.setCompanyNo( getCellValueAsString(row.getCell(2)));
                financeDate.setCompanyId((long) getCellValueAsInt(row.getCell(3)));
                financeDate.setTypeNo( getCellValueAsString(row.getCell(4)));
                financeDate.setCurrentAmt( new BigDecimal(getCellValueAsString(row.getCell(5))));
                financeDate.setLastYearAmt( new BigDecimal(getCellValueAsString(row.getCell(6))));
                financeDate.setThisYearTotalAmt( new BigDecimal(getCellValueAsString(row.getCell(7))));
                financeDate.setLastYearTotalAmt( new BigDecimal(getCellValueAsString(row.getCell(8))));
                financeDate.setYearAddAmt( new BigDecimal(getCellValueAsString(row.getCell(9))));
                financeDate.setYearAddRate( new BigDecimal(getCellValueAsString(row.getCell(10))));
                financeDate.setAddAmt( new BigDecimal(getCellValueAsString(row.getCell(11))));
                financeDate.setAddRate( new BigDecimal(getCellValueAsString(row.getCell(12))));
                financeDate.setKeepDate(new Date(getCellValueAsString(row.getCell(13))));

                dateList.add(financeDate);
            }
            // 关闭工作簿
            workbook.close();

            financeDateService.saveBatch(dateList);

            return IResponse.success("文件上传成功！");
        } catch (IOException e) {
            e.printStackTrace();
            return IResponse.fail("文件上传失败！");
        }
    }

    // 获取数据
    @GetMapping("/data")
    public IResponse getData() {
        List<String> companyNos = new ArrayList<>();
        List<FinanceList> list = financeDateMapper.selectListFinanceDate("",companyNos,"");
        return IResponse.success(list);
    }


    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private int getCellValueAsInt(Cell cell) {
        if (cell == null) {
            return 0;
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        return 0;
    }
}
