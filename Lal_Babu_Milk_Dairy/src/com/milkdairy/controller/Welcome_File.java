package com.milkdairy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/w-f")
public class Welcome_File extends HttpServlet {

	public Welcome_File() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//cache control
		res.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		res.setHeader("pragma", "no-cache");
		//creating HttpSession obj
		HttpSession ses=null;
		ses=req.getSession(false);
		System.out.println("welcome-file is working");
		if(ses!=null)
			req.getRequestDispatcher("view_page1.html").forward(req, res);
		else
			req.getRequestDispatcher("login.html").forward(req, res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
