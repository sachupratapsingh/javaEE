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

@WebServlet("/mddc1")
public class MilkDairy_Daily_Cust1 extends HttpServlet {
	//	private String name;
	//	private String month;
	//	private double milk_quantity;
	private MilkDairy_Dao_Impl mddi;
	public MilkDairy_Daily_Cust1() {
		mddi=new MilkDairy_Dao_Impl(); 
	}

	//retriving cust data from dao class



	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//getting printwriter
		PrintWriter pw=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		//local-var dec.
		String name=null;
		String date=null;
		int count=0;
		double milk_quantity=0.0;
		ArrayList<MilkDairy_Dto> almddto=null;
		String[] cname=null,cno=null;
		//get data by end-user
		name=req.getParameter("opt");
		date=req.getParameter("date");
		if(name!=null && name!="")
			milk_quantity=Double.parseDouble(req.getParameter("quan"));	
		//getting data from database
		try {
			almddto=mddi.viewCustomerData();
			ArrayList<String> alist=new ArrayList<String>();
			ArrayList<String> alistno=new ArrayList<String>();
			for(MilkDairy_Dto mddto:almddto) {
				alist.add(mddto.getName());
				alistno.add(mddto.getCustno());
			}//for loop
				cname=new String[alist.size()];
				cname= alist.toArray(cname);
                cno=new String[alist.size()];
                cno=alistno.toArray(cno);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//including header part
		req.getRequestDispatcher("header.html").include(req, res);
		//creating table
		pw.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
		pw.println("<div style=\"height: 490px; background-image:url('images/md4.jpg');background-size: 60% 100%;\" align=\"center\"  >");
		pw.println("<form action='mddc1' method='post'>");
		pw.println("<div class=\"w3-container\">");
		pw.println("<table class='w3-table-all w3-card-4'>");
		int i1=0;
		pw.println("<tr><th>CUSTOMER NO.</th><th>CUSTOMER NAME</th><th>DATE</th><th>MILK-QUANTITY</th></tr>");
		pw.println("<tr><td></td><td></td><td><input type='date' name='date' required></td><td></td></tr>");
		for(int i=0;i<cname.length;i++) {
			pw.println("<tr><td>"+cno[i]+"</td><td>"+cname[i]+"</td><td></td><td><input type='text' name='"+cname[i]+"' required> </td></tr>");	
			//        System.out.println(cname[i]);
			//        System.out.println(req.getParameter(cname[i]));
			//      / / System.out.println(req.getParameter("date"));

			try {
				if(cname[i]!=null && cname[i]!="" && date!=null && date!="" ) {
					System.out.println("if block executed..");
					milk_quantity=Double.parseDouble(req.getParameter(cname[i]));
					date=req.getParameter("date");
					mddi.existedCustomerInsert(cname[i],date ,milk_quantity );
					count++;
				}//if close

			} catch (Exception e) {
				req.getRequestDispatcher("ep?type='err'").forward(req, res);
				e.printStackTrace();
			}
		}//for loop..
		pw.println("<tr><td><input type='submit' value='SUBMIT'></td></tr></table></form>");
		if(count==cname.length)
			pw.println(" <h3 style='color:#800000;font-size:20px;text-align:center'><b>**INSERTION DONE.<b></h3>");
		req.getRequestDispatcher("footer.html").include(req, res);    
	}//do get method


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
