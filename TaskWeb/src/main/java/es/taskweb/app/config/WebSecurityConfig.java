package es.taskweb.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(proxyTargetClass = true, securedEnabled = true)
public class WebSecurityConfig {
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() { 
		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
		//return encoder;
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(
				(requests) -> requests
				.anyRequest().authenticated())
				.formLogin((form) -> form
						.loginPage("/login").permitAll()
						.defaultSuccessUrl("/index") )
				.logout((logout) -> logout.permitAll());

		return http.build();
	}
	
	/* ***** CON BASE DE DATOS ***** */
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
	
	/* ***** SIN BASE DE DATOS ***** */
	/*
	  
	@Bean 
	public PasswordEncoder passwordEncoder() { 
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
		return encoder; 
	}

	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails admin =  User.builder().username("admin").password(passwordEncoder().encode("admin" )).roles("ADMIN").build();
		UserDetails user =  User.builder().username("alberto").password(passwordEncoder().encode("password" )).roles("USER").build();
		
		//User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();

		return new InMemoryUserDetailsManager(admin, user);
	}
	*/
}
