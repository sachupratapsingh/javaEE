package com.corona.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corona.dao.CoronaDaoImpl;
import com.corona.vo.CoronaVo;

import serp.util.Numbers;

@WebServlet("/cms_view")
public class CoronaMainServlet_view extends HttpServlet {
	 CoronaDaoImpl cdi;
	 int k=0;
    public CoronaMainServlet_view() {
    	super();
    	cdi=new CoronaDaoImpl();
        // TODO Auto-generated constructor stub
    }
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null;
		String state=null;
		String status=null;
		String search_value=null;
		pw=res.getWriter();
		
		//getting data from request body.
		name=req.getParameter("patname");
		state=req.getParameter("state");
		status=req.getParameter("status");
		search_value=req.getParameter("option");
		System.out.println(search_value+":::"+name);
		int i=1,j=0;
		res.setContentType("text/html");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>All Corona Patient Data</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<script type='text/javascript' src='js/corona1.js'></script>");
		pw.println("<link rel=\"stylesheet\" href=\"css/corona.css\">");
		pw.println("<div class=\"marry\">");
		pw.println("<form action='cms_view' method='post' onsubmit='return check(this)'>");
		pw.println("<table width:100% bgcolor='pink'>");
		pw.println("<label ><b style='margin-left:20px'>SEARCH-BY</b></label>");
		pw.println("<label ><b style='margin-left:230px'>PATIENT-STATE</b></label>");
		pw.println("<label><b style='margin-left:160px'>PATIENT-NAME</b></label>");
		pw.println("<label><b style='margin-left:215px'>PATIENT-STATUS</b><br></label>");
		pw.println("<select name='option' id='option1' class='marry1' margin-left:400px;\r\n" + 
				"  margin-right:200px; onchange='fun2(this.value)' >");
		pw.println("<option value='spby'>"+"-----SEARCH PATIENT BY-----"+"</option>");
		pw.println("<option value='state'>"+"STATE"+"</option>");
		pw.println("<option value='name'>"+"NAME"+"</option>");
		pw.println("<option value='status'>"+"STATUS"+"</option>");
		pw.println("</select>&nbsp;&nbsp;&nbsp;&nbsp;");
		
		
		pw.println("<select name='state' id='state1' class='marry1' disabled >");
		try {
			//getting list of Object Of class cvo(CoronaVo class);
			ArrayList<CoronaVo> li=cdi.view(null, name, state, status);
			ArrayList<CoronaVo> list=cdi.view(search_value,name,state,status);
			
			//creating list of String for storing state,patient name & status;
			List<String> li1=new ArrayList<String>();//for storing state
			List<String> li2=new ArrayList<String>();//for storing patient-name
			List<String> li3=new ArrayList<String>();//for storing status
			//storing the state details to the String List
			for(CoronaVo cvo:li ) {
				li1.add(cvo.getHome_state());
				li2.add(cvo.getPatient_name());
				li3.add(cvo.getStatus());
				}
		   //avoid duplicate String from the List.and Create one new String List
			Set<String> s=new LinkedHashSet<String>(li1);
			Set<String> s1=new LinkedHashSet<String>(li2);
			Set<String> s2=new LinkedHashSet<String>(li3);
			//creating String Array for Storing value from List.
			String[] st=new String[s.size()];//state
			String[] st1=new String[s1.size()];//name
			String[] st2=new String[s2.size()];//status
			//convert String List to the String Array.
		    st=s.toArray(st);
		    st1=s1.toArray(st1);
		    st2=s2.toArray(st2);
			for(CoronaVo cvo:list ) {
				if(i==1) {
					pw.println("<option id='state'>"+"-----SELECT STATE-----"+"</option>");
					for(int j1=0;j1<s.size();j1++)
			pw.println("<option>"+st[j1]+"</option>");	
			pw.println("</select>&nbsp;&nbsp;&nbsp;&nbsp;");
			
			
			pw.println("<select name='patname' id='patname1' class='marry1' disabled>");
			pw.println("<option>"+"-----SELECT PATIENT-NAME-----"+"</option>");
					for(int j1=0;j1<s1.size();j1++)
				pw.println("<option id='pname'>"+st1[j1]+"</option>");	
	          //  pw.println("</div>");
				pw.println("</select>");
				
				
			pw.println("<select name='status' id='status1' class='marry1' disabled>");
			pw.println("<option>"+"-----SELECT PATIENT-STATUS-----"+"</option>");
					for(int j1=0;j1<s2.size();j1++)
				pw.println("<option>"+st2[j1]+"</option>");	
	          //  pw.println("</div>");
				pw.println("</select><div style='text-align:center' ><span id='sp1' style='color:red' class='h1'></span><span id='sp2' style='color:red' class='h1'></span><span text-align='center' id='sp3' style='color:red' class='h1'></span><span id='sp4' style='color:red' class='h1'></span></div>&nbsp;&nbsp;&nbsp;&nbsp;");}
				
				if(i==1)
		pw.println("<tr bgcolor='red'><th style='padding:5px';>PATIENT-NAME</th><th>PATIENT-AGE</th><th>PATIENT-MOB</th><th>PATIENT-HOME-STATE</th><th>GENDER</th><th>STATUS</th><th>ADMIT-DATE</th><th>DISCHARGE-DATE</th><th>DEAD-DATE</th><th>PATIENT-NO</th><th>EDIT</th><th>DELETE</th></tr>");
		pw.println("<tr bgcolor='cyan'><td style='padding:5px';>"+cvo.getPatient_name()+"</td><td>"+cvo.getAge()+"</td><td>"+cvo.getMob()+"</td><td>"+cvo.getHome_state()+"</td><td>"+cvo.getGender()+"</td><td>"+cvo.getStatus()+"</td><td>"+cvo.getAdmit_date()+"</td><td>"+cvo.getDischarge_date()+"</td><td>"+cvo.getDead_date()+"</td><td>"+cvo.getPatient_no()+"</td><td>"+"<a href='corona_patient.html'>EDIT</a></td><td>"+"<a href='cms_delete?delete=true&pno="+(cvo.getPatient_no())+"'onclick='return ondelete()'"+">DELETE</a>"+"</td>"+"</tr>");
				   i++;k++;
			}
			pw.println("<td></td><td></td><td></td><td></td><td></td><td><td></td></tr>");
			//pw.println("</select>");
			pw.println("</table><br>");
			pw.println("<div class='b'>");
			pw.println("<input type='submit' value='SEARCH' name='sub' id='sub1' class='but'  ");
			pw.println("</div></div>");
			pw.println("</form>");
			pw.println("</body>");
			pw.println("</html>");
			}//try
		catch (Exception e) {
        System.out.println("Error is:: "+e);
			e.printStackTrace();
		}//catch
		
		if(i==1) 
		pw.println("<h1 style='color:red;text-align:center'>No Records.</h1>");
		pw.println("<h1 style='color:blue;text-align:center'><a style='text-decoration:none' href='corona_patient.html'>Home Page</a></h1>");
		if(state!=null || status!=null || name!=null)
		pw.println("<h1 style='color:blue;text-align:center'><a style='text-decoration:none' href='cms_view'>Back Page</a></h1>");
				
	}//if
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
