package com.smart.om.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期处理类
 */
public class DateUtil {
	
	//根据业务名称和类型定义编号
	public static String getNo(String noName){
		StringBuffer serialNo = new StringBuffer();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		serialNo.append(noName).append(sf.format(new Date()));
		return serialNo.toString();
	}

	//获得单据编号序列号
	public static String getSerialNo(){
		String serialNo = "";
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		serialNo = sf.format(new Date());
		return serialNo;
	}
	
	//获得单据编号序列号
	public static String getSerialUUIDNo(Integer i){
		String serialNo = "";
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		//每次都生成新的UUID，以免服务器时间来回修改导致主键重复
		String uuid = "";
		if(i < 10){
			uuid = "00" + i.toString();
		}
		else if(i< 100){
			uuid = "0" + i.toString();
		}
		else{
			uuid = i.toString();
		}
		serialNo = sf.format(new Date()) + "-" + uuid;
		return serialNo;
	}
		
	// 获得当前日期的年、月、日，如2013-05-17
	public static String getDateY_M_D() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(new Date());
	}
	// 获得当前日期的年、月、日，如20130517
	public static String getDateYMD() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		return sf.format(new Date());
	}
	
	// 获得当前日期的年、月、日、小时、分钟、秒，如2013-05-17 10:12:30
	public static String getDateY_M_DH_M_S() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sf.format(new Date());
	}
	
	// 获得当前日期的年、月、日、小时、分钟、秒，如2013-05-17 10:12:30
	public static String getDateY_M_DH_M_SS() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(new Date());
	}
	
	// 获得当前日期的年、月、日、小时、分钟、秒，如20130517101230
	public static String getDateYMDHMS() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sf.format(new Date());
	}
	
	// 获取当月第一天
	public static String getFristDayOfMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.set(Calendar.DAY_OF_MONTH, 1);
		return df.format(gc.getTime());
	}
	
	public static String getFristDayOfMonth(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return df.format(cal.getTime());
	}
	
	// 获取当月最后一天
	public static String getLastDayOfMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);// 当前月＋1，即下个月
		cal.set(Calendar.DATE, 1);// 将下个月1号作为日期初始值
		cal.add(Calendar.DATE, -1);// 下个月1号减去一天，即得到当前月最后一天		
		return df.format(cal.getTime());
	}
	
	public static String getLastDayOfMonth(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);// 当前月＋1，即下个月
		cal.set(Calendar.DATE, 1);// 将下个月1号作为日期初始值
		cal.add(Calendar.DATE, -1);// 下个月1号减去一天，即得到当前月最后一天		
		return df.format(cal.getTime());
	}
	
	
	// 获得一个月前的这一天
	public static String getCurrentDayOfLastMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);// 当前月-1，即 上个月		
		return df.format(cal.getTime());
	}
	
	// 获得上个月
	public static String getLastMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);// 当前月-1，即 上个月		
		return df.format(cal.getTime());
	}
	
	// 获得一个月前的这一天
	public static String getCurrentDayOfNextMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);// 当前月＋1，即下个月		
		return df.format(cal.getTime());
	}
	
	public static String dateToString(Date date,String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}
	
	public static Date StringToDate(String datetime,String format) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
		java.util.Date date = null;
		try {
			date = bartDateFormat.parse(datetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
     * @param n
     * @return 当前日期的前n天
     */
    public static String beforNowDay(int n)
    {   
        Calendar c=Calendar.getInstance();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        c.setTime(new Date());
        c.add(Calendar.DATE,-n);
        Date d2=c.getTime();
        String s=df.format(d2);
        return s;
    }
	//获得指定时间的前一天
	public static String getBeforeDayOfSpecified(String specifiedDay) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = sf.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		String dayBefore = sf.format(c.getTime());
		return dayBefore;
	}
	
	//获得指定时间的后一天
	public static String getAfterDayOfSpecified(String specifiedDay) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = sf.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);
		String dayBefore = sf.format(c.getTime());
		return dayBefore;
	}
	
	//获得指定时间的后一天
	public static Date getAfterDayOfSpecified(Date specifiedDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(specifiedDate);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);
		return c.getTime();
	}
	
	public static Date addDay(int days,Date specifiedDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(specifiedDate);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + days);
		return c.getTime();
	}
	
	public static String addDay(int days,String specifiedDate,String formart) {
		SimpleDateFormat sf = new SimpleDateFormat(formart);
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = sf.parse(specifiedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return sf.format(c.getTime());
	}
	
	public static Date addYear(int years,Date specifiedDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(specifiedDate);
		int year = c.get(Calendar.YEAR);
		c.set(Calendar.YEAR, year + years);
		return c.getTime();
	}
	
	// 获取星期开始日期
	public static String getStartDayOfWeek(Date time) {
		String startTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(time);
			// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
			int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			if (1 == dayWeek) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
			}
			// 输出要计算日期
			cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
			int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
			startTime = sdf.format(cal.getTime()) + " 00:00:00";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return startTime;
	}

	// 获取星期结束日期
	public static String getEndDayOfWeek(Date time) {
		String endTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(time);
			// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
			int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			if (1 == dayWeek) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
			}
			// 输出要计算日期
			cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
			int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
			cal.add(Calendar.DATE, 6);
			endTime = sdf.format(cal.getTime()) + " 23:59:59";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return endTime;
	}

	// 获取上星期开始日期
	public static String getStartDayOfPreWeek(Date time) {
		String startTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(time);
			// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
			int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			if (1 == dayWeek) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
			}
			// 输出要计算日期
			cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
			int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
			cal.add(Calendar.DATE, -7);
			startTime = sdf.format(cal.getTime()) + " 00:00:00";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return startTime;
	}

	// 获取上星期结束日期
	public static String getEndDayOfPreWeek(Date time) {
		String endTime = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(time);
			// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
			int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			if (1 == dayWeek) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
			}
			// 输出要计算日期
			cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
			int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
			cal.add(Calendar.DATE, -1);
			endTime = sdf.format(cal.getTime()) + " 23:59:59";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return endTime;
	}
	// 获取时间
	public static Date getDateOfTime(String time) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = null;
		try {
			date = bartDateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	// 获取时间
	public static Date getDateOfTime(String time,String format) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
		java.util.Date date = null;
		try {
			date = bartDateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	//根据传入的时间计算出两个日期之前的时间差距，以天--时--分的形式返回
	public static String getTimeBetween2Dates(Date date1, Date date2) {
		long distance = Math.abs(date1.getTime() - date2.getTime()) / 1000;
		long days = distance / (24 * 3600);
		long hours = distance % (24 * 3600) / 3600;
		long minutes = distance % 3600 / 60;
		int _hours = Integer.parseInt(String.valueOf(days));
		int _minutes = Integer.parseInt(String.valueOf(hours));
		int _seconds = Integer.parseInt(String.valueOf(minutes));
		StringBuffer _buffer = new StringBuffer();		
		if ((_hours == 0) && (_minutes == 0)) {
			_buffer.append(_seconds).append("分钟");
		} else if (_hours == 0) {
			if (_seconds == 0) {
				_buffer.append(_minutes).append("小时");
			} else {
				if (_minutes >= 10) {
					if (_seconds >= 10) {
						_buffer.append(_minutes).append("小时").append(_seconds).append("分");
					} else {
						_buffer.append(_minutes).append("小时").append("0" + _seconds).append("分");
					}
				} else {
					if (_seconds >= 10) {
						_buffer.append(_minutes).append("小时").append(_seconds).append("分");
					} else {
						_buffer.append(_minutes).append("小时").append("0" + _seconds).append("分");
					}
				}
			}
		} else {
			if (_hours >= 10) {
				if (_minutes >= 10) {
					if (_seconds >= 10) {
						_buffer.append(_hours).append("天").append(_minutes).append("小时").append(_seconds).append("分");
					} else {
						_buffer.append(_hours).append("天").append(_minutes).append("小时").append("0" + _seconds).append("分");
					}
				} else {
					if (_seconds >= 10) {
						_buffer.append(_hours).append("天").append("0" + _minutes).append("小时").append(_seconds).append("分");
					} else {
						_buffer.append(_hours).append("天").append("0" + _minutes).append("小时").append("0" + _seconds).append("分");
					}
				}
			} else {
				if (_minutes >= 10) {
					if (_seconds >= 10) {
						_buffer.append(_hours).append("天").append(_minutes).append("小时").append(_seconds).append("分");
					} else {
						_buffer.append(_hours).append("天").append(_minutes).append("小时").append("0" + _seconds).append("分");
					}
				} else {
					if (_seconds >= 10) {
						_buffer.append(_hours).append("天").append("0" + _minutes).append("小时").append(_seconds).append("分");
					} else {
						_buffer.append(_hours).append("天").append("0" + _minutes).append("小时").append("0" + _seconds).append("分");
					}
				}
			}
		}
		return _buffer.toString();
	}
	
	/**
	 * 返回一个<code>String</code>类型的字符串, 将秒数转化为分钟数或小时数 例如: 1000秒-->16:40
	 * 5687秒-->01：34：47
	 */
	public static String formatSecToTime(int _strTemp) {
		int _str = _strTemp;
		if (_str < 0) {
			_str = _str * (-1);
		}
		int _hours = _str / 3600;
		int _minutes = (_str % 3600) / 60;
		int _seconds = ((_str % 3600) % 60) % 60;

		StringBuffer _buffer = new StringBuffer();
		if ((_hours == 0) && (_minutes == 0)) {
			if (_seconds >= 10) {
				_buffer.append("00").append(":").append("00").append(":")
						.append(_seconds);
			} else {
				_buffer.append("00").append(":").append("00").append(":")
						.append("0" + _seconds);
			}
		} else if (_hours == 0) {
			if (_seconds == 0) {
				if (_minutes >= 10) {
					_buffer.append("00").append(":").append(_minutes).append(
							":").append("00");
				} else {
					_buffer.append("00").append(":").append("0" + _minutes)
							.append(":").append("00");
				}
			} else {
				if (_minutes >= 10) {
					if (_seconds >= 10) {
						_buffer.append("00").append(":").append(_minutes)
								.append(":").append(_seconds);
					} else {
						_buffer.append("00").append(":").append(_minutes)
								.append(":").append("0" + _seconds);
					}
				} else {
					if (_seconds >= 10) {
						_buffer.append("00").append(":").append("0" + _minutes)
								.append(":").append(_seconds);
					} else {
						_buffer.append("00").append(":").append("0" + _minutes)
								.append(":").append("0" + _seconds);
					}
				}
			}
		} else {
			if (_hours >= 10) {
				if (_minutes >= 10) {
					if (_seconds >= 10) {
						_buffer.append(_hours).append(":").append(_minutes)
								.append(":").append(_seconds);
					} else {
						_buffer.append(_hours).append(":").append(_minutes)
								.append(":").append("0" + _seconds);
					}
				} else {
					if (_seconds >= 10) {
						_buffer.append(_hours).append(":").append(
								"0" + _minutes).append(":").append(_seconds);
					} else {
						_buffer.append(_hours).append(":").append(
								"0" + _minutes).append(":").append(
								"0" + _seconds);
					}
				}
			} else {
				if (_minutes >= 10) {
					if (_seconds >= 10) {
						_buffer.append("0" + _hours).append(":").append(
								_minutes).append(":").append(_seconds);
					} else {
						_buffer.append("0" + _hours).append(":").append(
								_minutes).append(":").append("0" + _seconds);
					}
				} else {
					if (_seconds >= 10) {
						_buffer.append("0" + _hours).append(":").append(
								"0" + _minutes).append(":").append(_seconds);
					} else {
						_buffer.append("0" + _hours).append(":").append(
								"0" + _minutes).append(":").append(
								"0" + _seconds);
					}
				}
			}
		}
		if (_strTemp >= 0) {
			return _buffer.toString();
		} else {
			return "-" + _buffer.toString();
		}
	}
	
	/**
	 * 获取当前的年份
	 */
	public static String getYear() {
		return new String("") + Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 获取当前月份
	 */
	public static String getMonth(int type) {
		String result = "";
		int month = Calendar.getInstance().get(Calendar.MONTH);
		if (type == 0) {
			if (month >= 10) {
				result += month;
			} else {
				result += "0" + month;
			}
		} else if (type == 1) {
			if (month >= 9) {
				result += (month + 1);
			} else {
				result += "0" + (month + 1);
			}
		} else if (type == 2) {
			if (month >= 12) {
				result += (month - 1);
			} else {
				result += "0" + (month - 1);
			}
		}
		return result;
	}

	/**
	 * 获取当前日期
	 */
	public static String getDay() {
		String result = "";

		int day = Calendar.getInstance().get(Calendar.DATE);
		if (day >= 10) {
			result += day;
		} else {
			result += "0" + day;
		}

		return result;
	}
	
	/**
	 * 将字符串转换成时间格式 例子: 20030612-->2003年06月12日
	 */
	public static String cvtStringToTime(String _str) {
		StringBuffer buff = new StringBuffer();
		buff.append(_str.substring(0, 4));
		buff.append("年");
		buff.append(_str.substring(4, 6));
		buff.append("月");
		buff.append(_str.substring(6, 8));
		buff.append("日");
		return buff.toString();
	}

	/**
	 * 将字符串转换成时间格式 例子: 20030612101010-->2003-06-12 10:10:10
	 */
	public static String cvtStringToDateTime(String _str) {
		StringBuffer buff = new StringBuffer();
		buff.append(_str.substring(0, 4));
		buff.append("-");
		buff.append(_str.substring(4, 6));
		buff.append("-");
		buff.append(_str.substring(6, 8));
		buff.append(" ");
		buff.append(_str.substring(8, 10));
		buff.append(":");
		buff.append(_str.substring(10, 12));
		buff.append(":");
		buff.append(_str.substring(12, 14));
		return buff.toString();
	}
	
	/**
	 * 计算两个日期的时间差，精确到秒
	 */
	public static String getDateDiff(String dateStop,String dateStart) {
		String diffStr = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	    
        Date d1 = null;
        Date d2 = null;    
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);    
            
            long diff = d2.getTime() - d1.getTime();//毫秒ms    
            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            diffStr = diffDays + " 天, " + diffHours + " 小时, " + diffMinutes + " 分钟, "  + diffSeconds + " 秒.";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffStr;
	}
	
	/**
	 * 比较2个时间的大小
	 * @param dateStop
	 * @param dateStart
	 */
	public static boolean getTimeDiff(String dateStop,String dateStart){
		boolean diffStr = false;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");	
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");	    
        Date d1 = null;
        Date d2 = null; 
        try {
        	if(!"".equals(dateStart) && null!=dateStart){
        		d1 = format.parse(dateStart);
        		d2 = format.parse(dateStop);             
                long diff = d2.getTime() - d1.getTime();//毫秒ms  
                if(diff > 0){
                	diffStr = true;
                }
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffStr;
	}
	
	/**
	 * 计算两个日期的时间差，精确到秒
	 */
	public static int getDayDiff(String dateStop,String dateStart) {
		int diffStr = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");	    
        Date d1 = null;
        Date d2 = null;    
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);             
            long diff = d2.getTime() - d1.getTime();//毫秒ms  
            long diffDays = diff / (24 * 60 * 60 * 1000);
            diffStr = Long.valueOf(diffDays).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diffStr;
	}
	
	// 获取上月第一天
	public static String getFristDayOfLastMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);// 上月
		cal.set(Calendar.DATE, 1);// 将上月1号作为日期初始值	
		return df.format(cal.getTime());
	}
	
	// 获取上月最后一天
	public static String getLastDayOfLastMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 0);// 当前月
		cal.set(Calendar.DATE, 1);// 将当前月1号作为日期初始值
		cal.add(Calendar.DATE, -1);// 当前月1号减去一天，即得到上月月最后一天		
		return df.format(cal.getTime());
	}
	
	//获得指定月份的最后一天
    public static String getLastDayOfSpecifiedDayMonth(String specifiedDay) {
    	//格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf.parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(date);	
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);        
        String lastDayOfMonth = sdf.format(cal.getTime());         
        return lastDayOfMonth;
    }
	
	public static void main(String[] args) {
//		System.out.println("指定月份的最后一天："+getLastDayOfSpecifiedDayMonth("2015-04-01"));
//		System.out.println("上月第一天："+getFristDayOfLastMonth());
//		System.out.println("上月最后一天："+getLastDayOfLastMonth());
//		String date = "2015-06-25";		
//		String day = getEndDayOfPreWeek(new Date());
//		System.out.println("day:"+day);
//		String day1 = getDateDiff("2013-02-19 09:29:58", "2013-02-19 08:39:50");
//		System.out.println("day1:"+day1);
		
		//boolean flag = getTimeDiff("2013-02-19 08:29", "2013-02-19 08:39");
		//System.out.println("flag:"+flag);
		
		System.out.println("beforNowDay(2):"+beforNowDay(2));
		
		
//		String serialNo = getSerialNo();
//		System.out.println("serialNo:"+serialNo);	
//		
//		String serialUUIDNo = getSerialUUIDNo(200);
//		System.out.println("serialUUIDNo:"+serialUUIDNo);	
//		System.out.println(DateUtil.addDay(7,"2015-07-27","yyyy-MM-dd"));
//		System.out.println(DateUtil.getLastMonth());
//		System.out.println(DateUtil.getStartDayOfPreWeek(new Date()));
		
//		System.out.println(DateUtil.getDayDiff("2015-06-02", "2015-05-28"));
//		System.out.println(DateUtil.addDay(3,date,"yyyy-MM-dd"));
//		System.out.println(getDateOfTime("2015-06-19 00:00:00"));
//		System.out.println(DateUtil.getAfterDayOfSpecified("2015-06-19"));
		
	}
}

