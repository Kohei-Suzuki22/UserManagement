package com.user.manegiment.basis.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.manegiment.basis.entity.MasterUser;
import com.user.manegiment.basis.repository.UserMapper;
import com.user.manegiment.basis.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void signup(MasterUser masterUser) {
		masterUser.setDepartmentId(1);
		masterUser.setRole("ROLE_GENERAL");

		userMapper.insertOne(masterUser);
	}
	
	@Override
	public List<MasterUser> getUsers(){
		return userMapper.findMany();
	}
	
	@Override
	public MasterUser getUserOne(String userId) {
		return userMapper.findOne(userId);
	}
	
	@Override
	public void updateUserOne(MasterUser masterUser) {
		 userMapper.updateOne(masterUser);
	}
	
	@Override
	public void deleteUserOne(String userId) {
		 userMapper.deleteOne(userId);
	}

}
