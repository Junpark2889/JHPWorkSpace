package com.k404gwProject.Dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.k404gwProject.Entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberFormDto {    
	
	private Long id;
	@NotEmpty(message = "이메일은 필수 입력 값입니다.") // not null, 문자열의 길이 0인지 검사
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;
	
    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=1, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String pwd;
	
	@NotBlank(message = "이름은 필수 입력 값입니다.") // not null, 문자열의 길이 0인지 검사, 빈문자열 " " 검사
    private String name;
	
//	@NotBlank(message = "당신은 무성이 아닙니다.")
    private String gender;
	
	@NotBlank(message = "전화번호는 필수 입력 값입니다.")
    private String phone;
	
    private String github;
	
    private String img;
	    
    private String address;    

//    public static ModelMapper modelMapper = new ModelMapper();
//    
//    public static MemberFormDto of(Member member) {
//    	return modelMapper.map(member,MemberFormDto.class);
//    }
}
