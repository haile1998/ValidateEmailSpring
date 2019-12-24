package app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.model.IModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class HomeController {
    private static Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

    public HomeController() {
        pattern = Pattern.compile(EMAIL_REGEX);
    }

    @GetMapping("/validate")
    public ModelAndView viewHome() {
        ModelAndView modelAndView = new ModelAndView("/home");
        return modelAndView;
    }

    @PostMapping("/validate")
    public ModelAndView validateEmail(@RequestParam("email") String email) {
        ModelAndView modelAndView;
        boolean isvalid = this.validate(email);
        if(!isvalid) {
            modelAndView = new ModelAndView("/home");
            modelAndView.addObject("message","invalid email !!!");
        } else {
            modelAndView = new ModelAndView("/success");
            modelAndView.addObject("email", email);
        }
        return modelAndView;
    }

    private boolean validate(String regex) {
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
