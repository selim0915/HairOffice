package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.bit.dto.SearchSpaceDto;
import kr.or.bit.dto.SpaceDto;
import kr.or.bit.utils.TeamConvert;

public class SpaceDao {
	DataSource ds = null;

	public SpaceDao() throws Exception {
		Context context = new InitialContext(); // 이름기반 검색
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle"); /// jdbc/oracle pool 검색
	}

	//Users 데이터 삽입
	public int insertSpace(SpaceDto dto) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO Space(SpaceID, SpaceType, BranchID, SpaceName)\n" + 
				     "VALUES(?,?,?,?)\n" ;
		
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getSpaceId());
			pstmt.setString(2, dto.getSpaceType());
			pstmt.setInt(3, dto.getBranchId());
			pstmt.setString(4, dto.getSpaceName());
			
			
			row=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}
	
	public SpaceDto getSpaceById (int spaceID) {
		SpaceDto dto = new SpaceDto();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT SpaceID, SpaceType, BranchID, SpaceName FROM Space WHERE SpaceID=?";
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, spaceID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setSpaceId(rs.getInt("SpaceID"));
				dto.setSpaceType(rs.getString("SpaceType"));
				dto.setBranchId(rs.getInt("BranchID"));
				dto.setSpaceName(rs.getString("SpaceName"));
							
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

	public List<SpaceDto> getSpaceList () {
		List<SpaceDto> dtoList = new ArrayList<SpaceDto>();
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT SpaceID, SpaceType, BranchID, SpaceName FROM Space";
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SpaceDto dto = new SpaceDto();

				dto.setSpaceId(rs.getInt("SpaceID"));
				dto.setSpaceType(rs.getString("SpaceType"));
				dto.setBranchId(rs.getInt("BranchID"));
				dto.setSpaceName(rs.getString("SpaceName"));
				
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
	
	public List<SearchSpaceDto> getSpaceAvailableList (int numberOfPersons, Date startPeriod, Date endPeriod ) {
		List<SearchSpaceDto> dtoList = new ArrayList<SearchSpaceDto>();
		
		System.out.println("getSpaceAvailableList");
		System.out.println(numberOfPersons);
		System.out.println(startPeriod);
		System.out.println(endPeriod);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT B.BRANCHNAME, S.SPACEID, S.SPACENAME, C.CODE, C.CODENAME \r\n" + 
					" FROM SPACE S JOIN CODE C ON S.SPACETYPE = C.CODE         \r\n" + 
					"              JOIN BRANCH B ON S.BRANCHID = B.BRANCHID    \r\n" + 
					" WHERE S.MINNUMBER >= ?   \r\n" + 
					" AND S.SPACEID NOT IN (   \r\n" + 
					"   SELECT SPACEID         \r\n" + 
					"   FROM RENTCONTRACT      \r\n" + 
					"   WHERE STARTDATE BETWEEN ? AND ? \r\n" + 
					"   OR ENDDATE BETWEEN ? AND ?      \r\n" + 
					") ORDER BY S.MINNUMBER  \r\n" ; 
						
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, numberOfPersons);
			pstmt.setDate(2, TeamConvert.dateFromUtilToSql(startPeriod));
			pstmt.setDate(3, TeamConvert.dateFromUtilToSql(endPeriod));
			pstmt.setDate(4, TeamConvert.dateFromUtilToSql(startPeriod));
			pstmt.setDate(5, TeamConvert.dateFromUtilToSql(endPeriod));
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SearchSpaceDto dto = new SearchSpaceDto();
				
				dto.setBranchName(rs.getString("branchname"));
				dto.setSpaceId(rs.getInt("spaceid"));
				dto.setSpaceName(rs.getString("spacename"));
				dto.setCode(rs.getString("code"));
				dto.setCodeName(rs.getString("codename"));
				
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


	public int updateSpace(SpaceDto dto) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE Space\n" + 
				       "SET SpaceID = ?,\n" + 
				   "    SpaceType = ?,\n" + 
				   "    BranchID = ?,\n" + 
				   "    SpaceName = ?,\n"+
				   " WHERE SpaceID = ?\n" ; 		
		
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getSpaceId());
			pstmt.setString(2, dto.getSpaceType());
			pstmt.setInt(3, dto.getBranchId());
			pstmt.setString(4, dto.getSpaceName());
			
			row=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (Exception e){};
			try {conn.close();} catch (Exception e){};
		}
		
		return row;
	}

	public int deleteSpace(int spaceid) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM Space WHERE SpaceID = ? ";	
		
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, spaceid);
			
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
