package com.weekendceo.web.admin.user.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.weekendceo.common.admin.role.domain.Role;
import com.weekendceo.common.admin.role.domain.RoleUserMapngVO;
import com.weekendceo.common.admin.role.repository.RoleUserMapngRepository;
import com.weekendceo.common.admin.user.domain.UserVO;
import com.weekendceo.common.admin.user.repository.UserManageRepository;
import com.weekendceo.common.common.GlobalConstants;
import com.weekendceo.common.idgen.service.IdgenService;
import com.weekendceo.common.util.StringUtil;
import com.weekendceo.web.admin.user.service.UserManageService;

@Service("userManageService")
@Transactional
public class UserManageServiceImpl implements UserManageService {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name = "userManageRepository")
	private UserManageRepository userManageRepository;
	
	@Resource(name = "usersIdgen")
	private IdgenService usersIdgen;
	
	@Resource(name = "roleUserMapngRepository")
	private RoleUserMapngRepository roleUserMapngRepository;
	
	@Resource(name = "roleUserMapngIdgen")
	private IdgenService roleUserMapngIdgen;
	
	//사용자관리 리스트
	public Page<UserVO> selectUserList(Pageable pageable, UserVO userVO) throws Exception {
		Page<UserVO> selectUserList = null;
		if(!StringUtil.isEmpty(userVO.getSortSubject()) && !StringUtil.isEmpty(userVO.getSortDescend())) { //정렬조건이 있을 때
			if(userVO.getSortDescend().equals("asc")) {
				//PageRequest.of(1,10); 1~10페이지, 정렬방식, 정렬종류...
				pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, GlobalConstants.PAGE_SIZE, Sort.Direction.ASC, userVO.getSortSubject());
			} else {
				pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, GlobalConstants.PAGE_SIZE, Sort.Direction.DESC, userVO.getSortSubject());
			}
			
		} else { //정렬조건 없으면 
			pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, GlobalConstants.PAGE_SIZE, Sort.Direction.DESC, "userId");
		}
		
		if(!StringUtil.isEmpty(userVO.getSearchCondition())) {
			String searchCondition = userVO.getSearchCondition();
			switch (searchCondition) {
				case "userNm" :
					selectUserList = userManageRepository.findByUserNmContaining(userVO.getSearchKeyword(), pageable);
					break;
				case "userId" :
					selectUserList = userManageRepository.findByUserIdContaining(userVO.getSearchKeyword(), pageable);
					break;
				default:
					selectUserList = userManageRepository.findByEmailAddrContaining(userVO.getSearchKeyword(), pageable);
					break;
			}
		} else {
			selectUserList = userManageRepository.findAll(pageable);
		}
		
		return selectUserList;
	}
	
	//사용자관리 상세보기
	public UserVO selectUserDetail(UserVO userVO) throws Exception {
		return userManageRepository.findByUserId(userVO.getUserId());
	}
	
	//이메일 중복체크
	public Integer selectEmailAddrChk(UserVO userVO) throws Exception {
		List<UserVO> resultVO = userManageRepository.findByEmailAddr(userVO.getEmailAddr());
		log.debug("selectEmailAddrChk service cnt>> "+resultVO.size());
		return resultVO.size();
	}
	
	//사용자등록
	public void insertUserProc(UserVO userVO) throws Exception {
		userVO.setUserId(usersIdgen.getNextStringId());
		userVO.setRegDt(LocalDateTime.now());
		userManageRepository.save(userVO);
		
		if(!StringUtil.isEmpty(userVO.getUserId())) {
			RoleUserMapngVO roleUserMapngVO = new RoleUserMapngVO();
			roleUserMapngVO.setMapngId(roleUserMapngIdgen.getNextStringId());
			roleUserMapngVO.setRoleId(Role.AUTHENTICATED.stringValue());
			roleUserMapngVO.setUserId(userVO.getUserId());
			roleUserMapngVO.setRegId(userVO.getRegId());
			roleUserMapngVO.setRegDt(LocalDateTime.now());
			roleUserMapngRepository.save(roleUserMapngVO);
		}
	}
	
	//사용자 삭제
	public void deleteUserProc(UserVO userVO) throws Exception {
		//사용자의 권한 삭제
		int result = roleUserMapngRepository.deleteByUserId(userVO.getUserId());
		//사용자 삭제
		userManageRepository.deleteById(userVO.getUserId());
		log.debug("deleteUserProc >>>>>>>>> "+result);
	}
	
	//사용자이메일 수정시 중복체크
	public boolean selectEmailUptChk(UserVO userVO) throws Exception {
		boolean result = false;
		UserVO resultVO = userManageRepository.findByEmailAddrAndUserIdNot(userVO.getEmailAddr(), userVO.getUserId());
		if(resultVO == null) { //중복사용자가 없으면
			result = true;
		} else {
			result = false;
		}
		log.debug("selectUserEmailChk Service >>>>>>>>>> "+result);
		return result;
	}
	
	//사용자 수정
	public void updateUserProc(UserVO userVO) throws Exception {
		if(StringUtil.isEmpty(userVO.getUserPw())) { //패스워드가 존재하지 않으면 기존 패스워드로
			UserVO resultVO = userManageRepository.findByUserId(userVO.getUserId());
			userVO.setUserPw(resultVO.getUserPw());
		}
		userVO.setModDt(LocalDateTime.now());
		userManageRepository.save(userVO);
	}
}
