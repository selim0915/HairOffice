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
		
		String sql = " INSERT INTO SPACE(SPACEID, SPACENAME, SPACETYPE, MINNUMBER, MAXNUMBER, BRANCHID ) \r\n" + 
				     " VALUES(ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?) \r\n" ;
		
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getSpaceName());
			pstmt.setString(2, dto.getSpaceType());
			pstmt.setInt(3, dto.getMinNumber());
			pstmt.setInt(4, dto.getMaxNumber());
			pstmt.setInt(5, dto.getBranchId());
						
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
		
		String sql = " SELECT SPACEID, SPACENAME, SPACETYPE, MINNUMBER, MAXNUMBER, BRANCHID \r\n" + 
				     " FROM SPACE  \r\n" + 
				     " WHERE SPACEID = ? \r\n";
		
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, spaceID);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setSpaceId(rs.getInt("SpaceID"));
				dto.setSpaceName(rs.getString("SpaceName"));
				dto.setSpaceType(rs.getString("SpaceType"));
				dto.setMinNumber(rs.getInt("minnumber"));
				dto.setMaxNumber(rs.getInt("maxnumber"));
				dto.setBranchId(rs.getInt("branchid"));
							
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
		
		String sql = " SELECT SPACEID, SPACENAME, SPACETYPE, MINNUMBER, MAXNUMBER, BRANCHID \r\n" + 
			         " FROM SPACE  \r\n";
		
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SpaceDto dto = new SpaceDto();

				dto.setSpaceId(rs.getInt("SpaceID"));
				dto.setSpaceName(rs.getString("SpaceName"));
				dto.setSpaceType(rs.getString("SpaceType"));
				dto.setMinNumber(rs.getInt("minnumber"));
				dto.setMaxNumber(rs.getInt("maxnumber"));
				dto.setBranchId(rs.getInt("branchid"));
				
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
	
	public List<SpaceDto> getSpaceAvailableList (int numberOfPersons, int branchId, Date startPeriod, Date endPeriod ) {
		List<SpaceDto> dtoList = new ArrayList<SpaceDto>();
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT SPACEID, SPACENAME, SPACETYPE, MINNUMBER, MAXNUMBER, BRANCHID \r\n" + 
						" FROM SPACE \r\n" + 
						" WHERE MINNUMBER <= ?   \r\n" + 
						" AND BRANCHID = ?     \r\n" + 
						" AND SPACEID NOT IN (   \r\n" + 
						"    SELECT SPACEID      \r\n" + 
						"    FROM RENTCONTRACT   \r\n" + 
						"    WHERE STARTDATE BETWEEN ? AND ? \r\n" + 
						"    OR ENDDATE BETWEEN ? AND ? \r\n" + 
						") ORDER BY MINNUMBER \r\n" ; 
						
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, numberOfPersons);
			pstmt.setInt(2, branchId);
			pstmt.setDate(3, TeamConvert.dateFromUtilToSql(startPeriod));
			pstmt.setDate(4, TeamConvert.dateFromUtilToSql(endPeriod));
			pstmt.setDate(5, TeamConvert.dateFromUtilToSql(startPeriod));
			pstmt.setDate(6, TeamConvert.dateFromUtilToSql(endPeriod));
			
			
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
	

	public int updateSpace(SpaceDto dto) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = " UPDATE SPACE  \r\n" + 
				     "  SET SPACENAME = ? , \r\n" + 
				     "      SPACETYPE = ? , \r\n" + 
				     "      MINNUMBER = ? , \r\n" + 
				     "      MAXNUMBER = ?  \r\n" + 
				     " WHERE SPACEID = ? "; 		
		
		try {
			conn = ds.getConnection();
			//
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getSpaceName());
			pstmt.setString(2, dto.getSpaceType());
			pstmt.setInt(3, dto.getMinNumber());
			pstmt.setInt(4, dto.getMaxNumber());
			pstmt.setInt(5, dto.getSpaceId());
			
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
		
		String sql = "DELETE FROM SPACE WHERE SPACEID = ? ";	
		
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
