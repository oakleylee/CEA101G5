package com.menusupchardetail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MenuSupcharDetailDAO implements MenuSupcharDetailDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CEA101G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO MENU_SUPCHAR_DETAIL (MENU_SUPCHAR_DETAIL_ID,MENU_SUPCHAR_ID,MENU_SUPCHAR_DETAIL_NAME,MENU_SUPCHAR_PRICE) VALUES ('MSD'||LPAD(SEQ_MENU_SUPCHAR_DETAIL_ID.NEXTVAL,6,'0'), ?, ? ,?)";
	private static final String GET_ONE_STMT = "SELECT MENU_SUPCHAR_DETAIL_ID,MENU_SUPCHAR_ID,MENU_SUPCHAR_DETAIL_NAME,MENU_SUPCHAR_PRICE FROM MENU_SUPCHAR_DETAIL WHERE MENU_SUPCHAR_DETAIL_ID= ?";
	private static final String GET_ALL_STMT = "SELECT MENU_SUPCHAR_DETAIL_ID,MENU_SUPCHAR_ID,MENU_SUPCHAR_DETAIL_NAME,MENU_SUPCHAR_PRICE FROM MENU_SUPCHAR_DETAIL ";
	private static final String UPDATE = "UPDATE MENU_SUPCHAR_DETAIL SET MENU_SUPCHAR_DETAIL_NAME=? ,MENU_SUPCHAR_PRICE= ? WHERE MENU_SUPCHAR_DETAIL_ID= ?";

	@Override
	public void insert(MenuSupcharDetailVO menuSupcharDetailVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, menuSupcharDetailVO.getMenuSupcharId());
			pstmt.setString(2, menuSupcharDetailVO.getMenuSupcharDetailName());
			pstmt.setString(3, menuSupcharDetailVO.getMenuSupcharPrice());

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
	public void update(MenuSupcharDetailVO menuSupcharDetailVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, menuSupcharDetailVO.getMenuSupcharDetailName());
			pstmt.setString(2, menuSupcharDetailVO.getMenuSupcharPrice());

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
	public MenuSupcharDetailVO findByPrimaryKey(String menuSupcharDetailId) {
		// TODO Auto-generated method stub
		MenuSupcharDetailVO menuSupcharDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, menuSupcharDetailId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				menuSupcharDetailVO = new MenuSupcharDetailVO();
				menuSupcharDetailVO.setMenuSupcharDetailId(rs.getString("MENU_SUPCHAR_DETAIL_ID"));
				menuSupcharDetailVO.setMenuSupcharId(rs.getString("MENU_SUPCHAR_ID"));
				menuSupcharDetailVO.setMenuSupcharDetailName(rs.getString("MENU_SUPCHAR_DETAIL_NAME"));
				menuSupcharDetailVO.setMenuSupcharPrice(rs.getString("MENU_SUPCHAR_PRICE"));
			}

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
		return menuSupcharDetailVO;
	}

	@Override
	public List<MenuSupcharDetailVO> getAll() {
		// TODO Auto-generated method stub
		List<MenuSupcharDetailVO> list = new ArrayList<MenuSupcharDetailVO>();
		MenuSupcharDetailVO menuSupcharDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				menuSupcharDetailVO = new MenuSupcharDetailVO();
				menuSupcharDetailVO.setMenuSupcharDetailId(rs.getString("MENU_SUPCHAR_DETAIL_ID"));
				menuSupcharDetailVO.setMenuSupcharId(rs.getString("MENU_SUPCHAR_ID"));
				menuSupcharDetailVO.setMenuSupcharDetailName(rs.getString("MENU_SUPCHAR_DETAIL_NAME"));
				menuSupcharDetailVO.setMenuSupcharPrice(rs.getString("MENU_SUPCHAR_PRICE"));
				list.add(menuSupcharDetailVO); // Store the row in the list
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
