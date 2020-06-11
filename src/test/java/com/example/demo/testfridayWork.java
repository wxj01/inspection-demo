package com.example.demo;

import utils.GetDataUtils;

import java.util.List;

public class testfridayWork {

    public static void main(String[] args) throws Exception {
        String startDay = "2020-01-01";
        String endDay = "2020-12-31";
        List<String> allWeekly2 = GetDataUtils.getAllWeekly2(startDay, endDay);
        for (int i = 0; i < allWeekly2.size();i++){
            String day = allWeekly2.get(i);
            String dat =  day.replaceAll("-", "");
//            if( !"0".equals(GetDataUtils.getHoliday(dat))){
                System.out.println(day);
//            }
        }
    }
}
