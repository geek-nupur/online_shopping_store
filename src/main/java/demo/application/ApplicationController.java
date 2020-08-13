package demo.application;

import demo.application.services.SMTPMailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import demo.application.services.UserServiceImpl;

@Controller
@RequestMapping("/")
public class ApplicationController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    SMTPMailSenderService emailService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping("/register")
    public String signUp() {
        return "sign_up";
    }

//    @GetMapping("/registration")
//    public String signUp(Model model) {
//        model.addAttribute("user", new UserRegistrationDto());
//        return "sign_up";
//    }


    @PostMapping("/register")
    public String signUp(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.saveUser(registrationDto);
        return "redirect:/register?success";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/reset_password")
    public String resetPassword() {
        return "reset_password";
    }

    @PostMapping("/reset_password")
    public String resetPassword(String emailId) {
        emailService.sendAResetPasswordLink(emailId, "Test java mail sender service", "Mail sent successfully");
        return "redirect:/reset_password?success";
    }

}
