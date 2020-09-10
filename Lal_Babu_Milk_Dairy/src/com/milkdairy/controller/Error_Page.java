package com.milkdairy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ep")
public class Error_Page extends HttpServlet {
	public Error_Page() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter 
		PrintWriter pw=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		String type="";
		type=req.getParameter("type");
		if(type.equalsIgnoreCase("insert"))
			pw.println(" <h3 style='color:#800000;font-size:20px;text-align:center'><b>***INSERTION DONE..<b></h3>");
		else if(type.equalsIgnoreCase("update"))
			pw.println(" <h3 style='color:#800000;font-size:20px;text-align:center'><b>***UPDATE DONE..<b></h3>");
		else if(type.equalsIgnoreCase("delete"))
			pw.println(" <h3 style='color:#800000;font-size:20px;text-align:center'><b>***DELETION DONE<b></h3>");
		//desging web page
		else {
			req.getRequestDispatcher("header.html").include(req, res);
			pw.println("<div style=\"height: 490px; background-image:url('images/md4.jpg');background-size: 60% 100%;\" align=\"center\"  >");
			pw.println(" <h3 style=\"color:#800000;font-size:50px\"><br>SORRY,INTERNAL ERROR..TRY AGAIN<br></h3>");
			pw.println("<h2><a href='view_page1.html'><b>GO TO HOME PAGE<b></a></h2></div>");
			req.getRequestDispatcher("footer.html").include(req, res);
		}//else
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
