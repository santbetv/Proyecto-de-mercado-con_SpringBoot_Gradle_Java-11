package com.santbetv.developermercado.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.santbetv.developermercado.domain.service.MercadoUserDetailsService;
import com.santbetv.developermercado.web.security.filter.JwtFilterRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MercadoUserDetailsService mercadoUserDetailsService;

	@Autowired
	private JwtFilterRequest jwtFilterRequest;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(mercadoUserDetailsService).passwordEncoder(passwordEncoder());
	}
    
//	  @Override
//	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	  PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	
//	  auth.inMemoryAuthentication()
//	      .withUser("santiago")
//	      .password(encoder.encode("1234"))
//	      .roles("USER");
//	  }
  
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
// 
//        auth.inMemoryAuthentication()
//            .withUser("user")
//            .password(encoder.encode("password"))
//            .roles("USER");
	
//    }

    
    //autorizamos todas las peticionas que se hagan autenticate
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
        		.antMatchers(HttpMethod.GET, "/**/products/{id}").permitAll()
        		.antMatchers(HttpMethod.POST, "/auth/authenticate").permitAll()
                .anyRequest().authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

       http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }
//
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/security",
                "/swagger-ui.html", "/webjars/**");
    }
//
    @Override
    @Bean//Indicamos que elegimos este proceso como gestor de la autenticacion de la app 
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
