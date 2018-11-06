package com.fred.spring.transaction.demo.jsp.demo;

import com.fred.spring.transaction.demo.jsp.demo.domain.form.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Fred
 */
@Controller
public class JspFormController {

    /**
     * checkbox, 可以用三种方式来给jsp赋值，然后还能回来，第三种没选，回来会置为null
     * select, 如果path是多个，那么就多选，否则单选，form绑定的是选择的内容而非列表
     * @param model
     * @param userForm
     * @return
     */
    @RequestMapping("/helloJspForm")
    public String helloJspForm(Model model, User userForm) {
        User user = this.getUser();
        model.addAttribute("command", user);
        // for select.
        model.addAttribute("skills", user.getSkills());
        return "form";
    }

    private User getUser() {
        User user = new User();
        user.setReceiveNewsletter(true);
        user.setFavouriteWord("Magic");
        user.setInterests(new String[]{"Quidditch", "no #2", "Defence Against the Dark Arts"});

        Map<String, String> skills = new HashMap<String, String>(10);
        skills.put("Potions", "Potions");
        skills.put("Herbology", "Herbology");
        skills.put("Quidditch", "Quidditch");
        user.setSkills(skills);
        user.setSkill("Herbology");
        user.setSkill2("Ravenclaw");
        return user;
    }

}
