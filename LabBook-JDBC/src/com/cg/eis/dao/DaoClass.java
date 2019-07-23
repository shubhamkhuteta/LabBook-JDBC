package com.cg.eis.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.takehome.bean.Employee;


public class DaoClass implements IDao {
	ConnectionDatabase cd = new ConnectionDatabase();
	PreparedStatement ps;
	Connection con;
	@Override
	public boolean setdata(Employee e) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		con = cd.getConnection();
		ps=con.prepareStatement("insert into user2 values(?,?,?,?,?)");
		ps.setString(2, e.getName());
		ps.setInt(1, e.getId());
		ps.setInt(3, e.getSalary());
		ps.setString(4, e.getDesignation());
		ps.setString(5, e.getInsuranceScheme());
		int i=ps.executeUpdate();
		boolean n=false;
		if(i==1) {
			n=true;
		}
		return n;
	}
	@Override
	public Employee getData(int id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		con = cd.getConnection();
		ps=con.prepareStatement("select * from user2 where id=? ");
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		Employee e=new Employee();
		rs.next();
		e.setId(rs.getInt(1));
		e.setName(rs.getString(2));
		e.setDesignation(rs.getString(4));
		e.setSalary(rs.getInt(3));
		e.setInsuranceScheme(rs.getString(5));
		
		return e;
	}
}