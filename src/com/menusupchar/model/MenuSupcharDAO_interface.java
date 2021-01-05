package com.menusupchar.model;

import java.util.List;

public interface MenuSupcharDAO_interface {
	public void insert(MenuSupcharVO menuSupcharVO);
	public void update(MenuSupcharVO menuSupcharVO);
	public MenuSupcharVO findByPrimaryKey(String menuSupcharId);
	public List<MenuSupcharVO> getAll();
}
