package com.example.springbootdemo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Create by Bakatparin
 * on 2019/2/27
 * 字母转小写
 */
public class ToLowerCaseTag extends SimpleTagSupport {
    private String message;

    @Override
    public void doTag() throws JspException, IOException {
        if(message != null){
            getJspContext().getOut().println(message.toLowerCase());
        }else{
            StringWriter sw = new StringWriter();
            JspFragment jspFragment = getJspBody();
            jspFragment.invoke(sw);
            String str = sw.toString().toLowerCase();
            getJspContext().getOut().println(str);
        }
        super.doTag();
    }



    public void setMessage(String message) {
        this.message = message;
    }
}
