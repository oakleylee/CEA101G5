package com.storecmtreport.model;
import java.sql.*;
public class StoreCmtReportVO implements java.io.Serializable{
	private String store_cmt_report_id;
	private String mem_phone;
	private String store_id;
	private String store_report_content;
	private Integer store_cmt_report_status;
	public String getStore_cmt_report_id() {
		return store_cmt_report_id;
	}
	public void setStore_cmt_report_id(String store_cmt_report_id) {
		this.store_cmt_report_id = store_cmt_report_id;
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
	public String getStore_report_content() {
		return store_report_content;
	}
	public void setStore_report_content(String store_report_content) {
		this.store_report_content = store_report_content;
	}
	public Integer getStore_cmt_report_status() {
		return store_cmt_report_status;
	}
	public void setStore_cmt_report_status(Integer store_cmt_report_status) {
		this.store_cmt_report_status = store_cmt_report_status;
	}
	
}
