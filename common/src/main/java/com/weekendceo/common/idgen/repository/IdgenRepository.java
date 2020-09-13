package com.weekendceo.common.idgen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weekendceo.common.idgen.domain.IdgenVO;


@Repository("idgenRepository")
public interface IdgenRepository extends JpaRepository<IdgenVO, String> {
	public IdgenVO findByTableName(String tableName);
}
