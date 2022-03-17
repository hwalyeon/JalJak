package kr.co.seculink.config;

import java.util.LinkedHashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kr.co.seculink.domain.GEConstant.ROLE;
import kr.co.seculink.auth.JwtTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	public static String[] authSkipUris = new String[] {
			"/favicon.ico", "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger/**"
    		,"/v1/auth/**", "/v1/home/**"
    		,"/v1/mypage/notiList"
    		,"/v1/mypage/notiDtl"
    		,"/v1/ar/**"
    		,"/test/getTokenUser"
    		,"/test/getTokenAdmin"
    		// 김은찬 CTO 요청
    		,"/v1/as/"
    		,"/v1/astr/"
    		,"/v1/as/asBrndDtl"
    		,"/v1/as/asBrndRevwList"
    		,"/v1/common/srchAddr"
    		,"/v1/astr/astrDtl"
    		,"/v1/as/srchAsBrnd"
    		,"/v1/common/cd"
    		,"/v1/mypage/allAllc"
    		,"/v1/common/srchCoord"
    		,"/v1/astr/astrShar"
    		// health check
    		,"/v1/home/health"
    		,"/v1/crawling/**"
    		,"/"
    		,"/**/*.pg"
    		,"/img/**"
			,"/css/plugins/summernote/font/**"
			,"/js/**/*.map"
			,"/css/**/*.map"
			,"/css/**/*.woff2"
    		,"/css/**/*.css"
    		,"/css/**/*.png"
    		,"/js/**/*.js"
    		,"/fonts/**"
    		,"/font-awesome/**"
    		,"/uploads/**"
	};
	
	@Autowired
	JwtTokenFilter jwtTokenFilter;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	//.httpBasic().disable() // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
    	//.and()
    	.headers().frameOptions().disable()
    	.and()
    	.csrf().disable()
    	//.authenticationProvider(authenticationProvider())
    	.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
    	//.httpBasic()
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	.and()
		.authorizeRequests()
			.antMatchers(authSkipUris).permitAll()
			.antMatchers("/admin/**").hasRole(ROLE.어드민_시큐리티)
//		    .anyRequest().hasRole(ROLE.일반사용자_시큐리티)
			.anyRequest().authenticated()
		.and()
		.logout()
			.invalidateHttpSession(true)
		.and()
		;
    }
    
    /*
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        .antMatchers(authSkipUris)
        ;
    }
    */
    
    @Bean
    public LinkedHashSet<String> blackListToken() {
    	return new LinkedHashSet<String>();
    }
    
    /*
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean
	CustomAuthenticationProvider authenticationProvider() {
		return new CustomAuthenticationProvider();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider())
		;
	}
	*/
}
