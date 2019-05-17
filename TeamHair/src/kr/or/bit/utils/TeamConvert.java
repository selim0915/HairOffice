package kr.or.bit.utils;

import java.sql.Date;
import java.sql.Timestamp;

public class TeamConvert {

	public static String checkToYn(String str) {
		String yn = "";
		if (str == null) {
			yn = "N";
		} else if (str.trim().equals("on")) {
			yn = "Y";
		}
		return yn;
	}

	public static java.sql.Date dateFromUtilToSql(java.util.Date utilDate) {
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}

	public static java.sql.Timestamp dateFromUtitlToTimestamp(java.util.Date utilDate) {
		Timestamp ts = new Timestamp(utilDate.getTime());
		return ts;
	}

}
