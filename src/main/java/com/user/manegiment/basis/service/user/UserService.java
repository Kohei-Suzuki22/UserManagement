package com.user.manegiment.basis.service.user;

import java.util.List;

import com.user.manegiment.basis.entity.MasterUser;

public interface UserService {
	
	public void signup(MasterUser masterUser);
	
	public List<MasterUser> getUsers();
	
	public MasterUser getOne(String userId);

}
