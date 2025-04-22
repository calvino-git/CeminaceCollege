/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.adminfaces.starter.infra.security;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Spring Security Configuration.
 *
 * @author Marcelo Fernandes
 */
@SuppressFBWarnings("SPRING_CSRF_PROTECTION_DISABLED")
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(ApplicationUsers.class)
public class SecurityConfig {
	private BeanFactory beanFactory;
	/**
	 * Configure security.
	 * @param http security var.
	 * @param mvc to create request matchers not handled by faces.
	 * @return the security filter chain.
	 **/
	@Bean
	public SecurityFilterChain configure(HttpSecurity http, MvcRequestMatcher.Builder mvc) {
		try {
			http.authorizeHttpRequests(authz -> authz
							.requestMatchers("/**").permitAll()
							.anyRequest().authenticated()
					)
					.formLogin(withDefaults())
					.csrf(AbstractHttpConfigurer::disable);
//			http.csrf(AbstractHttpConfigurer::disable);
//			http.authorizeHttpRequests((authorize) -> authorize.requestMatchers(mvc.pattern("/"))
//				.permitAll()
//				.requestMatchers(new AntPathRequestMatcher("/**.faces"))
//				.permitAll()
//				.requestMatchers(new AntPathRequestMatcher("/jakarta.faces.resource/**"))
//				.permitAll()
//				.anyRequest()
//				.authenticated())
//				.formLogin((formLogin) -> formLogin.loginPage("/login.faces")
//					.permitAll()
//					.failureUrl("/login.faces?error=true")
//					.defaultSuccessUrl("/index.faces"))
//				.logout((logout) -> logout.logoutSuccessUrl("/login.faces").deleteCookies("JSESSIONID"));
			LoggerFactory.getLogger(SecurityConfig.class).info("HttpSecurity configuration : " + http.toString());
			return http.build();
		}
		catch (Exception ex) {
			throw new BeanCreationException("Wrong spring security configuration", ex);
		}
	}

	@Scope("prototype")
	@Bean
	MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
		return new MvcRequestMatcher.Builder(introspector);
	}

	/**
	 * UserDetailsService that configures an in-memory users store.
	 * @param applicationUsers - autowired users from the application.yml file
	 * @return a manager that keeps all the users' info in the memory
	 */
	@Bean
	public InMemoryUserDetailsManager userDetailsService(ApplicationUsers applicationUsers) {
//		UserDetails user = User.withDefaultPasswordEncoder()
//				.username("user1@example.com")
//				.password("user1")
//				.roles("USER")
//				.build();
//		return new InMemoryUserDetailsManager(user);
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		InMemoryUserDetailsManager result = new InMemoryUserDetailsManager();
		for (UserCredentials userCredentials : applicationUsers.getUsersCredentials()) {
			String[] authorities = userCredentials.getAuthorities().toArray(new String[0]);
			result.createUser(User.builder()
					.username(userCredentials.getUsername())
					.password(encoder.encode(userCredentials.getPassword()))
					.authorities(authorities)
					.build());
		}

		LoggerFactory.getLogger(SecurityConfig.class).info("InMemoryUserDetailsManager UserDetails(user) : " + result.loadUserByUsername("user").toString());
		return result;
	}

}
