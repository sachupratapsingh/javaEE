package com.milkdairy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.milkdairy.dao.MilkDairy_Dao_Impl;
import com.milkdairy.dto.MilkDairy_Dto;

@WebServlet("/md-vc-sd")
public class MilkDairy_View_Cust_Details extends HttpServlet {
	private MilkDairy_Dao_Impl mddi;
    public MilkDairy_View_Cust_Details() {
     mddi=new MilkDairy_Dao_Impl(); 
    }
    
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//PrintWriter 
	PrintWriter pw=null;
	pw=res.getWriter();
	res.setContentType("text/html");
	//creating Arraylist
	ArrayList<MilkDairy_Dto> mddto=null;
	try {
		mddto=this.mddi.viewCustomerData();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//creating table
			req.getRequestDispatcher("header.html").include(req, res);
			pw.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
			pw.println("<div style=\"height: 490px; background-image:url('images/md4.jpg');background-size: 60% 100%;\" align=\"center\"  >");
			pw.println("<form action='' method='post'>");
			pw.println("<div class=\"w3-container\">");
			pw.println("<table class='w3-table-all w3-card-4'>");
			pw.println("<tr><th>CUSTOMER-NO.</th><th>CUSTOMER NAME</th><th>RATE</th><th>ADDRESS</th><th>MOB-NO</th><th>SLOT</th></tr>");	
			for(MilkDairy_Dto mddto1:mddto)
			pw.println("<tr><td>"+mddto1.getCustno()+"</td><td>"+mddto1.getName()+"</td><td>"+mddto1.getRate()+"</td><td>"+mddto1.getAddress()+"</td><td>"+mddto1.getMob()+"</td><td>"+mddto1.getSlot()+"</td></tr>");	
			pw.println("</table>");
			req.getRequestDispatcher("footer.html").include(req, res);
			pw.println("</div></form></div>");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
