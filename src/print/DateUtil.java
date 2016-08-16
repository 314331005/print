package print;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author alone
 * 
 */
public class DateUtil {
	private static String ymdhms = "yyyy-MM-ddHH:mm:ss";
	private static String ymd = "yyyy-MM-dd";
	public static SimpleDateFormat ymdSDF = new SimpleDateFormat(ymd);
	private static String year = "yyyy";
	private static String month = "MM";
	private static String day = "dd";
	private static String time = "HH:mm:ss";
	public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(ymdhms);
	public static SimpleDateFormat yearSDF = new SimpleDateFormat(year);
	public static SimpleDateFormat monthSDF = new SimpleDateFormat(month);
	public static SimpleDateFormat daySDF = new SimpleDateFormat(day);

	
	
	public static String  getYear(Date d){
		SimpleDateFormat sf  = new SimpleDateFormat(year);
		return sf.format(d);
	}
	public static String  getMonth(Date d){
		SimpleDateFormat sf  = new SimpleDateFormat(month);
		return sf.format(d);
	}
	public static String  getDay(Date d){
		SimpleDateFormat sf  = new SimpleDateFormat(day);
		return sf.format(d);
	}
	public static String  getTime(Date d){
		SimpleDateFormat sf  = new SimpleDateFormat(time);
		return sf.format(d);
	}
	public static String  getDate(Date d){
		SimpleDateFormat sf  = new SimpleDateFormat(ymd);
		return sf.format(d);
	}
	
	public static String getWeekOfDate(Date d) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
	public static Date getDate(String time){
		SimpleDateFormat sf  = new SimpleDateFormat(ymdhms);
		try {
			return sf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
}