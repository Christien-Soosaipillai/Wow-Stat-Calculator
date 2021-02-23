package com.christien.wowstatcalculator.controllers.ui;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.christien.wowstatcalculator.entities.User;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SystemUI {

	@GetMapping("/login")
	public String login(Model model) {
		String name = "SomeName";
		model.addAttribute("val", name);
		return "login";
	}

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@GetMapping("/home")
	public String home(HttpServletRequest request, Model model){
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			model.addAttribute("username", null);
			return "home";
		}else {
			model.addAttribute("username", user.getUsername());
			model.addAttribute("fullName", user.getFirstName() + " " + user.getLastName());
			return "home";
		}
	}


	// TEST ROUTES FOR SPRING SECURITY BOOT
//	@GetMapping("/")
//	public String home(){
//		return "<h1>WELCOME ALL</h1>";
//	}
//
//	@GetMapping("/user")
//	public String user(){
//		return "<h1>WELCOME USER</h1>";
//	}
//
//
//	@GetMapping("/admin")
//	public String admin(){
//		return "<h1>WELCOME ADMIN</h1>";
//	}

}
