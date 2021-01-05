package com.menusupchar.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuSupcharJDBCDAO implements MenuSupcharDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEST";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO MENU_SUPCHAR (MENU_SUPCHAR_ID, MENU_SUPCHAR_NAME) VALUES ('MS' ||LPAD(SEQ_MENU_SUPCHAR_ID.NEXTVAL,6,'0'), ?)";
	private static final String GET_ONE_STMT = "SELECT MENU_SUPCHAR_ID , MENU_SUPCHAR_NAME FROM MENU_SUPCHAR WHERE MENU_SUPCHAR_ID= ?";
	private static final String GET_ALL_STMT = "SELECT MENU_SUPCHAR_ID , MENU_SUPCHAR_NAME FROM MENU_SUPCHAR ";
	private static final String UPDATE = "UPDATE MENU_SUPCHAR SET MENU_SUPCHAR_NAME= ? WHERE MENU_SUPCHAR_ID= ?";

	@Override
	public void insert(MenuSupcharVO menuSupcharVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, menuSupcharVO.getMenuSupcharName());

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
	public void update(MenuSupcharVO menuSupcharVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, menuSupcharVO.getMenuSupcharName());
			pstmt.setString(2, menuSupcharVO.getMenuSupcharId());

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
	public MenuSupcharVO findByPrimaryKey(String menuSupcharId) {
		// TODO Auto-generated method stub
		MenuSupcharVO menuSupcharVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, menuSupcharId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				menuSupcharVO = new MenuSupcharVO();
				menuSupcharVO.setMenuSupcharId(rs.getString("MENU_SUPCHAR_ID"));
				menuSupcharVO.setMenuSupcharName(rs.getString("MENU_SUPCHAR_NAME"));
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
		return menuSupcharVO;
	}

	@Override
	public List<MenuSupcharVO> getAll() {
		// TODO Auto-generated method stub
		List<MenuSupcharVO> list = new ArrayList<MenuSupcharVO>();
		MenuSupcharVO menuSupcharVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuSupcharVO = new MenuSupcharVO();
				menuSupcharVO.setMenuSupcharId(rs.getString("MENU_SUPCHAR_ID"));
				menuSupcharVO.setMenuSupcharName(rs.getString("MENU_SUPCHAR_NAME"));
				list.add(menuSupcharVO); // Store the row in the list
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
		MenuSupcharJDBCDAO dao = new MenuSupcharJDBCDAO();

		// 新增
		MenuSupcharVO menuSupcharVO1 = new MenuSupcharVO();
		menuSupcharVO1.setMenuSupcharName("甜度");
		dao.insert(menuSupcharVO1);

		// 查詢
		MenuSupcharVO menuSupcharVO2 = dao.findByPrimaryKey("MS000001");
			System.out.print(menuSupcharVO2.getMenuSupcharId() + ",");
			System.out.print(menuSupcharVO2.getMenuSupcharName());
		System.out.println("---------------------");
		
		List<MenuSupcharVO> list = dao.getAll();
		for (MenuSupcharVO aMenuSupchar : list) {
			System.out.print(aMenuSupchar.getMenuSupcharId() + ",");
			System.out.print(aMenuSupchar.getMenuSupcharName());
		}
		System.out.println("---------------------");
	}
}
