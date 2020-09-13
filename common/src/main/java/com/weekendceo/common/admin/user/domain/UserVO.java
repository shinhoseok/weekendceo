package com.weekendceo.common.admin.user.domain;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.weekendceo.common.admin.role.domain.RoleUserMapngVO;
import com.weekendceo.common.common.domain.CommDefaultVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserVO extends CommDefaultVO implements Serializable {
	@Id
	@Column(name = "user_id")
	private String userId;
	@Column(name = "user_pw")
	private String userPw;
	@Column(name = "user_nm")
	private String userNm;
	@Column(name = "del_yn")
	private String delYn;
	@Column(name = "pno")
	private String pno;
	@Column(name = "mbl_pno")
	private String mblPno;
	@Column(name = "zip_no")
	private String zipNo;
	@Column(name = "addr")
	private String addr;
	@Column(name = "email_addr")
	private String emailAddr;
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
	@OneToMany(mappedBy = "userVO")
	private List<RoleUserMapngVO> roleUserMapngs = new ArrayList<>();
	@Builder
	public UserVO(String userId, String userPw, String userNm, String delYn, String pno, String mblPno, String zipNo,
			String addr, String emailAddr, String regId, LocalDateTime regDt, String modId, LocalDateTime modDt) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userNm = userNm;
		this.delYn = delYn;
		this.pno = pno;
		this.mblPno = mblPno;
		this.zipNo = zipNo;
		this.addr = addr;
		this.emailAddr = emailAddr;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
}
