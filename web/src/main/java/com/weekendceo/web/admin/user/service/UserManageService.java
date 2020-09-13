package com.weekendceo.web.admin.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.weekendceo.common.admin.user.domain.UserVO;

public interface UserManageService {
	//사용자관리 리스트
	public Page<UserVO> selectUserList(Pageable pageable, UserVO userVO) throws Exception;
	//사용자관리 상세보기
	public UserVO selectUserDetail(UserVO userVO) throws Exception;
	//이메일 중복체크
	public Integer selectEmailAddrChk(UserVO userVO) throws Exception;
	//사용자등록
	public void insertUserProc(UserVO userVO) throws Exception;
	//사용자 삭제
	public void deleteUserProc(UserVO userVO) throws Exception;
	//사용자이메일 수정시 중복체크
	public boolean selectEmailUptChk(UserVO userVO) throws Exception;
	//사용자 수정
	public void updateUserProc(UserVO userVO) throws Exception;
}
