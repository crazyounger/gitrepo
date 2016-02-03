package com.airshiplay.mobile.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Create 2013-7-20
 * @author lig
 * @version 1.0
 * @since 1.0
 */
public class DateUtil {
	public static SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
	}

	/**
	 * 时间格式化
	 * 
	 * @param date
	 * @return 返回yyyy-MM-dd HH:mm:ss格式时间
	 */
	public static String format(Date date) {
		return getDateFormat().format(date);
	}
	
	
	/**
	 * 时间格式化
	 * 返回 yyyy-MM-dd 格式时间
	 */
	
	public static String getDate(long time){
		return new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA).format(new Date(time));
	}
	
	/**
	 * 时间格式化
	 * 返回 HH:mm:ss格式时间
	 */
	public static String getTime(long time){
		return new SimpleDateFormat("HH:mm:ss",Locale.CHINA).format(new Date(time));
	}

	/**
	 * 时间格式化
	 * 
	 * @param date
	 * @return 返回yyyy-MM-dd HH:mm:ss格式时间
	 */
	public static String format(long time) {
		return getDateFormat().format(new Date(time));
	}
	
	/**
	 * @param time 返回HH时mm分ss秒格式时间
	 * @return
	 */
	public static String formatTime(long time) {
		String text="";
		long  hour=time/(1*60*60*1000);
		long  mini=(time%(1*60*60*1000))/(60*1000);
		long second=((time%(1*60*60*1000))%(60*1000))/1000;
		if(hour!=0){
			text=hour+"时";
		}
		if(mini!=0){
			text+=(mini+"分");
		}if(second!=0){
			text+=(second+"秒");
		}
		return text;
	}
	/**
	 * 
	 * @param time  返回 分钟  
	 * @return
	 */
	public static double formatLongToMinutes(long time){
		
		return  (double)time / ( 1000 * 60 ) ;
	}
	
	//返回秒
	public static double formatLongToSecond(long time){
			return    (double)time / (double)1000 ;
	}
	
	
	public static String LongToIp(long ip) {
		return ""+((ip >> 24) & 0xFF) + "."   
				+ ((ip >> 16) & 0xFF) + "."   
				+ ((ip >> 8) & 0xFF) + "."   
				+ (ip & 0xFF);  
	}
	
	 /**
     * 返回几个小时前的时间 毫秒数
     * @param d
     * @param hour
     * @return
     */
    public static long getDateBeforeHour(Date d,int hour){
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.HOUR, now.get(Calendar.HOUR) - hour);
        return now.getTimeInMillis();
    }
    
    /**
     * 获取几天前的时间 毫秒数
     * @param d
     * @param day
     * @return
     */
    public static long getDateBeforeDay(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);    
       return  now.getTimeInMillis();
    }
}
