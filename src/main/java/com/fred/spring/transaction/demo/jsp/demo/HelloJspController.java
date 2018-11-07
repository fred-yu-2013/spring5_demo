package com.fred.spring.transaction.demo.jsp.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Fred
 */
@Controller
public class HelloJspController {

    @RequestMapping("/hellojsp")
    public String helloJsp(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("/international")
    public String getInternationalPage(Model model, @RequestParam(value="lang", required=false, defaultValue="en") String lang) {
        return "international";
    }

}
