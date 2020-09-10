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
import com.milkdairy.service.Milk_Dairy_View_Bill;

@WebServlet("/md-vb-it")
public class MilkDairy_View_Bill_InTable extends HttpServlet {
	private Milk_Dairy_View_Bill mdvb1;
	public MilkDairy_View_Bill_InTable() {
		mdvb1=new Milk_Dairy_View_Bill();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//printwriter stream
		PrintWriter pw=null;
		String name=null;
		String month=null;
		String allmon=null;
		// creating ArrayList Of Milk_Dairy_View_Bill class;
		ArrayList<Milk_Dairy_View_Bill> mdvb=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		//getting data from the req obj
		name=req.getParameter("opt");
		month=req.getParameter("mon");
		allmon=req.getParameter("allmon");
		if(allmon!=null)
			month="";
		//calling method from dao class..
		try {
			mdvb=mdvb1.view_bill(name, month);
			//System.out.println("try"+mdvb1.getTquantity());
		} catch (Exception e) {
			req.getRequestDispatcher("mdvb").forward(req, res);
			e.printStackTrace();
		}
		//creating table
		req.getRequestDispatcher("header.html").include(req, res);
		pw.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
		pw.println("<div style=\"height: 490px; background-image:url('images/md4.jpg');background-size: 60% 100%;\" align=\"center\"  >");
		pw.println("<form action='' method='post'>");
		pw.println("<div class=\"w3-container\">");
		pw.println("<table class='w3-table-all w3-card-4'>");
		int i1=0;
		pw.println("<tr><th>CUSTOMER-NO.</th><th>CUSTOMER NAME</th><th>DATE/MONTH</th><th>QUANTITY</th><th>BILL-AMOUNT</th><th>PAID-AMOUNT</th><th>DUES AMOUNT</th><th>EDIT</th><th>DELETE</th></tr>");	
		int i=0;
		System.out.println("value------"+mdvb);
		for(Milk_Dairy_View_Bill mdvb2:mdvb)
			try {
                //condition
				if(name.equalsIgnoreCase("ALL-CUSTOMERS")) {
					pw.println("<tr><td>"+mdvb2.getCustno()+"</td><td>"+mdvb2.getCusname()+"</td><td>"+mdvb2.getMonth()+"</td><td>"+mdvb2.getQuantity()+" Lit.</td><td>"+mdvb2.getQuantity()*mdvb2.getRate()+" Rupees.</td><td></td><td></td><td></td><td></td></tr>");	
				}//if
				else {
				pw.println("<tr><td>"+"</td><td>"+"</td><td>"+mdvb2.getDate()+"</td><td>"+mdvb2.getQuantity()+" Lit.</td><td></td><td></td><td></td><td><a href='ed1?name="+mdvb2.getCusname()+"&date="+mdvb2.getDate()+"'>EDIT</a></td><td><a href='dl1?name="+mdvb2.getCusname()+"&date="+mdvb2.getDate()+"'>DELETE</a></td></tr>");
				i++;
				if(i==mdvb.size())
					pw.println("<tr><td><b>"+mdvb2.getCustno()+"</b></td><td><b>"+mdvb2.getCusname()+"</b></td><td></td><td><b>"+mdvb2.getTquantity()+" Lit.</b>"+"</td><td><b>"+mdvb2.getBamount()+" Rupees.</b></td><td></td><td></td><td></td><td></td></tr>");
				}//else
			}//if

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.println("</table>");
		req.getRequestDispatcher("footer.html").include(req, res);
	    pw.println("</div></form></div>");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
