package com.zhaxd.web.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTime extends Date {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 现在的时间
	 * 
	 * @return 现在的时间
	 */
	public static DateTime now() {
		return new DateTime();
	}
	
	/**
	 * 当前时间
	 */
	public DateTime() {
		super();
	}

	/**
	 * 给定日期的构造
	 * 
	 * @param date 日期
	 */
	public DateTime(Date date) {
		this(date.getTime());
	}

	/**
	 * 给定日期的构造
	 * 
	 * @param calendar {@link Calendar}
	 */
	public DateTime(Calendar calendar) {
		this(calendar.getTime());
	}

	/**
	 * 给定日期毫秒数的构造
	 * 
	 * @param timeMillis 日期毫秒数
	 */
	public DateTime(long timeMillis) {
		super(timeMillis);
	}
	
	/**
	 * 获得日期的某个部分<br>
	 * 例如获得年的部分，则使用 getField(DatePart.YEAR)
	 * @param field 表示日期的哪个部分的枚举 {@link DateField}
	 * @return 某个部分的值
	 */
	public int getField(DateField field){
		return getField(field.getValue());
	}
	
	/**
	 * 获得日期的某个部分<br>
	 * 例如获得年的部分，则使用 getField(Calendar.YEAR)
	 * @param field 表示日期的哪个部分的int值 {@link Calendar}
	 * @return 某个部分的值
	 */
	public int getField(int field){
		return toCalendar().get(field);
	}
	
	/**
	 * 获得年的部分
	 * 
	 * @return 年的部分
	 */
	public int year() {
		return getField(DateField.YEAR);
	}

	/**
	 * 获得当前日期所属季度<br>
	 * 1：第一季度<br>
	 * 2：第二季度<br>
	 * 3：第三季度<br>
	 * 4：第四季度<br>
	 * 
	 * @return 第几个季度
	 */
	public int season() {
		return monthStartFromOne() /3 + 1;
	}
	
	/**
	 * 获得月份，从0开始计数
	 * 
	 * @return 月份
	 */
	public int month() {
		return getField(DateField.MONTH);
	}
	
	/**
	 * 获得月份，从1开始计数<br>
	 * 由于{@link Calendar} 中的月份按照0开始计数，导致某些需求容易误解，因此如果想用1表示一月，2表示二月则调用此方法
	 * 
	 * @return 月份
	 */
	public int monthStartFromOne() {
		return month() +1;
	}

	/**
	 * 获得指定日期是所在年份的第几周<br>
	 * 
	 * @return 周
	 */
	public int weekOfYear() {
		return getField(DateField.WEEK_OF_YEAR);
	}

	/**
	 * 获得指定日期是所在月份的第几周<br>
	 * 
	 * @return 周
	 */
	public int weekOfMonth() {
		return getField(DateField.WEEK_OF_MONTH);
	}

	/**
	 * 获得指定日期是这个日期所在月份的第几天<br>
	 * 
	 * @return 天
	 */
	public int dayOfMonth() {
		return getField(DateField.DAY_OF_MONTH);
	}

	/**
	 * 获得指定日期是星期几
	 * 
	 * @return 天
	 */
	public int dayOfWeek() {
		return getField(DateField.DAY_OF_WEEK);
	}
	
	/**
	 * 获得天所在的周是这个月的第几周
	 * 
	 * @return 天
	 */
	public int dayOfWeekInMonth() {
		return getField(DateField.DAY_OF_WEEK_IN_MONTH);
	}

	/**
	 * 获得指定日期的小时数部分<br>
	 * 
	 * @param is24HourClock 是否24小时制
	 * @return 小时数
	 */
	public int hour(boolean is24HourClock) {
		return getField(is24HourClock ? DateField.HOUR_OF_DAY : DateField.HOUR);
	}

	/**
	 * 获得指定日期的分钟数部分<br>
	 * 例如：10:04:15.250 -> 4
	 * 
	 * @return 分钟数
	 */
	public int minute() {
		return getField(DateField.MINUTE);
	}

	/**
	 * 获得指定日期的秒数部分<br>
	 * 
	 * @return 秒数
	 */
	public int second() {
		return getField(DateField.SECOND);
	}

	/**
	 * 获得指定日期的毫秒数部分<br>
	 * 
	 * @return 毫秒数
	 */
	public int millsecond() {
		return getField(DateField.MILLISECOND);
	}

	/**
	 * 转换为Calendar，默认{@link TimeZone}，默认 {@link Locale}
	 * 
	 * @return {@link Calendar}
	 */
	public Calendar toCalendar() {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(this);
		return cal;
	}
	
	/**
	 * 转换为Calendar
	 * @param locale 地域 {@link Locale}
	 * @return {@link Calendar}
	 */
	public Calendar toCalendar(Locale locale) {
		final Calendar cal = Calendar.getInstance(locale);
		cal.setTime(this);
		return cal;
	}
	
	/**
	 * 转换为Calendar
	 * @param zone 时区 {@link TimeZone}
	 * @return {@link Calendar}
	 */
	public Calendar toCalendar(TimeZone zone) {
		return toCalendar(zone, Locale.getDefault(Locale.Category.FORMAT));
	}
	
	/**
	 * 转换为Calendar
	 * @param zone 时区 {@link TimeZone}
	 * @param locale 地域 {@link Locale}
	 * @return {@link Calendar}
	 */
	public Calendar toCalendar(TimeZone zone, Locale locale) {
		final Calendar cal = Calendar.getInstance(zone, locale);
		cal.setTime(this);
		return cal;
	}
}