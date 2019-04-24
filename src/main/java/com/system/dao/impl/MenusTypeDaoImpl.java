package com.system.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.dao.MenusTypeDao;
import com.system.domain.MenusType;
import com.system.util.DBUtil;

public class MenusTypeDaoImpl implements MenusTypeDao{
private DBUtil db;

	public MenusTypeDaoImpl() {
	super();
	this.db = db;
}

	@Override
	public List<MenusType> seletAllMenusType() {
		this.db=new DBUtil();
		List<MenusType> list=new ArrayList<>();
		String sql="select * from menustype";
		try {
			ResultSet rs = this.db.query(sql);
			while(rs.next()){
				MenusType menustype=new MenusType();
				menustype.setMtid(rs.getInt("mtid"));
				menustype.setMtname(rs.getString("mtname"));
				list.add(menustype);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.db.closed();
		}
		return null;
	}

}
