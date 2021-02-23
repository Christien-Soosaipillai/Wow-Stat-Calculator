package com.christien.wowstatcalculator.controllers.api;

import com.christien.wowstatcalculator.entities.User;
import com.christien.wowstatcalculator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SystemApi {
	
	@Autowired
    UserRepository userRepository;

	@GetMapping(value = "/all")
	public List<User> getAll(){
		return userRepository.findAll();
	}

	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public User login(@RequestBody Map<String, String> data, HttpServletRequest request){
		String identification = String.valueOf(data.get("identification"));
		String password = String.valueOf(data.get("password"));
		List<User> users = userRepository.findAll();
		HttpSession session = request.getSession();
		User user = users.stream().filter(u ->{
			if(u.getEmail().equals(identification) || u.getUsername().equals(identification)) {
				if(u.getPassword().equals(password)) {
					return true;
				}
			}
			return false;
		}).findFirst().get();
		if(user != null) {
			session.setAttribute(user.getUsername(), user);
			return user;
		}else {
			return null;
		}

	}

	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	public void signup(@RequestBody User user) {
		userRepository.save(user);
	}

	@RequestMapping(value = "/signout/{username}", method=RequestMethod.POST)
	public void signout(@PathVariable String username, HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute(username);
	}
	
	
}
