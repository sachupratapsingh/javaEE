package com.milkdairy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogOut")
public class LogOut extends HttpServlet {
	public LogOut() {
		super();
		// TODO Auto-generated constructor stub....
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String value=null;
		HttpSession ses=null;
		ses=req.getSession();
		res.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		res.setHeader("pragma", "no-cache");
		value=req.getParameter("value");
		if(value.equalsIgnoreCase("true")) {
			System.out.println("ses:::"+ses);
			ses.invalidate();
			req.getRequestDispatcher("login.html").forward(req, res);
		}
		/*
		 * else req.getRequestDispatcher(req.getServletPath()).forward(req, res);
		 */
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
