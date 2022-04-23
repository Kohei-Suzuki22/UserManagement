package com.user.manegiment.basis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
		
		// ログイン不要ページ: permitAll()
		// ログイン必要ページ: authenticated();
		http.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/user/signup").permitAll()
			.anyRequest().authenticated();
		
		// CSRF対策を無効に設定
		http.csrf().disable();

	}

}
