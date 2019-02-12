package com.gfi.microservicios.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.authorizeRequests()
			 .antMatchers("/health").permitAll()
		     .anyRequest().authenticated();
	}
	
}
