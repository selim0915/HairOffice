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
import kr.or.bit.dto.UsersDto;
import kr.or.bit.utils.TeamConvert;

public class RentContractDao {

	DataSource ds = null;

	public RentContractDao() throws Exception {
		Context context = new InitialContext(); 
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle"); 
	}

	//RentContract 데이터 삽입
	public int insertRentContract(RentContractDto dto) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = " INSERT INTO RENTCONTRACT(RENTID, USERID, SPACEID, STARTDATE, ENDDATE, DEPOSIT, MONTHLYRENTAL, DISCOUNTAMOUNT, PAYMETHOD, CREATEDATE, UPDATEDATE) \r\n" + 
				     " VALUES(ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE) \r\n"; 
				
		
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getUserId());
			pstmt.setInt(2, dto.getSpaceId());
			pstmt.setDate(3, TeamConvert.dateFromUtilToSql(dto.getStartDate())); 
			pstmt.setDate(4, TeamConvert.dateFromUtilToSql(dto.getEndDate()));
			pstmt.setDouble(5, dto.getDeposit());
			pstmt.setDouble(6, dto.getMonthlyrental());
			pstmt.setDouble(7, dto.getDiscountAmount());
			pstmt.setString(8, dto.getPayMethod());
			
			row=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}
	
	public RentContractDto getRentContractbyRentId (int rentId) {
		
		RentContractDto dto = new RentContractDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT RENTID, USERID, SPACEID, STARTDATE, ENDDATE, DEPOSIT, MONTHLYRENTAL, DISCOUNTAMOUNT, PAYMETHOD, CREATEDATE, UPDATEDATE \r\n" + 
				     " FROM RENTCONTRACT WHERE RENTID = ? \r\n" ;
		
		try {
			conn = ds.getConnection();
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rentId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setRentId(rs.getInt("rentid"));
				dto.setUserId(rs.getString("userid"));
				dto.setSpaceId(rs.getInt("spaceid"));
				dto.setStartDate(rs.getDate("startdate"));
				dto.setEndDate(rs.getDate("startdate"));
				dto.setDeposit(rs.getDouble("deposit"));
				dto.setMonthlyrental(rs.getDouble("monthlyrental"));
				dto.setDiscountAmount(rs.getDouble("discountamount"));
				dto.setPayMethod(rs.getString("paymethod"));
				dto.setCreateDate(rs.getDate("createdate"));
				dto.setUpdateDate(rs.getDate("updatedate"));
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

	public RentContractDto getRentContractbyCondition (int rentId, String userId, int spaceId) {
		
		RentContractDto dto = new RentContractDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT RENTID, USERID, SPACEID, STARTDATE, ENDDATE, DEPOSIT, MONTHLYRENTAL, DISCOUNTAMOUNT, PAYMETHOD, CREATEDATE, UPDATEDATE \r\n" + 
				     " FROM RENTCONTRACT WHERE RENTID = ? AND USERID = ? AND SPACEID = ? \r\n" ;
		
		try {
			conn = ds.getConnection();
		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rentId);
			pstmt.setString(2, userId);
			pstmt.setInt(3, spaceId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setRentId(rs.getInt("rentid"));
				dto.setUserId(rs.getString("userid"));
				dto.setSpaceId(rs.getInt("spaceid"));
				dto.setStartDate(rs.getDate("startdate"));
				dto.setEndDate(rs.getDate("startdate"));
				dto.setDeposit(rs.getDouble("deposit"));
				dto.setMonthlyrental(rs.getDouble("monthlyrental"));
				dto.setDiscountAmount(rs.getDouble("discountamount"));
				dto.setPayMethod(rs.getString("paymethod"));
				dto.setCreateDate(rs.getDate("createdate"));
				dto.setUpdateDate(rs.getDate("updatedate"));
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


	public List<RentContractDto> getRentContractList () {
		
		List<RentContractDto> dtoList = new ArrayList<RentContractDto>();
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql =  " SELECT RENTID, USERID, SPACEID, STARTDATE, ENDDATE, DEPOSIT, MONTHLYRENTAL, DISCOUNTAMOUNT, PAYMETHOD, CREATEDATE, UPDATEDATE \r\n" + 
			          " FROM RENTCONTRACT \r\n";

		try {
			conn = ds.getConnection();
		
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RentContractDto dto = new RentContractDto();

				dto.setRentId(rs.getInt("rentid"));
				dto.setUserId(rs.getString("userid"));
				dto.setSpaceId(rs.getInt("spaceid"));
				dto.setStartDate(rs.getDate("startdate"));
				dto.setEndDate(rs.getDate("startdate"));
				dto.setDeposit(rs.getDouble("deposit"));
				dto.setMonthlyrental(rs.getDouble("monthlyrental"));
				dto.setDiscountAmount(rs.getDouble("discountamount"));
				dto.setPayMethod(rs.getString("paymethod"));
				dto.setCreateDate(rs.getDate("createdate"));
				dto.setUpdateDate(rs.getDate("updatedate"));
					
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

	
	public int updateRentContract(RentContractDto dto) {
		
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql =" UPDATE RENTCONTRACT \r\n" + 
				    "  SET STARTDATE = ?, \r\n" + 
				    "      ENDDATE = ?, \r\n" + 
				    "      DEPOSIT = ?, \r\n" + 
				    "      MONTHLYRENTAL = ?, \r\n" + 
				    "      DISCOUNTAMOUNT = ?, \r\n" + 
				    "      PAYMETHOD = ?, \r\n" + 
				    "      UPDATEDATE = SYSDATE \r\n" + 
				    " WHERE RENTID = ? \r\n" ; 
				 
		
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDate(1, TeamConvert.dateFromUtilToSql(dto.getStartDate()));
			pstmt.setDate(2, TeamConvert.dateFromUtilToSql(dto.getEndDate()));
			pstmt.setDouble(3, dto.getDeposit());
			pstmt.setDouble(4, dto.getMonthlyrental());
			pstmt.setDouble(5, dto.getDiscountAmount());
			pstmt.setString(6, dto.getPayMethod());
			pstmt.setInt(7, dto.getRentId());
			
			row=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}

	
	public int deleteRentContract(int rentid) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM RENTCONTRACT WHERE RENTID = ? ";	
		
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rentid);
			
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
