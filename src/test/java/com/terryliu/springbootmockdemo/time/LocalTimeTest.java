package com.terryliu.springbootmockdemo.time;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Date;

/**
 * <h1>Copyright (C), 2022 ~ 2023, NyquistAi.inc</h1>
 * <p>System.out.println\([^)]*\);</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/4/19
 */
public class LocalTimeTest {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    @Test
    void testLocalDate(){
        //获取现在时间
        LocalDate now = LocalDate.now();
        System.out.println(now);

        //LocalDate 格式成 字符串
        String format = dtf.format(now);
        System.out.println(format);

        //LocalDate -> Date  ZoneId.systemDefault() 系统时区
        Date date = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(date);

        //Date -> LocalDate
        LocalDate date_2_localdate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(date_2_localdate);

        //时间戳ms
//        ZoneOffset offset = ZoneId.systemDefault().getRules().getOffset(Instant.EPOCH);
        long epochMilli = now.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(epochMilli);

        //时间戳s
        long epochSecond = now.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
        System.out.println(epochSecond);

        //时间戳ms -> LocalDate
        LocalDate millis_2_localdate = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(millis_2_localdate);

        //时间戳s -> LocalDate
        LocalDate second_2_localdate = Instant.ofEpochSecond(epochSecond).atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(second_2_localdate);
    }

    @Test
    void testLocalDateTime(){
        //获取现在时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        //LocalDateTime 格式成 字符串
        String format = dtf.format(now);
        System.out.println(format);

        //LocalDateTime -> Date  ZoneId.systemDefault() 系统时区
        Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(date);

        //Date -> LocalDateTime
        LocalDateTime date_2_localdatetime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(date_2_localdatetime);

        //时间戳ms
//        ZoneOffset offset = ZoneId.systemDefault().getRules().getOffset(Instant.EPOCH);
        long epochMilli = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(epochMilli);

        //时间戳s
        long epochSecond = now.atZone(ZoneId.systemDefault()).toEpochSecond();
        System.out.println(epochSecond);

        //时间戳ms -> LocalDateTime
        LocalDateTime millis_2_localdatetime = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(millis_2_localdatetime);

        //时间戳s -> LocalDateTime
        LocalDateTime second_2_localdatetime = Instant.ofEpochSecond(epochSecond).atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(second_2_localdatetime);
    }
    
    
    @Test
    public void test3(){
        // 示例日期
        LocalDate date1 = LocalDate.of(2025, 1, 1);
        LocalDate date2 = LocalDate.of(2025, 4, 25);
        LocalDate date3 = LocalDate.of(2025, 12, 31);
        LocalDate date4 = LocalDate.of(2026, 1, 1);
        WeekInfo weekAndYear1 = getWeekAndYear(date1);
        WeekInfo weekAndYear2 = getWeekAndYear(date2);
        WeekInfo weekAndYear3 = getWeekAndYear(date3);
        WeekInfo weekAndYear4 = getWeekAndYear(date4);
        
        System.out.println(date1 + " 是" + weekAndYear1.weekBasedYear + "年，第 " + weekAndYear1.weekNumber + " 周");
        System.out.println(date2 + " 是" + weekAndYear2.weekBasedYear + "年，第 " + weekAndYear2.weekNumber + " 周");
        System.out.println(date3 + " 是" + weekAndYear3.weekBasedYear + "年，第 " + weekAndYear3.weekNumber + " 周");
        System.out.println(date3 + " 是" + weekAndYear4.weekBasedYear + "年，第 " + weekAndYear4.weekNumber + " 周");
    }
    
    private static WeekInfo getWeekAndYear(LocalDate date) {
        // 定义周从周六开始，最小天数为1（ISO标准）
        WeekFields weekFields = WeekFields.of(DayOfWeek.SATURDAY, 1);
        
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        int weekBasedYear = date.get(weekFields.weekBasedYear());
        
        return new WeekInfo(weekNumber, weekBasedYear);
    }
    
    record WeekInfo(int weekNumber, int weekBasedYear) {
    
    }
}
