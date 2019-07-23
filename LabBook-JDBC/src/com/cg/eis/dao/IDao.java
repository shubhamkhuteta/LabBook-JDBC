package com.cg.eis.dao;

import java.sql.SQLException;

import com.capgemini.takehome.bean.Employee;

public interface IDao {

	boolean setdata(Employee e) throws SQLException, ClassNotFoundException;

	Employee getData(int id1) throws SQLException, ClassNotFoundException;

}
