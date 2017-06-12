package cn.util.TimeUtil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gzy39
 * @version 1.0
 * 2017/5/16
 */
public class TimeUtil {
	
	static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//Ĭ��ʱ���ʽ
	
	/**
	 * Date��ʽתTimestamp��ʽ
	 * @param Date
	 * @return Timestamp
	 * @author gzy39
	 * @version 1.0
	 */
	public static Timestamp toTimesTamp(Date date){
		return new Timestamp(date.getTime());
	}
	
	/**
	 * String��ʽתTimestamp��ʽ
	 * @param String
	 * @return Timestamp
	 * @author gzy39
	 * @version 1.0
	 */
	public static Timestamp toTimestamp(String dateStr){
		Timestamp ts = new Timestamp(System.currentTimeMillis()); 
		 try {
			 ts = Timestamp.valueOf(dateStr);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return ts;
	}
	
	/**
	 * Timestamp��ʽתDate��ʽ
	 * @param Timestamp
	 * @return Date
	 * @author gzy39
	 * @version 1.0
	 */
	public static Date toDate(Timestamp ts){
		return new Date(ts.getTime());
	}
	
	/**
	 * Timestamp��ʽתDate��ʽ
	 * @param Timestamp
	 * @param String ʱ���ʽ
	 * @return Date
	 * @author gzy39
	 * @version 1.0
	 */
	public static Date toDate(Timestamp ts,String format){
		Date date =  new Date(ts.getTime());
		DateFormat df= new SimpleDateFormat(format);
		String dataStr = df.format(date);
		Date date_1 = null;
		try {
			date_1 = df.parse(dataStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return date_1;
	}
	
	/**
	 * String��ʽתDate��ʽ
	 * @param String
	 * @return Date
	 * @author gzy39
	 * @version 1.0
	 */
	public static Date toDate(String dateStr){
		 Date date = new Date();
		 try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		 return date;
	}
	
	/**
	 * String��ʽתDate��ʽ
	 * @param String ʱ���ַ���
	 * @param String ʱ���ʽ
	 * @return Date
	 * @author gzy39
	 * @version 1.0
	 * δ���
	 */
	public static Date toDate(String dateStr,String format){
		 Date date = new Date();
		 try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		 return date;
	}
}
