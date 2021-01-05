package com.menureport.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.menureport.model.MenuReportService;
import com.menureport.model.MenuReportVO;



@WebServlet("/MenuReportServlet")
public class MenuReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		
		if ("getOne_For_Display".equals(action)) { // 來自select_emp_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String str = req.getParameter("menu_report_id");
				if (str == null || (str.trim()).length() == 0) {// str=null 防呆除錯用，防止請求參數打錯
					errorMsgs.add("請輸入編號");				//當getParameter打錯時比較好除錯
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/menureport/select_menureport_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String menu_report_id = null;
				try {
					menu_report_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/meunreport/select_menureport_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MenuReportService mrSvc = new MenuReportService();
				MenuReportVO mrVO = mrSvc.getOneMenuReport(menu_report_id);
				if (mrVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/menureport/select_menureport_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mrVO", mrVO); // 資料庫取出的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/menureport/listOneMenuReport.jsp"); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/menureport/select_menureport_page.jsp");
				failureView.forward(req, res);
				
			}
		}	
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String menu_report_id =req.getParameter("menu_report_id");
				
				/***************************2.開始查詢資料****************************************/
				MenuReportService mrSvc =new MenuReportService();
				MenuReportVO mrVO = mrSvc.getOneMenuReport(menu_report_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("mrVO", mrVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/menureport/update_menureport_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/menureport/listAllMenuReport.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			MenuReportVO mrVO = new MenuReportVO();
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String menu_report_id =(req.getParameter("menu_report_id").trim());
				
				String mem_phone = req.getParameter("mem_phone");
				if (mem_phone == null || mem_phone.trim().length() == 0) {
					errorMsgs.add("會員帳號請勿空白");
				}	
				String store_id = req.getParameter("store_id").trim();
				if (store_id == null || store_id.trim().length() == 0) {
					errorMsgs.add("請填餐廳編號");
				}	
				
				String menu_id = null;
				try {
					menu_id = (req.getParameter("menu_id").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請填入餐點編號.");
				}
				
				String menu_report_content = req.getParameter("menu_report_content");
				String menuReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
				if (menu_report_content == null || menu_report_content.trim().length() == 0) {
					errorMsgs.add("檢舉內容勿空白");
				} else if(!menu_report_content.trim().matches(menuReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("檢舉內容: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
	            }
				
				
				Integer menu_report_status = null;
				try {
					menu_report_status = new Integer(req.getParameter("menu_report_status").trim());
				} catch (NumberFormatException e) {
					menu_report_status=0;
					errorMsgs.add("請填檢舉狀態.");
				}


				
				mrVO.setMenu_report_id(menu_report_id);
				mrVO.setMem_phone(mem_phone);
				mrVO.setStore_id(store_id);
				mrVO.setMenu_id(menu_id);
				mrVO.setMenu_report_content(menu_report_content);
				mrVO.setMenu_report_status(menu_report_status);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mrVO", mrVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/menureport/update_menureport_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MenuReportService mrSvc = new MenuReportService();
				mrVO = mrSvc.updateMenuReport(menu_report_id, mem_phone, store_id, menu_id, menu_report_content, menu_report_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mrVO", mrVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/menureport/listOneMenuReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				e.printStackTrace();
				req.setAttribute("mrVO", mrVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/menureport/update_menureport_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		 if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				MenuReportVO mrVO = new MenuReportVO();
				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//					String menu_report_id =(req.getParameter("menu_report_id").trim());
					
					String mem_phone = req.getParameter("mem_phone");
					if (mem_phone == null || mem_phone.trim().length() == 0) {
						errorMsgs.add("會員帳號請勿空白");
					}	
					String store_id = req.getParameter("store_id").trim();
					if (store_id == null || store_id.trim().length() == 0) {
						errorMsgs.add("請填餐廳編號");
					}	
					
					String menu_id = null;
					try {
						menu_id = (req.getParameter("menu_id").trim());
					} catch (NumberFormatException e) {
						errorMsgs.add("請填入餐點編號.");
					}
					
					String menu_report_content = req.getParameter("menu_report_content");
					String menuReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,100}$";
					if (menu_report_content == null || menu_report_content.trim().length() == 0) {
						errorMsgs.add("檢舉內容勿空白");
					} else if(!menu_report_content.trim().matches(menuReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("檢舉內容: 只能是中、英文字母、數字和_ , 且長度必需在2到100之間");
		            }
					
					
					Integer menu_report_status = null;
					try {
						menu_report_status = new Integer(req.getParameter("menu_report_status").trim());
					} catch (NumberFormatException e) {
						menu_report_status=0;
						errorMsgs.add("請填檢舉狀態.");
					}


					
					
					mrVO.setMem_phone(mem_phone);
					mrVO.setStore_id(store_id);
					mrVO.setMenu_id(menu_id);
					mrVO.setMenu_report_content(menu_report_content);
					mrVO.setMenu_report_status(menu_report_status);
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("mrVO", mrVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/menureport/update_menureport_input.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/
					MenuReportService mrSvc = new MenuReportService();
					mrVO = mrSvc.addMenuReport(mem_phone, store_id, menu_id, menu_report_content, menu_report_status);
					
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("mrVO", mrVO); // 資料庫update成功後,正確的的empVO物件,存入req
					String url = "/back-end/menureport/listAllMenuReport.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					e.printStackTrace();
					req.setAttribute("mrVO", mrVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/menureport/update_menureport_input.jsp");
					failureView.forward(req, res);
				}
		 }
				
				if ("delete".equals(action)) { // 來自listAllEmp.jsp

						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);
				
						try {
							/***************************1.接收請求參數***************************************/
							String menu_report_id =req.getParameter("menu_report_id");
							
							/***************************2.開始刪除資料***************************************/
							MenuReportService mrSvc = new MenuReportService();
							mrSvc.deleteMenuReport(menu_report_id);
							/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
							String url = "/back-end/menureport/listAllMenuReport.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
							successView.forward(req, res);
							
							/***************************其他可能的錯誤處理**********************************/
							
						} catch (Exception e) {
							errorMsgs.add("刪除資料失敗:"+e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/menureport/listAllMenuReport.jsp");
							failureView.forward(req, res);
				}
		 }
	}
		 
}
