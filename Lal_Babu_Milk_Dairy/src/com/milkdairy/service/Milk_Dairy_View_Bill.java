package com.milkdairy.service;

import java.util.ArrayList;

import com.milkdairy.dao.MilkDairy_Dao_Impl;
import com.milkdairy.dto.MilkDairy_Dto;
import com.milkdairy.service.MailkDairy_Service;
import com.milkdairy.vo.MilkDairy_Vo;

public class Milk_Dairy_View_Bill implements MailkDairy_Service{
	private MilkDairy_Dao_Impl mddi;
	public Milk_Dairy_View_Bill() {
		mddi=new MilkDairy_Dao_Impl();
	}
	private ArrayList<Milk_Dairy_View_Bill> almdvb;
	private String custno;
	private String cusname;
	private String date;
	private String status;
	private String month;
	private double dquantity;
	private double tquantity;
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getTquantity() {
		return tquantity;
	}

	public void setTquantity(double tquantity) {
		this.tquantity = tquantity;
	}
	private double bamount;
	private double pamount;
	private double rate;
	
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getQuantity() {
		return dquantity;
	}

	public void setQuantity(double quantity) {
		this.dquantity = quantity;
	}

	public double getBamount() {
		return bamount;
	}

	public void setBamount(double bamount) {
		this.bamount = bamount;
	}

	public double getPamount() {
		return pamount;
	}

	public void setPamount(double pamount) {
		this.pamount = pamount;
	}

	public double getDamount() {
		return damount;
	}

	public void setDamount(double damount) {
		this.damount = damount;
	}

	private double damount;
	
	@Override
	public ArrayList<Milk_Dairy_View_Bill> view_bill(String name, String month) throws Exception {
      //gathering all customer regular data 
		if(name.equalsIgnoreCase("ALL-CUSTOMERS"))
			almdvb=mddi.getAllCustomerData(name, month+"%");
		//gathering dynamic data from dao class
		else
		almdvb=mddi.viewDynamicBill(month+"%", name);
      //calling total_milk_qunatity method...
    double mil_quantity= mddi.milk_quantiy(name, month+"%");
    this.setTquantity(mil_quantity);
    System.out.println("quan:: "+mil_quantity);
    //month getting
   for(Milk_Dairy_View_Bill mdvb :almdvb) {
    	if(month.contains("2020-01"))
    	mdvb.setMonth("JANUARY");
    	//    setting month value      
    	else if(month.contains("2020-02"))
        	mdvb.setMonth("FEBRUARY");
    	else if(month.contains("2020-03"))
        	mdvb.setMonth("MARCH");
    	else if(month.contains("2020-04"))
        	mdvb.setMonth("APRIL");
    	else if(month.contains("2020-05"))
        	mdvb.setMonth("MAY");
    	else if(month.contains("2020-06"))
        	mdvb.setMonth("JUNE");
    	else if(month.contains("2020-07"))
        	mdvb.setMonth("JULY");
    	else if(month.contains("2020-08"))
        	mdvb.setMonth("AUGUST");
    	else if(month.contains("2020-09"))
        	mdvb.setMonth("SEPTEMBER");
    	else if(month.contains("2020-10"))
        	mdvb.setMonth("OCTOBER");
    	else if(month.contains("2020-11"))
        	mdvb.setMonth("NOVMBER");
    	else if(month.contains("2020-12"))
        	mdvb.setMonth("DECEMBER");
    	else
    		mdvb.setMonth("NO IDEA");
    
    }	
    
    //for each loop
    for(Milk_Dairy_View_Bill mdvb :almdvb) {
    	mdvb.setTquantity(this.getTquantity());
        mdvb.setBamount(mil_quantity*mdvb.getRate());
        System.out.println(":::::::::::::::::::::::::::::::::::::"+mdvb.getMonth());
    }
    this.setBamount(mil_quantity*this.getRate());
    System.out.println("bill:: "+this.getBamount());
    return almdvb;
	}
	
	@Override
	public String register_new_customer(MilkDairy_Dto mddto, MilkDairy_Vo mdvo) throws Exception {
		return null;
		// TODO Auto-generated method stub

	}


}
