package com.corona.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corona.dao.CoronaDaoImpl;

@WebServlet("/cms_delete")
public class CoronaMainServlet_delete1 extends HttpServlet {

	CoronaDaoImpl cimpl=null;
    public CoronaMainServlet_delete1() {
        super();
        // TODO Auto-generated constructor stub
    }
	public void init(ServletConfig config) throws ServletException {
		
		cimpl=new CoronaDaoImpl();
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		String patient_no=null;
		String delete=null;
		patient_no=req.getParameter("pno");
		delete=req.getParameter("delete");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>All Corona Patient Delete</title>");
		pw.println("</head>");
		pw.println("<body>");
		//pw.println("<script type='text/javascript' src='js/corona1.js'></script>");
		//pw.println("<link rel=\"stylesheet\" href=\"css/corona.css\">");
		//pw.println("<div class=\"marry\">");
		pw.println("<form action='' method='post' ");
		if(delete.equalsIgnoreCase("true")) {
			try {
				int s=cimpl.delete(patient_no);
				pw.println("<html>");
				pw.println("<h2 style='color:blue;text-align:center'>"+s+" Rows Deleted Successfully.</h2>");
			} //try
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//catch
			}//if
			else
				pw.println("<h2 style='color:blue;text-align:center'>Sorry Rows Deletes Fails....Please try again... </h2>");
		        pw.println("<h1 style='color:blue;text-align:center'><a style='text-decoration:none' href='cms_view'>Back Page</a></h1>");
		        pw.println("<h1 style='color:blue;text-align:center'><a style='text-decoration:none' href='corona_patient.html'>Home Page</a></h1>");
				pw.println("</form>");
				pw.println("</head>");
				pw.println("</html>");
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
