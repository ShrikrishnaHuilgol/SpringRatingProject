package com.example.main.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.main.Model.Ratings;
import com.example.main.Model.Users;
import com.example.main.Service.UserService;

@Controller
public class PageController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path="/")
	public String homePage() {
		return "HomePage";
	}
	
	@RequestMapping(path="/RegisterUser")
	public String addUser(Model m) {
		Users user=new Users();
		m.addAttribute("user", user);
		return "Registration";
	}
	
	@RequestMapping(path="/Register",method=RequestMethod.GET)
	public String registerUser(@ModelAttribute("user") Users user) {
		System.out.println("Entered Register user");
		userService.insert(user);
		return "Login";
	}
	
	@RequestMapping(path="/Rating",method=RequestMethod.POST)
	public String ratings(@RequestParam("userName") String uname,@RequestParam("password") String password, HttpServletRequest request,Model m) {
		System.out.println(userService.isUserPresent(uname));
		System.out.println(userService.verifyUser(uname, password));
		Ratings ratings=new Ratings();
		if(userService.isUserPresent(uname)) {
			if(userService.verifyUser(uname, password)){
				request.getSession().setAttribute("email", uname);
				//ratings.setUser(uname);
				m.addAttribute("ratings", ratings);
				return "RatingPage";
			}
		}
		return "Login";
	}
	
	@RequestMapping(path="/ThankYou")
	public String thankYou(@ModelAttribute Ratings rating,HttpServletRequest request) {
		String user=(String) request.getSession().getAttribute("email");
		rating.setUser(user);
		userService.insertRating(rating);
		return "ThankYou";
	}
	
	@RequestMapping(path="/AdminLogin")
	public String adminLogin() {
		return "AdminLoginPage";
	}
	
	@RequestMapping(path="/VerifyAdmin",method=RequestMethod.POST)
	public String verifyAdmin(@RequestParam("email") String email,@RequestParam("password") String password,Model m) {
		if(email.equals("admin@gmail.com") && password.equals("12345")) {
			List<Ratings> ratings=userService.getAllRatings();
			m.addAttribute("ratings", ratings);
			return "ViewRatings";
		}
		else {
			return "AdminLoginPage";
		}
	}
	
	@RequestMapping(path="/FilterResults")
	public String filerResults() {
		return "FilteredResultInputPage";
	}
	
	@RequestMapping(path="/GetFilteredResult")
	public String getFilterResults(@RequestParam("Ambiance") String amb,@RequestParam("Food") String food,@RequestParam("Service") String service,@RequestParam("Drinks") String drinks,@RequestParam("Cleanliness") String clean,Model m) {
		List<Ratings> ratings=userService.fetchByRestrictions(amb,food,drinks,service,clean);
		m.addAttribute("filteredratings", ratings);
		return "FilteredResultPage";
	}
	
}
