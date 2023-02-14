package com.k404gwProject.Entity;

//import javax.management.relation.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.k404gwProject.Constant.Role;
import com.k404gwProject.Dto.MemberFormDto;

import lombok.Data;

@Entity
@SequenceGenerator(
		name="member_seq_gen", //시퀀스 제너레이터 이름
		sequenceName="member_seq", //시퀀스 이름
		initialValue=1, // 시작값
		allocationSize=1 // 메모리 통해 할당할 범위 사이즈, 1로 설정시 insert할때마다 시퀀스 호출
		)
@Table(name="member")
@Data
public class Member extends BaseEntity{
	@Id
    @Column(name="mem_id")
    @GeneratedValue(strategy = GenerationType.AUTO,
    		generator="member_seq_gen"
    		)
    private Long id;
    
    @Column(name="mem_pwd")
    private String pwd;
    
    @Column(name="mem_name")
    private String name;
    
    @Column(name="mem_email", unique = true)
    private String email;

    @Column(name="mem_phone")
    private String phone;
    
    @Column(name="mem_gender")
    private String gender;
    
    @Enumerated(EnumType.STRING) // enum 타입 명시, 타입을 문자열로 지정. 컴파일러에서 문법체크가능
    @Column(name="mem_role")
    private Role role;
    
    @Column(name="mem_img")
    private String img;
    
    @Column(name="mem_github")
    private String github;
    
//    @CreatedDate
//    @Column(name="join_Date")
//    private LocalDateTime joinDate;

    public static Member createMember(MemberFormDto memberFormDto){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setPhone(memberFormDto.getPhone());
        member.setGender(memberFormDto.getGender());
        member.setGithub(memberFormDto.getGithub());
//        member.setImg(memberFormDto.getImg());
//        member.setAddress(memberFormDto.getAddress());
//        String pwd = passwordEncoder.encode(memberFormDto.getPwd()); // 패스워드 인코딩 작업, Hasing, 패스워드를 특정 길이의 문자열로 변환하는 작업
        member.setPwd(memberFormDto.getPwd());
        member.setRole(Role.ADMIN); // 회원 가입시 기본 역할 지정 부분
        return member;
    }
    
    public void loginMember(MemberFormDto memberFormDto) {
    	this.email = memberFormDto.getEmail();
    	this.pwd = memberFormDto.getPwd();    	
    }
    
    public void updateMember(MemberFormDto memberFormDto){
//    	this.email = memberFormDto.getEmail();
    	this.pwd = memberFormDto.getPwd();
    	this.name = memberFormDto.getName();
    	this.gender = memberFormDto.getGender();
    	this.github = memberFormDto.getGithub();
//    	this.img = memberFormDto.getImg();
    	this.phone = memberFormDto.getPhone();
    	
    }

}
