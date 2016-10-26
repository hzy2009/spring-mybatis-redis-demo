package com.codingmobi.ui.core.web.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.codingmobi.ui.core.bean.systerm.Operation;
import com.codingmobi.ui.core.bean.systerm.OperationExample;
import com.codingmobi.ui.core.bean.systerm.Role;
import com.codingmobi.ui.core.persistence.systerm.OperationMapper;
import com.codingmobi.ui.core.utils.mybatis.ConstantsForCore;

@Component("customFilterInvocationSecurityMetadataSource")
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	@Autowired
	private OperationMapper operationMapper;
	
	private Map<RequestMatcher, List<Role>> metadataSourceMap;
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();  
		Collection<ConfigAttribute> attrs = Collections.emptyList();  
		
        for (Entry<RequestMatcher, List<Role>>  entry : metadataSourceMap.entrySet()) {  
            if (entry.getKey().matches(request)) {
            	List<Role> roleList = entry.getValue();
            	attrs = SecurityConfig.createListFromCommaDelimitedString(this.getRoleList(roleList));
                break;  
            }  
        }  
        
		return attrs;
	}
	
	public String getRoleList(List<Role> roleList){
		List<String> roleCodeList = new ArrayList<>();
		for (Iterator<Role> iterator = roleList.iterator(); iterator.hasNext();) {
			Role role =  iterator.next();
			roleCodeList.add(role.getCode());
		}
		return StringUtils.join(roleCodeList, ",");
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
        List<ConfigAttribute> attrs = new ArrayList<>();
		
        for (Entry<RequestMatcher, List<Role>>  entry : metadataSourceMap.entrySet()) {
        	List<Role> roleList = entry.getValue();
        	List<ConfigAttribute> confAttrs = SecurityConfig.createListFromCommaDelimitedString(this.getRoleList(roleList));
        	attrs.addAll(confAttrs);
        }  
        
		return attrs;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
	
	/**
	 * 初始化方法
	 */
	@PostConstruct
	private void init(){
		this.metadataSourceMap = new Hashtable<>();
		
		OperationExample operationExample =new OperationExample();
		List<Operation> operationList = this.operationMapper.selectByExample(operationExample);
		
		for (Iterator iterator = operationList.iterator(); iterator.hasNext();) {
			Operation operation = (Operation) iterator.next();
			RequestMatcher matcher = null;
			if (ConstantsForCore.OPERATION_TYPE_URL == operation.getType()) {
		        matcher = new AntPathRequestMatcher(operation.getValue());
			}
			
			if (matcher != null) {
				metadataSourceMap.put(matcher, operation.getRoleList());
			}
		}
	}

}
