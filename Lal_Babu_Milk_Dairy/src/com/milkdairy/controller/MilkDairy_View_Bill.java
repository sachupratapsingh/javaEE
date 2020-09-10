package com.milkdairy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.milkdairy.dao.MilkDairy_Dao_Impl;
import com.milkdairy.dto.MilkDairy_Dto;

@WebServlet("/mdvb")
public class MilkDairy_View_Bill extends HttpServlet {
	private MilkDairy_Dao_Impl mddi;
	public MilkDairy_View_Bill() {
		mddi=new MilkDairy_Dao_Impl();
	}


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter stream
		PrintWriter pw=null;
		pw=res.getWriter();
		res.setContentType("text/html");
	    res.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		res.setHeader("pragma", "no-cache");
		//declaration of variable..
		HttpSession ses=null;
		ServletContext sc=null;
		String name=null;
		String month=null;
		ArrayList<MilkDairy_Dto> almddto=null;
		String[] cname=null;
        sc=getServletContext();
		ses=req.getSession(false);
		//System.out.println("value::-"+ses.getId());
		System.out.println("value::-"+sc);
		 System.out.println(req.getServletPath());
		if(ses!=null ) {
			//gathering data from req
			name=req.getParameter("cname");
			month=req.getParameter("cmonth");
			try {
				almddto=mddi.viewCustomerData();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//creating Arraylist
			/*	try {
			almddto=mddi.viewCustomerData();
			ArrayList<String> alist=new ArrayList<String>();
			for(MilkDairy_Dto mddto:almddto) {
				alist.add(mddto.getName());
				cname=new String[alist.size()];
				cname= alist.toArray(cname);

			}//for loop
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			 */
			//creating table
			req.getRequestDispatcher("header.html").include(req, res);
			pw.println("<head><script type='text/javascript' src='js/exists1.js'></script>");
			pw.println("</head>");
			pw.println("<div style=\"height: 490px; background-image:url('images/md4.jpg');background-size: 60% 100%;\" align=\"center\"  >");
			pw.println("<form action='md-vb-it' method='post' onsubmit='return fun1(this)'>");
			pw.println("<br><br><br><table align='center' bgcolor='#8080ff' style='border:2px solid red;border-radius:5px;opacity: 0.9'>");
			pw.println("<tr><td>CHOOSE CUSTOMER NAME::</td><td><select name='opt'>");
			pw.println("<option id=''>"+"---select customer name---"+"</option>");
			for(MilkDairy_Dto mddto:almddto)//<span id='sp1' style='color:red'></span>
				pw.println("<option id=''>"+mddto.getName()+"</option>");
			pw.println("<option>ALL-CUSTOMERS</option></select><span id='sp1' style='color:red'></span></td></tr>");
			pw.println("<tr><td>CHOOSE A SPECIFIC MONTH::</td><td><input type='month' name='mon'>");
			pw.println("<span id='sp2' style='color:red'></span></td></tr><tr><td>ALL MONTHS::</td><td><input type='checkbox' name='allmon' id='allmont' value='month'><span id='sp3' style='color:red'></span></td></tr>");
			pw.println("<tr><td><input type='submit' value='CHECK BILL'></td></tr>");
			pw.println("</table></form></div>");
			req.getRequestDispatcher("footer.html").include(req, res);
		}// main if close
		else
			req.getRequestDispatcher("login.html").forward(req, res);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
