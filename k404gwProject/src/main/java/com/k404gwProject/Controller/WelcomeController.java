package com.k404gwProject.Controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	 @GetMapping(value = "/")
	 public String welcome() {
	
		 return "welcome";
	 }
	 
//	 @PostMapping(value = "/login")
//	 public String main(Principal principal) {
//		 if (principal != null) {
//				System.out.println("타입정보 : " + principal.getClass());
//				System.out.println("ID정보 : " + principal.getName());
//			}
//		System.out.println("수행 했니??");
//		 return "redirect:/main";
//	 }
}
