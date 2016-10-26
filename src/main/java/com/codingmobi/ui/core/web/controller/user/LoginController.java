package com.codingmobi.ui.core.web.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codingmobi.ui.core.bean.systerm.UserExample;
import com.codingmobi.ui.core.persistence.systerm.UserMapper;

@Controller
public class LoginController {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
     * 登陆成功后，跳转首页
     * *
     */
    @RequestMapping("/index.html")
    public ModelAndView navigateIndexView(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main/index");
        return mav;
    }
    
    @RequestMapping("admin")
    public String admin(){
    	return "main/admin";
    }
    
//    /**
//     * 登陆失败跳转方法
//     */
//    @RequestMapping("/loginFail.html")
//    public ModelAndView loginFail() {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("tip", "用户名或密码错误/账号已被禁止，请重新输入");
//        mav.setViewName("main/login");
//        return mav;
//    }
//    
    @RequestMapping("/user.html")
    public ModelAndView login(String username, String password) {
    	UserExample example = new UserExample();
    	example.createCriteria().andLoginNameEqualTo(username).andPasswordEqualTo(password);
    	
    	int count = this.userMapper.countByExample(example);
    	
    	ModelAndView mav = new ModelAndView();
    	if(count > 0){
    		mav.setViewName("main/index");
    	}else{
    		mav.setViewName("main/login");
    	}
    	
        return mav;
    }
//    
//    @RequestMapping("/loginView.html")
//    public ModelAndView navigateLoginView() {
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("main/login");
//        return mav;
//    }

}
