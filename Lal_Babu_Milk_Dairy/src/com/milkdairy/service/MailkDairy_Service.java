package com.milkdairy.service;

import java.util.ArrayList;

import com.milkdairy.dto.MilkDairy_Dto;
import com.milkdairy.vo.MilkDairy_Vo;

public interface MailkDairy_Service {
    public String register_new_customer(MilkDairy_Dto mddto,MilkDairy_Vo mdvo) throws Exception;
    public ArrayList<Milk_Dairy_View_Bill> view_bill(String name,String month) throws Exception;
}
