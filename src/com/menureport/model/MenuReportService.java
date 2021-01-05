package com.menureport.model;
import java.util.List;

public class MenuReportService {
	
	private MenuReportDAO_interface dao;
	
	public MenuReportService() {
		dao=new MenuReportDAO();
	}
	public MenuReportVO addMenuReport(String mem_phone,String store_id,String menu_id,String menu_report_content,Integer menu_report_status) {
		MenuReportVO mrVO=new MenuReportVO();
		mrVO.setMem_phone(mem_phone);
		mrVO.setStore_id(store_id);
		mrVO.setMenu_id(menu_id);
		mrVO.setMenu_report_content(menu_report_content);
		mrVO.setMenu_report_status(menu_report_status);
		dao.insert(mrVO);
		
		return mrVO;
		
	}
	
	public MenuReportVO updateMenuReport(String menu_report_id,String mem_phone,String store_id,String menu_id,String menu_report_content,Integer menu_report_status) {
		MenuReportVO mrVO=new MenuReportVO();
		mrVO.setMenu_report_id(menu_report_id);
		mrVO.setMem_phone(mem_phone);
		mrVO.setStore_id(store_id);
		mrVO.setMenu_id(menu_id);
		mrVO.setMenu_report_content(menu_report_content);
		mrVO.setMenu_report_status(menu_report_status);
		dao.update(mrVO);
		return mrVO;
		
	}
	public void deleteMenuReport(String menu_report_id){
		dao.delete(menu_report_id);
		
	}
	public MenuReportVO getOneMenuReport(String menu_report_id) {
		return dao.findByPrimaryKey(menu_report_id);
		
	}
	public List<MenuReportVO> getAll(){
		return dao.getAll();
	}
	
}
