package com.yifan;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Configuration
@EnableOAuth2Sso
public class SSOSecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf()
				.csrfTokenRepository(csrfTokenRepository()).and()
				.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
				.logout().logoutUrl("/dashboard/logout").permitAll()
				.logoutSuccessUrl("/");

		// Entry points
		http.authorizeRequests()
				//配合@CrossOrigin 解决跨域问题
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/v2/**").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				//开放登录
				.antMatchers("/login").permitAll()
				// 其他请求都需要认证
				.anyRequest().authenticated();
	}

	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request
						.getAttribute(CsrfToken.class.getName());
				if (csrf != null) {
					Cookie cookie = new Cookie("XSRF-TOKEN",
							csrf.getToken());
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				filterChain.doFilter(request, response);
			}
		};
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}


}
