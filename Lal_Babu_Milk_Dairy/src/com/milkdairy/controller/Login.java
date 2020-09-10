package com.milkdairy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.milkdairy.dao.MilkDairy_Dao_Impl;



@WebServlet("/Login")
public class Login extends HttpServlet {
	private MilkDairy_Dao_Impl mddi;
	public Login() {
		mddi=new MilkDairy_Dao_Impl(); 
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		res.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		res.setHeader("pragma", "no-cache");
		//Session and other obj
		HttpSession ses=null;
		ServletContext sc=null;
		Cookie cookie=null;
		String uname=null;
		String pwd=null;
		String rem=null;
		//gather data from req.
		uname=req.getParameter("uname");
		pwd=req.getParameter("psd");
		rem=req.getParameter("rem");
		if(uname==null && pwd==null && rem==null )
		{
			HttpSession ses1=req.getSession(false);
			System.out.println(ses1.getId());
			if(ses1!=null)
				req.getRequestDispatcher("view_page1.html").forward(req, res);
			else 
				req.getRequestDispatcher("login.html").forward(req, res);
		}else {
			int value=0;
			try {
				value=mddi.authenticate(uname, pwd);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(value!=0) {
				System.out.println("sa:: "+req.getParameter("JSESSIONID"));
				//creating session obj.
				ses=req.getSession();
				sc=getServletContext();
				cookie=new Cookie("JSESSIONID",ses.getId());
				cookie.setMaxAge(Integer.MAX_VALUE);
				res.addCookie(cookie);
				//setting value to attribute..
				//System.out.println(Long.MAX_VALUE);
				ses.setAttribute("uname", "sachupratapsingh@gmail.com");
				ses.setAttribute("pwd", "Sach@4132661");
				///System.out.println(pwd.equalsIgnoreCase((String) ses.getAttribute("pwd"))+":::::"+uname.equalsIgnoreCase((String) ses.getAttribute("uname")));
				//System.out.println(pwd+";;;;"+ses.getAttribute("pwd"));

				if(uname.equalsIgnoreCase((String) ses.getAttribute("uname")) && pwd.equalsIgnoreCase((String) ses.getAttribute("pwd"))) {
					//System.out.println("url:: "+req.getServletPath());
					//System.out.println("url1:: "+req.);
					req.getRequestDispatcher("view_page1.html").forward(req, res);
				}
			}
			else
				pw.println("<h3 style=color:blue;text-align:center>***INVALID USERNAME/PASSWORD.<br>PLEASE TRY AGAIN***</h3>");
			req.getRequestDispatcher("login.html").include(req, res);
		}//main else
	}//doGet(-,-)

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
