package com.benz.resource.api.config;

import com.benz.resource.api.security.SecurityProperties;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {



    private SecurityProperties securityProperties;

    private TokenStore tokenStore;

    public ResourceServerConfiguration(SecurityProperties securityProperties)
    {
        this.securityProperties=securityProperties;
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Bean
    public DefaultTokenServices tokenServices(TokenStore tokenStore)
    {
        DefaultTokenServices tokenServices=new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        return tokenServices;
    }

    @Bean
    public TokenStore tokenStore() throws Exception
    {
        if(tokenStore==null)
            tokenStore=new JwtTokenStore(tokenConverter());
        return tokenStore;
    }

    private JwtAccessTokenConverter tokenConverter() throws Exception
    {
        JwtAccessTokenConverter converter=new JwtAccessTokenConverter();
        converter.setVerifierKey(getPublicKeyAsString());
        return converter;
    }


    private String getPublicKeyAsString() throws Exception
    {
         return IOUtils.toString(securityProperties.getJwt().getPublicKey().getInputStream(),UTF_8);
    }
}
