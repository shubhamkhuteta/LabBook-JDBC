package com.cg.eis.ui;


import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

import com.capgemini.takehome.bean.Employee;

import com.capgemini.takehome.service.*;

public class UI {
	static IEmployeeService ies = new EmployeeService() ;
		
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException  {
			while(true) {
				int ch;
				Scanner sc=new Scanner(System.in);
				System.out.println("1)	Set Information of Employee \n 2)	Get information of employee \n 3) Exit\n" );
				ch=sc.nextInt();
				switch(ch){
				case 1:System.out.println("Employee Id");
					int code=sc.nextInt();
					while(!ies.codevalid(code)) {
							System.out.println("Employee Id");
							code=sc.nextInt();
					}	
					System.out.println("Employee name");
					String quan=sc.next();
					while(!ies.namevalid(quan)) {
						System.out.println("Employee name");
						quan=sc.next();
					}
					System.out.println("Employee Salary");
					int sal=sc.nextInt();
					
					System.out.println("Employee Designation");
					String desgn=sc.next();
					while(!ies.desgnvalid(desgn)) {
						System.out.println("Employee Designation");
						desgn=sc.next();
					}
					boolean n=ies.setData(code, quan, sal, desgn);
					if(n) {
						System.out.println("succesful insertion");
					}
				
							break;
				case 2:		
					System.out.println("enter employee id");
					int id=sc.nextInt();
					Employee e1=ies.getData(id);
				System.out.println("Employee ID : "+e1.getId()+"\n Employee Name: "+ e1.getName()+"\n Employee Salary : "+ e1.getSalary()+"\n Employee Designation : "+ e1.getDesignation()+"\n Employee Insurance Scheme : "+ e1.getInsuranceScheme());
			break;
				case 3: System.exit(0);
				}
			}
	}
}
