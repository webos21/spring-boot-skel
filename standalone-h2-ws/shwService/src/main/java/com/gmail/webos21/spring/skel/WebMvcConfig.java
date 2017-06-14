/*
 * Copyright 2017 Cheolmin Jo (webos21@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gmail.webos21.spring.skel;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.gmail.webos21.spring.common.log.LoggingInterceptor;
import com.gmail.webos21.spring.common.mvc.MvcPostProcessor;

import javax.servlet.Filter;


@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new MvcPostProcessor();
    }

    @Bean
    public WebContentInterceptor webContentInterceptor() {
        WebContentInterceptor interceptor = new WebContentInterceptor();
        // XXX : explicit setting (cache : no-store)
        interceptor.setCacheSeconds(0);
        interceptor.setCacheControl(CacheControl.noStore());
        // XXX : below setting is default
        // interceptor.setUseExpiresHeader(true);
        // interceptor.setUseCacheControlHeader(true);
        // interceptor.setUseCacheControlNoStore(true);
        return interceptor;
    }

    @Bean
    public Filter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter f = new HiddenHttpMethodFilter();
        return f;
    }

    @Bean
    public Filter encodingFilter() {
        CharacterEncodingFilter f = new CharacterEncodingFilter();
        f.setEncoding("UTF-8");
        f.setForceEncoding(true);
        return f;
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).favorParameter(true).parameterName("mediaType").ignoreAcceptHeader(true).useJaf(false)
                .defaultContentType(MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor());
        registry.addInterceptor(webContentInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// XXX : NOTHING TO DO
		// registry.addResourceHandler("/**").addResourceLocations("classpath:/WEB-INF/classes/static/").setCachePeriod(31556926);
		// registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
		// registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
		// registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
		// registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}