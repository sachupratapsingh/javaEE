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

@WebServlet("/mddc")
public class MilkDairy_Daily_Cust extends HttpServlet {
	private MilkDairy_Dao_Impl mddi;
	public MilkDairy_Daily_Cust() {
		mddi=new MilkDairy_Dao_Impl();

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		//local-var dec.
		String name=null;
		String date=null;
		double milk_quantity=0.0;
		ArrayList<MilkDairy_Dto> almddto=null;
		String[] cname=null;
		//get data by end-user
		name=req.getParameter("opt");
		date=req.getParameter("date");
		try {
			if(name!=null && name!="" && !name.equalsIgnoreCase("---select customer name---"))
				milk_quantity=Double.parseDouble(req.getParameter("quan"));	
			//getting data from database
			almddto=mddi.viewCustomerData();
			ArrayList<String> alist=new ArrayList<String>();
			for(MilkDairy_Dto mddto:almddto) {
				alist.add(mddto.getName());
				cname=new String[alist.size()];
				cname= alist.toArray(cname);

			}//for loop
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//create table
		req.getRequestDispatcher("header.html").include(req, res);
		pw.println("<head><script type='text/javascript' src='js/exists.js'></script>");
		pw.println("</head>");
		pw.println("<div style=\"height: 490px; background-image:url('images/md4.jpg');background-size: 60% 100%;\" align=\"center\"  >");
		pw.println("<form action='mddc' method='post' onsubmit='return fun1(this)'><br><br><br><table align='center' bgcolor='#8080ff' style='border:2px solid red;border-radius:5px;opacity: 0.9'> ");
		pw.println("<tr><td>CHOOSE CUSTOMER NAME::</td><td><select name='opt'>");
		pw.println("<option id=''>"+"---select customer name---"+"</option>");
		for(int i=0;i<cname.length;i++) {System.out.println(cname[i]);
		pw.println("<option id=''>"+cname[i]+"</option>");}
		pw.println("</select><span id='sp1' class='' style='color:red'></span></td></tr><tr></select><td>ENTER DATE::</td><td><input type='date' name='date'><span id='sp2' class='' style='color:red'></span></td></tr>");
		//pw.println("<tr><td>ENTER MOB-NO::</td><td><input type='tel' name='mob'></td></tr>");
		pw.println("<tr><td>ENTER MILK-QUANTITY::</td><td><input type='text' name='quan'><span id='sp3' class='' style='color:red'></span></td></tr>");
		pw.println("<tr><td><input type='submit' value='SUBMIT'></td><td><a href='mddc1'><b text-align:'center'>ENTER ALL DATA IN ONCE TIME</b></a></td></tr>");
		pw.println("</table></form></div>");

		if(name!=null && name!="" && date!=null && date!="" || milk_quantity!=0.0) {

			System.out.println("executed1");
			//calling method for storing to database
			try {
				System.out.println("executed");
				String value=mddi.existedCustomerInsert(name,date,milk_quantity);
				if(value.equalsIgnoreCase("Hurray...!1 rows inserted..."))
				pw.println(" <h3 style='color:#800000;font-size:20px;text-align:center'><b>***INSERTION DONE.<b></h3>");
				
				//req.getRequestDispatcher("mddc").include(req, res);
				//req.getRequestDispatcher("ep?type='insert'").include(req, res);
			} catch (Exception e) {
				req.getRequestDispatcher("ep?type='error'").forward(req, res);
				e.printStackTrace();
			}//catch
		}//if
		req.getRequestDispatcher("footer.html").include(req, res);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
