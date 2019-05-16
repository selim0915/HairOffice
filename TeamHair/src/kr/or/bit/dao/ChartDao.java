package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.bit.dto.ChartPhotoDto;
import kr.or.bit.dto.ChartUserDto;

public class ChartDao {
	DataSource ds = null;
	
	public ChartDao() throws NamingException{
		Context context = new InitialContext(); //이름기반 검색
		 ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle"); ///jdbc/oracle pool 검색
	}
	
	//유저 목록 얻어오기(차트)
	public List<ChartUserDto> userlist() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ChartUserDto> userlist = new ArrayList<ChartUserDto>();
		
		try {
			conn = ds.getConnection();
			String sql = "select * from (SELECT SUM(DECODE(L.LIKEYN,'Y',1,'N',0)) AS LIKECOUNT, P.USERID FROM PHOTO P JOIN LIKES L ON P.PHOTOID = L.PHOTOID GROUP BY P.USERID ORDER BY LIKECOUNT DESC) where rownum<=10"; 

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ChartUserDto dto = new ChartUserDto();
				dto.setUserid(rs.getString("USERID"));
				dto.setLikecount(rs.getInt("LIKECOUNT"));
				userlist.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			 if(pstmt != null)try{pstmt.close();}catch (Exception e){e.printStackTrace();}
			 if(conn != null) try{conn.close();}catch (Exception e){e.printStackTrace();}  //반환
		}
		return userlist;
	}
	
	
	
	//사진 목록 얻어오기(차트)
	public List<ChartPhotoDto> photolist() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<ChartPhotoDto> photolist = new ArrayList<ChartPhotoDto>();
		
		try {
			conn = ds.getConnection();
			String sql = " select * from " +
                    " (SELECT SUM(DECODE(L.LIKEYN,'Y',1,'N',0)) AS LIKECOUNT, P.PHOTOID " +
                    " FROM PHOTO P JOIN LIKES L ON P.PHOTOID = L.PHOTOID " +
                    " GROUP BY P.PHOTOID " +
                    " ORDER BY LIKECOUNT DESC) " +
                    " where rownum<=10";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ChartPhotoDto dto = new ChartPhotoDto();
				dto.setPhotoid(rs.getInt("PHOTOID"));
				dto.setLikecount(rs.getInt("LIKECOUNT"));
				photolist.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			 if(pstmt != null)try{pstmt.close();}catch (Exception e){e.printStackTrace();}
			 if(conn != null) try{conn.close();}catch (Exception e){e.printStackTrace();}  //반환
		}
		return photolist;
	}
}
