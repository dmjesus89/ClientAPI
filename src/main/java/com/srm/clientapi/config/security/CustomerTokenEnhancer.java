package com.srm.clientapi.config.security;
 
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.srm.clientapi.mvc.entity.UserSystem;

public class CustomerTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UserSystem user = (UserSystem) authentication.getPrincipal();
		Map<String, Object> addInfo = new HashMap<>();
		//addInfo.put("firstName", user.getUser().getFirstName());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}
	
	

	

}
