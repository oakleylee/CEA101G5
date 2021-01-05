package com.storecmtreport.model;
import java.util.*;
public interface StoreCmtReportDAO_interface {
	public void insert(StoreCmtReportVO sotrecmtreportVO);
	public void update(StoreCmtReportVO sotrecmtreportVO);
	public void delete(String store_cmt_report_id);
	public StoreCmtReportVO findByPrimaryKey(String store_cmt_report_id);
	public List<StoreCmtReportVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<StoreCmtReportVO> getAll(Map<String, String[]> map);
}
