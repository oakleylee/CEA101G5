package com.menusupplies.model;

import java.sql.*;
import java.util.*;

public class MenuSuppliesJDBCDAO implements MenuSuppliesDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO MENU_SUPPLIES (MENU_SUPCHAR_ID, MENU_ID) VALUES (?,  ?)";
	private static final String GET_ONE_BYMENUID = "SELECT MENU_SUPCHAR_ID FROM MENU_SUPPLIES WHERE MENU_ID= ?";
	private static final String GET_ONE_BYMENUSUPCHARID = "SELECT MENU_ID FROM MENU_SUPPLIES WHERE MENU_SUPCHAR_ID= ?";
	private static final String UPDATE = "UPDATE MENU_SUPPLIES SET MENU_SUPCHAR_ID= ? WHERE MENU_ID= ?";

	@Override
	public void insert(MenuSuppliesVO menuSuppliesVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, menuSuppliesVO.getMenuSupcharId());
			pstmt.setString(2, menuSuppliesVO.getMenuId());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(MenuSuppliesVO menuSuppliesVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, menuSuppliesVO.getMenuSupcharId());
			pstmt.setString(2, menuSuppliesVO.getMenuId());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<MenuSuppliesVO> findByMenuId(String menuId) {
		// TODO Auto-generated method stub
		List<MenuSuppliesVO> list = new ArrayList<MenuSuppliesVO>();
		MenuSuppliesVO menuSuppliesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BYMENUID);

			pstmt.setString(1, menuId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				menuSuppliesVO = new MenuSuppliesVO();
				menuSuppliesVO.setMenuId(rs.getString("MENU_ID"));
				menuSuppliesVO.setMenuSupcharId(rs.getString("MENU_SUPCHAR_ID"));
				list.add(menuSuppliesVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public List<MenuSuppliesVO> findByMenuSupcharId(String menuSupcharId) {
		List<MenuSuppliesVO> list = new ArrayList<MenuSuppliesVO>();
		MenuSuppliesVO menuSuppliesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BYMENUSUPCHARID);

			pstmt.setString(1, menuSupcharId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				menuSuppliesVO = new MenuSuppliesVO();
				menuSuppliesVO.setMenuSupcharId(rs.getString("MENU_SUPCHAR_ID"));
				menuSuppliesVO.setMenuId(rs.getString("MENU_ID"));
				list.add(menuSuppliesVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public static void main(String[] args) {
		MenuSuppliesJDBCDAO dao = new MenuSuppliesJDBCDAO();

		// 新增
		MenuSuppliesVO menuSuppliesVO1 = new MenuSuppliesVO();
		menuSuppliesVO1.setMenuSupcharId("MS000001");
		menuSuppliesVO1.setMenuId("M000001");
		dao.insert(menuSuppliesVO1);

		// 新增
		MenuSuppliesVO menuSuppliesVO2 = new MenuSuppliesVO();
		menuSuppliesVO2.setMenuSupcharId("MS000002");
		menuSuppliesVO2.setMenuId("M000001");
		dao.insert(menuSuppliesVO2);

		// 新增
		MenuSuppliesVO menuSuppliesVO3 = new MenuSuppliesVO();
		menuSuppliesVO3.setMenuSupcharId("MS000003");
		menuSuppliesVO3.setMenuId("M000001");
		dao.insert(menuSuppliesVO3);

		// 新增
		MenuSuppliesVO menuSuppliesVO4 = new MenuSuppliesVO();
		menuSuppliesVO4.setMenuSupcharId("MS000004");
		menuSuppliesVO4.setMenuId("M000002");
		dao.insert(menuSuppliesVO4);

		// 新增
		MenuSuppliesVO menuSuppliesVO5 = new MenuSuppliesVO();
		menuSuppliesVO5.setMenuSupcharId("MS000005");
		menuSuppliesVO5.setMenuId("M000002");
		dao.insert(menuSuppliesVO5);

		// 查詢
		List<MenuSuppliesVO> list = dao.findByMenuId("M000001");
		for (MenuSuppliesVO aMenuSupplies : list) {
			System.out.print(aMenuSupplies.getMenuId() + ",");
			System.out.print(aMenuSupplies.getMenuSupcharId() + ",");
		}
		System.out.println("---------------------");
		
		List<MenuSuppliesVO> list2 = dao.findByMenuSupcharId("MS000001");
		for (MenuSuppliesVO aMenuSupplies : list2) {
			System.out.print(aMenuSupplies.getMenuSupcharId() + ",");
			System.out.print(aMenuSupplies.getMenuId() + ",");
		}
		System.out.println("---------------------");
	}
}
