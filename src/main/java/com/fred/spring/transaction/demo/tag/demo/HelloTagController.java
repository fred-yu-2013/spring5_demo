package com.fred.spring.transaction.demo.tag.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Fred
 */
@Controller
public class HelloTagController {

    @RequestMapping("/hellotag")
    public String helloJsp(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "customTag";
    }

}
