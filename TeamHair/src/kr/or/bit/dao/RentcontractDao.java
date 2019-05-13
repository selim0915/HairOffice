package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.bit.dto.BranchDto;
import kr.or.bit.dto.RentContractDto;
import kr.or.bit.utils.TeamConvert;

public class RentcontractDao {
	
	DataSource ds = null;

	public RentcontractDao() throws Exception {
		Context context = new InitialContext(); // 이름기반 검색
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle"); /// jdbc/oracle pool 검색
	}
	
	//Rentcontract 데이터 삽입
		public int insertBranch(RentContractDto dto) {
			int row = 0;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			String sql = "INSERT INTO RENTCONTRACT(UserID, SpaceID, StartDate, EndDate, RentID, MonthlyRental, DiscountAmount, CreateDate, UpdateDate, DeposIt, PayMethod)\n" + 
					     "VALUES(?,?,?,?,?,?,?,?,?,?,?)\n" ;
			
			try {
				conn = ds.getConnection();
				//
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, dto.getUserId());
				pstmt.setInt(2, dto.getSpaceId());
				pstmt.setTimestamp(3, TeamConvert.dateFromUtitlToTimestamp(dto.getStartDate()));
				pstmt.setTimestamp(4, TeamConvert.dateFromUtitlToTimestamp(dto.getEndDate()));
				pstmt.setInt(5, dto.getRentId());
				pstmt.setDouble(6, dto.getMonthlyrental());
				pstmt.setDouble(7, dto.getDiscountAmount());
				pstmt.setDate(8, TeamConvert.dateFromUtilToSql(dto.getCreateDate()));
				pstmt.setDate(9, TeamConvert.dateFromUtilToSql(dto.getUpdateDate()));
				pstmt.setDouble(10, dto.getDeposit());
				pstmt.setString(11, dto.getPayMethod());
				
				row=pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {pstmt.close();} catch (Exception e){};
				try {conn.close();} catch (Exception e){};
			}
			
			return row;
		}

		public List<RentContractDto> getBranchList () {
			List<RentContractDto> dtoList = new ArrayList<RentContractDto>();
			
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "SELECT UserID, SpaceID, StartDate, EndDate, RentID, MonthlyRental, DiscountAmount, CreateDate, UpdateDate, DeposIt, PayMethod FROM RENTCONTRACT";
			try {
				conn = ds.getConnection();
				//
				pstmt = conn.prepareStatement(sql);
							
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					RentContractDto dto = new RentContractDto();
					dto.setUserId(rs.getString("userid"));
					dto.setSpaceId(rs.getInt("spaceid"));
					dto.setStartDate(rs.getDate("startdate"));
					dto.setEndDate(rs.getDate("enddate"));
					dto.setRentId(rs.getInt("rentid"));
					dto.setMonthlyrental(rs.getInt("monthlyrental"));
					dto.setDiscountAmount(rs.getInt("discountamout"));
					dto.setCreateDate(rs.getDate("createdate"));
					dto.setUpdateDate(rs.getDate("updatedate"));
					dto.setDeposit(rs.getInt("deposit"));
					dto.setPayMethod(rs.getString("paymethod"));
					System.out.println(dto.toString());
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
}
