package com.christien.wowstatcalculator.controllers.api;

import com.christien.wowstatcalculator.entities.User;
import com.christien.wowstatcalculator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	@ResponseBody
	public ResponseEntity login(@RequestBody Map<String, String> data, HttpServletRequest request){
		String username = String.valueOf(data.get("username"));
		String password = String.valueOf(data.get("password"));
		Optional<User> user = userRepository.findByUsername(username);
		HttpSession session = request.getSession();
		if(user.isPresent()){
			if(password.equals(user.get().getPassword())){
				session.setAttribute(username, user);
				return ResponseEntity.ok(user);
			}else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
//		Optional<User> user = users.stream().filter(u ->{
//			if(u.getEmail().equals(identification) || u.getUsername().equals(identification)) {
//				if(u.getPassword().equals(password)) {
//					return true;
//				}
//			}
//			return false;
//		}).findFirst().get();
//		if(user != null) {
//			session.setAttribute(user.getUsername(), user);
//			return user;
//		}else {
//			return null;
//		}

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
