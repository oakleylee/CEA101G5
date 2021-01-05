package com.storecmtreport.model;
import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class StoreCmtReportDAO implements StoreCmtReportDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT ="INSERT INTO STORE_CMT_REPORT(STORE_CMT_REPORT_ID,MEM_PHONE,STORE_ID,STORE_REPORT_CONTENT,STORE_CMT_REPORT_STATUS)"
			+ "VALUES(('SCR' ||LPAD(SEQ_STORE_CMT_REPORT_ID.NEXTVAL,6,'0')),?,?,?,?)";
	
	private static final String GET_ALL_STMT ="SELECT STORE_CMT_REPORT_ID,MEM_PHONE,STORE_ID,STORE_REPORT_CONTENT,STORE_CMT_REPORT_STATUS FROM STORE_CMT_REPORT ORDER BY STORE_CMT_REPORT_ID";
	private static final String GET_ONE_STMT ="SELECT STORE_CMT_REPORT_ID,MEM_PHONE,STORE_ID,STORE_REPORT_CONTENT,STORE_CMT_REPORT_STATUS FROM STORE_CMT_REPORT WHERE STORE_CMT_REPORT_ID=?";
	private static final String DELETE = "DELETE FROM STORE_CMT_REPORT WHERE STORE_CMT_REPORT_ID=? ";
	private static final String UPDATE = "UPDATE STORE_CMT_REPORT SET MEM_PHONE=? ,STORE_ID=? ,STORE_REPORT_CONTENT=? ,STORE_CMT_REPORT_STATUS=? WHERE STORE_CMT_REPORT_ID=?";
	
	
	@Override
	public void insert(StoreCmtReportVO sotrecmtreportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, sotrecmtreportVO.getMem_phone());
			pstmt.setString(2,sotrecmtreportVO.getStore_id());
			pstmt.setString(3, sotrecmtreportVO.getStore_report_content());
			pstmt.setInt(4, sotrecmtreportVO.getStore_cmt_report_status());
			pstmt.executeUpdate();
	}catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
}
	@Override
	public void update(StoreCmtReportVO sotrecmtreportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, sotrecmtreportVO.getMem_phone());
			pstmt.setString(2,sotrecmtreportVO.getStore_id());
			pstmt.setString(3, sotrecmtreportVO.getStore_report_content());
			pstmt.setInt(4, sotrecmtreportVO.getStore_cmt_report_status());
			pstmt.setString(5, sotrecmtreportVO.getStore_cmt_report_id());
			pstmt.executeUpdate();
	}catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
}
	@Override
	public void delete(String store_cmt_report_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, store_cmt_report_id);
			pstmt.executeUpdate();
	}catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
}	
	@Override
	public StoreCmtReportVO findByPrimaryKey(String store_cmt_report_id) {
		StoreCmtReportVO scrVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, store_cmt_report_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				scrVO=new StoreCmtReportVO();
				scrVO.setStore_cmt_report_id(rs.getString("store_cmt_report_id"));
				scrVO.setMem_phone(rs.getString("mem_phone"));
				scrVO.setStore_id(rs.getString("store_id"));
				scrVO.setStore_report_content(rs.getString("store_report_content"));
				scrVO.setStore_cmt_report_status(rs.getInt("store_cmt_report_status"));
			
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return scrVO;
	}
	@Override
	public List<StoreCmtReportVO> getAll() {
		List<StoreCmtReportVO> list=new ArrayList<StoreCmtReportVO>();
		StoreCmtReportVO scrVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				scrVO=new StoreCmtReportVO();
				scrVO.setStore_cmt_report_id(rs.getString("store_cmt_report_id"));
				scrVO.setMem_phone(rs.getString("mem_phone"));
				scrVO.setStore_id(rs.getString("store_id"));
				scrVO.setStore_report_content(rs.getString("store_report_content"));
				scrVO.setStore_cmt_report_status(rs.getInt("store_cmt_report_status"));
				list.add(scrVO);
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
}
