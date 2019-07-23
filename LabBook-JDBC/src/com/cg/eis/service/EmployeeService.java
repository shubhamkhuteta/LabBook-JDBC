package com.cg.eis..service;

import java.sql.SQLException;

import com.capgemini.takehome.bean.Employee;
import com.capgemini.takehome.dao.DaoClass;
import com.capgemini.takehome.dao.IDao;
class MyException extends Exception {
	String s1;

	MyException(String s) {
		s1 = s;

	}

	public String toString() {
		return (s1);
	}

}

public class EmployeeService implements IEmployeeService {
	IDao id=new DaoClass();

	@Override
	public boolean codevalid(int code)  {
		String s=Integer.toString(code);
		try {
		if(s.length()!=4) {
			throw new MyException("Enter Valid Code");
			}
		else
			return true;
	}catch(MyException e) {
		System.out.println(e);
		return false;
		}
	}

	

	@Override
	public boolean namevalid(String quan) {
		// TODO Auto-generated method stub
			String regex = "^[A-Z][a-z]*( [A-Z][a-z]*)*";
			try {
			if(quan.matches(regex)) 
			return true;
			else 
				throw new MyException("     Invalid Name");}
			catch (MyException E) {
				System.out.println(E);
				return false;}
	
	}

	@Override
	public boolean desgnvalid(String quan) {
		// TODO Auto-generated method stub
				try {
			if(quan.equals("System Associate") || quan.equals("Manager") || quan.equals("Clerk") || quan.equals("Programmer"))
			return true;
			else 
				throw new MyException("     Invalid Designation\n    Case Sensitive");}
			catch (MyException E) {
				System.out.println(E);
				return false;}
	
	}


	@Override
	public boolean setData(int code, String quan, int sal, String desgn) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String iS="";
		if(sal>5000 && sal< 20000 && desgn.equals("System Associate"))
			iS="Scheme C";
		else if(sal>=20000 && sal< 40000 && desgn.equals("Programmer"))
			iS="Scheme B";
		else if(sal>=40000 && desgn.equals("Manager"))
			iS="Scheme A";
		else if(sal<500000 && desgn.equals("Clerk"))
			iS="No Scheme";
		System.out.println(iS);
		Employee e=new Employee(code, quan, sal, desgn, iS);
		
		boolean n=id.setdata(e);
		return n;
	}



	@Override
	public Employee getData(int id1) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Employee e=id.getData(id1);
		return e;
	}



}
