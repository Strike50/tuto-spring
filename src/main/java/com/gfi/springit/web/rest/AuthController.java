package com.gfi.springit.web.rest;

import com.gfi.springit.domain.User;
import com.gfi.springit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthController {

    private UserService userService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/profile")
    public String profile(){
        return "auth/profile";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user",new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if( bindingResult.hasErrors()){
            logger.info("Validation errors were found while registering a new user");
            model.addAttribute("user",user);
            model.addAttribute("validationErrors",bindingResult.getAllErrors());
            return "auth/register";
        }else{
            //register new user
            User newUser = userService.register(user);
            redirectAttributes
                    .addAttribute("id",newUser.getId())
                    .addFlashAttribute("success",true);
        }
        return "redirect:/register";
    }

    @GetMapping("/activate/{email}/{activationCode}")
    public String activate(@PathVariable String email,@PathVariable String activationCode){
        Optional<User> user = userService.findByEmailAndActivationCode(email,activationCode);
        if( user.isPresent() ){
            User newUser = user.get();
            newUser.setEnabled(true);
            newUser.setConfirmPassword(newUser.getPassword());
            userService.save(newUser);
            userService.sendWelcomeEmail(newUser);
            return "auth/activated";
        }
        return "redirect:/";
    }

}