package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.LoginUser;
import com.example.demo.service.UserDetailsServiceImpl;

@Controller
public class LoginController {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// ユーザー登録画面を表示
	@GetMapping("/register")
	public String reqister(Model model) {
		logger.info("ユーザー登録画面が表示されました");
		model.addAttribute("loginUser", new LoginUser());
		return "register";
	}

	// ユーザー登録フォームの送信処理
	@PostMapping("/register")
	public String registerUser(@ModelAttribute LoginUser loginUser, Model model) {
		// ユーザーを保存
		userDetailsServiceImpl.saveUser(loginUser);
		model.addAttribute("message", "ユーザー登録が完了しました。");
		logger.info(loginUser + "が登録された");
		return "register";
	}

}
