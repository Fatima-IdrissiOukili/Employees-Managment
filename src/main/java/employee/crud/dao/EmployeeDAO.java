package employee.crud.dao;

import java.util.List;


import employee.crud.beans.Employee;

public interface EmployeeDAO {

	//1- insert employee
	public boolean addEmployee(Employee employee);
	
	
	//2-update employee
	public boolean updateEmployee(Employee employee);
	
	//3- Delete employee
	boolean deleteEmployee(int employeeId);
	
	//4- Get All employee
	public List<Employee> getAllEmployees();
	
	
	//5- Get single employee
	public Employee getEmployee(int employeeId);


	


	


	


	
	
}
