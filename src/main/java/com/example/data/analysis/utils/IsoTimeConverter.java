package com.example.data.analysis.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class IsoTimeConverter {
    // ISO 8601 解析器
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_INSTANT;
    // 默认目标格式（可自定义）
    private static final DateTimeFormatter DEFAULT_TARGET_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * ISO 转 LocalDateTime（UTC 时区）
     */
    public static LocalDateTime toUtcLocalDateTime(String isoTime) {
        if (isoTime == null || isoTime.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.parse(isoTime), ZoneId.of("UTC"));
    }

    /**
     * ISO 转 LocalDateTime（本地时区）
     */
    public static LocalDateTime toLocalDateTime(String isoTime) {
        if (isoTime == null || isoTime.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.parse(isoTime), ZoneId.systemDefault());
    }

    /**
     * ISO 转 Date
     */
    public static Date toDate(String isoTime) {
        if (isoTime == null || isoTime.trim().isEmpty()) {
            return null;
        }
        return Date.from(Instant.parse(isoTime));
    }

    /**
     * ISO 转自定义格式字符串（UTC 时区）
     */
    public static String toUtcString(String isoTime, String targetFormat) {
        LocalDateTime ldt = toUtcLocalDateTime(isoTime);
        if (ldt == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(targetFormat);
        return ldt.format(formatter);
    }

    /**
     * ISO 转自定义格式字符串（本地时区）
     */
    public static String toLocalString(String isoTime, String targetFormat) {
        LocalDateTime ldt = toLocalDateTime(isoTime);
        if (ldt == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(targetFormat);
        return ldt.format(formatter);
    }

    /**
     * ISO 转默认格式字符串（yyyy-MM-dd HH:mm:ss，本地时区）
     */
    public static String toDefaultString(String isoTime) {
        return toLocalString(isoTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 适配 IPage，返回 items（本质是 getRecords()）
     */
    public static <T> List<T> items(IPage<T> page) {
        return page == null ? Collections.emptyList() : page.getRecords();
    }

    // 测试入口
    public static void main(String[] args) {
        String isoTime = "2025-10-10T00:00:00Z";
        System.out.println("UTC LocalDateTime：" + toUtcLocalDateTime(isoTime));
        System.out.println("本地 LocalDateTime：" + toLocalDateTime(isoTime));
        System.out.println("Date：" + toDate(isoTime));
        System.out.println("UTC 字符串：" + toUtcString(isoTime, "yyyy-MM-dd HH:mm:ss"));
        System.out.println("本地字符串：" + toLocalString(isoTime, "yyyy-MM-dd HH:mm:ss"));
        System.out.println("默认格式字符串：" + toDefaultString(isoTime));
    }
}
