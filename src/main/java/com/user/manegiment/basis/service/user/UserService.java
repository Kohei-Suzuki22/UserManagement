package com.user.manegiment.basis.service.user;

import java.util.List;

import com.user.manegiment.basis.entity.MasterUser;

public interface UserService {
	
	public void signup(MasterUser masterUser);
	
	public MasterUser findLoginUser(String userId);
	
	public List<MasterUser> getUsers(MasterUser masterUser);
	
	public MasterUser getUserOne(String userId);
	
	public void updateUserOne(MasterUser masterUser);
	
	public void deleteUserOne(String userId);

}
