package com.menusupplies.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MenuSuppliesDAO implements MenuSuppliesDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO MENU_SUPPLIES (MENU_SUPCHAR_ID, MENU_ID) VALUES (?,  ?)";
	private static final String GET_ONE_BYMENUID = "SELECT MENU_SUPCHAR_ID FROM MENU_SUPPLIES WHERE MENU_ID= ?";
	private static final String GET_ONE_BYMENUSUPCHARID = "SELECT MENU_ID FROM MENU_SUPPLIES WHERE MENU_SUPCHAR_ID= ?";
	private static final String UPDATE = "UPDATE MENU_SUPPLIES SET MENU_SUPCHAR_ID= ? WHERE MENU_ID= ?";

	@Override
	public void insert(MenuSuppliesVO menuSuppliesVO) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, menuSuppliesVO.getMenuSupcharId());
			pstmt.setString(2, menuSuppliesVO.getMenuId());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, menuSuppliesVO.getMenuSupcharId());
			pstmt.setString(2, menuSuppliesVO.getMenuId());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

			con = ds.getConnection();
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
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		// TODO Auto-generated method stub
		List<MenuSuppliesVO> list = new ArrayList<MenuSuppliesVO>();
		MenuSuppliesVO menuSuppliesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
