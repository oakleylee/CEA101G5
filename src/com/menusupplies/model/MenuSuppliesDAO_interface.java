package com.menusupplies.model;

import java.util.List;

public interface MenuSuppliesDAO_interface {
	public void insert(MenuSuppliesVO menuSuppliesVO);
	public void update(MenuSuppliesVO menuSuppliesVO);
	public List<MenuSuppliesVO> findByMenuId(String menuId);
	public List<MenuSuppliesVO> findByMenuSupcharId(String menuSupcharId);

}
