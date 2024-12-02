package com.example.demo.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

//@Componentはより一般的で、特に分類が必要ないクラスに使われます。
@Component
public class LoginUserDao {

	@Autowired
	private JdbcTemplate jdbc;

	public LoginUser searchByUserName(String userName) {

		String query = "SELECT * FROM login_user WHERE username like ?";

		//SQL実行
		List<Map<String, Object>> list = jdbc.queryForList(query, userName);

		return getLoginUser(list);
	}

	private LoginUser getLoginUser(List<Map<String, Object>> result) {

		LoginUser loginUser = new LoginUser();

		for (Map<String, Object> element : result) {
			Long id = (Long) element.get("id");
			String userName = (String) element.get("username");
			String password = (String) element.get("password");
			String email = (String) element.get("email");

			loginUser.setId(id);
			loginUser.setUsername(userName);
			loginUser.setPassword(password);
			loginUser.setEmail(email);
		}

		return loginUser;
	}
}