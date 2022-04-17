package com.user.manegiment.basis.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<MasterUser> getUsers(MasterUser masterUser){
		return userMapper.findMany(masterUser);
	}
	
	@Override
	public MasterUser getUserOne(String userId) {
		return userMapper.findOne(userId);
	}
	
	@Transactional
	@Override
	public void updateUserOne(MasterUser masterUser) {
		 userMapper.updateOne(masterUser);
	}
	
	@Transactional
	@Override
	public void deleteUserOne(String userId) {
		 userMapper.deleteOne(userId);
	}

}
