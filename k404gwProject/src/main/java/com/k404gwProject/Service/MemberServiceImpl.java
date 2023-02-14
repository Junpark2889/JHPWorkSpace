package com.k404gwProject.Service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k404gwProject.Dto.MemberFormDto;
import com.k404gwProject.Entity.Member;
import com.k404gwProject.Repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private  final MemberRepository memberRepository;
	
	@Override
	public Member saveMember(MemberFormDto memberFormDto){
		Member member = Member.createMember(memberFormDto); // createMember Entity 클래스에서 정의했던 메서드를 이용해 회원 가입시 편함
		return memberRepository.save(member);
	}
	
	@Override
	public Member loginMember(String email){
		Member loginMember = memberRepository.findByEmail(email);
		System.out.println("로그인"+loginMember);
		return loginMember; 
	}

	@Override
	public Long updateMember(MemberFormDto memberFormDto) {
			Member member = memberRepository.findById(memberFormDto.getId()).orElseThrow(EntityNotFoundException::new);
			member.updateMember(memberFormDto);
			
		return member.getId();
	}

	@Override
	public void deleteMember(Long id) {
		Member deleteMember = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		System.out.println("으아아 "+deleteMember);
		memberRepository.delete(deleteMember);
		
		
	}

	
	
	
	
//	@Override
//	public MemberFormDto getMemberDtl(String email){
//		Member memberDtl = memberRepository.findByEmail(email);
//		MemberFormDto memberFormDto = MemberFormDto.of(memberDtl);
//		return memberFormDto;
//	}
}