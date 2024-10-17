package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.LoginUser;
import com.example.demo.repository.LoginUserRepository;

//はい、自動的に呼ばれます。ユーザーがログインフォームに情報を入力し、送信すると、
//Spring Securityが認証プロセスを実行します。その際にUserDetailsServiceの実装である
//UserDetailsServiceImplのloadUserByUsernameメソッドが呼ばれ、指定されたユーザー名に基づいてユーザー情報がデータベースから取得されます。
//Securityの内部で管理されており、開発者が明示的に呼び出す必要はありません。
//UserDetailsServiceの実装はほとんどが定型文です。
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private LoginUserRepository loginUserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder; // PasswordEncoderを追加

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		LoginUser user = loginUserRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("not found :" + username);
		}

		return new CustomUserDetails(user);
	}

	public LoginUser saveUser(LoginUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return loginUserRepository.save(user);
	}

}