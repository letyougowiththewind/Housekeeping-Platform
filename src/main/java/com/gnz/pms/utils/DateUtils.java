package com.gnz.pms.utils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.gnz.pms.service.ISellcontractService;
import com.gnz.pms.service.impl.SellContractServiceImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.annotation.Resource;

/**
 * 时间工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     * 
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * 
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }
    
    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算相差天数
     */
    public static int differentDaysByMillisecond(Date date1, Date date2)
    {
        return Math.abs((int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24)));
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 实现对日期字符串的重构
     * @author gnz
     * @return
     */
    public static List<String> assemblyDate(){
        List<String> result = new ArrayList<>();
        String rawDate = getDate();
        int[] bigMonth = {1,3,5,7,8,10,12};
        int[] smallMonth = {4,6,9,11};
        int year = Integer.parseInt(getDate().substring(0,4));
        if(ArrayUtils.contains(bigMonth,Integer.parseInt(getDate().substring(5,7)))){
            String startDate = rawDate.substring(0,8)+"01";
            String finalDate = rawDate.substring(0,8)+"31";
            result.add(0,startDate);
            result.add(1,finalDate);
        }else if(ArrayUtils.contains(smallMonth,Integer.parseInt(getDate().substring(5,7)))){
            String startDate = rawDate.substring(0,8)+"01";
            String finalDate = rawDate.substring(0,8)+"30";
            result.add(0,startDate);
            result.add(1,finalDate);
        }
        if ((year % 4 == 0 & year % 100 != 0) || year % 400 == 0 || Integer.parseInt(getDate().substring(5,7))==2) {
            String startDate = rawDate.substring(0,8)+"01";
            String finalDate = rawDate.substring(0,8)+"29";
            result.add(0,startDate);
            result.add(1,finalDate);
        } else if(Integer.parseInt(getDate().substring(5,7))==2){
            String startDate = rawDate.substring(0,8)+"01";
            String finalDate = rawDate.substring(0,8)+"28";
            result.add(0,startDate);
            result.add(1,finalDate);
        }
        return result;
    }

    /**
     * 实现将日期毫秒数放入数组中
     * @author gnz
     * @return
     */
    public static List<Long> assemblyDay(){
        List<Long> list = new ArrayList<>();
        int[] bigMonth = {1,3,5,7,8,10,12};
        int[] smallMonth = {4,6,9,11};
        //获取当前年份
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        //如果是闰年的二月
        if ((year % 4 == 0 & year % 100 != 0) || year % 400 == 0 || Integer.parseInt(getDate().substring(5,7))==2){
            for(int i=1;i<=29;i++){
                list.add(i-1,new Date(year, month, i).getTime());
            }
        }
        else if(Integer.parseInt(getDate().substring(5,7))==2){
            for(int i=1;i<=28;i++){
                list.add(i-1,new Date(year, month, i).getTime());
            }
        }
        else if(ArrayUtils.contains(smallMonth,Integer.parseInt(getDate().substring(5,7)))){
            for(int i=1;i<=30;i++){
                list.add(i-1,new Date(year, month, i).getTime());
            }
        }
        else if(ArrayUtils.contains(bigMonth,Integer.parseInt(getDate().substring(5,7)))){
            for(int i=1;i<=31;i++){
                list.add(i-1,new Date(year, month+1, i).getTime()-new Date(1969,12,31).getTime()-28800000);
            }
        }
        System.out.println(list.size());
        return list;
    }

    public static List<String> assemblyYear(){
        List<String> result = new ArrayList<>();
        String rawDate = getDate();
        String start = rawDate.substring(0,5)+"01-01";
        String end = rawDate.substring(0,5)+"12-31";
        result.add(0,start);
        result.add(1,end);
        return result;
    }

    /**
     * .Description://根据字符日期返回星期几
     * .Author:gnz
     * .@Date: 2022/01/09
     */
    public static String getWeek(String dateTime){
        String week = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateTime);
            SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
            week = dateFm.format(date);
            week=week.replaceAll("星期","周");
        }catch (ParseException e){
            e.printStackTrace();
        }
        return week;
    }

    /**
     * 获取过去7天内的日期数组
     * @param intervals      intervals天内
     * @return              日期数组
     */
    public static ArrayList<String> getDays(int intervals) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = intervals -1; i >= 0; i--) {
            pastDaysList.add(getPastDate(i));
        }
        return pastDaysList;
    }
    /**
     * 获取过去第几天的日期
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }
}
