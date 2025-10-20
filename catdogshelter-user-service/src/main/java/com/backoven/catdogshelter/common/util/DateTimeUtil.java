package com.backoven.catdogshelter.common.util;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 현재 시간 → 문자열
    public static String now() {
        return LocalDateTime.now().format(FORMATTER);
    }

    // LocalDateTime → 문자열
    public static String format(LocalDateTime time) {
        return time.format(FORMATTER);
    }

    // 문자열 → LocalDateTime
    public static LocalDateTime toLocalDateTime(String time) {
        return LocalDateTime.parse(time, FORMATTER);
    }

    public static void validationTime(String time) {
        if (isAfterNow(time)) {
            throw new UsernameNotFoundException("정지 해제까지 " + remainingTime(time));
        }
    }

    /**
     * 특정 시간이 현재 시각보다 이후인지 확인
     */
    private static boolean isAfterNow(String time) {
        LocalDateTime target = toLocalDateTime(time);
        return target.isAfter(LocalDateTime.now());
    }

    /**
     * 현재 시각 기준으로 얼마나 남았는지 ("2시간 15분 남음") 반환
     */
    private static String remainingTime(String time) {
        LocalDateTime target = toLocalDateTime(time);
        Duration duration = Duration.between(LocalDateTime.now(), target);

        long seconds = duration.getSeconds();
        if (seconds <= 0) return "기간이 이미 지났습니다.";

        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 0) return days + "일 남음";
        if (hours > 0) return hours + "시간 " + (minutes % 60) + "분 남음";
        if (minutes > 0) return minutes + "분 남음";
        return seconds + "초 남음";
    }
}
