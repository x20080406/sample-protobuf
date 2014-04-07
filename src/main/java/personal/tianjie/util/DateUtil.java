package personal.tianjie.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	public static final String DEFAULT_DATETIME_HYPHEN_FORMAT_LONG		= "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DEFAULT_DATETIME_HYPHEN_FORMAT			= "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_YEAR_MONTH_DAY_TIME				= "yyyyMMddHHmmss";
	public static final String COMPLEX_GMT_FORMAT_PATTERN				= "EEE, dd MMM yyyy HH:mm:ss zzz";
	public static final String GMT_FORMAT_PATTERN						= "d MMM yyyy hh:mm:ss zzz";
	public static final String DEFAULT_DATE 							= "yyyyMMdd";
	public static final String DEFAULT_YEAR_MONTH 						= "yyyyMM";
	public static final String DEFAULT_MONTH_DAY 						= "MMdd";
	public static final String DEFAULT_TIME 							= "HHmmss";
	public static final String DEFAULT_HOUR 							= "H";
	public static final java.util.Date MIN_VALUE 						= createDate(1, 1, 1);
	public static final java.util.Date MAX_VALUE 						= createDate(9999, 12, 31, 23, 59, 59);

	public static final java.util.Date SmallDateTime_MinValue 			= createDate( 1900, 1, 1);
	public static final java.util.Date SmallDateTime_MaxValue 			= createDate( 2079, 6, 6);

	public static final java.util.Date MysqlTimeStamp_Min 				= createDate(1970, 1, 1);
	public static final java.util.Date MysqlTimeStamp_Max 				= createDate(2038, 1, 1);

	public static final java.util.Date DateTime_MinValue				= createDate(1753, 1, 1);
	public static final java.util.Date DateTime_MaxValue 				= createDate(9999, 12, 31);

	public static String getDefaultYearMonthDayTime() {
		return formatDate("yyyy-MM-dd HH:mm:ss");
	}

	public static String getClientDateTime(java.util.Date d) {
		return String.format("%s GMT",
				new Object[] { formatDate(d, "E, dd MMM yyyy HH:mm:ss")
						.toString() });
	}

	public static String getDefaultYearMonthDayTime(java.util.Date d) {
		return formatDate(d, "yyyy-MM-dd HH:mm:ss");
	}

	public static int getDefaultHourTime() {
		return Integer.parseInt(formatDate("H"));
	}

	public static int getDefaultDate() {
		return Integer.parseInt(formatDate("yyyyMMdd"));
	}

	public static int getDefaultYearMonth() {
		return Integer.parseInt(formatDate("yyyyMM"));
	}

	public static int getDefaultMonthDay() {
		return Integer.parseInt(formatDate("MMdd"));
	}

	public static int getDefaultTime() {
		return Integer.parseInt(formatDate("HHmmss"));
	}

	public static int getDefaultTime(String pattern) {
		return Integer.parseInt(formatDate(pattern));
	}

	public static java.sql.Date getSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static java.util.Date getUtilDate(Object obj) throws Exception {
		if ((obj instanceof java.sql.Date)) {
			return new java.util.Date(((java.sql.Date) obj).getTime());
		}
		throw new Exception("Class is not java.sql.Date." + obj.getClass());
	}

	public static java.util.Date getDefaultDate(String dateString)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = format.parse(dateString);
		return date;
	}

	public static java.util.Date getDefaultDate(String dateString,
			String pattern) throws ParseException {
		DateFormat format = new SimpleDateFormat(pattern);
		java.util.Date date = format.parse(dateString);
		return date;
	}

	public static java.util.Date addDatefield(java.util.Date date, int filed,
			int count) {
		if ((filed != 1) && (filed != 2) && (filed != 5) && (filed != 10)
				&& (filed != 12) && (filed != 13)) {
			throw new IllegalStateException("不能处理的时间类型");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(filed, count);
		return c.getTime();
	}

	public static java.util.Date addDay(java.util.Date date, int count) {
		return addDatefield(date, 5, count);
	}

	public static java.util.Date addMonth(java.util.Date date, int count) {
		return addDatefield(date, 2, count);
	}

	public static String getSystemCurrentDate(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		java.util.Date date = new java.util.Date();
		String currentDate = format.format(date);
		return currentDate;
	}

	public static String month(String year, String month) {
		StringBuffer sb = new StringBuffer();
		sb.append(year);
		sb.append("-");

		if ((month != null) && (month.length() == 1)) {
			month = "0" + month;
		}
		sb.append(month);
		sb.append("-01");
		return sb.toString();
	}

	public static String monthAdd(String year, String month) {
		int addMonth = Integer.parseInt(month);
		String strAddMonth = Integer.toString(addMonth + 1);
		StringBuffer sb = new StringBuffer();
		if (strAddMonth.equals("13")) {
			sb.append(Integer.parseInt(year) + 1);
			sb.append("-");
			sb.append("01");
			sb.append("-01");
		} else {
			sb.append(year);
			sb.append("-");
			sb.append(strAddMonth);
			sb.append("-01");
		}

		return sb.toString();
	}

	public static Timestamp getSystemCurrentTime() {
		java.util.Date currentDate = new java.util.Date();
		Timestamp tmspt = new Timestamp(currentDate.getTime());
		return tmspt;
	}

	public static Timestamp getTimestamp(java.util.Date date) {
		Timestamp tmspt = new Timestamp(date.getTime());
		return tmspt;
	}

	public static Timestamp getTimestamp(String dateString)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = format.parse(dateString);
		Timestamp tmspt = new Timestamp(date.getTime());
		return tmspt;
	}

	public static String getTimestampString(Object timestamp) {
		Timestamp tsmp = (Timestamp) timestamp;
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.format(new java.util.Date(tsmp.getTime()));
	}

	public static int getDateDiffDay(java.util.Date from, java.util.Date to) {
		from = truncDate(from);
		to = truncDate(to);

		return getDateFiled(from, to, 5);
	}

	public static int getDateFiled(java.util.Date from, java.util.Date to,
			int field) {
		if (from == null) {
			return 0;
		}
		if (to == null) {
			return 0;
		}
		java.util.Date date1 = from;
		java.util.Date date2 = to;
		if (!to.after(from)) {
			date1 = to;
			date2 = from;
		}

		if ((field != 1) && (field != 2) && (field != 5) && (field != 10)
				&& (field != 12) && (field != 13)) {
			throw new IllegalStateException("不能处理的时间类型");
		}
		int auditCycle = 0;
		java.util.Date tempDate = null;
		Calendar c = Calendar.getInstance();
		c.setTime(date1);
		while (true) {
			c.add(field, 1);
			tempDate = c.getTime();
			if (tempDate.after(date2)) {
				break;
			}
			auditCycle++;
		}

		if (!to.after(from)) {
			return 0 - auditCycle;
		}
		return auditCycle;
	}

	public static java.util.Date truncDate(java.util.Date date) {
		if (date == null)
			return null;
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int y = c.get(1);
			int m = c.get(2);
			int d = c.get(5);
			c = Calendar.getInstance();
			c.set(y, m, d);
			return c.getTime();
		} catch (Exception e) {
		}
		return null;
	}

	private static String formatDate(String pattern) {
		java.util.Date date = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat(pattern);

		String nowDate = format.format(date);

		return nowDate;
	}

	public static String formatDate(java.util.Date d, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.US);
		return format.format(d);
	}

	public static int compareDate(java.util.Date date1, java.util.Date date2) {
		return date1.compareTo(date2);
	}

	public static String formatDateStr(Calendar cal, String format) {
		SimpleDateFormat formator = new SimpleDateFormat(format);

		return formator.format(cal.getTime());
	}

	public static String formatDateStr(java.util.Date date, String format) {
		SimpleDateFormat formator = new SimpleDateFormat(format);

		return formator.format(date);
	}

	public static String[] getPreviousWeek() {
		String[] ret = new String[2];
		Calendar calendar = Calendar.getInstance();

		calendar.add(3, -1);
		calendar.set(7, 2);
		ret[0] = formatDateStr(calendar, "yyyy-MM-dd");
		calendar.set(7, 8);
		ret[1] = formatDateStr(calendar, "yyyy-MM-dd");

		return ret;
	}

	public static String[] getPreviousMonth() {
		String[] ret = new String[2];
		Calendar calendar = Calendar.getInstance();

		calendar.add(2, -1);
		calendar.set(5, calendar.getActualMinimum(5));
		ret[0] = formatDateStr(calendar, "yyyy-MM-dd");
		calendar.set(5, calendar.getActualMaximum(5));
		ret[1] = formatDateStr(calendar, "yyyy-MM-dd");

		return ret;
	}

	public static boolean isRightDateFormat(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			format.parse(dateString);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static String getAttachmentTime(Timestamp date) {
		java.util.Date createDate = new java.util.Date(date.getTime());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmssSSS_");
		return simpleDateFormat.format(createDate);
	}

	public static String formatDateString(String date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		java.util.Date d = null;
		try {
			d = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			d = new java.util.Date();
		}
		return simpleDateFormat.format(d);
	}

	public static java.util.Date parseComplexGMTStringDate(String date) {
		java.util.Date d = null;
		try {
			d = getComplexGMTFormater().parse(date);
		} catch (ParseException e) {
			d = null;
		}
		return d;
	}

	public static int getDateField(java.util.Date date, int field) {
		try {
			if ((field != 1) && (field != 2) && (field != 5) && (field != 10)
					&& (field != 12) && (field != 13) && (field != 7)
					&& (field != 11) && (field != 5)) {
				throw new IllegalStateException("不能处理的时间类型");
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(field);
		} catch (Exception e) {
		}
		return 0;
	}

	public static int getDayOfWeak(java.util.Date date) {
		return getDateField(date, 7);
	}

	public static int getDayOfMonth(java.util.Date date) {
		return getDateField(date, 5);
	}

	public static java.util.Date getMonthFirst(java.util.Date date) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int yr = c.get(1);
			int mon = c.get(2);
			c.set(yr, mon, 1);
			return c.getTime();
		} catch (Exception e) {
		}
		return null;
	}

	public static java.util.Date getMonthEnd(java.util.Date date) {
		try {
			java.util.Date ret = getMonthFirst(date);

			ret = addMonth(ret, 1);
			Calendar c = Calendar.getInstance();
			c.setTime(ret);

			c.add(5, -1);
			return c.getTime();
		} catch (Exception e) {
		}
		return null;
	}

	public static java.util.Date getUTCDate(java.util.Date dt) {
		Calendar cal = GregorianCalendar.getInstance();

		int zoneOffset = cal.get(15);

		int dstOffset = cal.get(16);

		return new java.util.Date(dt.getTime() - (zoneOffset + dstOffset));
	}

	public static java.util.Date getUTCNow() {
		return getUTCDate(new java.util.Date());
	}

	private static java.util.Date createDate(int argYear, int argMonth,
			int argDate) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar(argYear,
				argMonth - 1, argDate);
		gregorianCalendar.setLenient(false);
		return gregorianCalendar.getTime();
	}

	private static java.util.Date createDate(int argYear, int argMonth,
			int argDate, int hour, int minute, int second) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar(argYear,
				argMonth - 1, argDate, hour, minute, second);
		gregorianCalendar.setLenient(false);
		return gregorianCalendar.getTime();
	}

	public static boolean verfiySmallDateTime(java.util.Date date) {
		if ((date.after(SmallDateTime_MinValue))
				&& (date.before(SmallDateTime_MaxValue))) {
			return true;
		}
		return false;
	}

	public static java.util.Date getMysqlTimeStamp(java.util.Date date) {
		if (date.after(MysqlTimeStamp_Max))
			return MysqlTimeStamp_Max;
		if (date.before(MysqlTimeStamp_Min))
			return MysqlTimeStamp_Min;
		return date;
	}

	public static java.util.Date getBirthDateFor229(java.util.Date birthDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(birthDate);
		int m = c.get(2);
		int d = c.get(5);
		if ((m == 2) && (d == 29)) {
			birthDate = addDay(birthDate, -1);
		}
		return birthDate;
	}

	public static java.util.Date getGMTDate(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int zoneOffset = cal.get(15);

		int dstOffset = cal.get(16);

		cal.add(14, zoneOffset + dstOffset);
		return new java.util.Date(cal.getTimeInMillis());
	}

	public static Calendar getGMTCalendar() {
		return Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.UK);
	}

	public static SimpleDateFormat getGMTFormater() {
		SimpleDateFormat format = new SimpleDateFormat(
				"d MMM yyyy hh:mm:ss zzz", Locale.UK);
		format.setCalendar(getGMTCalendar());
		return format;
	}

	public static SimpleDateFormat getComplexGMTFormater() {
		SimpleDateFormat format = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss zzz", Locale.UK);
		format.setCalendar(getGMTCalendar());

		return format;
	}

	public static String toGMTString(java.util.Date date) {
		return getGMTFormater().format(date);
	}

	public static String toComplexGMTString(java.util.Date date) {
		return getComplexGMTFormater().format(date);
	}

	public static String getTodayDate(String pattern) {
		return formatDate(pattern);
	}
}