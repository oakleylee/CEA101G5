package com.menureport.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class MenuReportDAO implements MenuReportDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT ="INSERT INTO MENU_REPORT (MENU_REPORT_ID,MEM_PHONE,STORE_ID,MENU_ID,MENU_REPORT_CONTENT,MENU_REPORT_STATUS)VALUES(('MR' ||LPAD(SEQ_MENU_REPORT_ID.NEXTVAL,6,'0')),?,?,?,?,?)";
	
	private static final String GET_ALL_STMT ="SELECT MENU_REPORT_ID,MEM_PHONE,STORE_ID,MENU_ID,MENU_REPORT_CONTENT,MENU_REPORT_STATUS FROM MENU_REPORT ORDER BY MENU_REPORT_ID";
	private static final String GET_ONE_STMT ="SELECT MENU_REPORT_ID,MEM_PHONE,STORE_ID,MENU_ID,MENU_REPORT_CONTENT,MENU_REPORT_STATUS FROM MENU_REPORT WHERE MENU_REPORT_ID=?";
	private static final String DELETE = "DELETE FROM MENU_REPORT WHERE MENU_REPORT_ID=? ";
	private static final String UPDATE = "UPDATE MENU_REPORT SET MEM_PHONE=? ,STORE_ID=? ,MENU_ID=? ,MENU_REPORT_CONTENT=?,MENU_REPORT_STATUS=? where MENU_REPORT_ID=?";
	
	@Override
	public void insert(MenuReportVO menureportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, menureportVO.getMem_phone());
			pstmt.setString(2, menureportVO.getStore_id());
			pstmt.setString(3, menureportVO.getMenu_id());
			pstmt.setString(4, menureportVO.getMenu_report_content());
			pstmt.setInt(5, menureportVO.getMenu_report_status());
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
	public void update(MenuReportVO menureportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, menureportVO.getMem_phone());
			pstmt.setString(2, menureportVO.getStore_id());
			pstmt.setString(3, menureportVO.getMenu_id());
			pstmt.setString(4, menureportVO.getMenu_report_content());
			pstmt.setInt(5, menureportVO.getMenu_report_status());
			pstmt.setString(6,menureportVO.getMenu_report_id());
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
	public void delete(String menu_report_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, menu_report_id);
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
	public MenuReportVO findByPrimaryKey(String menu_report_id) {
		MenuReportVO mrVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, menu_report_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mrVO=new MenuReportVO();
				mrVO.setMenu_report_id(rs.getString("menu_report_id"));
				mrVO.setMem_phone(rs.getString("mem_phone"));
				mrVO.setStore_id(rs.getString("store_id"));
				mrVO.setMenu_id(rs.getString("menu_id"));
				mrVO.setMenu_report_content(rs.getString("menu_report_content"));
				mrVO.setMenu_report_status(rs.getInt("menu_report_status"));
			}
		} catch (SQLException se) {
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
		return mrVO;
	}

	
	@Override
	public List<MenuReportVO> getAll() {
		List<MenuReportVO> list=new ArrayList<MenuReportVO>();
		MenuReportVO mrVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mrVO=new MenuReportVO();
				mrVO.setMenu_report_id(rs.getString("menu_report_id"));
				mrVO.setMem_phone(rs.getString("mem_phone"));
				mrVO.setStore_id(rs.getString("store_id"));
				mrVO.setMenu_id(rs.getString("menu_id"));
				mrVO.setMenu_report_content(rs.getString("menu_report_content"));
				mrVO.setMenu_report_status(rs.getInt("menu_report_status"));
				list.add(mrVO);
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
