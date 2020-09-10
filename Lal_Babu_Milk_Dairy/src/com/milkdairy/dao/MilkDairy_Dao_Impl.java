package com.milkdairy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.milkdairy.bo.MilkDairy_Bo;
import com.milkdairy.dto.MilkDairy_Dto;
import com.milkdairy.service.Milk_Dairy_View_Bill;

public class MilkDairy_Dao_Impl implements MilkDairy_Dao {
	//sql query statement
	private static final String insert_new="insert into cus_stat_det values(?,?,?,SEQ3.NEXTVAL,?,?)";
	private static final String insert_exist="INSERT INTO CUS_DYN_DET VALUES(?,?,?)";
	private static final String view_cust_data="SELECT * FROM CUS_STAT_DET ORDER BY CUSTOMER_NO";
	private static final String view_cus_dyn_data="SELECT * FROM CUS_DYN_DET CDT NATURAL JOIN CUS_STAT_DET WHERE  MILK_DELIVER_DATE LIKE ? and customer_name=?  ORDER BY MILK_DELIVER_DATE";
	private static final String total_milk_quantity="SELECT SUM(MILK_QUANTITY) FROM CUS_DYN_DET WHERE CUSTOMER_NAME=? AND MILK_DELIVER_DATE LIKE ?"; 
	private static final String all_cust_total_milk="SELECT SUM(MILK_QUANTITY),CUSTOMER_NAME,CUSTOMER_NO,RATE FROM CUS_DYN_DET CDT NATURAL JOIN CUS_STAT_DET WHERE MILK_DELIVER_DATE LIKE ? group by CUSTOMER_NAME,CUSTOMER_NO,RATE ORDER BY CUSTOMER_NO";
	private static final String update_cus_data="UPDATE CUS_DYN_DET SET  MILK_QUANTITY=?  WHERE CUSTOMER_NAME=? AND  MILK_DELIVER_DATE=?";
	private static final String delete_cus_data="DELETE FROM CUS_DYN_DET WHERE CUSTOMER_NAME=? AND  MILK_DELIVER_DATE=?";
	private static final String authenticate="SELECT COUNT(*) FROM USERLOGIN WHERE USERID=? AND PWD=?";
	//new customer insertion method
	@Override
	public String newCustomerInsert(MilkDairy_Bo mdbo) throws Exception {
		Connection con=this.getConnection();
		PreparedStatement ps=con.prepareStatement(insert_new);
		ps.setNString(1, mdbo.getName());
		ps.setInt(2, mdbo.getRate());
		ps.setNString(3, mdbo.getAddress());
		ps.setLong(4,mdbo.getMob() );
		ps.setNString(5, mdbo.getSlot());
		System.out.println(mdbo.getSlot()+"::::");
		int i=ps.executeUpdate();
		return "Hurray...!"+i+" rows inserted...";

	}

	//existing customer data entry
	@Override
	public String existedCustomerInsert(String name,String date,double milk_quantity) throws Exception{
		Connection con=null;
		PreparedStatement ps=null;
		con=this.getConnection();
		ps=con.prepareStatement(insert_exist);
		ps.setString(1, name);
		ps.setString(2, date);
		ps.setDouble(3, milk_quantity);
		int i=ps.executeUpdate();
		return "Hurray...!"+i+" rows inserted..."; 
	}

	//view all static data of existed customer
	@Override
	public ArrayList<MilkDairy_Dto> viewCustomerData() throws Exception{
		ArrayList<MilkDairy_Dto> armddto=new ArrayList<MilkDairy_Dto>();

		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		con=this.getConnection();
		ps=con.prepareStatement(view_cust_data);
		rs=ps.executeQuery();
		while(rs.next()) {
			MilkDairy_Dto mddto=new MilkDairy_Dto();
			mddto.setName(rs.getString(1));
			mddto.setRate(rs.getInt(2));
			mddto.setAddress(rs.getString(3));
			mddto.setCustno(rs.getNString(4));
			mddto.setMob(rs.getLong(5));
			mddto.setSlot(rs.getNString(6));
			armddto.add(mddto);
		}
		return armddto;
	}

