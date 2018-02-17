package com.example.auth.oauth2server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Resource(name="tokenStore")
	TokenStore tokenStore;
	 
	@RequestMapping(method = RequestMethod.GET, value = "/tokens")
	@ResponseBody
	public List<String> getTokens() {
	    List<String> tokenValues = new ArrayList<String>();
	    Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("web_app"); 
	    if (tokens!=null){
	        for (OAuth2AccessToken token:tokens){
	            tokenValues.add(token.getValue());
	        }
	    }
	    return tokenValues;
	}
	
}
