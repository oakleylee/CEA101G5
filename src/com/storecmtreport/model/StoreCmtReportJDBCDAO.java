package com.storecmtreport.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreCmtReportJDBCDAO implements StoreCmtReportDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CEA101G5";
	String passwd = "123456";
	private static final String INSERT_STMT ="INSERT INTO STORE_CMT_REPORT(STORE_CMT_REPORT_ID,MEM_PHONE,STORE_ID,STORE_REPORT_CONTENT,STORE_CMT_REPORT_STATUS)"
			+ "VALUES(('SCR' ||LPAD(SEQ_STORE_CMT_REPORT_ID.NEXTVAL,6,'0')),?,?,?,?)";
	
	private static final String GET_ALL_STMT ="SELECT STORE_CMT_REPORT_ID,MEM_PHONE,STORE_ID,STORE_REPORT_CONTENT,STORE_CMT_REPORT_STATUS FROM STORE_CMT_REPORT order by STORE_CMT_REPORT_ID";
	private static final String GET_ONE_STMT ="SELECT STORE_CMT_REPORT_ID,MEM_PHONE,STORE_ID,STORE_REPORT_CONTENT,STORE_CMT_REPORT_STATUS FROM STORE_CMT_REPORT where STORE_CMT_REPORT_ID=?";
	private static final String DELETE = "DELETE FROM STORE_CMT_REPORT where STORE_CMT_REPORT_ID=? ";
	private static final String UPDATE = "UPDATE STORE_CMT_REPORT set MEM_PHONE=? ,STORE_ID=? ,STORE_REPORT_CONTENT=? ,STORE_CMT_REPORT_STATUS=? where STORE_CMT_REPORT_ID=?";
	
	
	@Override
	public void insert(StoreCmtReportVO sotrecmtreportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, sotrecmtreportVO.getMem_phone());
			pstmt.setString(2,sotrecmtreportVO.getStore_id());
			pstmt.setString(3, sotrecmtreportVO.getStore_report_content());
			pstmt.setInt(4, sotrecmtreportVO.getStore_cmt_report_status());
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
	public void update(StoreCmtReportVO sotrecmtreportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, sotrecmtreportVO.getMem_phone());
			pstmt.setString(2,sotrecmtreportVO.getStore_id());
			pstmt.setString(3, sotrecmtreportVO.getStore_report_content());
			pstmt.setInt(4, sotrecmtreportVO.getStore_cmt_report_status());
			pstmt.setString(5, sotrecmtreportVO.getStore_cmt_report_id());
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
	public void delete(String store_cmt_report_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, store_cmt_report_id);
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
	public StoreCmtReportVO findByPrimaryKey(String store_cmt_report_id) {
		StoreCmtReportVO scrVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	public static void main(String[] args) {
		StoreCmtReportJDBCDAO scrdao=new StoreCmtReportJDBCDAO();
		//新增
		StoreCmtReportVO scrVO1=new StoreCmtReportVO();
		scrVO1.setMem_phone("0921842855");
		scrVO1.setStore_id("S000005");
		scrVO1.setStore_report_content("評論不實評論不實");
		scrVO1.setStore_cmt_report_status(1);
		scrdao.insert(scrVO1);
		System.out.println("新增");
		
		//更新
		StoreCmtReportVO scrVO2=new StoreCmtReportVO();
		scrVO2.setStore_cmt_report_id("SCR000006");//where
		scrVO2.setMem_phone("0921842858");
		scrVO2.setStore_id("S000010");
		scrVO2.setStore_report_content("測試測試測試");
		scrVO2.setStore_cmt_report_status(2);
		scrdao.update(scrVO2);
		System.out.println("更新");
		
		//刪除
//		scrdao.delete("SCR000011");
		
		//查詢
		StoreCmtReportVO scrVO3=scrdao.findByPrimaryKey("SCR000005");
		System.out.print(scrVO3.getStore_id()+",");
		System.out.print(scrVO3.getMem_phone()+",");
		System.out.print(scrVO3.getStore_id()+",");
		System.out.print(scrVO3.getStore_report_content()+",");
		System.out.println(scrVO3.getStore_cmt_report_status());
		System.out.println("-----------------------------------");
		
		//查全部
		List<StoreCmtReportVO> list=scrdao.getAll();
		for(StoreCmtReportVO aStoreRC:list) {
			System.out.print(aStoreRC.getStore_id()+",");
			System.out.print(aStoreRC.getMem_phone()+",");
			System.out.print(aStoreRC.getStore_id()+",");
			System.out.print(aStoreRC.getStore_report_content()+",");
			System.out.println(aStoreRC.getStore_cmt_report_status());
		}
		
	}
	
	
}
