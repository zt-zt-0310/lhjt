package com.example.data.analysis.service;

import com.example.data.analysis.entity.third.ArticleResponse;
import com.example.data.analysis.entity.third.Stats;
import com.example.data.analysis.utils.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArticleApiService {
    // API基础地址（替换为实际地址）
    private static final String BASE_URL = "http://101.132.130.146:8000/v1/articles/";
    // Jackson解析器
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 获取文章列表并解析为实体类
     *
     * @param category     分类（必填，如bidding、cde_trend等）
     * @param page         页码（默认1）
     * @param pageSize     每页条数（默认20）
     * @return 解析后的文章列表响应实体
     * @throws IOException            网络或解析异常
     * @throws IllegalArgumentException 参数错误
     */
    public static ArticleResponse fetchArticles(String category,
                                                Integer page,
                                                Integer pageSize) throws IOException {
        // 1. 校验并构建参数
        Map<String, String> params = buildParams(category, page, pageSize);

        // 2. 发送GET请求
        String responseJson = HttpUtils.get(BASE_URL, params);
        if (responseJson.trim().isEmpty()) {
            throw new IOException("接口返回空数据");
        }

        // 3. 解析JSON为实体类
        return objectMapper.readValue(responseJson, ArticleResponse.class);
    }

    /**
     * 构建请求参数
     */
    private static Map<String, String> buildParams(String category,
                                                   Integer page,
                                                   Integer pageSize) {
        // 校验必填参数
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("分类category为必填参数");
        }

        Map<String, String> params = new HashMap<>();
        params.put("category", category);
        // 分页参数（默认值）
        params.put("page", page != null ? page.toString() : "1");
        params.put("page_size", pageSize != null ? pageSize.toString() : "20");

        return params;
    }

    /**
     * 处理文章列表结果（示例：打印关键信息）
     */
    public static void processArticles(ArticleResponse response) {
        if (response == null) {
            System.out.println("无返回数据");
            return;
        }

        // 打印统计信息（如果有）
        Stats stats = response.getStats();
        // 打印文章列表
        System.out.println("\n===== 文章列表 =====");
    }

    // 测试示例
    public static void main(String[] args) {
        try {
            // 示例：查询bidding分类下的国家集采，第1页，10条/页
            ArticleResponse response = fetchArticles(
                    "bidding",       // category
                    1,               // page
                    10               // page_size
            );

            // 处理并打印结果
            processArticles(response);

        } catch (IllegalArgumentException e) {
            System.err.println("参数错误：" + e.getMessage());
        } catch (IOException e) {
            System.err.println("请求或解析失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
