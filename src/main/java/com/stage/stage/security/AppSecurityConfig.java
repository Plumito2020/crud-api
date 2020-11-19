package com.stage.stage.security;

import com.stage.stage.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {


	@Qualifier("myUserDetailService")
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests().antMatchers("/auth" , "/register").permitAll().
				antMatchers("/product/add").hasAuthority("ADD_PRODUCT")
				.anyRequest().authenticated().and().
				exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//Rely on user details service
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}



}
