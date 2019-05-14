package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.bit.dto.BranchDto;
import kr.or.bit.dto.FollowerDto;
import kr.or.bit.dto.FollowingDto;

public class FollowingFollowerDao {
	DataSource ds = null;

	public FollowingFollowerDao() throws Exception {
		Context context = new InitialContext(); // 이름기반 검색
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle"); /// jdbc/oracle pool 검색
	}
	
	//Branch 데이터 삽입
	public int addFollowingFollower(FollowingDto followingDto) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		String sql = " INSERT INTO FOLLOWING(FOLLOWINGID, USERID) \r\n" + 
				     " VALUES(?, ?) \r\n" ;  
				    
		String sql2 = " INSERT INTO FOLLOWER(FOLLOWERID, USERID) \r\n" + 
			          " VALUES(?, ?) \r\n" ;  
		
		
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, followingDto.getFollowingId());
			pstmt.setString(2, followingDto.getUserId());
			
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setString(1, followingDto.getUserId());
			pstmt2.setString(2, followingDto.getFollowingId());
			
			row =  pstmt.executeUpdate();
			row += pstmt2.executeUpdate();
			
			if(row==2) { 
				conn.commit();
				System.out.println("Commit");
			} else {
				conn.rollback();
				System.out.println("Rollback");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {conn.setAutoCommit(true);} catch (Exception e){};
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}
	
	public int getFollowingNumberByUserId (String userId) {
		BranchDto dto = new BranchDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int followingNumber = 0;
		
		String sql = " SELECT COUNT(FOLLOWINGID) AS COUNT FROM FOLLOWING WHERE USERID = ? ";
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				 followingNumber = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e){};
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return followingNumber;
	}

	public int getFollowerNumberByUserId (String userId) {
		BranchDto dto = new BranchDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int followerNumber = 0;
		
		String sql = " SELECT COUNT(FOLLOWERID) AS COUNT FROM FOLLOWER WHERE USERID = ? ";
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				followerNumber = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e){};
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return followerNumber;
	}


	public int deleteFollowingFollower(FollowingDto followingDto) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		String sql = " DELETE FROM FOLLOWING WHERE FOLLOWINGID = ? AND USERID = ? \r\n" ;  
				    
		String sql2 = " DELETE FROM FOLLOWER WHERE FOLLOWERID = ? AND USERID = ? \r\n" ; 
		
		
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, followingDto.getFollowingId());
			pstmt.setString(2, followingDto.getUserId());
			
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setString(1, followingDto.getUserId());
			pstmt2.setString(2, followingDto.getFollowingId());
			
			row =  pstmt.executeUpdate();
			row += pstmt2.executeUpdate();
			
			if(row==2) { 
				conn.commit();
				System.out.println("Commit");
			} else {
				conn.rollback();
				System.out.println("Rollback");
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {conn.setAutoCommit(true);} catch (Exception e){};
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}

}
