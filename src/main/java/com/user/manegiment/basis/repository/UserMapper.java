package com.user.manegiment.basis.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.user.manegiment.basis.entity.MasterUser;

@Mapper
public interface UserMapper {
	
	
	public int insertOne(@Param("masterUser") MasterUser masteruser);
	
	public MasterUser findLoginUser(String userId);
	
	public List<MasterUser> findMany(@Param("masterUser") MasterUser masterUser);
	
	public MasterUser findOne(String userId);
	
	public int updateOne(@Param("masterUser") MasterUser masterUser);
	
	public int deleteOne(@Param("userId") String userId);

}
