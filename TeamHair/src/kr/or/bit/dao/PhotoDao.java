package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.bit.dto.PhotoDto;
import kr.or.bit.dto.ProfileDto;

public class PhotoDao {
	
	DataSource ds = null;

	public PhotoDao() throws Exception {
		Context context = new InitialContext(); 
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}

	//게시글 전체 조회
	public List<PhotoDto> selectPhotoAllList (String userid) {
		
		List<PhotoDto> dtoList = new ArrayList<PhotoDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM PHOTO WHERE USERID=? ORDER BY PHOTOID DESC"; 

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				PhotoDto dto = new PhotoDto();
				dto.setPhotoId(rs.getInt("photoId"));
				dto.setFileName(rs.getString("fileName"));
				dto.setDescription(rs.getString("description"));
				dto.setCreateDate(rs.getDate("createDate"));
				dto.setUpdateDate(rs.getDate("updateDate"));
				dto.setUserId(rs.getString("userId"));
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
	
	//게시글 쓰기
	public int insertPhoto(PhotoDto dto) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PHOTO(PHOTOID, FILENAME, DESCRIPTION, CREATEDATE, UPDATEDATE, USERID) " + 
				"VALUES(ID_SEQ.NEXTVAL, ?, ?, SYSDATE, SYSDATE, ?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFileName());
			pstmt.setString(2, dto.getDescription());
			pstmt.setString(3, dto.getUserId());
			row=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		return row;
	}
	
	//게시글 수정
	public int updatePhoto(PhotoDto dto) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql ="UPDATE PHOTO SET FILENAME=?, DESCRIPTION=? WHERE USERID=? AND PHOTOID=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFileName());
			pstmt.setString(2, dto.getDescription());
			pstmt.setString(3, dto.getUserId());
			pstmt.setInt(4, dto.getPhotoId());
			row=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		return row;
	}
	
	//게시글 삭제
	public int deletePhoto(String userid, int photoid) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM PHOTO WHERE USERID=? AND PHOTOID=?";	
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, photoid);
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}
	
	public int deletePhoto(int photoid) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM PHOTO WHERE PHOTOID=?";	
		
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
	
	//게시글상세보기(likes, comments 조인)
	public PhotoDto selectPhotoById (String userid, int photoid) {
		PhotoDto dto = new PhotoDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM PHOTO WHERE USERID=? AND PHOTOID=? ORDER BY PHOTOID DESC"; 
				
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, photoid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setPhotoId(rs.getInt("photoId"));
				dto.setFileName(rs.getString("fileName"));
				dto.setDescription(rs.getString("description"));
				dto.setCreateDate(rs.getDate("createDate"));
				dto.setUpdateDate(rs.getDate("updateDate"));
				dto.setUserId(rs.getString("userid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e){};
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		return dto;
	}
	
	public PhotoDto selectPhotoById (int photoid) {
		PhotoDto dto = new PhotoDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM PHOTO WHERE PHOTOID=? ORDER BY PHOTOID DESC"; 
				
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, photoid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setPhotoId(rs.getInt("photoId"));
				dto.setFileName(rs.getString("fileName"));
				dto.setDescription(rs.getString("description"));
				dto.setCreateDate(rs.getDate("createDate"));
				dto.setUpdateDate(rs.getDate("updateDate"));
				dto.setUserId(rs.getString("userid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rs.close();} catch (Exception e){};
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		return dto;
	}
	
}
