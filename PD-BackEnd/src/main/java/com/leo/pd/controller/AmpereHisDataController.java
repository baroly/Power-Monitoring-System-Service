package com.leo.pd.controller;


import com.leo.pd.comment.ReqResult;
import com.leo.pd.entity.AmpereData;
import com.leo.pd.entity.AmpereHisData;
import com.leo.pd.entity.UserIotDev;
import com.leo.pd.entity.UserSensor;
import com.leo.pd.mapper.AmpereDataMapper;
import com.leo.pd.mapper.UserSensorMapper;
import com.leo.pd.service.IAmpereHisDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author leo
 * @since 2023-12-02
 */
@RestController
@RequestMapping("/ampereHisData")
public class AmpereHisDataController {
    @Autowired
    IAmpereHisDataService ampereHisDataService;

    @Autowired
    AmpereDataMapper ampereDataMapper;

    @Autowired
    UserSensorMapper userSensorMapper;

    @GetMapping("getHis")
    public ReqResult getHis(String year, String id, String port) {
        LocalDate nowdate = LocalDate.now();
        if(nowdate.getYear() < Integer.parseInt(year)){
            return ReqResult.success(new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0});
        }

        int nowMonth = nowdate.getMonthValue();
        List<AmpereHisData> list = ampereHisDataService.lambdaQuery()
                .eq(AmpereHisData::getUserIotDevId, Integer.parseInt(id))
                .eq(AmpereHisData::getDevPort, Integer.parseInt(port))
                .eq(AmpereHisData::getYear, Integer.parseInt(year)).list();
        AmpereHisData data;

        int count = 1;

        if (list.size() > 0) {
            System.out.println(list.get(0).toString());
            Double[] datalist = list.get(0).getDataList();
            for (int i = 1, nowM = (nowdate.getYear() == Integer.parseInt(year) ? nowdate.getMonthValue() : 12) + 1; i < nowM; i++) {
                if (datalist[i - 1] == null) {
                    LocalDate date = LocalDate.parse(year + "-" + String.format("%02d", i) + "-01").with(TemporalAdjusters.lastDayOfMonth());
                    String startDateString = date.getYear() + "-" + String.format("%02d", date.getMonthValue()) + "-01 00:00:00";
                    String endDateString = date.getYear() + "-" + String.format("%02d", date.getMonthValue()) + "-" + date.getDayOfMonth() + " 23:59:59";

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime startDate = LocalDateTime.parse(startDateString, dtf);
                    LocalDateTime endDate = LocalDateTime.parse(endDateString, dtf);


                    AmpereData ampereData = new AmpereData();
                    ampereData.setUserIotDevId(Integer.parseInt(id));
                    ampereData.setPort(Integer.parseInt(port));
                    ampereData.setYear(year);
                    ampereData.setMonth(String.format("%2d", i));
                    // ampereData.setCreateTime(startDate);
                    // ampereData.setEndTime(endDate);

                    List<AmpereData> ampList = ampereDataMapper.getMonthSenseData(ampereData);
                    System.out.println(ampList.toString());
                    System.out.println(ampList.size());
                    if (ampList.size() > 0) {
                        if (ampList.get(0).getSenseData() != null) {
                            double formatData = Double.valueOf(String.format("%.3f", ampList.get(0).getSenseData()));
                            datalist[i - 1] = formatData;
                            System.out.println(formatData);
                            if (i < nowMonth) {
                                list.get(0).setDataList(i, formatData);
                            }
                        } else {
                            datalist[i - 1] = 0.0;
                            if (i < nowMonth) {
                                list.get(0).setDataList(i, 0.0);
                            }
                        }
                    } else {
                        datalist[i - 1] = 0.0;
                        if (i < nowMonth) {
                            list.get(0).setDataList(i, 0.0);
                        }
                    }
                }
                count++;
            }
            ampereHisDataService.updateById(list.get(0));
            for (int i = count; i < 13; i++) {
                datalist[i - 1] = 0.0;
            }
            return ReqResult.success(datalist);
        } else {
            AmpereHisData hisData = new AmpereHisData();
            hisData.setYear(Integer.parseInt(year));
            hisData.setUserIotDevId(Integer.parseInt(id));
            hisData.setDevPort(Integer.parseInt(port));

            Double[] tempData = new Double[12];

            for (int i = 1, nowM = (nowdate.getYear() == Integer.parseInt(year) ? nowdate.getMonthValue() : 12) + 1; i < nowM; i++) {
                LocalDate date = LocalDate.parse(year + "-" + String.format("%02d", i) + "-01").with(TemporalAdjusters.lastDayOfMonth());
                String startDateString = date.getYear() + "-" + String.format("%02d", date.getMonthValue()) + "-01 00:00:00";
                String endDateString = date.getYear() + "-" + String.format("%02d", date.getMonthValue()) + "-" + date.getDayOfMonth() + " 23:59:59";

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime startDate = LocalDateTime.parse(startDateString, dtf);
                LocalDateTime endDate = LocalDateTime.parse(endDateString, dtf);

                AmpereData ampereData = new AmpereData();
                ampereData.setUserIotDevId(Integer.parseInt(id));
                ampereData.setPort(Integer.parseInt(port));
                ampereData.setYear(year);
                ampereData.setMonth(String.format("%2d", i));
                // ampereData.setCreateTime(startDate);
                // ampereData.setEndTime(endDate);

                List<AmpereData> ampList = ampereDataMapper.getMonthSenseData(ampereData);
                System.out.println(ampList.toString());
                System.out.println(ampList.size());
                if (ampList.size() > 0) {
                    if (ampList.get(0).getSenseData() != null) {
                        double formatData = Double.valueOf(String.format("%.2f", ampList.get(0).getSenseData()));
                        tempData[i - 1] = formatData;

                        if (i < nowMonth) {
                            hisData.setDataList(i, formatData);
                        }

                    } else {
                        tempData[i - 1] = 0.0;
                        if (i < nowMonth) {
                            hisData.setDataList(i, 0.0);
                        }
                    }
                } else {
                    tempData[i - 1] = 0.0;
                    if (i < nowMonth) {
                        hisData.setDataList(i, 0.0);
                    }
                }
            }
            ampereHisDataService.save(hisData);

            for (int i = count; i < 13; i++) {
                tempData[i - 1] = 0.0;
            }

            return ReqResult.success(tempData);
        }
    }


}
