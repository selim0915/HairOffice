package kr.or.bit.test;

import kr.or.bit.utils.TeamDate;
import kr.or.bit.utils.TeamFormat;

public class TestTeamDate {
	public static void main(String[] args) {
		//System.out.println(TeamDate.datetime(2019, 05, 12, 15, 20));
		System.out.println(TeamFormat.dateTimeFormat(TeamDate.datetime(2019, 05, 12, 15, 20)));
	}
}
