package kr.co.seculink.web.model.cmon;

import java.io.Serializable;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;

@Data
public class SessionVO implements Serializable {

	private static final long serialVersionUID = -6879974319831987454L;

	private String userId;
	
	private String userNm;

	private String clntId;
	
	private List<SimpleGrantedAuthority> roleList;
}
