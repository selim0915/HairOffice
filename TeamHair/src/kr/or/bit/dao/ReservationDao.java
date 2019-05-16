package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.bit.dto.RentContractDto;
import kr.or.bit.dto.ReservationDto;
import kr.or.bit.utils.TeamConvert;

public class ReservationDao {

	DataSource ds = null;

	public ReservationDao() throws Exception {
		Context context = new InitialContext(); 
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle"); 
	}

	//RentContract 데이터 삽입
	public int insertReservation(ReservationDto dto) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = " INSERT INTO RESERVATION(RESERVEID, SERVICETYPE, STARTDATETIME, ENDDATETIME, CANCELYN, CREATEDATE, UPDATEDATE, USERID, SPACEID, PHOTOID) \r\n" + 
				     " VALUES(ID_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE, SYSDATE, ?, ?, ?) \r\n" ; 
				
				
		
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getServiceType());
			pstmt.setTimestamp(2, TeamConvert.dateFromUtitlToTimestamp(dto.getStartDateTime()));
			pstmt.setTimestamp(3, TeamConvert.dateFromUtitlToTimestamp(dto.getEndDateTime()));
			pstmt.setString(4, dto.getCancelYn());
			pstmt.setString(5, dto.getUserId());
			pstmt.setInt(6, dto.getSpaceId());
			pstmt.setInt(7, dto.getPhotoId());
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}

	public ReservationDto getReservationbyReserveId (int reserveId) {
		
		ReservationDto dto = new ReservationDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT RESERVEID, STARTDATETIME, ENDDATETIME, CANCELYN, CREATEDATE, UPDATEDATE, USERID, SPACEID, PHOTOID  \r\n" + 
					 " FROM RESERVATION WHERE RESERVEID = ? \r\n" ;
		
		try {
			conn = ds.getConnection();
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reserveId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setReserveId(rs.getInt("reserveid"));
				dto.setStartDateTime(rs.getTimestamp("startdatetime"));
				dto.setEndDateTime(rs.getTimestamp("enddatetime"));
				dto.setCancelYn(rs.getString("cancelyn"));
				dto.setCreateDate(rs.getDate("createdate"));
				dto.setUpdateDate(rs.getDate("updatedate"));
				dto.setUserId(rs.getString("userid"));
				dto.setSpaceId(rs.getInt("spaceid"));
				dto.setPhotoId(rs.getInt("photoid"));
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
	
	public List<ReservationDto> getReservationbyUserId (String userId) {
		
		List<ReservationDto> dtoList = new ArrayList<ReservationDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT RESERVEID, STARTDATETIME, ENDDATETIME, CANCELYN, CREATEDATE, UPDATEDATE, USERID, SPACEID, PHOTOID  \r\n" + 
				     " FROM RESERVATION \r\n" +
				     " WHERE USERID = ? \r\n";

		try {
			conn = ds.getConnection();
		
			pstmt = conn.prepareStatement(sql);	
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReservationDto dto = new ReservationDto();

				dto.setReserveId(rs.getInt("reserveid"));
				dto.setStartDateTime(rs.getTimestamp("startdatetime"));
				dto.setEndDateTime(rs.getTimestamp("enddatetime"));
				dto.setCancelYn(rs.getString("cancelyn"));
				dto.setCreateDate(rs.getDate("createdate"));
				dto.setUpdateDate(rs.getDate("updatedate"));
				dto.setUserId(rs.getString("userid"));
				dto.setSpaceId(rs.getInt("spaceid"));
				dto.setPhotoId(rs.getInt("photoid"));
					
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


	public List<ReservationDto> getReservationList () {
		
		List<ReservationDto> dtoList = new ArrayList<ReservationDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT RESERVEID, STARTDATETIME, ENDDATETIME, CANCELYN, CREATEDATE, UPDATEDATE, USERID, SPACEID, PHOTOID  \r\n" + 
				     " FROM RESERVATION \r\n" ;

		try {
			conn = ds.getConnection();
		
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReservationDto dto = new ReservationDto();

				dto.setReserveId(rs.getInt("reserveid"));
				dto.setStartDateTime(rs.getTimestamp("startdatetime"));
				dto.setEndDateTime(rs.getTimestamp("enddatetime"));
				dto.setCancelYn(rs.getString("cancelyn"));
				dto.setCreateDate(rs.getDate("createdate"));
				dto.setUpdateDate(rs.getDate("updatedate"));
				dto.setUserId(rs.getString("userid"));
				dto.setSpaceId(rs.getInt("spaceid"));
				dto.setPhotoId(rs.getInt("photoid"));
					
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

	
	public int updateReservation(ReservationDto dto) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql =" UPDATE RESERVATION  \r\n" + 
					"    SET STARTDATETIME = ?, \r\n" + 
					"        ENDDATETIME = ?, \r\n" + 
					"        CANCELYN = ?, \r\n" + 
					"        UPDATEDATE = SYSDATE \r\n" + 
					" WHERE RESERVEID = ?  \r\n"; 
		
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setTimestamp(1, TeamConvert.dateFromUtitlToTimestamp(dto.getStartDateTime()));
			pstmt.setTimestamp(2, TeamConvert.dateFromUtitlToTimestamp(dto.getEndDateTime()));
			pstmt.setString(3, dto.getCancelYn());
			pstmt.setInt(4, dto.getReserveId());
			
			row=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}


	public int deleteReservation(int reserveId) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM RESERVATION WHERE RESERVEID = ? ";	
		
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reserveId);
			
			row=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}

	
}
