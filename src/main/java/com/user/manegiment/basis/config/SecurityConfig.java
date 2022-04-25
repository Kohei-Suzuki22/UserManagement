package com.user.manegiment.basis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	

	/* セキュリティの対象外を設定 **/
	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring()
				.antMatchers("/webjars/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/error/**")
				.antMatchers("error");
			
	}
	
	/* セキュリティの各種設定 **/
	@Override
	public void configure(HttpSecurity http) throws Exception{
		
		/* ログイン不要ページ: permitAll() **/
		/* ログイン必要ページ: authenticated(); **/
		http.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/user/signup").permitAll()
			.antMatchers("/user/signup/rest").permitAll()
			.antMatchers("/admin").hasAuthority("ROLE_ADMIN")  // 権限制御
			.anyRequest().authenticated();
		
		
		/* ログイン処理 **/
		http.formLogin()
			.loginProcessingUrl("/login")  // ログイン処理のパス。 htmlからログイン情報をpostする先と合わせる
			.loginPage("/login") //ログインページ。　ログインを処理するリンク先をspringSecurityに登録
			.failureUrl("/loign?error") //ログイン失敗の遷移先
			.usernameParameter("userId") //ログイン画面のユーザーID入力欄のname属性と一致させる
			.passwordParameter("password") //ログイン画面のパスワード入力欄のname属性と一致させる
			.defaultSuccessUrl("/user/list", true);  //成功後の遷移先。
		
		/* ログアウト処理 **/

		http.logout()
			.logoutUrl("/logout")						// logoutがpostされるurlと合わせる th:action="@{post先のurl}"
			.logoutSuccessUrl("/login?logoutSuccess");	// 成功後の遷移先。
		
		/*
		 * ↑ springSecurityのログアウト処理を使う場合、logout用のコントローラは不要になる。
		 */
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		
		auth
			.inMemoryAuthentication()
			.withUser("user")
			.password(passwordEncoder.encode("password"))
			.roles("GENERAL")
		.and()
			.withUser("admin")
			.password(passwordEncoder.encode("password"))
			.roles("ADMIN");
		
		/*　ユーザーデータで認証 **/
		auth
			.userDetailsService(userDetailsService)		// 渡されたUserDetailsServiceに基づいた認証を追加
			.passwordEncoder(passwordEncoder);      			// エンコードする方式を指定

	}

}
