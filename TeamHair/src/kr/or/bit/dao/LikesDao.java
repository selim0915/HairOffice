package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.bit.dto.CodeDto;
import kr.or.bit.dto.LikesDto;
import kr.or.bit.dto.ProfileDto;

public class LikesDao {
	
	DataSource ds = null;

	public LikesDao() throws Exception {
		Context context = new InitialContext(); // 이름기반 검색
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle"); /// jdbc/oracle pool 검색
	}
	
	public List<LikesDto> getLikesList () {
		
		
		List<LikesDto> dtoList = new ArrayList<LikesDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT PHOTOID, USERID, LIKEYN, WASUSER FROM LIKES"; 
				
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LikesDto dto = new LikesDto();
				
				dto.setPhotoId(rs.getInt("photoid"));
				dto.setUserId(rs.getString("userid"));
				dto.setLikeYn(rs.getString("likeyn"));
				dto.setWasUser(rs.getString("wasuser"));
				
				dtoList.add(dto);
				System.out.println("select 들어옴");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e){};
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return dtoList;
	}
	
	public int insertlikes(LikesDto dto) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = " INSERT INTO LIKES(PHOTOID, USERID, LIKEYN, WASUSER)\r\n" + 
				     " VALUES(?, ?, ?, ?)\r\n" ; 
		
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("여기 들어옴?");
			
			pstmt.setInt(1, dto.getPhotoId());
			pstmt.setString(2, dto.getUserId());
			pstmt.setString(3, dto.getLikeYn());
			pstmt.setString(4, dto.getWasUser());

			row = pstmt.executeUpdate();
			System.out.println("여기여기");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}
	
		public LikesDto getLikesListByPhotoidUserid (int photoid, String userid) {
		
		
		LikesDto dtoList = new LikesDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT PHOTOID, USERID, LIKEYN, WASUSER FROM LIKES WHERE PHOTOID = ? AND USERID = ?"; 
				
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, photoid);
			pstmt.setString(2, userid);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
	            do {
	            	dtoList = new LikesDto();
	            	dtoList.setPhotoId(rs.getInt("photoid"));
	            	dtoList.setUserId(rs.getString("userid"));
	            	dtoList.setLikeYn(rs.getString("likeyn"));
	            	dtoList.setWasUser(rs.getString("wasuser"));
	            } while (rs.next());
			}

				System.out.println("select 들어옴");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e){};
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return dtoList;
		}
	}
