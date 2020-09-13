package com.weekendceo.web.admin.user.web;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.weekendceo.common.admin.user.domain.UserVO;
import com.weekendceo.common.common.GlobalConstants;
import com.weekendceo.web.admin.user.service.UserManageService;

@Controller
public class UserManageController {
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Resource(name = "userManageService")
	private UserManageService userManageService;
	
	//사용자관리 목록
	@RequestMapping("/usermng/r/m/selectUserList.do")
	public String selectUserList(Pageable pageable, @ModelAttribute("userVO") UserVO userVO, ModelMap model) throws Exception {
		
		Page<UserVO> selectUserList = userManageService.selectUserList(pageable, userVO);
		model.addAttribute("selectUserList", selectUserList);
		
		return "/admin/user/userList";
	}
	
	//사용자관리 상세보기
	@RequestMapping("/usermng/r/m/selectUserDetail.do")
	public String selectUserDetail(@ModelAttribute("userVO") UserVO userVO, ModelMap model) throws Exception {
		
		log.debug("/usermng/r/m/selectUserDetail.do >>>> "+userVO.getUserId());
		UserVO resultVO = userManageService.selectUserDetail(userVO);
		model.addAttribute("resultVO", resultVO);
		
		return "/admin/user/userDetail";
	}
	
	//사용자관리 등록
	@RequestMapping("/usermng/r/m/insertUser.do")
	public String insertUser(@ModelAttribute("userVO") UserVO userVO) throws Exception {
		
		log.debug("/usermng/r/m/insertUser.do>>>>>>> ");
		
		return "/admin/user/userInsert";
	}
	
	//아이디 중복체크
	@RequestMapping("/usermng/r/m/selectEmailAddrChk.do")
	public String selectEmailAddrChk(@ModelAttribute("userVO") UserVO userVO, ModelMap model) throws Exception {
		
		log.debug("/usermng/r/m/selectUserIdChk.do>>>>>>> ");
		int resultCnt = userManageService.selectEmailAddrChk(userVO);
		model.addAttribute("resultCnt", resultCnt);
		
		return GlobalConstants.JSON_VIEW_RESOLVER;
	}
	
	//사용자 등록
	@RequestMapping("/usermng/r/m/insertUserProc.do")
	public String insertUserProc(@ModelAttribute("userVO") UserVO userVO, ModelMap model, SessionStatus status) throws Exception {
		userVO.setRegId("USR-000001"); //후에는 세션에서 빼먹어야한다.
		
		String message;
		String redirectUrl;
		try {
			userManageService.insertUserProc(userVO);
			redirectUrl = "/usermng/r/m/selectUserList.do";
			message = GlobalConstants.INSERT_SUCC_MESSAGE;
			status.setComplete(); //중복 submit 방지
		} catch(Exception e) {
			redirectUrl = "/usermng/r/m/insertUser.do";
			message = GlobalConstants.ERROR_MESSAGE;
		}
		model.addAttribute("redirectUrl", redirectUrl);
		model.addAttribute("message", message);
		
		return GlobalConstants.ACTION_MESSAGE_HTML;
	}
	
	//사용자 삭제 처리
	@RequestMapping("/usermng/w/n/deleteUserProc.do")
	public String deleteUserProc(@ModelAttribute("userVO") UserVO userVO, ModelMap model, SessionStatus status) throws Exception {
		String message;
		String redirectUrl;
		try {
			userManageService.deleteUserProc(userVO);
			redirectUrl = "/usermng/r/m/selectUserList.do";
			message = GlobalConstants.DELETE_SUCC_MESSAGE;
			status.setComplete(); //중복 submit 방지
		} catch(Exception e) {
			redirectUrl = "/usermng/r/m/selectUserDetail.do?userId="+userVO.getUserId();
			message = GlobalConstants.ERROR_MESSAGE;
		}
		model.addAttribute("redirectUrl", redirectUrl);
		model.addAttribute("message", message);
		
		return GlobalConstants.ACTION_MESSAGE_HTML;
	}
	
	//사용자 수정
	@RequestMapping("/usermng/w/m/updateUser.do")
	public String updateUser(@ModelAttribute("userVO") UserVO userVO, ModelMap model) throws Exception {
		
		log.debug("/usermng/r/m/selectUserDetail.do >>>> "+userVO.getUserId());
		UserVO resultVO = userManageService.selectUserDetail(userVO);
		model.addAttribute("resultVO", resultVO);
		
		return "/admin/user/userUpdate";
	}
	
	//사용자수정시 중복체크
	@RequestMapping("/usermng/r/m/selectEmailUptChk.do")
	public String selectEmailUptChk(@ModelAttribute("userVO") UserVO userVO, ModelMap model) throws Exception {
		
		boolean result = userManageService.selectEmailUptChk(userVO);
		model.addAttribute("result", result);
		
		return GlobalConstants.JSON_VIEW_RESOLVER;
	}
	
	//사용자 수정처리
	@RequestMapping("/usermng/w/n/updateUserProc.do")
	public String updateUserProc(@ModelAttribute("userVO") UserVO userVO, ModelMap model, SessionStatus status) throws Exception {
		
		userVO.setModId("USR-000001"); //추후 세션에서 빼야함.
		userManageService.updateUserProc(userVO);
		status.setComplete();//중복서브밋 방지
		
		return "redirect:/usermng/r/m/selectUserDetail.do?userId="+userVO.getUserId();
	}
}
