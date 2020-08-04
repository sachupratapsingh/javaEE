package com.corona.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corona.service.Corona_Recovery;

@WebServlet("/CMS_Recovery")
public class CoronaMainServlet_Recovery extends HttpServlet {
	Corona_Recovery cr=null;
    public CoronaMainServlet_Recovery() throws Exception {
        
        cr=new Corona_Recovery();
    }

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	ArrayList<Corona_Recovery> crecov=null;
	PrintWriter pw=null;
	String type=null;
	pw=res.getWriter();
	res.setContentType("text/html");
	try {
		type=req.getParameter("option");
		if(type==null || type.equalsIgnoreCase("none"))
		crecov=cr.getStateList();
		else if(type.equalsIgnoreCase("htl"))
		crecov=cr.higher_to_lower();
		else if(type.equalsIgnoreCase("lth"))
		crecov=cr.lower_to_higher();
		pw.println("<head>");
		
		
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<link rel=\"stylesheet\" href=\"css/corona.css\">");
		pw.println("<div class=\"marry\">");
		pw.println("<form method='post'>");
		pw.println("<table align=center width:100% bgcolor='#ffff99'>");
		pw.println("<tr bgcolor='green'><th>STATE</th><th>RECOVER PERCATANGE</th><th>SORT BY<SELECT class='marry1' name='option'><OPTION value='none'>---SORT-BY---</OPTION><OPTION value='htl'>HIGH TO LOW</OPTION><OPTION value=lth>LOW TO HIGH</OPTION></SELECT></th><th><input type='submit' value='SUBMIT' class='but'></th><th><input type='reset' value='RESET' class='but'></th></tr>");
		
		for(Corona_Recovery cr:crecov) {
		pw.println("<tr style='padding:5px;' bgcolor='cyan'><td style='padding:5px'>"+cr.getState()+"</td><td>"+cr.getRecovery_percentange()+"</td></tr>");	
		}
		pw.println("</table></form></body><h2 style='text-align:center'><a style='color:brown;text-align:center' href='corona_patient.html'>HOME PAGE"+"</h2>");
	} catch (Exception e) {
		pw.println("<h2 style='color:brown;text-align:center'>INTERNAL ERROR"+"</h2>");
		
		e.printStackTrace();
	}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
