package com.milkdairy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.milkdairy.dto.MilkDairy_Dto;
import com.milkdairy.service.MilkDairy_Service_Impl;
import com.milkdairy.vo.MilkDairy_Vo;

@WebServlet("/mdnc")
public class MilkDairy_New_Cust extends HttpServlet {
	private MilkDairy_Vo mdvo;
	private MilkDairy_Dto mddto;
	private MilkDairy_Service_Impl mdsi;
	public MilkDairy_New_Cust() {
		mdvo=new MilkDairy_Vo();
		mddto=new MilkDairy_Dto();
		mdsi=new MilkDairy_Service_Impl();

	}


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//Printwriter Object
		PrintWriter pw=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		//established the variables
		String name=null;
		String address=null;
		String rate=null;
		String mob=null;
		String slot=null;
		//create table
		req.getRequestDispatcher("header.html").include(req, res);
		pw.println("<head><script type='text/javascript' src='js/new_cust.js'></script>");
		pw.println("</head>");
		pw.println("<div style=\"height: 490px; background-image:url('images/md4.jpg');background-size: 60% 100%;\" align=\"center\"  >");
		pw.println("<form action='mdnc' method='post' onsubmit='return fun1(this)'><br><br><br><table align='center' bgcolor='#8080ff' style='border:2px solid red;border-radius:5px;opacity: 0.9'> ");
		pw.println("<tr><td>ENTER CUSTOMER NAME::</td><td><input type='text' name='cname'><span id='sp1' class='' style='color:red'></span></td></tr>");
		pw.println("<tr><td>ENTER CUSTOMER ADDRESS::</td><td><textarea rows='4' cols='50' name='caddress'></textarea><span id='sp2' class='' style='color:red'></span></td></tr>");
		pw.println("<tr><td>ENTER MOB-NO::</td><td><input type='tel' name='mob'><span id='sp3' class='' style='color:red'></span></td></tr>");
		pw.println("<tr><td>ENTER MILK-RATE::</td><td><input type='number' name='rate'><span id='sp4' class='' style='color:red'></span></td></tr>");
		pw.println("<tr><td>CHOOSE SLOT::</td><td><select name='slot'><option>---CHOOSE SLOT---</option><option>MORNING</option><option>EVENING</option><option>NO GUARNATTY</option></select><span id='sp5' class='' style='color:red' ></span></td></tr>");
		pw.println("<tr><td><input type='submit'  value='REGISTER'></td></tr>");
		pw.println("</table></form></div>");
		//storing value from form data...
		name=req.getParameter("cname");
		address=req.getParameter("caddress");
		rate=req.getParameter("rate");
		mob=req.getParameter("mob");
		slot=req.getParameter("slot");
		System.out.println(slot);
		//writing condition for storing purpose...
		if(name!="" && name!=null || address!="" && address!=null || rate!="" && rate!=null) {
			mdvo.setName(name);
			mdvo.setAddress(address);
			mdvo.setRate(rate);
			mdvo.setMob(mob);
			mdvo.setSlot(slot);
			mddto.setAddress(address);
			mddto.setName(name);
			mddto.setRate(Integer.parseInt(rate));
			mddto.setMob(Long.parseLong(mob));
			mddto.setSlot(slot);
			try {
				String status=mdsi.register_new_customer(mddto, mdvo);
				pw.println(" <h3 style='color:#800000;font-size:20px;text-align:center'><b>**INSERTION DONE.<b></h3>");
			} 
			catch (SQLIntegrityConstraintViolationException sql) {
				pw.println(" <h3 style=\\\"color:#800000;font-size:50px;text-align:center\\\"><b>DUPLICATE VALUE.<b></h3>");
				//req.getRequestDispatcher("mdnc").include(req, res);	
				req.getRequestDispatcher("ep?type='fail'").forward(req, res);
				sql.printStackTrace();
			}//catch
			catch (Exception e) {
				req.getRequestDispatcher("ep?type='fail'").forward(req, res);
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
