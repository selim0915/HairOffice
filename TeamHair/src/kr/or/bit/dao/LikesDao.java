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
import kr.or.bit.dto.LikeListDto;
import kr.or.bit.dto.LikePhotoListDto;
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
			
			pstmt.setInt(1, dto.getPhotoId());
			pstmt.setString(2, dto.getUserId());
			pstmt.setString(3, dto.getLikeYn().toUpperCase());
			pstmt.setString(4, dto.getWasUser());

			row = pstmt.executeUpdate();

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


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e){};
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return dtoList;
		}
		
		public List<LikesDto> getLikesListByUserid(String userid) {
			
			
			List<LikesDto> dtoList = new ArrayList<LikesDto>();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "SELECT PHOTOID, USERID, LIKEYN, WASUSER FROM LIKES WHERE USERID = ?"; 
					
			try {
				conn = ds.getConnection();
				//
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, userid);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					LikesDto dto = new LikesDto();
					
					dto.setPhotoId(rs.getInt("photoid"));
					dto.setUserId(rs.getString("userid"));
					dto.setLikeYn(rs.getString("likeyn"));
					dto.setWasUser(rs.getString("wasuser"));
					
					dtoList.add(dto);

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
		
		public int getLikeNumberByPhotoId(int photoId) {
			int likeNumber = 0;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = " SELECT SUM(DECODE(L.LIKEYN,'Y',1,'N',0)) AS LIKECOUNT \r\n" + 
 						 " FROM PHOTO P JOIN LIKES L   \r\n" + 
						 " ON P.PHOTOID = L.PHOTOID    \r\n" + 
						 " AND P.PHOTOID = ?           \r\n";
					
					
			try {
				conn = ds.getConnection();
				//
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, photoId);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
		            do {
		            	likeNumber = rs.getInt("likecount");
		            } while (rs.next());
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {rs.close();} catch (Exception e){};
				try {pstmt.close();} catch (Exception e){};
				try {conn.close();} catch (Exception e){};
			}
			
			return likeNumber;
		}
		
		public List<LikeListDto> getLikeNumberListByUserId(String userId) {
			List<LikeListDto> dtoList = new ArrayList<LikeListDto>();

			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = " SELECT SUM(DECODE(L.LIKEYN,'Y',1,'N',0)) AS LIKECOUNT, P.PHOTOID  \r\n" + 
					     " FROM PHOTO P JOIN LIKES L    \r\n" + 
					     " ON P.PHOTOID = L.PHOTOID     \r\n" + 
					     " AND P.USERID = ?             \r\n" + 
					     " GROUP BY P.PHOTOID 			\r\n" +	
						 " ORDER BY PHOTOID DESC	    \r\n";
					
					
			try {
				conn = ds.getConnection();
	
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, userId);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
		            do {
		            	LikeListDto dto = new LikeListDto();
		            	
		            	dto.setLikeCount(rs.getInt("likecount"));
		            	dto.setPhotoId(rs.getInt("photoid"));
		            	
		            	dtoList.add(dto);
		            } while (rs.next());
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
		
		public List<LikePhotoListDto> getLikePhotoList() {
			List<LikePhotoListDto> dtoList = new ArrayList<LikePhotoListDto>();

			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = " SELECT SUM(DECODE(L.LIKEYN,'Y',1,'N',0)) AS LIKECOUNT, P.PHOTOID,  P.FILENAME, P.USERID  \r\n" + 
					     " FROM PHOTO P JOIN LIKES L    \r\n" + 
					     " ON P.PHOTOID = L.PHOTOID     \r\n" + 
					     " GROUP BY P.PHOTOID, P.FILENAME, P.USERID 			\r\n" +	
						 " ORDER BY LIKECOUNT DESC	    \r\n";
					
					
			try {
				conn = ds.getConnection();
				//
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
		            do {
		            	LikePhotoListDto dto = new LikePhotoListDto();
		            	
		            	dto.setLikecount(rs.getInt("likecount"));
		            	dto.setPhotoid(rs.getInt("photoid"));
		            	dto.setFilename(rs.getString("filename"));
		            	dto.setUserid(rs.getString("userid"));
		            	
		            	dtoList.add(dto);
		            } while (rs.next());
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
		
		public int updateLikes(LikesDto dto) {
			int row = 0;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			String sql = "update likes set likeyn = ? where userid = ? and photoid = ?"; 		
			
			try {
				conn = ds.getConnection();
				//
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, dto.getLikeYn());
				pstmt.setString(2, dto.getUserId());
				pstmt.setInt(3, dto.getPhotoId());
				
				row=pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {pstmt.close();} catch (Exception e){};
				try {conn.close();} catch (Exception e){};
			}
			
			return row;
		}
		
		public int deleteLikes(int photoid) {
			int row = 0;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "DELETE FROM LIKES WHERE PHOTOID=?";	
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, photoid);
				row = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {pstmt.close();} catch (Exception e){};
				try {conn.close();} catch (Exception e){};
			}
			
			return row;
		}
		
	}
