package com.k404gwProject.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.k404gwProject.Dto.MemberFormDto;
import com.k404gwProject.Entity.Member;
import com.k404gwProject.Repository.MemberRepository;
import com.k404gwProject.Service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {
	
	 private final MemberService memberService;
	 private final MemberRepository memberRepository;
    @GetMapping(value = "/new") // 회원가입창 뷰
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }    
    
	@PostMapping(value = "/new") // 회원가입 로직 처리
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
        	System.out.println("오류발생");
        	System.out.println(bindingResult);
            return "member/memberForm";
        }
        
        try {	
            
            memberService.saveMember(memberFormDto);
            
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }
	
	@PostMapping(value = "/login") // 로그인 로직 처리
	public String loginMember(HttpServletRequest request, HttpServletResponse response, Model model, String email,MemberFormDto memberFormDto) {
		Member loginMember = memberService.loginMember(email);
		
//		MemberFormDto memberFormDto = memberService.getMemberDtl(email);
		System.out.println("loginMember: " +loginMember);
		
		if(loginMember == null) {
			return "welcome";
		}	else  if(!loginMember.getPwd().equals(memberFormDto.getPwd())) {
			System.out.println("패스워드 입력안했어");
			 return "welcome";
		 } else {
				HttpSession session = request.getSession();
				session.setAttribute("email", loginMember);		
				return "redirect:/main";
		 }
		
//		System.out.println(memberFormDto);
//		if(loginMember != null) {			
//			HttpSession session = request.getSession();
//			session.setAttribute("email", loginMember);		
//			return "redirect:/main";
//		} else {			
////			request.setAttribute("msg", "비밀번호를 잘못 입력했습니다.");
////			request.setAttribute("loc", "loginView.sw");
//			return "welcome";
//		}
	}
	
	@GetMapping(value = "/md")
	public String mdForm(HttpServletRequest request, Member member, MemberFormDto memberFormDto, Model model,  String email) {
		HttpSession session = request.getSession();
		Member mdMember = (Member) session.getAttribute("email");
		System.out.println(mdMember);
		System.out.println(mdMember.getName());
		System.out.println(mdMember.getId());
		model.addAttribute("member", mdMember);
		
		return "member/mdForm";
	}
	
	@PostMapping(value="/md") 
	public String mdMember(HttpServletRequest request, HttpServletResponse response, @Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model, String email) {
		  if(bindingResult.hasErrors()){
	        	System.out.println("오류발생");
	        	System.out.println(bindingResult);
	            return "member/mdForm";
	        }
		  HttpSession session = request.getSession();
		  Member member = (Member) session.getAttribute("email");
		  System.out.println("세션 변경 전 : " +member);
		    try { 
			memberService.updateMember(memberFormDto);

		Member memT = memberRepository.findByEmail(email);
	       System.out.println(memT);
	       session.setAttribute("email", memT );
	        } catch (Exception e){
	            model.addAttribute("errorMessage", "회원 수정 중 에러가 발생했습니다.");
	            System.out.println(memberFormDto.getId());
	            System.out.println("수행했니");
	            return "member/mdForm";
	        }
		    
		    return "redirect:/main";
	}
	
	@DeleteMapping(value ="/del/{id}") 
	public @ResponseBody ResponseEntity deleteById(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		 HttpSession session = request.getSession();
		 Member del = (Member) session.getAttribute("email");
		memberService.deleteMember(id);
		
		return new ResponseEntity<Long>(id, HttpStatus.OK); 		
	}
}
