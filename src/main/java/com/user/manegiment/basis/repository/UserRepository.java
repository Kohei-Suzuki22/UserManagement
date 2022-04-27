package com.user.manegiment.basis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.manegiment.basis.entity.MasterUser;

public interface UserRepository extends JpaRepository<MasterUser, String> {

}
