package com.visualpathit.account.controller;

import com.visualpathit.account.model.User;
<<<<<<< HEAD
import com.visualpathit.account.service.SecurityService;
import com.visualpathit.account.service.UserService;
import com.visualpathit.account.validator.UserValidator;

=======
import com.visualpathit.account.service.ProducerService;
import com.visualpathit.account.service.SecurityService;
import com.visualpathit.account.service.UserService;
import com.visualpathit.account.utils.MemcachedUtils;
import com.visualpathit.account.validator.UserValidator;

import java.util.List;
import java.util.UUID;

>>>>>>> 5ee292ba1cad632882cff41c5b1e9ca2f78f89bb
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**{@author waheedk}*/
=======
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**{@author imrant}*/
>>>>>>> 5ee292ba1cad632882cff41c5b1e9ca2f78f89bb
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
<<<<<<< HEAD
=======
    @Autowired
    private ProducerService producerService;
    
>>>>>>> 5ee292ba1cad632882cff41c5b1e9ca2f78f89bb
    /** {@inheritDoc} */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public final String registration(final Model model) {
        model.addAttribute("userForm", new User());
<<<<<<< HEAD

        return "registration";
    }
=======
             	return "registration";
      }
>>>>>>> 5ee292ba1cad632882cff41c5b1e9ca2f78f89bb
    /** {@inheritDoc} */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public final String registration(final @ModelAttribute("userForm") User userForm, 
    	final BindingResult bindingResult, final Model model) {
    	
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        System.out.println("User PWD:"+userForm.getPassword());
        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }
    /** {@inheritDoc} */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public final String login(final Model model, final String error, final String logout) {
<<<<<<< HEAD
        if (error != null){
=======
        System.out.println("Model data"+model.toString());
    	if (error != null){
>>>>>>> 5ee292ba1cad632882cff41c5b1e9ca2f78f89bb
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (logout != null){
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }
    /** {@inheritDoc} */
    @RequestMapping(value = { "/", "/welcome"}, method = RequestMethod.GET)
    public final String welcome(final Model model) {
        return "welcome";
    }
    /** {@inheritDoc} */
    @RequestMapping(value = { "/index"} , method = RequestMethod.GET)
    public final String indexHome(final Model model) {
        return "index_home";
    }
<<<<<<< HEAD
=======
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllUsers(Model model)
    {	
   
        List<User> users = userService.getList();
        //JSONObject jsonObject
        System.out.println("All User Data:::" + users);
        model.addAttribute("users", users);
        return "userList";
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public String getOneUser(@PathVariable(value="id") String id,Model model)
    {	
    	String Result ="";
    	try{
    		if( id != null && MemcachedUtils.memcachedGetData(id)!= null){    			
    			User userData =  MemcachedUtils.memcachedGetData(id);
    			Result ="Data is From Cache";
    			System.out.println("--------------------------------------------");
    			System.out.println("Data is From Cache !!");
    			System.out.println("--------------------------------------------");
    			System.out.println("Father ::: "+userData.getFatherName());
    			model.addAttribute("user", userData);
    			model.addAttribute("Result", Result);
    		}
    		else{
	    		User user = userService.findById(Long.parseLong(id)); 
	    		Result = MemcachedUtils.memcachedSetData(user,id);
	    		if(Result == null ){
	    			Result ="Memcached Connection Failure !!";
	    		}
	    		System.out.println("--------------------------------------------");
    			System.out.println("Data is From Database");
    			System.out.println("--------------------------------------------");
		        System.out.println("Result ::: "+ Result);	       
		        model.addAttribute("user", user);
		        model.addAttribute("Result", Result);
    		}
    	} catch (Exception e) {    		
    		System.out.println( e.getMessage() );
		}
        return "user";
    }
    
    /** {@inheritDoc} */
    @RequestMapping(value = { "/user/{username}"} , method = RequestMethod.GET)
    public final String userUpdate(@PathVariable(value="username") String username,final Model model) {
    	User user = userService.findByUsername(username); 
    	System.out.println("User Data:::" + user);
    	model.addAttribute("user", user);
    	return "userUpdate";
    }
    @RequestMapping(value = { "/user/{username}"} , method = RequestMethod.POST)
    public final String userUpdateProfile(@PathVariable(value="username") String username,final @ModelAttribute("user") User userForm,final Model model) {
    	User user = userService.findByUsername(username);
    	user.setUsername(userForm.getUsername());
    	user.setUserEmail(userForm.getUserEmail());
    	user.setDateOfBirth(userForm.getDateOfBirth());
    	user.setFatherName(userForm.getFatherName());
    	user.setMotherName(userForm.getMotherName());
    	user.setGender(userForm.getGender());
    	user.setLanguage(userForm.getLanguage());
    	user.setMaritalStatus(userForm.getMaritalStatus());
    	user.setNationality(userForm.getNationality());
    	user.setPermanentAddress(userForm.getPermanentAddress());
    	user.setTempAddress(userForm.getTempAddress());
    	user.setPhoneNumber(userForm.getPhoneNumber());
    	user.setSecondaryPhoneNumber(userForm.getSecondaryPhoneNumber());
    	user.setPrimaryOccupation(userForm.getPrimaryOccupation());
    	user.setSecondaryOccupation(userForm.getSecondaryOccupation());
    	user.setSkills(userForm.getSkills());
    	user.setWorkingExperience(userForm.getWorkingExperience());    	
    	userService.save(user); 
    	/*model.addAttribute("user", user);*/
    	return "welcome";
    }
    
    @RequestMapping(value={"/user/rabbit"}, method={RequestMethod.GET})
    public String rabbitmqSetUp() { 
    	System.out.println("Rabbit mq method is callled!!!");
      for (int i = 0; i < 20; i++) {
        producerService.produceMessage(generateString());
      }
      return "rabbitmq";
    }
    
    private static String generateString() {
      String uuid = UUID.randomUUID().toString();
      return "uuid = " + uuid;
    }
    

    
>>>>>>> 5ee292ba1cad632882cff41c5b1e9ca2f78f89bb
}
