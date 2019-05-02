package com.city.security.security.core.social;

import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

import javax.inject.Named;

public class CitySpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public CitySpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter)super.postProcess(object);

        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
