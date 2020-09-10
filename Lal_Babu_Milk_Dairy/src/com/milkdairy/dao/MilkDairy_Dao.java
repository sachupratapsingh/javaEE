package com.milkdairy.dao;

import java.util.ArrayList;

import com.milkdairy.bo.MilkDairy_Bo;
import com.milkdairy.controller.MilkDairy_View_Bill;
import com.milkdairy.dto.MilkDairy_Dto;
import com.milkdairy.service.Milk_Dairy_View_Bill;

public interface MilkDairy_Dao {
public String newCustomerInsert(MilkDairy_Bo mdbo) throws Exception;
public String existedCustomerInsert(String name,String date,double milk_quantity) throws Exception;
public ArrayList<MilkDairy_Dto> viewCustomerData() throws Exception;
//public ArrayList<MilkDairy_View_Bill> viewDynamicBill() throws Exception;
public ArrayList<Milk_Dairy_View_Bill> viewDynamicBill(String month, String name) throws Exception;
}
