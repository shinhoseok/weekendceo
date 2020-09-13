package com.weekendceo.common.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weekendceo.common.idgen.service.impl.IdgenServiceImpl;

@Configuration
public class ContextIdgen {
	//사용자 테이블 시퀀스 관리
	@Bean(name = "usersIdgen")
	public IdgenServiceImpl usersIdgen() {
		IdgenServiceImpl idgenServiceImpl = new IdgenServiceImpl();
		idgenServiceImpl.setCipers(6);
		idgenServiceImpl.setFillChar('0');
		idgenServiceImpl.setPrefix("USR-");
		idgenServiceImpl.setTableName("users");
		return idgenServiceImpl;
	}
	//권한 사용자 매핑 테이블 시퀀스 관리
	@Bean(name = "roleUserMapngIdgen")
	public IdgenServiceImpl roleUserMapngIdgen() {
		IdgenServiceImpl idgenServiceImpl = new IdgenServiceImpl();
		idgenServiceImpl.setCipers(6);
		idgenServiceImpl.setFillChar('0');
		idgenServiceImpl.setPrefix("RUM-");
		idgenServiceImpl.setTableName("role_user_mapng");
		return idgenServiceImpl;
	}
}
