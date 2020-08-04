package com.corona.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

import com.corona.dao.CoronaDaoImpl;
import com.corona.vo.CoronaVo;

public class Corona_Recovery {
	//ArrayList<Integer> discharge_patient;
	private ArrayList<Corona_Recovery> cr;
	private String recovery_percentange;
	private String state;
	private float percentage;
	//ArrayList<String> recovery_percent;
	CoronaDaoImpl cdi;
	List<String> li1;
	ArrayList<CoronaVo> li;
	Set<String> s;
	String[] st;
	public Corona_Recovery() throws Exception {
		
		cdi=new CoronaDaoImpl();
		li1=new ArrayList<String>();
	}


	public ArrayList<Corona_Recovery> getStateList() throws Exception {
		
		li=cdi.view(null, "", "", null);
		for(CoronaVo cvo:li ) 
			li1.add(cvo.getHome_state());
			
		 s=new LinkedHashSet<String>(li1);
			st=new String[s.size()];
			st=s.toArray(st);
		
		
		CoronaDaoImpl cdi=new CoronaDaoImpl();
		cr=new ArrayList<Corona_Recovery>();
		for(int i=0;i<this.st.length;i++) {
			Corona_Recovery crecov=new Corona_Recovery();
			System.out.println(st[i]);
			System.out.println(cdi.total_patient(st[i]));
			float x=cdi.total_patient(st[i]);
			float y=cdi.total_discharge_patient(st[i]);
			//System.out.println(x+":::::::::----->"+y+"::::::::");
			float z=(y/x)*100;
			String format=String.format("%.2f", z);
			String percentage=format+"%";
			crecov.state=st[i];
			crecov.percentage=z;
			crecov.recovery_percentange=percentage;
			cr.add(crecov);
		}//for loop
		return cr;
	}//method


	public String getRecovery_percentange() {
		return this.recovery_percentange;
	}


	public float getPercentage() {
		return this.percentage;
	}


	public String getState() {
		return this.state;
	}
	

    public ArrayList<Corona_Recovery> higher_to_lower() throws Exception{
    	ArrayList<Corona_Recovery> crecov=this.getStateList();
    	
    	Corona_Recovery crec=new Corona_Recovery();
	for(int i=0;i<crecov.size();i++) {
		for(int j=i+1;j<crecov.size();j++)
		{
			if(crecov.get(i).getPercentage()<crecov.get(j).getPercentage()) {
				crec=crecov.get(i);
			    crecov.set(i, crecov.get(j));
			    crecov.set(j, crec);
			}//if
			}//for loop
	}//for loop
	return crecov;
	}//higher_to_lower method;
    
    
    public ArrayList<Corona_Recovery> lower_to_higher() throws Exception{
    	ArrayList<Corona_Recovery> crecov=this.getStateList();
    	
    	Corona_Recovery crec=new Corona_Recovery();
	for(int i=0;i<crecov.size();i++) {
		for(int j=i+1;j<crecov.size();j++)
		{
			if(crecov.get(i).getPercentage()>crecov.get(j).getPercentage()) {
				crec=crecov.get(i);
			    crecov.set(i, crecov.get(j));
			    crecov.set(j, crec);
			}//if
			}//for loop
	}//for loop
	return crecov;
	}//higher_to_lower method;
}
