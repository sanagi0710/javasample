package com.example.demo.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.LoginUser;

public class CustomUserDetails implements UserDetails {

	//自分で作成したLoginUserをフィールドに持たせる
	private final LoginUser user;

	/**
	 * コンストラクタ
	 * @param user
	 */
	public CustomUserDetails(LoginUser user) {
		this.user = user;
	}

	/**
	 * ロールの取得（今回は使わないのでnullをリターン）
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	/**
	 * パスワードの取得
	 * ログインユーザーのパスワードをGetする
	 */
	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	/**
	 * ユーザー名の取得
	 * ログインユーザーの名前をGetする
	 */
	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	/**
	 * アカウントが有効期限でないか(使わないので常にtrue)
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * アカウントがロックされていないか(使わないので常にtrue)
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 認証情報が有効期限切れでないか(使わないので常にtrue)
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * アカウントが有効であるかどうか(使わないので常にtrue)
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}