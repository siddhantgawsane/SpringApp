package com.springmvcproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/myPage")
public class HelloController{
 
   @RequestMapping(value="/hello",method = RequestMethod.GET)
   public String printHello(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");
      return "hello";
   }
   @RequestMapping(value="/signin",method = RequestMethod.POST)
   public String displaySignInPage(@ModelAttribute("SpringWeb")Employee u, ModelMap model) {
	   //if (u.getusername() == "user" && u.getpassword() == "password")
		 //  return "home";
      return "signin";
   }
   
   @RequestMapping(value="/welcome",method = RequestMethod.GET)
   public ModelAndView signIn() {
      //model.addAttribute("message", "Hello Spring MVC Framework!");
	   return new ModelAndView("signin", "command", new Employee());
   }

   @RequestMapping(value="/signup",method = RequestMethod.GET)
   public String displaySignupPage() {
      return "signup";
   }
}