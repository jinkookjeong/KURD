package iq.kurd.com.util.format;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import com.ibm.icu.util.ChineseCalendar;

import iq.kurd.com.util.format.StringUtil;

/**
 * Date 에 대한 Util 클래스: date util class
 * @fileName  : DateUtil.java
 * @package   : iq.kurd.com.util.format
 * @since     : 2018. 2. 21
 * @author    : Jin Kook JEONG
 */
public class DateUtil {
 
    /**
     * 년월일 계산 : add Year,add Month,add Day
     * @methodName : addYearMonthDay
     * @return     : String
     * @param sDate
     * @param year
     * @param month
     * @param day
     * <pre>
     * DateUtil.addYearMonthDay("19810828", 0, 0, 19)  = "19810916"
     * DateUtil.addYearMonthDay("20060228", 0, 0, -10) = "20060218"
     * DateUtil.addYearMonthDay("20060228", 0, 0, 10)  = "20060310"
     * DateUtil.addYearMonthDay("20060228", 0, 0, 32)  = "20060401"
     * DateUtil.addYearMonthDay("20050331", 0, -1, 0)  = "20050228"
     * DateUtil.addYearMonthDay("20050301", 0, 2, 30)  = "20050531"
     * DateUtil.addYearMonthDay("20050301", 1, 2, 30)  = "20060531"
     * DateUtil.addYearMonthDay("20040301", 2, 0, 0)   = "20060301"
     * DateUtil.addYearMonthDay("20040229", 2, 0, 0)   = "20060228"
     * DateUtil.addYearMonthDay("20040229", 2, 0, 1)   = "20060301"
     * </pre>
     */
    public static String addYearMonthDay(String sDate, int year, int month, int day) {

    	String dateStr = validChkDate(sDate);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        try {
            cal.setTime(sdf.parse(dateStr));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }

        if (year != 0)
            cal.add(Calendar.YEAR, year);
        if (month != 0)
            cal.add(Calendar.MONTH, month);
        if (day != 0)
            cal.add(Calendar.DATE, day);
        return sdf.format(cal.getTime());
    }


    /**
     * 년 계산 : add Year
     * @methodName : addYear
     * @return     : String
     * @param dateStr
     * @param year
     * <pre>
     * DateUtil.addYear("20000201", 62)  = "20620201"
     * DateUtil.addYear("20620201", -62) = "20000201"
     * DateUtil.addYear("20040229", 2)   = "20060228"
     * DateUtil.addYear("20060228", -2)  = "20040228"
     * DateUtil.addYear("19000101", 200) = "21000101"
     * </pre>
     */
    public static String addYear(String dateStr, int year) {
        return addYearMonthDay(dateStr, year, 0, 0);
    }


    /**
     * 월계산 : add Month
     * @methodName : addMonth
     * @return     : String
     * @param dateStr
     * @param month
     * <pre>
     * DateUtil.addMonth("20010201", 12)  = "20020201"
     * DateUtil.addMonth("19800229", 12)  = "19810228"
     * DateUtil.addMonth("20040229", 12)  = "20050228"
     * DateUtil.addMonth("20050228", -12) = "20040228"
     * DateUtil.addMonth("20060131", 1)   = "20060228"
     * DateUtil.addMonth("20060228", -1)  = "20060128"
     * </pre>
     */
    public static String addMonth(String dateStr, int month) {
        return addYearMonthDay(dateStr, 0, month, 0);
    }

    /** 
     * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 일(day)를
     * 증감한다. <code>day</code>는 가감할 수를 의미하며, 음수를 입력할 경우 감한다.
     * <br/><br/>
     * 위에 정의된 addDays 메서드는 사용자가 ParseException을 반드시 처리해야 하는 불편함이
     * 있기 때문에 추가된 메서드이다.</p>
     *
     * <pre>
     * DateUtil.addDay("19991201", 62) = "20000201"
     * DateUtil.addDay("20000201", -62) = "19991201"
     * DateUtil.addDay("20050831", 3) = "20050903"
     * DateUtil.addDay("20050831", 3) = "20050903"
     * // 2006년 6월 31일은 실제로 존재하지 않는 날짜이다 -> 20060701로 간주된다
     * DateUtil.addDay("20060631", 1) = "20060702"
     * </pre>
     *
     * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
     * @param  day 가감할 일. 0이 입력될 경우 가감이 없다
     * @return  yyyyMMdd 형식의 날짜 문자열
     * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우.
     *         입력 값이 <code>null</code>인 경우.
     */
    /**
     * 일 계산 : add Day
     * @methodName : addDay
     * @return     : String
     * @param dateStr
     * @param day
     * <pre>
     * DateUtil.addDay("19991201", 62) = "20000201"
     * DateUtil.addDay("20000201", -62) = "19991201"
     * DateUtil.addDay("20050831", 3) = "20050903"
     * DateUtil.addDay("20050831", 3) = "20050903"
     * // 2006년 6월 31일은 실제로 존재하지 않는 날짜이다 -> 20060701로 간주된다
     * DateUtil.addDay("20060631", 1) = "20060702"
     * </pre>
     */
    public static String addDay(String dateStr, int day) {
        return addYearMonthDay(dateStr, 0, 0, day);
    }

