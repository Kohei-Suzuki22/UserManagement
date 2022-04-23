package com.user.manegiment.basis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

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
			.anyRequest().authenticated();
		
		/* CSRF対策を無効に設定 **/
		http.csrf().disable();
		
		/* ログイン処理 **/
		http.formLogin()
			.loginProcessingUrl("/login")  // ログイン処理のパス。 htmlからログイン情報をpostする先と合わせる
			.loginPage("/login") //ログインページ。　ログインを処理するリンク先をspringSecurityに登録
			.failureUrl("/loign?error") //ログイン失敗の遷移先
			.usernameParameter("userId") //ログイン画面のユーザーID入力欄のname属性と一致させる
			.passwordParameter("password") //ログイン画面のパスワード入力欄のname属性と一致させる
			.defaultSuccessUrl("/user/list", true);  //成功後の遷移先。
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		PasswordEncoder encoder = passwordEncoder();
		
		auth
			.inMemoryAuthentication()
			.withUser("user")
			.password(encoder.encode("password"))
			.roles("GENERAL")
		.and()
			.withUser("admin")
			.password(encoder.encode("password"))
			.roles("ADMIN");
	}

}
