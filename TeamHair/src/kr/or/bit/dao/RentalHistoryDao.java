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
import kr.or.bit.dto.RentalHistoryDto;
import kr.or.bit.utils.TeamConvert;

public class RentalHistoryDao {

	DataSource ds = null;

	public RentalHistoryDao() throws Exception {
		Context context = new InitialContext(); 
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle"); 
	}

	//RentContract 데이터 삽입
	public int insertRentalHistory(RentalHistoryDto dto) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = " INSERT INTO RENTALHISTORY(RENTID, USERID, SPACEID, BASEDATE, RENTALREVENUE, DISCOUNT, PAYMETHOD) \r\n" + 
				     " VALUES(?, ?, ?, ?, ?, ?, ?) \r\n";  
				
		
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getRentId());
			pstmt.setString(2, dto.getUserId());
			pstmt.setInt(3, dto.getSpaceId());
			pstmt.setString(4, dto.getBaseDate());
			pstmt.setDouble(5, dto.getRentalRevenue());
			pstmt.setDouble(6, dto.getDiscount());
			pstmt.setString(7, dto.getPayMethod());
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}

	public RentalHistoryDto getRentalHistorybyCondition (int rentId, String userId, int spaceId, String basedate) {
		RentalHistoryDto dto = new RentalHistoryDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT RENTID, USERID, SPACEID, BASEDATE, RENTALREVENUE, DISCOUNT, PAYMETHOD \r\n" + 
				     " FROM RENTALHISTORY \r\n" + 
				     " WHERE RENTID = ? AND USERID = ?  AND SPACEID = ? AND BASEDATE = ? \r\n";
		
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rentId);
			pstmt.setString(2, userId);
			pstmt.setInt(3, spaceId);
			pstmt.setString(4, basedate);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setRentId(rs.getInt("rentid"));
				dto.setUserId(rs.getString("userid"));
				dto.setSpaceId(rs.getInt("spaceid"));
				dto.setBaseDate(rs.getString("basedate"));
				dto.setRentalRevenue(rs.getDouble("rentalrevenue"));
				dto.setDiscount(rs.getDouble("discount"));
				dto.setPayMethod(rs.getString("paymethod"));
				
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

	public List<RentalHistoryDto> getRentalHistoryList () {
		
		List<RentalHistoryDto> dtoList = new ArrayList<RentalHistoryDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql =  " SELECT RENTID, USERID, SPACEID, BASEDATE, RENTALREVENUE, DISCOUNT, PAYMETHOD \r\n" + 
				      " FROM RENTALHISTORY \r\n";

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RentalHistoryDto dto = new RentalHistoryDto();

				dto.setRentId(rs.getInt("rentid"));
				dto.setUserId(rs.getString("userid"));
				dto.setSpaceId(rs.getInt("spaceid"));
				dto.setBaseDate(rs.getString("basedate"));
				dto.setRentalRevenue(rs.getDouble("rentalrevenue"));
				dto.setDiscount(rs.getDouble("discount"));
				dto.setPayMethod(rs.getString("paymethod"));
					
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
	
	public int updateRentalHistory(RentalHistoryDto dto, int rentId, String userId, int spaceId, String basedate ) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = " UPDATE RENTALHISTORY \r\n" + 
				     " SET USERID = ?, \r\n" + 
				     "     SPACEID = ?, \r\n" + 
				     "     BASEDATE = ?, \r\n" + 
				     "     RENTALREVENUE = ?, \r\n" + 
				     "     DISCOUNT = ?, \r\n" + 
				     "     PAYMETHOD = ? \r\n" + 
				     " WHERE RENTID = ? AND USERID = ? AND SPACEID = ? AND BASEDATE = ?  \r\n" ; 
				 
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, dto.getUserId());
			pstmt.setInt(2, dto.getSpaceId());
			pstmt.setString(3, dto.getBaseDate());
			pstmt.setDouble(4, dto.getRentalRevenue());
			pstmt.setDouble(5, dto.getDiscount());
			pstmt.setString(6, dto.getPayMethod());
			
			pstmt.setInt(7, rentId);
			pstmt.setString(8, userId);
			pstmt.setInt(9, spaceId);
			pstmt.setString(10, basedate);
			
			row=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}



	public int deleteRentalHistory(int rentid, String userid, int spaceid, String basedate) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = " DELETE FROM RENTALHISTORY WHERE RENTID = ? AND USERID = ? AND SPACEID = ? AND BASEDATE = ? ";	
		
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rentid);
			pstmt.setString(2, userid);
			pstmt.setInt(3, spaceid);
			pstmt.setString(4, basedate);
			
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
