package com.weekendceo.common.admin.role.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.weekendceo.common.admin.user.domain.UserVO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role_user_mapng")
public class RoleUserMapngVO implements Serializable {
	@Id
	@Column(name = "mapng_id")
	private String mapngId;
	
	@Column(name = "role_id")
	private String roleId;
	
	@Column(name = "reg_id")
	private String regId;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "reg_dt")
	private LocalDateTime regDt;
	
	@Column(name = "mod_id")
	private String modId;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "mod_dt")
	private LocalDateTime modDt;
	
	@Column(name = "user_id")
	private String userId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	private UserVO userVO;
	
	@Builder
	public RoleUserMapngVO(String roleId, String mapngId, String regId, LocalDateTime regDt,
			String modId, LocalDateTime modDt) {
		this.roleId = roleId;
		this.mapngId = mapngId;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}
	
}
