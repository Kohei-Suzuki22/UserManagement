package com.user.manegiment.basis.service.user.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
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
	public MasterUser findLoginUser(String userId) {

		Optional<MasterUser> findLoginUserResult = userRepository.findLoginUser(userId);

		MasterUser user = findLoginUserResult.orElse(null);

		return user;

	}

	/** ユーザー全件取得 */
	@Override
	public List<MasterUser> getUsers(MasterUser user) {

		// 検索条件
		ExampleMatcher matcher = ExampleMatcher
										.matchingAny() //or検索
										.withStringMatcher(StringMatcher.CONTAINING)  // Like検索
										.withIgnoreCase();
		
		/*
		 * user で送られてきた情報が 「userId = "hello",userName = "john"」 だとすると、
		 * matcherが and条件like検索であれば、
		 * userId = "hello", userName = "john" で and条件like検索をする。
		 */
		

		return userRepository.findAll(Example.of(user,matcher));	// userのプロパティと matcher 条件で検索。
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
	public void updateUserOne(MasterUser masterUser) {

		String password = passwordEncoder.encode(masterUser.getPassword());

		Integer i = userRepository.updateUser(masterUser.getUserId(), password, masterUser.getUserName());

		System.out.println(i);

	}

	/** ユーザー1件削除 */
	@Override
	@Transactional
	public void deleteUserOne(String userId) {

		userRepository.deleteById(userId);

	}

}