    /**
     * 일자 차이 구하기 : difference between
     * @methodName : getDaysDiff
     * @return     : int
     * @param sDate1
     * @param sDate2
     * <pre>
     * DateUtil.getDaysDiff("20060228","20060310") = 10
     * DateUtil.getDaysDiff("20060101","20070101") = 365
     * DateUtil.getDaysDiff("19990228","19990131") = -28
     * DateUtil.getDaysDiff("20060801","20060802") = 1
     * DateUtil.getDaysDiff("20060801","20060801") = 0
     * </pre>
     */
    public static int getDaysDiff(String sDate1, String sDate2) {
    	String dateStr1 = validChkDate(sDate1);
    	String dateStr2 = validChkDate(sDate2);

        if (!checkDate(sDate1) || !checkDate(sDate2)) {
            throw new IllegalArgumentException("Invalid date format: args[0]=" + sDate1 + " args[1]=" + sDate2);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());

        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdf.parse(dateStr1);
            date2 = sdf.parse(dateStr2);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: args[0]=" + dateStr1 + " args[1]=" + dateStr2);
        }
        int days1 = (int)((date1.getTime()/3600000)/24);
        int days2 = (int)((date2.getTime()/3600000)/24);

        return days2 - days1;
    }


    /**
     * 유효한 날짜인지 체크 : check date
     * @methodName : checkDate
     * @return     : boolean
     * @param sDate
     * <pre>
     * DateUtil.checkDate("1999-02-35") = false
     * DateUtil.checkDate("2000-13-31") = false
     * DateUtil.checkDate("2006-11-31") = false
     * DateUtil.checkDate("2006-2-28")  = false
     * DateUtil.checkDate("2006-2-8")   = false
     * DateUtil.checkDate("20060228")   = true
     * DateUtil.checkDate("2006-02-28") = true
     * </pre>
     */
    public static boolean checkDate(String sDate) {
    	String dateStr = validChkDate(sDate);

        String year  = dateStr.substring(0,4);
        String month = dateStr.substring(4,6);
        String day   = dateStr.substring(6);

        return checkDate(year, month, day);
    }


    /**
     * 유효한 날짜인지 체크 : check date
     * @methodName : checkDate
     * @return     : boolean
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static boolean checkDate(String year, String month, String day) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());

            Date result = formatter.parse(year + "." + month + "." + day);
            String resultStr = formatter.format(result);
            if (resultStr.equalsIgnoreCase(year + "." + month + "." + day))
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 날짜 포맷을 변경 : convert Date
     * @methodName : convertDate
     * @return     : String
     * @param strSource
     * @param fromDateFormat
     * @param toDateFormat
     * @param strTimeZone
     */
    public static String convertDate(String strSource, String fromDateFormat,
            String toDateFormat, String strTimeZone) {
        SimpleDateFormat simpledateformat = null;
        Date date = null;
        String strFromDateFormat = "";
        String strToDateFormat = "";

        if(StringUtil.isNullToString(strSource).trim().equals("")) {
            return "";
        }
        if(StringUtil.isNullToString(fromDateFormat).trim().equals(""))
        	strFromDateFormat = "yyyyMMddHHmmss";                    // default값
        if(StringUtil.isNullToString(toDateFormat).trim().equals(""))
        	strToDateFormat = "yyyy-MM-dd HH:mm:ss";                 // default값

        try {
        	simpledateformat = new SimpleDateFormat(strFromDateFormat, Locale.getDefault());
            date = simpledateformat.parse(strSource);
            if (!StringUtil.isNullToString(strTimeZone).trim().equals("")) {
                simpledateformat.setTimeZone(TimeZone.getTimeZone(strTimeZone));
            }
            simpledateformat = new SimpleDateFormat(strToDateFormat, Locale.getDefault());
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
        return simpledateformat.format(date);

    }


     /**
     * 오늘 날짜 포멧 변경 : Date Format(today)
     * @methodName : formatDate
     * @return     : String
     * @param ch
     */
    public static String formatDate(String ch) {
     	return formatDate(getToday(), ch);
     }

    /**
     * 입력 날짜 포멧 변경 : Date Format(sDate)
     * @methodName : formatDate
     * @return     : String
     * @param sDate
     * @param ch
     */
    public static String formatDate(String sDate, String ch) {
    	String dateStr = validChkDate(sDate);

        String str = dateStr.trim();
        String yyyy = "";
        String mm = "";
        String dd = "";

        if (str.length() == 8) {
            yyyy = str.substring(0, 4);
            if (yyyy.equals("0000"))
                return "";

            mm = str.substring(4, 6);
            if (mm.equals("00"))
                return yyyy;

            dd = str.substring(6, 8);
            if (dd.equals("00"))
                return yyyy + ch + mm;

            return yyyy + ch + mm + ch + dd;
        } else if (str.length() == 6) {
            yyyy = str.substring(0, 4);
            if (yyyy.equals("0000"))
                return "";

            mm = str.substring(4, 6);
            if (mm.equals("00"))
                return yyyy;

            return yyyy + ch + mm;
        } else if (str.length() == 4) {
            yyyy = str.substring(0, 4);
            if (yyyy.equals("0000"))
                return "";
            else
                return yyyy;
        } else
            return "";
    }

     /**
     * 시간문자열을 포멧 변경 : change Time format(HH24MISS)
     * @methodName : formatTime
     * @return     : String
     * @param sTime
     * @param ch
     */
    public static String formatTime(String sTime, String ch) {
     	String timeStr = validChkTime(sTime);
        return timeStr.substring(0, 2) + ch + timeStr.substring(2, 4) + ch + timeStr.substring(4, 6);
     }

     /**
      *
      * <p>연도를 입력 받아 해당 연도 2월의 말일(일수)를 문자열로 반환한다.</p>
      *
      * @param year
      * @return 해당 연도 2월의 말일(일수)
      */
    
     /**
     * 입력 년도 2월달 말일 계산 : leap Year(February end date)
     * @methodName : leapYear
     * @return     : String
     * @param year
     */
    public String leapYear(int year) {
         if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
             return "29";
         }
         return "28";
     }


     /**
     * 윤년 여부 : leap Year
     * @methodName : isLeapYear
     * @return     : boolean
     * @param year
     */
    public static boolean isLeapYear(int year) {
         if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
             return false;
         }
         return true;
     }



     /**
     * 현재 날짜(한국기준) : get Today
     * @methodName : getToday
     * @return     : String
     */
    public static String getToday(){
         return getCurrentDate("");
     }

     /**
     * 입력 dateType의 현재 날짜(한국기준) : get Today(format dateType)
     * @methodName : getCurrentDate
     * @return     : String
     * @param dateType
     */
    public static String getCurrentDate(String dateType) {
         Calendar aCalendar = Calendar.getInstance();

         int year = aCalendar.get(Calendar.YEAR);
         int month = aCalendar.get(Calendar.MONTH) + 1;
         int date = aCalendar.get(Calendar.DATE);
         String strDate = Integer.toString(year) +
                 ((month<10) ? "0" + Integer.toString(month) : Integer.toString(month)) +
                 ((date<10) ? "0" + Integer.toString(date) : Integer.toString(date));

         if(!"".equals(dateType)) strDate = convertDate(strDate, "yyyyMMdd", dateType);

         return  strDate;
     }


    /**
     * 지정한 날짜/시간을 지정한 포맷으로 출력 : convert Date
     * @methodName : convertDate
     * @return     : String
     * @param sDate
     * @param sTime
     * @param sFormatStr
	 * @See Letter  Date or Time Component  Presentation  Examples
	           G  Era designator  Text  AD
	           y  Year  Year  1996; 96
	           M  Month in year  Month  July; Jul; 07
	           w  Week in year  Number  27
	           W  Week in month  Number  2
	           D  Day in year  Number  189
	           d  Day in month  Number  10
	           F  Day of week in month  Number  2
	           E  Day in week  Text  Tuesday; Tue
	           a  Am/pm marker  Text  PM
	           H  Hour in day (0-23)  Number  0
	           k  Hour in day (1-24)  Number  24
	           K  Hour in am/pm (0-11)  Number  0
	           h  Hour in am/pm (1-12)  Number  12
	           m  Minute in hour  Number  30
	           s  Second in minute  Number  55
	           S  Millisecond  Number  978
	           z  Time zone  General time zone  Pacific Standard Time; PST; GMT-08:00
	           Z  Time zone  RFC 822 time zone  -0800

	           Date and Time Pattern  Result
	           "yyyy.MM.dd G 'at' HH:mm:ss z"  2001.07.04 AD at 12:08:56 PDT
	           "EEE, MMM d, ''yy"  Wed, Jul 4, '01
	           "h:mm a"  12:08 PM
	           "hh 'o''clock' a, zzzz"  12 o'clock PM, Pacific Daylight Time
	           "K:mm a, z"  0:08 PM, PDT
	           "yyyyy.MMMMM.dd GGG hh:mm aaa"  02001.July.04 AD 12:08 PM
	           "EEE, d MMM yyyy HH:mm:ss Z"  Wed, 4 Jul 2001 12:08:56 -0700
	           "yyMMddHHmmssZ"  010704120856-0700

     */
    public static String convertDate(String sDate, String sTime, String sFormatStr) {
    	String dateStr = validChkDate(sDate);
    	String timeStr = validChkTime(sTime);

    	Calendar cal = null;
    	cal = Calendar.getInstance() ;

    	cal.set(Calendar.YEAR        , Integer.parseInt(dateStr.substring(0,4)));
    	cal.set(Calendar.MONTH       , Integer.parseInt(dateStr.substring(4,6))-1 );
    	cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(6,8)));
    	cal.set(Calendar.HOUR_OF_DAY , Integer.parseInt(timeStr.substring(0,2)));
    	cal.set(Calendar.MINUTE      , Integer.parseInt(timeStr.substring(2,4)));

    	SimpleDateFormat sdf = new SimpleDateFormat(sFormatStr,Locale.ENGLISH);

    	return sdf.format(cal.getTime());
    }
    

    /**
     * 숫자타입을 데이트 타입으로 변환하는 기능 : number -> date
     * @methodName : convertDate
     * @return     : String
     * @param srcNumber
     * @return
     */
    public static String convertDate(int srcNumber) {

		String pattern = null;
		String cnvrStr = null;
	
		String srcStr = String.valueOf(srcNumber);
	
		// Date 형태인 8자리 및 14자리만 정상처리 
		if (srcStr.length() != 8 && srcStr.length() != 14) {
		    throw new IllegalArgumentException("Invalid Number: " + srcStr + " Length=" + srcStr.trim().length());
		}
	
		if (srcStr.length() == 8) {
		    pattern = "yyyyMMdd";
		} else if (srcStr.length() == 14) {
		    pattern = "yyyyMMddhhmmss";
		}
	
		SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern, Locale.KOREA);
	
		Date cnvrDate = null;
	
		try {
		    cnvrDate = dateFormatter.parse(srcStr);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	
		cnvrStr = String.format("%1$tY-%1$tm-%1$td", cnvrDate);
	
		return cnvrStr;

    }	

    /**
     * 입력받은 일자 사이의 임의의 일자를 반환 : get Random Date
     * @methodName : getRandomDate
     * @return     : String
     * @param sDate1
     * @param sDate2
     * @return
     */
    public static String getRandomDate(String sDate1, String sDate2) {
    	String dateStr1 = validChkDate(sDate1);
    	String dateStr2 = validChkDate(sDate2);

    	String randomDate   = null;

    	int sYear, sMonth, sDay;
    	int eYear, eMonth, eDay;

    	sYear  = Integer.parseInt(dateStr1.substring(0, 4));
    	sMonth = Integer.parseInt(dateStr1.substring(4, 6));
    	sDay   = Integer.parseInt(dateStr1.substring(6, 8));

    	eYear  = Integer.parseInt(dateStr2.substring(0, 4));
    	eMonth = Integer.parseInt(dateStr2.substring(4, 6));
    	eDay   = Integer.parseInt(dateStr2.substring(6, 8));

    	GregorianCalendar beginDate = new GregorianCalendar(sYear, sMonth-1, sDay,    0, 0);
    	GregorianCalendar endDate   = new GregorianCalendar(eYear, eMonth-1, eDay,   23,59);

    	if (endDate.getTimeInMillis() < beginDate.getTimeInMillis()) {
    	    throw new IllegalArgumentException("Invalid input date : " + sDate1 + "~" + sDate2);
    	}

    	SecureRandom r = new SecureRandom();

    	long rand = ((r.nextLong()>>>1)%( endDate.getTimeInMillis()-beginDate.getTimeInMillis() + 1)) + beginDate.getTimeInMillis();

    	GregorianCalendar cal = new GregorianCalendar();
    	//SimpleDateFormat calformat = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat calformat = new SimpleDateFormat("yyyyMMdd",Locale.ENGLISH);
    	cal.setTimeInMillis(rand);
    	randomDate = calformat.format(cal.getTime());

    	// 랜덤문자열를 리턴
    	return  randomDate;
    }

  
    /**
     * 양력 -> 음력 : Lift -> Lunar
     * @methodName : toLunar
     * @return     : HashMap
     * @param sDate
     * @return
     */
    public static HashMap toLunar(String sDate) {
    	String dateStr = validChkDate(sDate);

		HashMap hm = new HashMap();
		hm.put("day", "");
		hm.put("leap", 0);

		if(dateStr.length() != 8) {
			return hm;
		}

		Calendar cal ;
		ChineseCalendar lcal ;

		cal = Calendar.getInstance() ;
		lcal = new ChineseCalendar();

		cal.set(Calendar.YEAR        , Integer.parseInt(dateStr.substring(0,4)));
		cal.set(Calendar.MONTH       , Integer.parseInt(dateStr.substring(4,6))-1 );
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(6,8)));

		lcal.setTimeInMillis(cal.getTimeInMillis());

		String year  = String.valueOf(lcal.get(ChineseCalendar.EXTENDED_YEAR) - 2637);
		String month = String.valueOf(lcal.get(ChineseCalendar.MONTH        ) + 1   );
		String day   = String.valueOf(lcal.get(ChineseCalendar.DAY_OF_MONTH )       );
		String leap  = String.valueOf(lcal.get(ChineseCalendar.IS_LEAP_MONTH)       );

		String pad4Str = "0000";
		String pad2Str = "00";

		String retYear  = (pad4Str + year ) .substring(year .length());
		String retMonth = (pad2Str + month) .substring(month.length());
		String retDay   = (pad2Str + day  ) .substring(day  .length());

		String sDay = retYear+retMonth+retDay;

		hm.put("day", sDay);
		hm.put("leap", leap);

		return hm;
	}

    /**
     * <p>입력받은 음력일자를 변환하여 양력일자로 반환한다.</p>
     * @param sDate 음력일자
     * @param iLeapMonth 음력윤달여부(IS_LEAP_MONTH)
     * @return 양력일자
     */
	/**
     * 음력 -> 양력 : Lunar -> Lift
	 * @methodName : toSolar
	 * @return     : String
	 * @param sDate
	 * @param iLeapMonth
	 */
	public static String toSolar(String sDate, int iLeapMonth) {
    	String dateStr = validChkDate(sDate);

    	Calendar cal ;
		ChineseCalendar lcal ;

		cal = Calendar.getInstance() ;
		lcal = new ChineseCalendar();


		lcal.set(ChineseCalendar.EXTENDED_YEAR, Integer.parseInt(dateStr.substring(0,4)) + 2637);
		lcal.set(ChineseCalendar.MONTH        , Integer.parseInt(dateStr.substring(4,6)) - 1);
		lcal.set(ChineseCalendar.DAY_OF_MONTH , Integer.parseInt(dateStr.substring(6,8)));
		lcal.set(ChineseCalendar.IS_LEAP_MONTH, iLeapMonth);

		cal.setTimeInMillis(lcal.getTimeInMillis());

		String year  = String.valueOf(cal.get(Calendar.YEAR        )    );
		String month = String.valueOf(cal.get(Calendar.MONTH       ) + 1);
		String day   = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)    );

		String pad4Str = "0000";
		String pad2Str = "00";

		String retYear  = (pad4Str + year ).substring(year .length());
		String retMonth = (pad2Str + month).substring(month.length());
		String retDay   = (pad2Str + day  ).substring(day  .length());

		return retYear+retMonth+retDay;
	}


	/**
	 * 영문요일 -> 국문요일 : convert day of the week 
	 * @methodName : convertWeek
	 * @return     : String
	 * @param sWeek
	 */
	public static String convertWeek(String sWeek) {
		String retStr = null;

		if        (sWeek.equals("SUN")   ) { retStr = "일요일";
		} else if (sWeek.equals("MON")   ) { retStr = "월요일";
		} else if (sWeek.equals("TUE")   ) { retStr = "화요일";
		} else if (sWeek.equals("WED")   ) { retStr = "수요일";
		} else if (sWeek.equals("THR")   ) { retStr = "목요일";
		} else if (sWeek.equals("FRI")   ) { retStr = "금요일";
		} else if (sWeek.equals("SAT")   ) { retStr = "토요일";
		}

		return retStr;
	}

    /**
     * 일자 유효 체크 :  valid Date
     * @methodName : validDate
     * @return     : boolean
     * @param sDate
     */
    public static boolean validDate(String sDate) {
    	String dateStr = validChkDate(sDate);

		Calendar cal ;
		boolean ret  = false;

		cal = Calendar.getInstance() ;

		cal.set(Calendar.YEAR        , Integer.parseInt(dateStr.substring(0,4)));
		cal.set(Calendar.MONTH       , Integer.parseInt(dateStr.substring(4,6))-1 );
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(6,8)));

		String year  = String.valueOf(cal.get(Calendar.YEAR        )    );
		String month = String.valueOf(cal.get(Calendar.MONTH       ) + 1);
		String day   = String.valueOf(cal.get(Calendar.DAY_OF_MONTH)    );

		String pad4Str = "0000";
		String pad2Str = "00";

		String retYear  = (pad4Str + year ).substring(year .length());
		String retMonth = (pad2Str + month).substring(month.length());
		String retDay   = (pad2Str + day  ).substring(day  .length());

		String retYMD = retYear+retMonth+retDay;

		if(sDate.equals(retYMD)) {
			ret  = true;
		}

		return ret;
	}


    /**
     * 입력일자, 요일 유효 체크 :  valid Date
     * @methodName : validDate
     * @return     : boolean
     * @param sDate
     * @param sWeek
     */
    public static boolean validDate(String sDate, int sWeek) {
    	String dateStr = validChkDate(sDate);

		Calendar cal ;
		boolean ret  = false;

		cal = Calendar.getInstance() ;

		cal.set(Calendar.YEAR        , Integer.parseInt(dateStr.substring(0,4)));
		cal.set(Calendar.MONTH       , Integer.parseInt(dateStr.substring(4,6))-1 );
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateStr.substring(6,8)));

		int    week  =                cal.get(Calendar.DAY_OF_WEEK      );

		if (validDate(sDate)) {
			if (sWeek == week) {
				ret = true;
			}
		}

		return ret;
	}


    /**
     * 입력시간의 유효 여부 : valid Time
     * @methodName : validTime
     * @return     : boolean
     * @param sTime
     */
    public static boolean validTime(String sTime) {
    	String timeStr = validChkTime(sTime);

		Calendar cal ;
		boolean ret = false;

		cal = Calendar.getInstance() ;

		cal.set(Calendar.HOUR_OF_DAY  , Integer.parseInt(timeStr.substring(0,2)));
		cal.set(Calendar.MINUTE       , Integer.parseInt(timeStr.substring(2,4)));

		String hh     = String.valueOf(cal.get(Calendar.HOUR_OF_DAY  ));
		String mm     = String.valueOf(cal.get(Calendar.MINUTE       ));

		String pad2Str = "00";

		String retHH = (pad2Str + hh).substring(hh.length());
		String retMM = (pad2Str + mm).substring(mm.length());

		String retTime = retHH + retMM;

		if(sTime.equals(retTime)) {
			ret  = true;
		}

		return ret;
	}

    /**
     * <p>입력된 일자에 연, 월, 일을 가감한 날짜의 요일을 반환한다.</p>
     * @param sDate 날짜
     * @param year 연
     * @param month 월
     * @param day 일
     * @return 계산된 일자의 요일(DAY_OF_WEEK)
     */
    
    
    /**
     * 입력된 일자에 연, 월, 일을 가감한 날짜의 요일을 반환 : add year,month,day to Week
     * @methodName : addYMDtoWeek
     * @return     : String
     * @param sDate
     * @param year
     * @param month
     * @param day
     */
    public static String addYMDtoWeek(String sDate, int year, int month, int day) {
    	String dateStr = validChkDate(sDate);

		dateStr = addYearMonthDay(dateStr, year, month, day);

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",Locale.ENGLISH);
		try {
			cal.setTime(sdf.parse(dateStr));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format: " + dateStr);
		}

		SimpleDateFormat rsdf = new SimpleDateFormat("E",Locale.ENGLISH);

		return rsdf.format(cal.getTime());
	}

    /**
     * 입력된 일자에 연, 월, 일, 시간, 분을 가감한 날짜, 시간을 포멧스트링 형식으로 반환 : subtraction days(hours, year, month, date, Time) 
     * @methodName : addYMDtoDayTime
     * @return     : String
     * @param sDate
     * @param sTime
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param formatStr
     */
    public static String addYMDtoDayTime(String sDate, String sTime, int year, int month, int day, int hour, int minute, String formatStr) {
    	String dateStr = validChkDate(sDate);
    	String timeStr = validChkTime(sTime);

		dateStr = addYearMonthDay(dateStr, year, month, day);

		dateStr = convertDate(dateStr, timeStr, "yyyyMMddHHmm");

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm",Locale.ENGLISH);

        try {
    		cal.setTime(sdf.parse(dateStr));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }

		if (hour != 0) {
			cal.add(Calendar.HOUR, hour);
		}

		if (minute != 0) {
			cal.add(Calendar.MINUTE, minute);
		}

		SimpleDateFormat rsdf = new SimpleDateFormat(formatStr,Locale.ENGLISH);

		return rsdf.format(cal.getTime());
	}


    /**
     * 입력된 일자를 int 형으로 반환 : date to Int
     * @methodName : datetoInt
     * @return     : int
     * @param sDate
     */
    public static int datetoInt(String sDate) {
    	return Integer.parseInt(convertDate(sDate, "0000", "yyyyMMdd"));
    }


    /**
     * 입력된 시간을 int 형으로 반환 : time to Int
     * @methodName : timetoInt
     * @return     : int
     * @param sTime
     */
    public static int timetoInt(String sTime) {
        return Integer.parseInt(convertDate("00000101", sTime, "HHmm"));
    }


    /**
     * 입력된 일자 문자열을 확인하고 8자리로 리턴 : valid check and 8-digit date return
     * @methodName : validChkDate
     * @return     : String
     * @param dateStr
     */
    public static String validChkDate(String dateStr) {
    	String mdateStr = dateStr;

        if (dateStr == null || !(dateStr.trim().length() == 8 || dateStr.trim().length() == 10)) {
            throw new IllegalArgumentException("Invalid date format: " + dateStr);
        }
        if (dateStr.length() == 10) {
        	mdateStr = StringUtil.remove(dateStr, '/');
        }
        return mdateStr;
    }


    /**
     * 입력된 시간 문자열을 확인하고4자리로 리턴 :  valid check and 4-digit time return
     * @methodName : validChkTime
     * @return     : String
     * @param timeStr
     * @return
     */
    public static String validChkTime(String timeStr) {
    	String mtimeStr = timeStr;

    	if (mtimeStr.length() == 5) {
    		mtimeStr = StringUtil.remove(mtimeStr,':');
    	}
    	if (mtimeStr == null || !(mtimeStr.trim().length() == 4)) {
    	    throw new IllegalArgumentException("Invalid time format: " + mtimeStr);
    	}

    	return mtimeStr;
    }



    /**
     * String 날짜 포멧 변경 : Date format changes
     * @methodName : formatStrDate
     * @return     : String
     * @param strDate
     * @param format
     */
    public static String formatStrDate(String strDate, String format){
        String tmpYear = "";
        String tmpMonth = "";
        String tmpDay = "";
        if(strDate.length()>6){
            tmpYear = strDate.substring(0, 4);
            tmpMonth = strDate.substring(4, 6);
            tmpDay = strDate.substring(6, 8);
        }else if(format.equals("yy.mm") ||format.equals("yyyy.mm") ){
            tmpYear = strDate.substring(0, 4);
            tmpMonth = strDate.substring(4, 6);
        }else if(format.equals("mm.dd")){
            tmpMonth = strDate.substring(0, 2);
            tmpDay = strDate.substring(2, 4);
        }

        StringBuffer rstValue = new StringBuffer();
        if(format == null || format.equals(""))
            return null;
        else{
            if(format.equals("yyyy.mm.dd"))
                rstValue.append(tmpYear).append(".").append(tmpMonth).append(".").append(tmpDay);
            else if(format.equals("yyyy/mm/dd"))
                rstValue.append(tmpYear).append("/").append(tmpMonth).append("/").append(tmpDay);
            else if(format.equals("yyyy-mm-dd"))
                rstValue.append(tmpYear).append("-").append(tmpMonth).append("-").append(tmpDay);
            else if(format.equals("yyyy년mm월dd일"))
                rstValue.append(tmpYear).append("년").append(tmpMonth).append("월").append(tmpDay).append("일");
            else if(format.equals("yyyy년 mm월 dd일"))
                rstValue.append(tmpYear).append("년 ").append(tmpMonth).append("월 ").append(tmpDay).append("일");
            else if(format.equals("yy.mm.dd"))
                rstValue.append(tmpYear.substring(2, 4)).append(".").append(tmpMonth).append(".").append(tmpDay);
            else if(format.equals("yyyy.mm"))
                rstValue.append(tmpYear).append(".").append(tmpMonth);
            else if(format.equals("yy.mm"))
                rstValue.append(tmpYear.substring(2, 4)).append(".").append(tmpMonth);
            else if(format.equals("mm.dd"))
                rstValue.append(tmpMonth).append(".").append(tmpDay);
            else
                rstValue.append(tmpYear).append(".").append(tmpMonth).append(".").append(tmpDay);
            return rstValue.toString();
        }
    }


    /**
     * 시간 포멧 변경 : Time format changes
     * @methodName : formatStrTime
     * @return     : String
     * @param strTime
     * @param format
     * <pre>format 예 : 'HH:MM:SS'</pre>
     */
    public static String formatStrTime(String strTime, String format){
        String tmpHour = strTime.substring(0, 2);
        String tmpMinite = strTime.substring(2, 4);
        String tmpSecond = strTime.substring(4, 6);
        StringBuffer rstValue = new StringBuffer();
        if(format == null || format.equals(""))
            return null;
        else{
            if(format.equals("HH:MM:SS"))
                rstValue.append(tmpHour).append(":").append(tmpMinite).append(":").append(tmpSecond);
            else
                rstValue.append(tmpHour).append(":").append(tmpMinite).append(":").append(tmpSecond);
            return rstValue.toString();
        }
    }


    /**
     * 문자 -> 년도 : get Year From string
     * @methodName : getYearFromStr
     * @return     : Date
     * @param sDate
     * @return
     */
    public static Date getYearFromStr(String sDate) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR        , Integer.parseInt(sDate.substring(0,4)));
		cal.set(Calendar.MONTH       , 0);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		cal.set(Calendar.HOUR_OF_DAY , 0);
    	cal.set(Calendar.MINUTE      , 0);
    	cal.set(Calendar.SECOND      , 0);
    	cal.set(Calendar.MILLISECOND  , 0);

		return new Date(cal.getTimeInMillis());
	}
    

    /**
     * string -> date : get Date from string (Month 201405)
     * @methodName : getMonthFromStr
     * @return     : Date
     * @param sDate
     */
    public static Date getMonthFromStr(String sDate) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR        , Integer.parseInt(sDate.substring(0,4)));
		cal.set(Calendar.MONTH       , Integer.parseInt(sDate.substring(4,6))-1 );
		cal.set(Calendar.DAY_OF_MONTH, 0);
		cal.set(Calendar.HOUR_OF_DAY , 0);
    	cal.set(Calendar.MINUTE      , 0);
    	cal.set(Calendar.SECOND      , 0);
    	cal.set(Calendar.MILLISECOND  , 0);

		return new Date(cal.getTimeInMillis());
	}

    /**
     * string -> date : get Date from string (Day 20130123)
     * @methodName : getDayFromStr
     * @return     : Date
     * @param sDate
     * @return
     */
    public static Date getDayFromStr(String sDate) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR        , Integer.parseInt(sDate.substring(0,4)));
		cal.set(Calendar.MONTH       , Integer.parseInt(sDate.substring(4,6))-1 );
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sDate.substring(6,8)));
		cal.set(Calendar.HOUR_OF_DAY , 0);
    	cal.set(Calendar.MINUTE      , 0);
    	cal.set(Calendar.SECOND      , 0);
    	cal.set(Calendar.MILLISECOND  , 0);

		return new Date(cal.getTimeInMillis());
	}
    
 
    /**
     * string -> date : get Date from string (Hour 2014012322)
     * @methodName : getHourFromStr
     * @return     : Date
     * @param sDate
     * @return
     */
    public static Date getHourFromStr(String sDate) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR        , Integer.parseInt(sDate.substring(0,4)));
		cal.set(Calendar.MONTH       , Integer.parseInt(sDate.substring(4,6))-1 );
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sDate.substring(6,8)));
    	cal.set(Calendar.HOUR_OF_DAY , Integer.parseInt(sDate.substring(8,10)));
    	cal.set(Calendar.MINUTE      , 0);
    	cal.set(Calendar.SECOND      , 0);
    	cal.set(Calendar.MILLISECOND  , 0);

    	return new Date(cal.getTimeInMillis());
	}

   
    /**
     * string -> date : get Date from string (Minute 201005231200)
     * @methodName : getMinuteFromStr
     * @return     : Date
     * @param sDate
     */
    public static Date getMinuteFromStr(String sDate) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR        , Integer.parseInt(sDate.substring(0,4)));
		cal.set(Calendar.MONTH       , Integer.parseInt(sDate.substring(4,6))-1 );
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sDate.substring(6,8)));
    	cal.set(Calendar.HOUR_OF_DAY , Integer.parseInt(sDate.substring(8,10)));
    	cal.set(Calendar.MINUTE      , Integer.parseInt(sDate.substring(10,12)));
    	cal.set(Calendar.SECOND      , 0);
    	cal.set(Calendar.MILLISECOND  , 0);

    	return new Date(cal.getTimeInMillis());
	}
    

    /**
     * string -> date : get Date from string (Second 20100523120012)
     * @methodName : getSecondFromStr
     * @return     : Date
     * @param sDate
     */
    public static Date getSecondFromStr(String sDate) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR        , Integer.parseInt(sDate.substring(0,4)));
		cal.set(Calendar.MONTH       , Integer.parseInt(sDate.substring(4,6))-1 );
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sDate.substring(6,8)));
    	cal.set(Calendar.HOUR_OF_DAY , Integer.parseInt(sDate.substring(8,10)));
    	cal.set(Calendar.MINUTE      , Integer.parseInt(sDate.substring(10,12)));
    	cal.set(Calendar.SECOND      , Integer.parseInt(sDate.substring(12,14)));
    	cal.set(Calendar.MILLISECOND  , 0);

    	return new Date(cal.getTimeInMillis());
	}
    
  
    /**
     * string -> date : get Date from string (MilliSecond 20100523120012121)
     * @methodName : getMilliSecondFromStr
     * @return     : Date
     * @param sDate
     * @return
     */
    public static Date getMilliSecondFromStr(String sDate) {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR        , Integer.parseInt(sDate.substring(0,4)));
		cal.set(Calendar.MONTH       , Integer.parseInt(sDate.substring(4,6))-1 );
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sDate.substring(6,8)));
    	cal.set(Calendar.HOUR_OF_DAY , Integer.parseInt(sDate.substring(8,10)));
    	cal.set(Calendar.MINUTE      , Integer.parseInt(sDate.substring(10,12)));
    	cal.set(Calendar.SECOND      , Integer.parseInt(sDate.substring(12,14)));
    	cal.set(Calendar.MILLISECOND  , Integer.parseInt(sDate.substring(12,17)));

    	return new Date(cal.getTimeInMillis());
	}


    /**
     * string -> date : get Date from string (day 2010/05/23)
     * @methodName : getMilliSecondFromStr
     * @return     : Date
     * @param sDate
     */
    public static Date getDayFromCalStr(String sDate) {

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR        , Integer.parseInt(sDate.substring(0,4)));
		cal.set(Calendar.MONTH       , Integer.parseInt(sDate.substring(5,7))-1 );
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sDate.substring(8,10)));

		return new Date(cal.getTimeInMillis());
	}


    /**
     * string -> date : get Date from string (Time 'HH:MM:SS HH:MI')
     * @methodName : getMilliSecondFromStr
     * @return     : Date
     * @param sDate
     */
    public static Date getMinuteFromCalStr(String sDate) {

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR        , Integer.parseInt(sDate.substring(0,4)));
    	cal.set(Calendar.MONTH       , Integer.parseInt(sDate.substring(5,7))-1 );
    	cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sDate.substring(8,10)));
    	cal.set(Calendar.HOUR_OF_DAY , Integer.parseInt(sDate.substring(11,13)));
    	cal.set(Calendar.MINUTE      , Integer.parseInt(sDate.substring(14,16)));

    	return new Date(cal.getTimeInMillis());
	}

    /**
     * <p>현재 Date를 스트링으로 리턴/p>
     * <pre>format 예 : 'yyyy-MM-dd'</pre>
     * @param strTime 시간
     * @param format 변경시간Format
     * @return
     */
    /**
     * 현재 Date를 스트링으로 리턴 : get Current Time(format)
     * @methodName : getCurrentTime
     * @return     : String
     * @param format
     * @return
     */
    public static String getCurrentTime(String format) {

    	Date date = new Date();
        TimeZone.setDefault(null);

    	SimpleDateFormat fmt = new SimpleDateFormat(format , Locale.getDefault());
    	return fmt.format(date);

    }

    public static void main(String[] args) {
		DateUtil util = new DateUtil();
		System.out.println(util.getCurrentTime("yyyyMMdd")+":"+util.getCurrentTime("hhmmss"));
	}

    /**
     * 현재 시간을  yyyy,mm,dd 형식으로 리턴 : get Current Date
     * @methodName : getCurrentDate
     * @return     : String
     */
    public static String getCurrentDate(){

    	Calendar oCalendar = Calendar.getInstance( );


    	String[] monthsArray = new String[] {"January", "February", "March", "April", "May", "June", "July", "August","September", "October", "November", "December"};

    	int year = oCalendar.get(Calendar.YEAR);
  	    int month = oCalendar.get(Calendar.MONTH);
  	    int day = oCalendar.get(Calendar.DAY_OF_MONTH);
  	    int hour = oCalendar.get(Calendar.HOUR_OF_DAY);
  	    int minute = oCalendar.get(Calendar.MINUTE);
  	    int second = oCalendar.get(Calendar.SECOND);



  	    String strDate =

  	    	monthsArray[month] + " " +
  	    	Integer.toString(day) + ", " + 	Integer.toString(year) + " " +
  	    	((hour<10) ? "0" + Integer.toString(hour) : Integer.toString(hour)) + ":" +
  	    	((minute<10) ? "0" + Integer.toString(minute) : Integer.toString(minute)) + ":" +
  	    	((second<10) ? "0" + Integer.toString(second) : Integer.toString(second));


  	    return strDate;


    }


}

