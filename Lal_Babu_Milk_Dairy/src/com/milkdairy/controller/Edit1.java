package com.milkdairy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.milkdairy.dao.MilkDairy_Dao_Impl;


@WebServlet("/ed1")
public class Edit1 extends HttpServlet {
	private MilkDairy_Dao_Impl mddi;
	public Edit1() {
		mddi=new MilkDairy_Dao_Impl();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		HttpSession hs=req.getSession();
		//gathering data from req..
		String name=null;
		String date=null;
		double update_milk_quantity=0;
		//retrive data from req.
		name=req.getParameter("name");
		date=req.getParameter("date");
		//designing web-page
		req.getRequestDispatcher("header.html").include(req, res);
		System.out.println("name::"+name);
		pw.println("<div style=\"height: 490px; background-image:url('images/md4.jpg');background-size: 60% 100%;\" align=\"center\"  >");
		pw.println("<form action='ed1' method='post'><table bgcolor='#8080ff' style='border:2px solid red;border-radius:5px;opacity: 0.9'> ");
		pw.println("<tr><td>ENTER MILK_QUANTITY:: </td><td><input type='text' name='quan' required></td></tr> ");
		pw.println("<tr><td><input type='submit' value='UPDATE'></td></tr> ");
		pw.println("<h2><a href='view_page1.html'><b>GO TO HOME PAGE<b></a></h2></table></form></div>");
		//setting value in ses attr.
		if(name!=null && date!=null && date!="" && name!="") {
			hs.setAttribute("name", name);
			hs.setAttribute("date", date);
			System.out.println(hs.getId());
		}
		if(req.getParameter("quan")!=null && req.getParameter("quan")!="") {
			try {
			update_milk_quantity=Double.parseDouble(req.getParameter("quan"));
			
				String status=mddi.update_cust(update_milk_quantity, (String)hs.getAttribute("name"), (String)hs.getAttribute("date"));
				System.out.println("-:"+(String)hs.getAttribute("name"));
				System.out.println(hs.getId());
				hs.invalidate();
				pw.println(" <h3 style='color:#800000;font-size:20px;text-align:center'><b>***UPDATION DONE.<b></h3>");
				
				//req.getRequestDispatcher("mdvb").forward(req, res);
			} catch (Exception e) {
				req.getRequestDispatcher("/ep?type='error'").forward(req, res);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//if 
		req.getRequestDispatcher("footer.html").include(req, res);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
