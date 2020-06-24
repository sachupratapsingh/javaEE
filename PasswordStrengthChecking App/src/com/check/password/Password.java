package com.check.password;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Password extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		PrintWriter pw=null;
		String password=null;
		int i1=0,i2=0,i3=0,i4=0,charc=0;
		pw=res.getWriter();
		res.setContentType("text/html");
		password=req.getParameter("pass");
		
		for(int i=0;i<password.length();i++)
		{
			charc=password.charAt(i);
			if(charc>=65 && charc<=90)
				i1++;
			else if(charc>=97 && charc<=122)
				i2++;
			else if(charc>=48 && charc<=57)
				i3++;
			else if(charc>=33 && charc<=47 || charc>=58 && charc<=64 || charc>=123 && charc<=126 || charc>=91 && charc<=96)
				i4++;
		}
		
		
		
		
		
		
		
		
		if(password.length()>8 && i1>0 && i2>0 && i3>0 && i4>0)
		{
			pw.println("<h1 style='color:blue;text-align:center'>Great! You Have Choosen A Strong Password</h1>");
			
		}
		else
			pw.println("<h1 style='color:red;text-align:center'> Sorry! You Have Choosen A Weak Password</h1>");
		pw.println("<a href=form.html> Go Back To Home</a>");
		pw.close();
		
	}
   
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
