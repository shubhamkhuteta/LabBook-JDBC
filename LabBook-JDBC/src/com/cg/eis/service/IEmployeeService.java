package com.cg.eis.service;

import java.sql.SQLException;

import com.capgemini.takehome.bean.Employee;


public interface IEmployeeService {



boolean namevalid(String quan);


boolean setData(int code, String quan, int sal, String desgn) throws SQLException, ClassNotFoundException;


boolean codevalid(int code);


boolean desgnvalid(String quan);


Employee getData(int id) throws SQLException, ClassNotFoundException;



}
