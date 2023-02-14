package com.k404gwProject.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.k404gwProject.Entity.Member;
import com.k404gwProject.Repository.MemberRepository;

@Controller
public class MainController {	
	
	@GetMapping(value = "/main")
	public String main() {

		return "main/main";
	}
}
