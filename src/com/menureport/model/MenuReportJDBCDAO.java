package com.menureport.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuReportJDBCDAO implements MenuReportDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";
	private static final String INSERT_STMT ="INSERT INTO MENU_REPORT (MENU_REPORT_ID,MEM_PHONE,STORE_ID,MENU_ID,MENU_REPORT_CONTENT,MENU_REPORT_STATUS)VALUES(('MR' ||LPAD(SEQ_MENU_REPORT_ID.NEXTVAL,6,'0')),?,?,?,?,?)";
	
	private static final String GET_ALL_STMT ="SELECT MENU_REPORT_ID,MEM_PHONE,STORE_ID,MENU_ID,MENU_REPORT_CONTENT,MENU_REPORT_STATUS FROM MENU_REPORT order by MENU_REPORT_ID";
	private static final String GET_ONE_STMT ="SELECT MENU_REPORT_ID,MEM_PHONE,STORE_ID,MENU_ID,MENU_REPORT_CONTENT,MENU_REPORT_STATUS FROM MENU_REPORT where MENU_REPORT_ID=?";
	private static final String DELETE = "DELETE FROM MENU_REPORT where MENU_REPORT_ID=? ";
	private static final String UPDATE = "UPDATE MENU_REPORT set MEM_PHONE=? ,STORE_ID=? ,MENU_ID=? ,MENU_REPORT_CONTENT=?,MENU_REPORT_STATUS=? where MENU_REPORT_ID=?";
	
	@Override
	public void insert(MenuReportVO menureportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, menureportVO.getMem_phone());
			pstmt.setString(2, menureportVO.getStore_id());
			pstmt.setString(3, menureportVO.getMenu_id());
			pstmt.setString(4, menureportVO.getMenu_report_content());
			pstmt.setInt(5, menureportVO.getMenu_report_status());
			pstmt.executeUpdate();
			
	}catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, menureportVO.getMem_phone());
			pstmt.setString(2, menureportVO.getStore_id());
			pstmt.setString(3, menureportVO.getMenu_id());
			pstmt.setString(4, menureportVO.getMenu_report_content());
			pstmt.setInt(5, menureportVO.getMenu_report_status());
			pstmt.setString(6,menureportVO.getMenu_report_id());
			pstmt.executeUpdate();
			
	}catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, menu_report_id);
			pstmt.executeUpdate();
			
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return list;
	}	
		
		
	public static void main(String[] argv) {
		MenuReportJDBCDAO mrdao=new MenuReportJDBCDAO();
		//新增
//		MenuReportVO mrVO1=new MenuReportVO();
//		mrVO1.setMem_phone("0921842855");
//		mrVO1.setStore_id("S000005");
//		mrVO1.setMenu_id("M000008");
//		mrVO1.setMenu_report_content("測試測試測試");
//		mrVO1.setMenu_report_status(1);
//		mrdao.insert(mrVO1);
//		System.out.println("新增");
		
		//更新
		MenuReportVO mrVO2=new MenuReportVO();
		mrVO2.setMenu_report_id("MR000006");//where條件
		mrVO2.setMem_phone("0921842858");
		mrVO2.setStore_id("S000002");
		mrVO2.setMenu_id("M000002");
		mrVO2.setMenu_report_content("ssa測試測試測dss測試測試測s試");
		mrVO2.setMenu_report_status(1);
		mrdao.update(mrVO2);
		//刪除
//		mrdao.delete("MR000012");
		
		//查詢
		MenuReportVO mrVO3=mrdao.findByPrimaryKey("MR000002");
		System.out.print(mrVO3.getMenu_report_id()+",");
		System.out.print(mrVO3.getMem_phone()+",");
		System.out.print(mrVO3.getStore_id()+",");
		System.out.print(mrVO3.getMenu_id()+",");
		System.out.print(mrVO3.getMenu_report_content()+",");
		System.out.println(mrVO3.getMenu_report_status());
		System.out.println("-----------------------------------");
		
		List<MenuReportVO> list=mrdao.getAll();
		for(MenuReportVO aMenuR:list) {
			System.out.print(aMenuR.getMenu_report_id()+",");
			System.out.print(aMenuR.getMem_phone()+",");
			System.out.print(aMenuR.getStore_id()+",");
			System.out.print(aMenuR.getMenu_id()+",");
			System.out.print(aMenuR.getMenu_report_content()+",");
			System.out.println(aMenuR.getMenu_report_status());
		}
	}



}
