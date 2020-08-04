package com.atrribute.form;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Form extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		pw=res.getWriter();
		res.setContentType("text/html");
		String fn=null,ln=null,email=null,gen=null,hobby[]=null,marry=null;
		String address=null,edu=null,cour[]=null,ts=null,st=null,range=null,url=null,dob=null,month=null;
		int age=0,pin=0;
		long mob=0;
		String n=req.getParameter("name");
		System.out.println(n+"::::::::::::::");
		fn=req.getParameter("fn");
		System.out.println(fn+";;;;;;;;;;;");
		ln=req.getParameter("ln");
		email=req.getParameter("email");
		gen=req.getParameter("gn");
		age=Integer.parseInt(req.getParameter("page"));
		hobby=req.getParameterValues("hb");
		marry=req.getParameter("marriage");
		address=req.getParameter("ta");
		pin=Integer.parseInt(req.getParameter("pc"));
		edu=req.getParameter("edu");
		cour=req.getParameterValues("cour");
		ts=req.getParameter("ts");
		st=req.getParameter("st");
		dob=req.getParameter("dob");
		month=req.getParameter("mon");
		range=req.getParameter("range");
		url=req.getParameter("url");
		mob=Long.parseLong(req.getParameter("phone"));
		if(hobby==null)
		hobby= new String[]{"NO ANY HOBBY."};
		if(marry==null)
		marry="Single";
		if(gen==null)
		gen="Other";
		if(cour==null)
		cour=new String[] {"No Any Course Package Is Selected"};
		pw.println("<a href=form1.html>GO BACK TO HOME</a>");
		if(gen.equalsIgnoreCase("M"))
		{
			if(age<6)
			pw.println("<h1 style='color:red;text-align:center'> Master "+fn+"-"+ln+" You Are A Baby Boy Now.</h1>");
			else if(age<14)
			pw.println("<h1 style='color:red;text-align:center'> Master "+fn+"-"+ln+" You Are A Mediator Now.</h1>");
			else if(age<=24)
			pw.println("<h1 style='color:red;text-align:center'> Mr. "+fn+"-"+ln+" You Are An Adult Boy. Now.</h1>");
			else if(age<=40)
			pw.println("<h1 style='color:red;text-align:center'> Mr. "+fn+"-"+ln+" You Are A Younger Man Now.</h1>");
			else if(age<=60)
			pw.println("<h1 style='color:red;text-align:center'> Mr. "+fn+"-"+ln+" You Are Nearest To Become Old Man.</h1>");
			else
			pw.println("<h1 style='color:red;text-align:center'> Master "+fn+"-"+ln+" You Are An Old Man Now.</h1>");
			
			
			
			
		}
		else if(gen.equalsIgnoreCase("F"))
		{
			if(age<6)
			pw.println("<h1 style='color:red;text-align:center'> Master "+fn+"-"+ln+" You Are A Baby Girl Now.</h1>");
			else if(age<14)
			{
				if(marry.equalsIgnoreCase("married"))
			pw.println("<h1 style='color:red;text-align:center'> Mrs. "+fn+"-"+ln+" You Are A Mediator Girl Right Now.</h1>");
				else
			pw.println("<h1 style='color:red;text-align:center'> Ms. "+fn+"-"+ln+" You Are A Mediator Girl Right Now.</h1>");
			}		
			else if(age<24)
			{
				if(marry.equalsIgnoreCase("married"))
			pw.println("<h1 style='color:red;text-align:center'> Mrs."+fn+"-"+ln+"You Are An Adult Girl. Now..</h1>");
				else
			pw.println("<h1 style='color:red;text-align:center'> Ms."+fn+"-"+ln+"You Are An Adult Girl. Now.</h1>");
			}	
			
			else if(age<40)
			{
				if(marry.equalsIgnoreCase("married"))
			pw.println("<h1 style='color:red;text-align:center'> Mrs. "+fn+"-"+ln+" You Are A Younger Women Now.. Now..</h1>");
				else
			pw.println("<h1 style='color:red;text-align:center'> Ms. "+fn+"-"+ln+" You Are A Younger Girl Now.. Now.</h1>");
			}
			
			else if(age<60)
			{
				if(marry.equalsIgnoreCase("married"))
			pw.println("<h1 style='color:red;text-align:center'> Mrs. "+fn+"-"+ln+" You Are Neareast To Become Old WoMen Now.</h1>");
				else
			pw.println("<h1 style='color:red;text-align:center'> Ms. "+fn+"-"+ln+" You Are Neareast To Become A Old Girl  Now.</h1>");
			}
			
			else
			if(marry.equalsIgnoreCase("married"))
			pw.println("<h1 style='color:red;text-align:center'> Mrs. "+fn+"-"+ln+" You Are A Old WoMen Now.</h1>");
			else
			pw.println("<h1 style='color:red;text-align:center'> Ms. "+fn+"-"+ln+" You Are A Old WoMen Now.</h1>");
		}
		else
		pw.println("<h1 style='color:red;text-align:center'> Mx. "+fn+"-"+ln+" Sorry You Are Transgender</h1>");
		pw.println("<body background=images/java.jpg;>");
		pw.println("<h2 style='color:#3399ff;text-align:center'>Full Name Is:::-"+fn+"-"+ln);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Email-Id Is:::-"+email);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Conatact No. Is:::-"+mob);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Genger Is:::-"+gen);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Age Is:::-"+age);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Hobby Is:::-"+Arrays.toString(hobby));
		pw.println("<h2 style='color:#3399ff;text-align:center'>Marrital Status Is:::-"+marry);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Full Address Is:::-"+address);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Pincode Is:::-"+pin);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Education level Is:::-"+edu);
		pw.println("<h2 style='color:#3399ff;text-align:center'>All Courses Package Is:::-"+Arrays.toString(cour));
		pw.println("<h2 style='color:#3399ff;text-align:center'>Favourite URL Is:::-"+url);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Study Time Is:::-"+st);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Job Package Excepected Is:::-"+range+"K");
		pw.println("<h2 style='color:#3399ff;text-align:center'>Month In Which You Want To Join The Class Is:::-"+month);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Date Of birth Is:::-"+dob+"</h2><br>");
		
		/*pw.println("<h2 style='color:#3399ff;text-align:center'>Full Name Is:::-"+fn+""+ln);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Full Name Is:::-"+fn+""+ln);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Full Name Is:::-"+fn+""+ln);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Full Name Is:::-"+fn+""+ln);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Full Name Is:::-"+fn+""+ln);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Full Name Is:::-"+fn+""+ln);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Full Name Is:::-"+fn+""+ln);
		pw.println("<h2 style='color:#3399ff;text-align:center'>Full Name Is:::-"+fn+""+ln);
		*/pw.close();
		
		
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}

}
