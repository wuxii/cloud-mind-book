package com.harmony.cmb.core.config;

import com.harmony.umbrella.security.jwt.JwtTokenGenerator;
import com.harmony.umbrella.security.jwt.support.JwtAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService, JwtTokenGenerator jwtTokenGenerator) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // add success handle and unsuccessful handle
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());

//        auth.apply(new SecurityTokenAuthenticationProviderConfigurer<>())
//                .securityTokenUserDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // AjaxAuthenticationHandler authenticationHandler = new AjaxAuthenticationHandler();
        // @formatter:off
        http
            .sessionManagement().disable()
            .securityContext().disable()
            .csrf().disable()
            .headers().frameOptions().disable().and()
            .authorizeRequests()
				.antMatchers("/").anonymous()
                .antMatchers("/favicon.ico").anonymous()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .successHandler(new JwtAuthenticationSuccessHandler(jwtTokenGenerator))
                .and();
//            .logout()
//                .addLogoutHandler(authenticationHandler)
//                .and()
//            .apply(new SecurityTokenAuthenticationConfigurer<>())
//                .addSecurityTokenExtractor(HttpHeaderSecurityTokenExtractor.INSTANCE)
//                .and()
//            .exceptionHandling()
//                .accessDeniedHandler(authenticationHandler)
//                .authenticationEntryPoint(authenticationHandler);
        // @formatter:on
    }

}