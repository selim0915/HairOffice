package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.bit.dto.QnACommentsDto;
import kr.or.bit.dto.QnADto;

public class QnADao {
	DataSource ds = null;
	
	public QnADao() throws NamingException{
		Context context = new InitialContext(); 
		 ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	
	public int insertQnA(QnADto qna) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into qna(boardid, boardsubject, boardcontent, filename, replyref, replydepth, replyseq, createdate, updatedate, readcount, notice, userid)"
								+ "values(BOARD_ID_SEQ.NEXTVAL,?,?,?,0,0,1,SYSDATE,SYSDATE,0,null,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qna.getBoardSubject());
			pstmt.setString(2, qna.getBoardContent());
			pstmt.setString(3, qna.getFileName());
			pstmt.setString(4, qna.getUserID());
			
			row = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			 if(pstmt != null)try{pstmt.close();}catch (Exception e){e.printStackTrace();}
			 if(conn != null) try{conn.close();}catch (Exception e){e.printStackTrace();}  //반환
		}

		return row;
	}
	
	//전체리스트보기
	public List<QnADto> QnAlist(int cpage, int pagesize) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<QnADto> qnalist = new ArrayList<QnADto>();
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM (SELECT ROWNUM AS RN, BOARDID, USERID, BOARDSUBJECT, READCOUNT, CREATEDATE, REPLYREF, REPLYDEPTH, REPLYSEQ " + 
					"FROM (select * from qna start with replyref = 0 connect by prior boardid = replyref order siblings by boardid DESC)) " + 
					" WHERE RN BETWEEN ? AND ? ";

			int start = cpage * pagesize -(pagesize-1);
			int end = cpage*pagesize;

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				QnADto dto = new QnADto();
				
				dto.setBoardID(rs.getInt("BoardID"));
				dto.setUserID(rs.getString("UserID"));
				dto.setBoardSubject(rs.getString("BoardSubject"));
				dto.setReadCount(rs.getInt("readcount"));
				dto.setCreateDate(rs.getDate("CreateDate"));
				dto.setReplyDepth(rs.getInt("replyDepth"));
				
				qnalist.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			 if(pstmt != null)try{pstmt.close();}catch (Exception e){e.printStackTrace();}
			 if(conn != null) try{conn.close();}catch (Exception e){e.printStackTrace();}  //반환
		}

		return qnalist;
	}
	
	//게시물 총 건수 구하기
		public int totalboardCount(){
			int totalNum=0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = ds.getConnection();
				String sql = "SELECT COUNT(*) AS CNT FROM QNA";

				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					totalNum = rs.getInt("cnt");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally {
				 if(pstmt != null)try{pstmt.close();}catch (Exception e){e.printStackTrace();}
				 if(conn != null) try{conn.close();}catch (Exception e){e.printStackTrace();}  //반환
			}

			return totalNum;
		}

	//글 상세보기
	public QnADto searchQnA(int BoardID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		QnADto qna = new QnADto();
		
		try {
			conn = ds.getConnection();
			String sql = "select BoardID, ReadCount, BoardSubject, BoardContent, CreateDate, UpdateDate, UserID, FileName, replyref, replydepth, replyseq from qna where BoardID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,BoardID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
	            do {
	            	qna.setBoardID(rs.getInt("BoardID"));
	            	qna.setReadCount(rs.getInt("ReadCount"));
	            	qna.setBoardSubject(rs.getString("boardSubject"));
	            	qna.setBoardContent(rs.getString("boardContent"));
	            	qna.setUserID(rs.getString("userID"));
	            	qna.setCreateDate(rs.getDate("createDate"));
	            	qna.setUpdateDate(rs.getDate("updateDate"));
	            	qna.setFileName(rs.getString("fileName"));
	            	qna.setReplyRef(rs.getInt("replyref"));
	            	qna.setReplyDepth(rs.getInt("replyDepth"));
	            	qna.setReplySeq(rs.getInt("replyseq"));
	            } while (rs.next());
	        } else {
	            System.out.println("데이터가 없습니다.");
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			 if(pstmt != null)try{pstmt.close();}catch (Exception e){e.printStackTrace();}
			 if(conn != null) try{conn.close();}catch (Exception e){e.printStackTrace();}  //반환
		}

		return qna;
	}
	
	//글 삭제하기
	public int deleteQnA(int boardid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();

			String sql = "DELETE FROM qna WHERE boardid=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardid);
			
			row = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			 if(pstmt != null)try{pstmt.close();}catch (Exception e){e.printStackTrace();}
			 if(conn != null) try{conn.close();}catch (Exception e){e.printStackTrace();}  //반환
		}
		return row;
	}
	
	//글 수정하기
	public int updateQnA(QnADto qna) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "update qna set boardsubject=?, boardcontent=?, filename=?, readcount=? where boardID=?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qna.getBoardSubject());
			pstmt.setString(2, qna.getBoardContent());
			pstmt.setString(3, qna.getFileName());
			pstmt.setInt(4, qna.getReadCount());
			pstmt.setInt(5, qna.getBoardID());
			
			row = pstmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			 if(pstmt != null)try{pstmt.close();}catch (Exception e){e.printStackTrace();}
			 if(conn != null) try{conn.close();}catch (Exception e){e.printStackTrace();}  //반환
		}

		return row;
	}
	
	
	//댓글리스트보기
	public List<QnACommentsDto> QnACommentslist(int boardid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<QnACommentsDto> qnacommentslist = new ArrayList<QnACommentsDto>();
		
		try {
				conn = ds.getConnection();
				
				String sql = "select CommentID, Comments, CreateDate, updateDate, BoardID, userID from QnAComments where BoardID=? order by commentid";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, boardid);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					QnACommentsDto q = new QnACommentsDto();
					
					q.setCommentID(rs.getInt("CommentID"));
					q.setComments(rs.getString("Comments"));
					q.setCreateDate(rs.getDate("CreateDate"));
					q.setUpdateDate(rs.getDate("UpdateDate"));
					q.setUserID(rs.getString("userID"));
					
					qnacommentslist.add(q);
				}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			 if(pstmt != null)try{pstmt.close();}catch (Exception e){e.printStackTrace();}
			 if(conn != null) try{conn.close();}catch (Exception e){e.printStackTrace();}
		}

		return qnacommentslist;
	}
	
	
	//댓글 추가
	public int insertQnAComments(QnACommentsDto com) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "insert into qnacomments(CommentID, Comments, CreateDate, UpdateDate, BoardID, UserID) values(COMMENT_SEQ.NEXTVAL,?,SYSDATE,SYSDATE,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, com.getComments());
			pstmt.setInt(2, com.getBoardID());
			pstmt.setString(3, com.getUserID());
			
			row = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			 if(pstmt != null)try{pstmt.close();}catch (Exception e){e.printStackTrace();}
			 if(conn != null) try{conn.close();}catch (Exception e){e.printStackTrace();}  //반환
		}

		return row;
	}
	
	//댓글 삭제
	public int deleteQnAComments(int commentid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "delete from qnacomments where commentid=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, commentid);
			
			row = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			 if(pstmt != null)try{pstmt.close();}catch (Exception e){e.printStackTrace();}
			 if(conn != null) try{conn.close();}catch (Exception e){e.printStackTrace();}  //반환
		}

		return row;
	}
	
	
	
	//답글추가
	public int insertQnAreply(QnADto qna, int ref, int seq, int depth) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "insert into qna(boardid, boardsubject, boardcontent, filename, replyref, replydepth, replyseq, createdate, updatedate, readcount, notice, userid)"
								+ "values(BOARD_ID_SEQ.NEXTVAL,?,?,?,?,?,?,SYSDATE,SYSDATE,0,null,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qna.getBoardSubject());
			pstmt.setString(2, qna.getBoardContent());
			pstmt.setString(3, qna.getFileName());
			pstmt.setInt(4, ref);
			pstmt.setInt(5, depth+1);
			pstmt.setInt(6, seq+1);
			pstmt.setString(7, qna.getUserID());
			
			row = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			 if(pstmt != null)try{pstmt.close();}catch (Exception e){e.printStackTrace();}
			 if(conn != null) try{conn.close();}catch (Exception e){e.printStackTrace();}  //반환
		}

		return row;
	}
}
