package com.fred.spring.transaction.demo.tag.demo;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 代表了标签对应的java类，用来处理进出参数
 * @author Fred
 */
public class HelloTag extends SimpleTagSupport {

    private String message;

    public void setMessage(String msg) {
        this.message = msg;
    }

    @Override
    public void doTag() throws JspException, IOException {
        // 往页面上输出内容
        JspWriter out = getJspContext().getOut();
        out.println("Hello Custom Tag!" + this.message);
    }

}
