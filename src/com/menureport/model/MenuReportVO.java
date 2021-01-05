package com.menureport.model;
import java.sql.*;
public class MenuReportVO implements java.io.Serializable{
	private String menu_report_id;
	private String mem_phone;
	private String store_id;
	private String menu_id;
	private String menu_report_content;
	private Integer  menu_report_status;
	public String getMenu_report_id() {
		return menu_report_id;
	}
	public void setMenu_report_id(String menu_report_id) {
		this.menu_report_id = menu_report_id;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_report_content() {
		return menu_report_content;
	}
	public void setMenu_report_content(String menu_report_content) {
		this.menu_report_content = menu_report_content;
	}
	public Integer getMenu_report_status() {
		return menu_report_status;
	}
	public void setMenu_report_status(Integer menu_report_status) {
		this.menu_report_status = menu_report_status;
	}
	
}
