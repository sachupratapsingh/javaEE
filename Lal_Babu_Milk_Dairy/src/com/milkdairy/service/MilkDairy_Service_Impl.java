package com.milkdairy.service;

import java.util.ArrayList;

import com.milkdairy.bo.MilkDairy_Bo;
import com.milkdairy.dao.MilkDairy_Dao_Impl;
import com.milkdairy.dto.MilkDairy_Dto;
import com.milkdairy.vo.MilkDairy_Vo;

public class MilkDairy_Service_Impl implements MailkDairy_Service{
	private String name;
	private String address;
	private int rate;
	private long mob=0l;
	private String slot;
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public long getMob() {
		return mob;
	}
	public void setMob(long mob) {
		this.mob = mob;
	}
	private MilkDairy_Dto mddto;
	private MilkDairy_Bo mdbo;
	private MilkDairy_Dao_Impl mddaoi;
    public MilkDairy_Service_Impl() {
    	mddto=new MilkDairy_Dto();
    	mdbo=new MilkDairy_Bo();
    	mddaoi=new MilkDairy_Dao_Impl();
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	@Override
	public String register_new_customer(MilkDairy_Dto mddto,MilkDairy_Vo mdvo) throws Exception {
		//storing value in current class-varables
		this.setName(mddto.getName());
		this.setAddress(mddto.getAddress());
		this.setRate(mddto.getRate());
		this.setMob(mddto.getMob());
		this.setSlot(mddto.getSlot());
		if(this.getAddress()=="" || this.getAddress()==null) {
			this.setAddress("NO ANY SPECIFIED ADDRESS IS AVAILABLE.");	
		}
		if(this.slot=="" || this.slot==null || this.slot.equalsIgnoreCase("---CHOOSE SLOT---")) {
			this.setSlot("NO ANY SPECIFIED SLOT HAS CHOOSEN.");	
		}
		mdbo.setAddress(address);
		mdbo.setName(name);
		mdbo.setRate(rate);
		mdbo.setMob(mob);
		mdbo.setSlot(slot);
		String value=mddaoi.newCustomerInsert(mdbo);
		System.out.println("value is:: "+value);
		return value;
	}
	@Override
	public ArrayList<Milk_Dairy_View_Bill> view_bill(String name, String month) throws Exception {
		return null;
		// TODO Auto-generated method stub
		
	}
	
	
	
}
