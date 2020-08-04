package com.corona.service;

import com.corona.dto.CoronaDto;
import com.corona.vo.CoronaVo;

public interface CoronaService {
   public String register(CoronaDto cdto,CoronaVo cvo) throws Exception;
}
