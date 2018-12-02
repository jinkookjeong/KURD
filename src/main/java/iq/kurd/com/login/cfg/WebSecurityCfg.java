package iq.kurd.com.login.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import iq.kurd.com.login.hdler.LoginFailHandler;
import iq.kurd.com.login.hdler.LoginSuccHandler;
import iq.kurd.com.login.hdler.LogoutHandler;

//@EnableGlobalMethodSecurity
//@EnableWebSecurity
public class WebSecurityCfg { //extends WebSecurityConfigurerAdapter {
  
  @Autowired(required=true)
  private UserDetailsService userDetailsService;
  
  @Autowired
  private LoginFailHandler loginFailHandler;
  
  @Autowired
  private LogoutHandler logoutHandler;
  
  @Autowired
  private LoginSuccHandler successHandler;
  
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  };
  
  //@Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  //@Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
       .antMatchers("/cm/public/**","/css/**","/js/**","/images/**");         
  }
  
  //@Override
  protected void configure(HttpSecurity http) throws Exception {
    http    
	    .authorizeRequests()
	    //.anyRequest().hasAnyRole("ADMIN", "USER")	    
	    //.authorizeRequests()    
	    .antMatchers("/*").permitAll() ///login패스는 모두 허용한다 로그인안해도 보여줘라
	    .antMatchers("/pt/**").hasAnyRole("ADMIN","USER")
	    .antMatchers("/admin/**").hasAnyRole("ADMIN")
        .anyRequest().authenticated()  //나머지는 인증이 필요하다.
        .and()
	    .formLogin()
	    .loginPage("/login.jsp")    
	    .loginProcessingUrl("/loginProcess.do").permitAll()	    
	    .failureHandler(loginFailHandler)	   
	    //.defaultSuccessUrl("/main.do", true)
	    .successHandler(successHandler)
	    .and()
	    .logout().logoutSuccessUrl("/home.jsp").permitAll()
	    .logoutSuccessHandler(logoutHandler)    
	    .deleteCookies("JSESSIONID")
	    .and()
	    .csrf().disable()
	    .exceptionHandling().accessDeniedPage("/WEB-INF/jsp/com/exception/accessDeny.jsp"); //access Deny
    
    }
  
	public static void main(String[] args) {
		String encoded= new BCryptPasswordEncoder().encode("admin123");		
		System.out.println(encoded);
		
	}
}