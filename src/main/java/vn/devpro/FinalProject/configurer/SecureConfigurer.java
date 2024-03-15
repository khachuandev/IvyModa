package vn.devpro.FinalProject.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecureConfigurer extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests() //Bắt các request từ browser
		
		.antMatchers("/frontend/**", "/backend/**", "/FileUploads/**", "/login-signup").permitAll()
		
//		.antMatchers("/admin/**").authenticated()
		.antMatchers("/admin/**").hasAuthority("admin")
		
		.and()
		
		.formLogin().loginPage("/login").loginProcessingUrl("/login_processing_url")
		
//		.defaultSuccessUrl("/admin/home", true)
		.successHandler(new UrlAuthenticationSuccessHandler())
		
		.failureUrl("/login?login_error=true")
		
		.and()
	
		.logout().logoutUrl("/signup").logoutSuccessUrl("/index").invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400);
	}

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(4));
	}

//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder(4).encode("123456"));
//	}
}
