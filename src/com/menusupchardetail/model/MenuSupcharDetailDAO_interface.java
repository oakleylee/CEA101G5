package com.menusupchardetail.model;

import java.util.List;

public interface MenuSupcharDetailDAO_interface {
	public void insert(MenuSupcharDetailVO menuSupcharDetailVO);
	public void update(MenuSupcharDetailVO menuSupcharDetailVO);
	public MenuSupcharDetailVO findByPrimaryKey(String menuSupcharDetailId);
	public List<MenuSupcharDetailVO> getAll();
}
