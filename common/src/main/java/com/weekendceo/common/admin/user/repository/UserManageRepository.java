package com.weekendceo.common.admin.user.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.weekendceo.common.admin.user.domain.UserVO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository("userManageRepository")
public interface UserManageRepository extends JpaRepository<UserVO, String> {
	//사용자 아이디검색 리스트 like %userId%
	public Page<UserVO> findByUserIdContaining(String userId, Pageable pageable);
	//사용자 메일검색 리스트 like %emailAddr%
	public Page<UserVO> findByEmailAddrContaining(String emailAddr, Pageable pageable);
	//사용자 이름검색 리스트 like %userNm%
	public Page<UserVO> findByUserNmContaining(String userNm, Pageable pageable);
	//사용자 관리 상세보기
	public UserVO findByUserId(String userId);
	//이메일 중복체크
	public List<UserVO> findByEmailAddr(String emailAddr);
	//이메일 수정시 중복체크
	public UserVO findByEmailAddrAndUserIdNot(String emailAddr, String userId);
}
