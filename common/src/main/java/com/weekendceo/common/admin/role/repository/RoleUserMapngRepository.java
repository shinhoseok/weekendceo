package com.weekendceo.common.admin.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weekendceo.common.admin.role.domain.RoleUserMapngVO;

@Repository("roleUserMapngRepository")
public interface RoleUserMapngRepository extends JpaRepository<RoleUserMapngVO, String>{
	public int deleteByUserId(String userId);
}
