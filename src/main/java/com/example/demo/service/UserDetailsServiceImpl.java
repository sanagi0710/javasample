package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.LoginUser;
import com.example.demo.repository.LoginUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private LoginUserRepository loginUserRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginUser user = loginUserRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found: " + username);
		}
		return new CustomUserDetails(user);
	}

	public LoginUser saveUser(LoginUser user) {
		if (loginUserRepository.findByUsername(user.getUsername()) != null) {
			throw new IllegalArgumentException("登録済みのユーザー名" + user.getUsername());
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return loginUserRepository.save(user);
	}
}
