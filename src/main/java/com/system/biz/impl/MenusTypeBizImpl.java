package com.system.biz.impl;

import java.util.List;

import com.system.biz.MenusTypeBiz;
import com.system.dao.MenusTypeDao;
import com.system.dao.impl.MenusTypeDaoImpl;
import com.system.domain.MenusType;

public class MenusTypeBizImpl implements MenusTypeBiz {
private MenusTypeDao mentDao;

	public MenusTypeBizImpl() {
	super();
	this.mentDao = new MenusTypeDaoImpl();
}

	@Override
	public List<MenusType> seletAllMenusType() {
		return this.mentDao.seletAllMenusType();
	}

}
