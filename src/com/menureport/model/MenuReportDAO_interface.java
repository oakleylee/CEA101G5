package com.menureport.model;

import java.util.List;

public interface MenuReportDAO_interface {
	public void insert(MenuReportVO menureportVO);
	public void update(MenuReportVO menureportVO);
	public void delete(String menu_report_id);
	public MenuReportVO findByPrimaryKey(String menu_report_id);

	public List<MenuReportVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//	 public List<MenuReportVO> getAll(Map<String, String[]> map);
}
