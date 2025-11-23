package com.example.data.analysis.utils;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @Description: http请求工具类
 * @Author: zt
 */
public class HttpUtils {

    // 超时时间（毫秒）
    private static final int TIMEOUT = 30000;

    /**
     * 通用HTTP请求方法
     * @param url 请求URL
     * @param method 请求方法（GET/POST）
     * @param params 请求参数（GET拼接在URL，POST可表单或JSON）
     * @param headers 请求头
     * @param isJson 是否以JSON格式发送POST数据
     * @return 响应结果字符串
     * @throws IOException 网络异常
     */
    public static String sendRequest(String url, String method, Map<String, String> params,
                                     Map<String, String> headers, boolean isJson) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        OutputStream out = null;

        try {
            // 处理GET参数拼接
            if ("GET".equalsIgnoreCase(method) && params != null && !params.isEmpty()) {
                url = buildGetUrl(url, params);
            }

            URL requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();

            // 设置基础属性
            connection.setRequestMethod(method);
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            connection.setDoInput(true);

            // 设置请求头
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            // 处理POST请求
            if ("POST".equalsIgnoreCase(method)) {
                connection.setDoOutput(true); // 允许输出
                // 默认设置Content-Type（可被headers覆盖）
                if (!isJson) {
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                } else {
                    connection.setRequestProperty("Content-Type", "application/json");
                }

                // 写入POST参数
                if (params != null && !params.isEmpty()) {
                    out = connection.getOutputStream();
                    String postData = isJson ? mapToJson(params) : mapToFormData(params);
                    out.write(postData.getBytes(StandardCharsets.UTF_8));
                    out.flush();
                }
            }

            // 读取响应
            int responseCode = connection.getResponseCode();
            // 处理正常响应和错误响应
            InputStream inputStream = responseCode >= 200 && responseCode < 300
                    ? connection.getInputStream()
                    : connection.getErrorStream();

            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();

        } finally {
            // 关闭资源
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * GET请求快捷方法
     */
    public static String get(String url) throws IOException {
        return sendRequest(url, "GET", null, null, false);
    }

    /**
     * 带参数的GET请求
     */
    public static String get(String url, Map<String, String> params) throws IOException {
        return sendRequest(url, "GET", params, null, false);
    }

    /**
     * 带参数和请求头的GET请求
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        return sendRequest(url, "GET", params, headers, false);
    }

    /**
     * POST表单请求快捷方法
     */
    public static String postForm(String url, Map<String, String> params) throws IOException {
        return sendRequest(url, "POST", params, null, false);
    }

    /**
     * POST JSON请求快捷方法
     */
    public static String postJson(String url, Map<String, String> params) throws IOException {
        return sendRequest(url, "POST", params, null, true);
    }

    /**
     * 带请求头的POST请求
     */
    public static String post(String url, Map<String, String> params, Map<String, String> headers, boolean isJson) throws IOException {
        return sendRequest(url, "POST", params, headers, isJson);
    }

    // 构建GET请求URL（拼接参数）
    private static String buildGetUrl(String url, Map<String, String> params) throws UnsupportedEncodingException {
        StringJoiner joiner = new StringJoiner("&");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.name());
            String value = URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name());
            joiner.add(key + "=" + value);
        }
        return url.contains("?") ? url + "&" + joiner : url + "?" + joiner;
    }

    // 转换参数为表单格式（key1=value1&key2=value2）
    private static String mapToFormData(Map<String, String> params) throws UnsupportedEncodingException {
        StringJoiner joiner = new StringJoiner("&");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.name());
            String value = URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name());
            joiner.add(key + "=" + value);
        }
        return joiner.toString();
    }

    // 简单转换参数为JSON格式（仅支持一级键值对）
    private static String mapToJson(Map<String, String> params) {
        StringBuilder json = new StringBuilder("{");
        StringJoiner joiner = new StringJoiner(",");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            joiner.add("\"" + escapeJson(entry.getKey()) + "\":\"" + escapeJson(entry.getValue()) + "\"");
        }
        json.append(joiner).append("}");
        return json.toString();
    }

    // 简单JSON转义（处理双引号和反斜杠）
    private static String escapeJson(String value) {
        if (value == null) return "";
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}
