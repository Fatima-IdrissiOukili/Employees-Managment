package employee.crud.controller;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import org.codehaus.jackson.map.ObjectMapper;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import employee.crud.beans.Employee;
import employee.crud.dao.EmployeeDAO;
import employee.crud.dao.EmployeeDAOImpl;
import jakarta.servlet.annotation.WebServlet;


@WebServlet("/")

public class EmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EmployeeDAO employeeDAO=null;
       
    
    public EmployeeControllerServlet() {
    	 super();
       
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	
    	super.init(config);
    	employeeDAO = new EmployeeDAOImpl() ;
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//add
		//update
		//delete
		//select
		
		System.out.println("EmployeeControllerServlet, doPost Method Started");
		
		String action =request.getServletPath();
		System.out.println("doPost, action==>" + action);
		
		switch(action) {
		case "/add":
			addNewEmployee(request,response);
			break;
			
        case "/update":
        	updateEmployee(request,response);
			break;
			
        case "/delete":
			deleteEmployee(request,response);
			break;
			
        case "/list":
			 getAllEmployee(request,response);
			break;
			
        case "/get":
        	getEmployee(request,response);
        	break;
        	
        default:
        	getAllEmployee(request,response);
        	break;
		}
	}
	
	private void getEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		  System.out.println("Start getEmployee");
			
			int id=Integer.parseInt(request.getParameter("employeeId"));
			System.out.println("getEmployee , Employee ID==>" + id);
			
			Employee employee = employeeDAO.getEmployee(id);
			System.out.println("getEmployee , result is ==>"+ employee);
			
			
		   // RequestDispatcher dispatcher= request.getRequestDispatcher("/employeeView.jsp");
			
		    try {
		    	
		     ObjectMapper mapper  = new ObjectMapper();
		     String employeeStr = mapper.writeValueAsString(employee);
			  
				
			 ServletOutputStream servletOutputStream = response.getOutputStream();
			 servletOutputStream.write(employeeStr.getBytes());
				
				
				//request.setAttribute("employee", employee);
				//dispatcher.forward(request,response);
				
			} catch ( IOException e) {
				
				e.printStackTrace();
			
		    }
	}
	
	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Start deleteEmployee");
		
		//int id=Integer.parseInt(request.getParameter("id"));
        
		String employeeIds = request.getParameter("employeeIds");
		System.out.println("deleteEmployee , Employee ID==>" + employeeIds);
		
		//boolean result = employeeDAO.deleteEmployee(id);
		//System.out.println("deleteEmployee , result is ==>"+ result);
		
		
		StringTokenizer tokenizer=new StringTokenizer(employeeIds , ",");
		while(tokenizer.hasMoreElements()) {
			
			int employeeId=Integer.parseInt(tokenizer.nextToken());
			boolean result = employeeDAO.deleteEmployee(employeeId);
			System.out.println("deleteEmployee , result is ==>"+ result);
		}
		
		 List<Employee> employees = employeeDAO.getAllEmployees();
		 System.out.println("getAllEmployee , employees size ==>"+ employees.size());
		
	    try {
	    	RequestDispatcher dispatcher= request.getRequestDispatcher("/employeeView.jsp");
			request.setAttribute("employees", employees);
	    	dispatcher.forward(request,response);
			
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		
	    }
		
	}
	
	
	private void getAllEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Start getAllEmployee");
			
			
		    List<Employee> employees = employeeDAO.getAllEmployees();
			System.out.println("getAllEmployee , employees size ==>"+ employees.size());
			
			
		    try {
		    	
		    	RequestDispatcher dispatcher= request.getRequestDispatcher("/employeeView.jsp");
		    	
				request.setAttribute("employees", employees);
				dispatcher.forward(request,response);
				
			} catch (ServletException | IOException e) {
				
				e.printStackTrace();
			
		    }
			
		
	}
	
	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
	    
		System.out.println("Start updateEmployee");
		
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		
		Employee employee =new Employee(id,name,email,phone,address);
		System.out.println("Employee Details==>" + employee);
		
		boolean result = employeeDAO.updateEmployee(employee);
		System.out.println("updateEmployee , result is ==>"+ result);
		
		
		 List<Employee> employees = employeeDAO.getAllEmployees();
		 System.out.println("getAllEmployee , employees size ==>"+ employees.size());
		 
	    
		
	    try {
	    	
	    	RequestDispatcher dispatcher= request.getRequestDispatcher("/employeeView.jsp");
	    	request.setAttribute("employees", employees);
	    	dispatcher.forward(request,response);
			
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		
	    }
		
	}
	
	private void addNewEmployee(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Start addNewEmployee");
		
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		
		Employee employee =new Employee(name,email,phone,address);
		System.out.println("Employee Details==>" + employee);
		
		boolean result = employeeDAO.addEmployee(employee);
		System.out.println("addNewEmployee , result is ==>"+ result);
		
		
		 List<Employee> employees = employeeDAO.getAllEmployees();
		 System.out.println("getAllEmployee , employees size ==>"+ employees.size());
		
	   
		
	    try {
	    	 RequestDispatcher dispatcher= request.getRequestDispatcher("/employeeView.jsp");
			 request.setAttribute("employees", employees);
			 dispatcher.forward(request,response);
			
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		
	    }
	}
}