	//view all dynamic data of customer
	@Override
	public ArrayList<Milk_Dairy_View_Bill> viewDynamicBill(String month,String name) throws Exception{

		ArrayList<Milk_Dairy_View_Bill> m_dvb=new ArrayList<Milk_Dairy_View_Bill>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		con=this.getConnection();
		ps=con.prepareStatement(view_cus_dyn_data);

		//System.out.println(month);
		ps.setString(1, month);
		ps.setNString(2, name);
		//getting resultset object
		rs=ps.executeQuery();
		while(rs.next()) {
			Milk_Dairy_View_Bill mdvb=new Milk_Dairy_View_Bill();
			mdvb.setCusname(rs.getNString(1));
			mdvb.setDate(rs.getNString(2));
			mdvb.setQuantity(rs.getDouble(3));
			mdvb.setRate(rs.getDouble(4));
			mdvb.setCustno(rs.getString(6));
			//mdvb.setQuantity(rs.getDouble());
			m_dvb.add(mdvb);

		}
		return m_dvb;
	}

	//view all customer dynamic data.
	public ArrayList<Milk_Dairy_View_Bill> getAllCustomerData(String name,String month) throws SQLException, NamingException,Exception{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<Milk_Dairy_View_Bill> mdvb1=null;
		mdvb1=new ArrayList<Milk_Dairy_View_Bill>();
		con=this.getConnection();
		ps=con.prepareStatement(all_cust_total_milk);
		ps.setNString(1, month);
		rs=ps.executeQuery();
		while(rs.next()) {
			Milk_Dairy_View_Bill mdvb=new Milk_Dairy_View_Bill();
			mdvb.setQuantity(rs.getDouble(1));
			mdvb.setCusname(rs.getNString(2));
			mdvb.setCustno(rs.getNString(3));
			mdvb.setRate(rs.getDouble(4));
			mdvb1.add(mdvb);
		}
		return mdvb1;
	}

	//gathering total liter of milk
	public double milk_quantiy(String name,String date) throws Exception{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		con=this.getConnection();
		ps=con.prepareStatement(total_milk_quantity);
		ps.setNString(1, name);
		ps.setNString(2, date);
		rs=ps.executeQuery();
		rs.next();
		double d=rs.getDouble(1);
		System.out.println(date);
		return d;
	}


	//UPDATE OPERTION
	public String update_cust(double quantity,String name,String date) throws SQLException, NamingException,Exception {
		Connection con=null;
		PreparedStatement ps=null;
		con=this.getConnection();
		ps=con.prepareStatement(update_cus_data);
		ps.setDouble(1, quantity);
		ps.setNString(2, name);
		ps.setNString(3, date);
		int i=ps.executeUpdate();
		return i+" Rows Updated.";
	}

	//DELETE OPERATION
	public String delete_cus(String name,String date) throws SQLException, NamingException {
		Connection con=null;
		PreparedStatement ps=null;
		con=this.getConnection();
		ps=con.prepareStatement(delete_cus_data);
		ps.setNString(1, name);
		ps.setNString(2, date);
		//		System.out.println(name);
		//		System.out.println(date);
		int i=ps.executeUpdate();
		return i+" Rows Deleted Successfully..";
	}

	//Authentication of login user
	public int authenticate(String user,String pwd) throws Exception
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		con=this.getConnection();
		ps=con.prepareStatement(authenticate);
		ps.setNString(1,user);
		ps.setNString(2, pwd);
		rs=ps.executeQuery();
		rs.next();
		String s=rs.getNString(1);
		int j=rs.getInt(1);
		return j;
		
	}


	//get connection from pool;
	private Connection getConnection() throws SQLException, NamingException {
		Connection con=((DataSource)new InitialContext().lookup("java:/comp/env/dsjndi")).getConnection();
		return con;
	}


}
