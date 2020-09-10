package com.milkdairy.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.milkdairy.dao.MilkDairy_Dao_Impl;


@WebServlet("/dl1")
public class Delete1 extends HttpServlet {
	private MilkDairy_Dao_Impl mddi=null;
	public Delete1() {
		mddi=new MilkDairy_Dao_Impl();
	}


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name=null;
		String date=null;
		double quan=0;
		String status=null;
		//retriving data from request
		name=req.getParameter("name");
		date=req.getParameter("date");
		try {
			status = mddi.delete_cus(name, date);
			if(status.equalsIgnoreCase("1 Rows Deleted Successfully..")) {
				req.getRequestDispatcher("mdvb").include(req, res);
				req.getRequestDispatcher("ep?type=delete").include(req, res);
			}
				else 
					req.getRequestDispatcher("mdvb").forward(req, res);
		} catch (SQLException | NamingException e) {
			req.getRequestDispatcher("ep?type='error'").forward(req, res);
			e.printStackTrace();
		}
        System.out.println(status);
        
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
