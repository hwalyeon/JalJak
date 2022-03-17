package kr.co.seculink.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.auth0.jwt.interfaces.DecodedJWT;

import kr.co.seculink.config.SecurityConfig;
import kr.co.seculink.util.GEUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenFilter extends GenericFilterBean {

	@Autowired
    private JwtTokenProvider jwtTokenProvider;
	
	@Autowired 
	private CustomAuthenticationProvider authenticationProvider;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String uri = ((HttpServletRequest) request).getRequestURI();
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		
		boolean isSkipAuth = false;
		
		if (GEUtil.antmachers(uri, SecurityConfig.authSkipUris)) {
			
			isSkipAuth = true;
			
			//chain.doFilter(request, response);
			//return;
		}
		
//		log.debug("token filter/isSkipAuth : {}, {}", uri, isSkipAuth);
		
		String token = this.jwtTokenProvider.getRequestToken((HttpServletRequest) request);
		
		if (null == token) {
			
			//System.out.println("invalid JWT token");
			//throw new ServletException("invalid JWT token");
//			log.debug("Token 없음");
			//servletResponse.setStatus(HttpStatus.FORBIDDEN.value());
			chain.doFilter(request, response);
			return;
			
		} else {
			if (false == this.jwtTokenProvider.validateToken(token)) {
				
				if (true == isSkipAuth) {
					chain.doFilter(request, response);
					return;
				}
				
				servletResponse.setStatus(HttpStatus.FORBIDDEN.value());
				//throw new ServletException("invalid JWT token");
			} else {
				log.debug("token 검증 성공");
				
				DecodedJWT jwt = this.jwtTokenProvider.decodeToken(token);
				
				String userId   = jwt.getClaim("userId").asString();
				String clientId = jwt.getClaim("clientId").asString();
				
				List<GrantedAuthority> grantedAuths = new ArrayList<>();
				
				String[] roles = jwt.getClaim("roles").asArray(String.class);
				
				if (null != roles && roles.length > 0) {
				
					for (String role : roles) {
						grantedAuths.add(new SimpleGrantedAuthority(role));
					}
				}

				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, clientId, grantedAuths);
				SecurityContextHolder.getContext().setAuthentication(this.authenticationProvider.authenticate(authToken));
				
				chain.doFilter(request, response);
			}
		}
	}
}
