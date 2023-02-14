package com.k404gwProject.Service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.k404gwProject.Dto.MemberFormDto;
import com.k404gwProject.Entity.Member;

public interface MemberService {
	public Member loginMember(String email); // 로그인
	public Member saveMember(MemberFormDto memberFormDto); // db에 저장
//	public MemberFormDto getMemberDtl(String email);
	public Long updateMember( MemberFormDto memberFormDto);
	public void deleteMember(Long id);
}
