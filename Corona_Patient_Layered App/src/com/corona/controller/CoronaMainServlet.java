package com.corona.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corona.dto.CoronaDto;
import com.corona.service.CoronaService;
import com.corona.service.CoronaServiceImpl;
import com.corona.vo.CoronaVo;

@WebServlet("/CoronaServlet")
public class CoronaMainServlet extends HttpServlet {
	private CoronaDto cdto=null;
    private CoronaVo cvo=null; 
    private CoronaService csi=null;
    
	
	public void init(ServletConfig config) throws ServletException {
		
		csi=new CoronaServiceImpl();
	}

	public void destroy() {
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null; 
		String patient_name=null;
		 String status=null;
	     String admit_date=null;
		 String discharge_date=null;
		 String dead_date=null;
		 String gender=null;
		 String home_state=null;
		 String mob;
		 String age;
		 String patient_no;
		 cdto=new CoronaDto();
		 cvo=new CoronaVo();
		 //using req param for getting value.
		 pw=res.getWriter();
		 res.setContentType("text/html");
		 patient_name=req.getParameter("pname").trim();
		 status=req.getParameter("pstat");
		 admit_date=req.getParameter("pad");
		 discharge_date=req.getParameter("pad1");
		 dead_date=req.getParameter("pad2");
		 gender=req.getParameter("pgen");
		 home_state=req.getParameter("state");
		 mob=req.getParameter("pmob").trim();
		 age=req.getParameter("page");
		 patient_no=req.getParameter("pno");
		 //passing data to the value object class
		 cvo.setPatient_name(patient_name);
		 cvo.setAge(age);
		 cvo.setAdmit_date(admit_date);
		 cvo.setDischarge_date(discharge_date);
		 cvo.setDead_date(dead_date);
		 cvo.setGender(gender);
		 cvo.setHome_state(home_state);
		 cvo.setStatus(status);
		 cvo.setMob(mob);
		 cvo.setPatient_no(patient_no);
		 //passing data to the Data Transfer Object Class.
		 cdto.setPatient_name(cvo.getPatient_name());
		 if(cvo.getAge()!=null)
		 cdto.setAge(Integer.parseInt(cvo.getAge()));
		 cdto.setDead_date(cvo.getDead_date());
		 cdto.setDischarge_date(cvo.getDischarge_date());
		 cdto.setGender(cvo.getGender());
		 cdto.setHome_state(cvo.getHome_state());
		 cdto.setMob(Long.parseLong(cvo.getMob()));
		 cdto.setStatus(cvo.getStatus());
		 cdto.setAdmit_date(cvo.getAdmit_date());
		 cdto.setPatient_no(cvo.getPatient_no());
		 
		 try {
			 String s=csi.register(cdto,cvo);
			 pw.println("<h2 style='color:red;text-align:center'>"+s+"</h2>");
			 pw.println("<h2 style='color:blue;text-align:center'>"+"<a href='corona_patient.html'>GO HOME</a>"+"</h2>");
		 }
		 catch( java.sql.SQLIntegrityConstraintViolationException e1) {System.out.println(e1);
		 e1.printStackTrace();
		 pw.println("<h2 style='color:blue;text-align:center'>"+"User Already Registered So,Please Try Again Along With Proper Data...</h2>");
		 pw.println("<h2 style='color:blue;text-align:center'>"+"<a href='corona_patient.html'>GO HOME</a>"+"</h2>");}
		 
		 catch(Exception e ) {
		 System.out.println(e);
		 e.printStackTrace();
		 pw.println("<h2 style='color:blue;text-align:center'>"+"Internal  Error Try Again Along With Proper Data...</h2>");
		 pw.println("<h2 style='color:blue;text-align:center'>"+"<a href='corona_patient.html'>GO HOME</a>"+"</h2>");
		 }
		 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
