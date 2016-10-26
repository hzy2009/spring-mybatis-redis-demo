package com.codingmobi.ui.core.web.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.codingmobi.ui.core.bean.systerm.User;
import com.codingmobi.ui.core.bean.systerm.UserExample;
import com.codingmobi.ui.core.persistence.systerm.UserMapper;
import com.codingmobi.ui.core.utils.mybatis.ConstantsForCore;

@Component("customUserDetailsServiceImpl")
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserExample userExample = new UserExample();
		userExample.createCriteria()
		           .andLoginNameEqualTo(username)
		           .andIsVaildEqualTo(ConstantsForCore.YES_INT);
		
		List<User> userList = this.userMapper.selectByExample(userExample);
		if(userList ==null || userList.size() == 0){
			throw new UsernameNotFoundException("User ["+ username +"] not found");
		}
		
		User user = userList.get(0);
		return user;
	}

}
