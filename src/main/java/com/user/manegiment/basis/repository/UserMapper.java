package com.user.manegiment.basis.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.user.manegiment.basis.entity.MasterUser;

@Mapper
public interface UserMapper {
	
	
	public int insertOne(MasterUser masteruser);
	
	public List<MasterUser> findMany();

}
