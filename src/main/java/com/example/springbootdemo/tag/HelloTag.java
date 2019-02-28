package com.example.springbootdemo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Create by Bakatparin
 * on 2019/2/27
 */
public class HelloTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {

        JspWriter out = pageContext.getOut();
        try {
            out.print("hello my tag!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
}
