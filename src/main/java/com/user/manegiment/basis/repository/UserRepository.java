package com.user.manegiment.basis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.user.manegiment.basis.entity.MasterUser;

@Repository
public interface UserRepository extends JpaRepository<MasterUser, String> {
	
	/**　ログインユーザー取得 */
	@Query("select user from MasterUser user where user.userId = :userId")
	public Optional<MasterUser> findLoginUser(@Param("userId") String userId);
	
	
	/** ユーザー更新 */
	@Modifying
	@Query(value = "update MasterUser set password = :password, userName = :userName where userId = :userId")
	public Integer updateUser(@Param("userId") String userId, @Param("password") String password, @Param("userName") String userName);
	
}
