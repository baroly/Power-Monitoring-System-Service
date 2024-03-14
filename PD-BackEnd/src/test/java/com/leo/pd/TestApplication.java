package com.leo.pd;

import com.leo.pd.entity.AmpereData;
import com.leo.pd.entity.UserIotDev;
import com.leo.pd.service.IAmpereDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class TestApplication {
    @Autowired
    IAmpereDataService ampereDataService;

    @Test
    public void createAmpereData(){
        LocalDate date = LocalDate.parse(2023 + "-" + String.format("%02d", 10) + "-01").with(TemporalAdjusters.lastDayOfMonth());
        String startDate = date.getYear() + "-" + date.getMonthValue() + "-01 00:00:00";
        String endDate = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth() + " 23:59:59";
        //date = date.with(TemporalAdjusters.lastDayOfMonth());
        //System.out.println(date);
        //LocalDateTime time = LocalDateTime.parse("2023-11-15T12:38:34");
        //time = time.plusMinutes(1);
        System.out.println(startDate);
        System.out.println(endDate);
    }

    @Test
    public void testList(){
        UserIotDev dev = new UserIotDev();
        dev.setAlias("123");
        dev.setId(1);
        dev.setUid("1234444");
        List<UserIotDev> list = new ArrayList<>();
        list.add(dev);

        System.out.println(list.contains("123"));
    }

    @Test
    public void test(){
        String uid = "AS06000001";
        System.out.println(Integer.parseInt(uid.substring(2, 4)));
        System.out.println(uid.substring(4));
    }

    @Test
    public void test2(){
        LocalDate date = LocalDate.now();
        System.out.println(date.getYear());
        System.out.println(date.getMonth().getValue());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());
    }
}
