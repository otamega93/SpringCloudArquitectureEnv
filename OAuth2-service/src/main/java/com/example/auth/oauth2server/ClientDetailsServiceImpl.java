package com.example.auth.oauth2server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import com.example.auth.entities.Client;
import com.example.auth.repositories.ClientRepository;
import com.google.common.base.Splitter;
import com.netflix.client.ClientException;

@Component
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		
        Client client = clientRepository.findByClientId(clientId);
        
        if(client == null) {
            try {
				throw new ClientException("no client found with the cliendId: " + clientId);
			} catch (ClientException e) {
				e.printStackTrace();
			}
        }

        // Converting strings into lists
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        Iterable<String> authStrings = Splitter.on(',')
       	       .trimResults()
       	       .omitEmptyStrings()
       	       .split(client.getAuthorities());
        for(String authString : authStrings) {
            authorities.add(new SimpleGrantedAuthority(authString));
        }
        
        List<String> authorizedGrantTypes = new ArrayList<String>();
        Iterable<String> authorizedGrantTypesStrings = Splitter.on(',')
       	       .trimResults()
       	       .omitEmptyStrings()
       	       .split(client.getAuthorizedGrantTypes());
        for(String authorizedGrantTypesString : authorizedGrantTypesStrings) {
        	authorizedGrantTypes.add(authorizedGrantTypesString);
        }
        
        List<String> resourceIds = new ArrayList<String>();
        Iterable<String> resourceIdsStrings = Splitter.on(',')
       	       .trimResults()
       	       .omitEmptyStrings()
       	       .split(client.getResourceId());
        for(String resourceIdsString : resourceIdsStrings) {
        	resourceIds.add(resourceIdsString);
        }
       
        List<String> scopes = new ArrayList<String>();
        Iterable<String> scopesStrings = Splitter.on(',')
       	       .trimResults()
       	       .omitEmptyStrings()
       	       .split(client.getScopes());
        for(String scopesString : scopesStrings) {
        	scopes.add(scopesString);
        }
        
        Set<String> redirectUris = new HashSet<String>();
        Iterable<String> RedirectUriStrings = Splitter.on(',')
        	       .trimResults()
        	       .omitEmptyStrings()
        	       .split(client.getRedirectUri());
         for(String redirectUri : RedirectUriStrings) {
        	 redirectUris.add(redirectUri);
         }

        // Create the client
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId(client.getClientId());
        baseClientDetails.setClientSecret(client.getClientSecret());
        baseClientDetails.setAuthorities(authorities);
        baseClientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
        baseClientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds());
        baseClientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds());
        baseClientDetails.setResourceIds(resourceIds);
        baseClientDetails.setScope(scopes);
        baseClientDetails.isAutoApprove(client.getIsAutoApprove().toString());  
        baseClientDetails.setRegisteredRedirectUri(redirectUris);
        return baseClientDetails;
	}



}
