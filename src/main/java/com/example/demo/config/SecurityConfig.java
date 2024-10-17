package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//　設定クラスであることを表す
@Configuration
//spring securityを有効
@EnableWebSecurity
//メソッドレベルのセキュリティ設定を可能にする
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//		HttpSecurityを使用して、セキュリティ設定を定義します。
		//		formLogin(): フォームベースのログインを有効にします。ログインページを/loginに指定し、ユーザーがログイン後にリダイレクトされるURLを/に設定します。
		//		logout(): ログアウト機能を設定し、ログアウト後のリダイレクトURLを指定します。
		//		authorizeHttpRequests(): リクエストの認可設定を行います。/css/**や静的リソースへのアクセスを許可し、それ以外は認証を要求します。
		http
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.and()
				.formLogin(login -> login
						.loginPage("/login")
						.permitAll()
						.defaultSuccessUrl("/"))
				//ここはname="user_id" name="pass_word"のカスタム				
				//.usernameParameter("user_id")
				//.passwordParameter("pass_word")
				.logout(logout -> logout
						.logoutSuccessUrl("/login?logout")
						.permitAll()
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID"))
				.authorizeHttpRequests(authz -> authz
						.requestMatchers("/register").permitAll()
						.requestMatchers("/css/**")
						.permitAll()
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
						.permitAll()
						.anyRequest().authenticated())
				.csrf().disable(); // テスト目的でCSRFを無効に（本番環境では推奨されません）
		return http.build();
	}

	//	@Bean アノテーションは、Springコンテナにオブジェクトを登録するために使用されます。
	//	これにより、メソッドが返すオブジェクトがSpringによって管理され、他のコンポーネントから依存性注入を通じて利用できるようになります。
	//	特に、設定クラス内での使用が一般的で、アプリケーションの構成やサービスを定義する際に便利です。
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}