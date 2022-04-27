package com.user.manegiment.basis.service.user.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.manegiment.basis.entity.MasterUser;
import com.user.manegiment.basis.repository.UserRepository;
import com.user.manegiment.basis.service.user.UserService;

@Service
@Primary
public class UserServiceJPAImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/** ユーザー登録 */
	@Transactional
	@Override
	public void signup(MasterUser user) {

		boolean exsists = userRepository.existsById(user.getUserId());

		if (exsists) {
			throw new DataAccessException("ユーザーが既に存在している") {
			};
		}

		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL");
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userRepository.save(user);

	}
	
	/** ログインユーザーの取得 */
	@Override
	public MasterUser findLoginUser(String userId){
		
		Optional<MasterUser> findUserResult = userRepository.findById(userId);
		
		MasterUser user = findUserResult.orElse(null);
		
		return user;
		
	}
	
	/** ユーザー全件取得 */
	@Override
	public List<MasterUser> getUsers(MasterUser user) {
		return userRepository.findAll();
	}

	/** ユーザー1件取得 */
	@Override
	public MasterUser getUserOne(String userId) {
		
		Optional<MasterUser> findUserResult = userRepository.findById(userId);
		
		
		MasterUser user = findUserResult.orElse(null);
		
		return user;
	}
	
	/** ユーザー1件更新 */
	@Override
	@Transactional
	public void updateUserOne(MasterUser masterUser) {}
	
	
	/** ユーザー1件削除 */
	@Override
	@Transactional
	public void deleteUserOne(String userId) {
		
		userRepository.deleteById(userId);
		
	}
	
	
}
