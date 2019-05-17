package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.bit.dto.CommentsDto;

public class CommentsDao{

	DataSource ds = null;

	public CommentsDao() throws Exception {
		Context context = new InitialContext(); // 이름기반 검색
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle"); /// jdbc/oracle pool 검색
	}
	
	//게시글 전체 조회
		public List<CommentsDto> selectCommentsAllList (int photoid) {
			
			List<CommentsDto> dtoList = new ArrayList<CommentsDto>();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM COMMENTS WHERE PHOTOID=? ORDER BY PHOTOID DESC"; 

			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, photoid);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					CommentsDto dto = new CommentsDto();
					dto.setCommentId(rs.getInt("commentid"));
					dto.setComments(rs.getString("comments"));
					dto.setPhotoId(rs.getInt("photoid"));
					dto.setCreateDate(rs.getDate("createdate"));
					dto.setUpdateDate(rs.getDate("updatedate"));
					dto.setWasUser(rs.getString("wasuser"));
					dto.setUserId(rs.getString("userid"));
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
		public int insertComments(CommentsDto dto) {
		int row = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO COMMENTS(COMMENTID, COMMENTS, PHOTOID, CREATEDATE, UPDATEDATE, USERID) VALUES (ID_SEQ.NEXTVAL, ?, ?, SYSDATE, SYSDATE, ?)"; 

		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getComments());
			pstmt.setInt(2, dto.getPhotoId());
			pstmt.setString(3, dto.getUserId());

			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}
		
		public int deleteComments(int photoid) {
			int row = 0;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			String sql = "DELETE FROM COMMENTS WHERE PHOTOID=?";	
	
			
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
