package com.corona.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.corona.bo.CoronaBo;
import com.corona.vo.CoronaVo;

public class CoronaDaoImpl implements CoronaDao {

	private static final String INSERT_QUERY="insert into corona_patient values(?,?,?,?,?,?,?,?,?,SEQ1.NEXTVAL)";
	private static final String UPDATE_QUERY="update corona_patient set status=?,discharge_date=?,dead_date=? where patient_name=? AND patient_mob=?"; 
	private static final String VIEW_QUERY="select * from corona_Patient";
	private static final String EDIT_QUERY="update corona_patient set patient_name=?,patient_age=?,patient_mob=?,patient_home_state=?,gender=? where patient_no=?";
	private static final String SPECIFIC_VIEW_BY_STATE="select * from corona_patient where patient_home_state=?";
	private static final String SPECIFIC_VIEW_BY_STATUS="select * from corona_patient where status=?";
	private static final String SPECIFIC_VIEW_BY_NAME="select * from corona_patient where patient_name=?";
	private static final String DELETE="delete from corona_patient  where patient_no=?";
	private static final String DISCHARGED_NO="select count(*) AS count from corona_patient where patient_home_state=? AND status='DISCHARGED'";
	private static final String TOTAL_NO="select count(*) AS count from corona_patient where patient_home_state=?";
	//insert operation
	@Override
	public int insert(CoronaBo cbo) throws Exception {
		PreparedStatement ps=null;
		ps=getConnection().prepareStatement(INSERT_QUERY);
		
		//inserting value
		ps.setString(1,cbo.getPatient_name());
		ps.setInt(2, cbo.getAge());
		ps.setLong(3, cbo.getMob());
		ps.setString(4, cbo.getHome_state());
		ps.setString(6, cbo.getStatus());
		ps.setString(5, cbo.getGender());
		ps.setString(7, cbo.getAdmit_date());
		ps.setString(8, cbo.getDischarge_date());
		ps.setString(9, cbo.getDead_date());
		int i=ps.executeUpdate();
		System.out.println(i+" row inserted.");
		getConnection().close();
		return i;
	}
	
	//update
	@Override
	public int update(CoronaBo cbo) throws Exception {
		PreparedStatement ps=getConnection().prepareStatement(UPDATE_QUERY);
		ps.setString(1,cbo.getStatus());
		ps.setString(2,cbo.getDischarge_date());
		ps.setString(4, cbo.getPatient_name());
		ps.setLong(5, cbo.getMob());
		ps.setString(3,cbo.getDead_date());
		int i=ps.executeUpdate();
		System.out.println(i+" Row Updated");
		getConnection().close();
		return i;
	}
	
	
	//view
	@Override
	public ArrayList<CoronaVo> view(String value,String name,String state,String status) throws Exception{
		PreparedStatement ps=null;
		//System.out.println("DAO::"+value);
		if(value!=null && value.equalsIgnoreCase("state")) {
			ps=getConnection().prepareStatement(SPECIFIC_VIEW_BY_STATE);
			ps.setString(1,state );
			}
		else if(value!=null && value.equalsIgnoreCase("status")) {
			ps=getConnection().prepareStatement(SPECIFIC_VIEW_BY_STATUS);
			ps.setString(1, status);
		    }
		else if(value!=null && value.equalsIgnoreCase("name")) {
			ps=getConnection().prepareStatement(SPECIFIC_VIEW_BY_NAME);
			ps.setString(1, name);
			}
		else
		ps=getConnection().prepareStatement(VIEW_QUERY);
		ResultSet rs=ps.executeQuery();
		ArrayList<CoronaVo> li=new ArrayList<CoronaVo>();
		while(rs.next()) {
			CoronaVo cvo=new CoronaVo();
			cvo.setPatient_name(rs.getNString(1));
			cvo.setAge(rs.getNString(2));
			cvo.setMob(rs.getNString(3));
			cvo.setHome_state(rs.getNString(4));
			cvo.setGender(rs.getNString(5));
			cvo.setStatus(rs.getNString(6));
			cvo.setAdmit_date(rs.getNString(7));
			cvo.setDischarge_date(rs.getNString(8));
			cvo.setDead_date(rs.getNString(9));
			cvo.setPatient_no(rs.getString(10));
			li.add(cvo);
		}
		getConnection().close();
		return li;
		}
	
	//recovery rate...
	@Override
	public int total_patient(String state) throws NamingException, SQLException {
	 int x=0;
	 Connection con=getConnection();
	 PreparedStatement ps=con.prepareStatement(TOTAL_NO);
	 ps.setString(1, state);
	 ResultSet rs=ps.executeQuery();
	 //System.out.println(" rows execuetd..");
	 
	 while(rs.next()) {
	 x=rs.getInt(1);
	//System.out.println(x+":::::");	
	}int i=ps.executeUpdate();
	con.close();
	return x;}
	
	
	//recovery rate1...
	@Override
		public int total_discharge_patient(String state) throws NamingException, SQLException {
		 int x=0;
		 Connection con=getConnection();
		 PreparedStatement ps=con.prepareStatement(DISCHARGED_NO);
		 ps.setString(1, state);
		 ResultSet rs=ps.executeQuery();
		 
		 //System.out.println(" rows execuetd.");
		 while(rs.next()) {
			 x=rs.getInt(1);
		//	 System.out.println(x+"y::::::");	
			} int i=ps.executeUpdate();
			con.close();
			return x;}
	
	
	
	
	
	
	
	
	  //edit
	@Override
	public int edit(CoronaBo cbo) throws Exception{
		PreparedStatement ps=getConnection().prepareStatement(EDIT_QUERY);
		ps.setString(1, cbo.getPatient_name());
		ps.setInt(2, cbo.getAge());
		ps.setLong(3, cbo.getMob());
		ps.setString(4, cbo.getHome_state());
		ps.setString(5, cbo.getGender());
		ps.setString(6, cbo.getPatient_no());
		int i=ps.executeUpdate();
		System.out.println(i+" row edit & update");
		getConnection().close();
		return i;
		
		
	}
	  
	//delete
	@Override
	public int delete(String patient_no) throws Exception {
		PreparedStatement ps=null;
		ps=getConnection().prepareStatement(DELETE);
		ps.setString(1, patient_no);
		int x=ps.executeUpdate();
		System.out.println(x +" row deleted Sucessfully.");
		getConnection().close();
		return x;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//get pooled connection object from con-pool;
	private Connection getConnection() throws NamingException, SQLException {
		InitialContext ic=new InitialContext();
		DataSource ds=(DataSource)ic.lookup("java:/comp/env/dsjndi");
		Connection con=ds.getConnection();
		return con;
		
		
	}

}
