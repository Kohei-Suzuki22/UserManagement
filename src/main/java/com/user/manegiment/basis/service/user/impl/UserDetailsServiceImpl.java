package com.user.manegiment.basis.service.user.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.manegiment.basis.entity.MasterUser;
import com.user.manegiment.basis.service.user.UserService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		MasterUser loginUser = userService.findLoginUser(username);
		
		if(Objects.isNull(loginUser)) {
			throw new UsernameNotFoundException("user not found");
		}
		
		/* 権限リストの作成 **/
		GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());
		List<GrantedAuthority> authorities = Arrays.asList(authority);
		
		/* springSecurityで使うユーザーオブジェクトの作成 **/
		UserDetails userDetails = (UserDetails) new User(loginUser.getUserId(),loginUser.getPassword(),authorities);
		
		return userDetails;
		
	}
}
