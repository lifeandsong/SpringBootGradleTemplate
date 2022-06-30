package org.swmaestro.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringBoot Application 설정 
 *
 * @since	2022-06-29
 * @author	ywkim
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

	private final AuthInterceptor authInterceptor;

	@Autowired
	public WebAppConfig(AuthInterceptor authInterceptor) {
		this.authInterceptor = authInterceptor;
	}

	/**
	 * 인터셉터 추가
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(authInterceptor);
			//.excludePathPatterns("/favicon.ico", "/js/**", "/css/**", "/img/**", "/fonts/**");
	}

	/**
	 * CORS 허가 설정
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
		.addMapping("/**");
		// .allowedOrigins("//dapi.kakao.com");
	}

//	@Bean
//	public FilterRegistrationBean getFilterRegistrationBean() {
//		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
////		filterRegistrationBean.setFilter(new LangCheckFilter());
//		filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
//		filterRegistrationBean.setOrder(1);
//		return filterRegistrationBean;
//     }
	
}