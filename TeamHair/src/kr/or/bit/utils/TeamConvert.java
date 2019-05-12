package kr.or.bit.utils;

import java.sql.Date;
import java.sql.Timestamp;

public class TeamConvert {
	
	public static String checkToYn(String str) {
		String yn="";
		if(str==null) {
			yn="N";
		} else if(str.trim().equals("on")) {
			yn="Y";
		}
		return yn;
	}
	
	// convert java.util.Date to java.sql.Date
	
	public static java.sql.Date dateFromUtilToSql(java.util.Date utilDate){
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
	
	// convert java.util.Date to java.sql.Timestamp
	
	public static java.sql.Timestamp dateFromUtitlToTimestamp(java.util.Date utilDate){
		Timestamp ts = new Timestamp(utilDate.getTime());
		return ts;
	}
	
}
