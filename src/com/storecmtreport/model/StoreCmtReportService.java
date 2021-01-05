package com.storecmtreport.model;

import java.util.List;

public class StoreCmtReportService {
	private StoreCmtReportDAO_interface dao;
	
	public StoreCmtReportService() {
		dao=new StoreCmtReportDAO();
	}
	public StoreCmtReportVO addStoreCmtReport(String mem_phone,String store_id,String store_report_content,Integer store_cmt_report_status) {
		StoreCmtReportVO scrVO=new StoreCmtReportVO();
		scrVO.setMem_phone(mem_phone);
		scrVO.setStore_id(store_id);
		scrVO.setStore_report_content(store_report_content);
		scrVO.setStore_cmt_report_status(store_cmt_report_status);
		dao.insert(scrVO);
		return scrVO;
		
	}
	public StoreCmtReportVO updateStoreCmcReport(String store_cmt_report_id,String mem_phone,String store_id,String store_report_content,Integer store_cmt_report_status) {
		StoreCmtReportVO scrVO=new StoreCmtReportVO();
		scrVO.setStore_cmt_report_id(store_cmt_report_id);
		scrVO.setMem_phone(mem_phone);
		scrVO.setStore_id(store_id);
		scrVO.setStore_report_content(store_report_content);
		scrVO.setStore_cmt_report_status(store_cmt_report_status);
		dao.update(scrVO);
		return scrVO;
	}
	public void deleteStoreCmtReport(String store_cmt_report_id) {
		dao.delete(store_cmt_report_id);
	}
	public StoreCmtReportVO getOneStoreCmtReport(String store_cmt_report_id) {
		return dao.findByPrimaryKey(store_cmt_report_id);
	}
	public List<StoreCmtReportVO> getAll(){
		return dao.getAll();
	}
	
}
