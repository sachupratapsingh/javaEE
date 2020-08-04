package com.corona.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import com.corona.bo.CoronaBo;
import com.corona.vo.CoronaVo;

public interface CoronaDao {
	public int insert(CoronaBo cbo) throws Exception;
	public int update(CoronaBo cbo)throws Exception;
	public ArrayList<CoronaVo> view(String value,String name,String state,String Status)throws Exception;
	public int edit(CoronaBo cbo) throws Exception;
    public int delete(String patient_no) throws Exception;
	int total_discharge_patient(String state) throws NamingException, SQLException;
	int total_patient(String state) throws NamingException, SQLException;
}
