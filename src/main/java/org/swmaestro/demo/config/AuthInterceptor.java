package org.swmaestro.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 로그인 여부를 체크하는 Interceptor
 *
 * @since	2022-06-29
 * @author	ywkim
 */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	public AuthInterceptor() {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("AuthInterceptor.preHandle");

		// 1. Validation
		//  (1) Controller 내 Method(HandlerMethod)인가?
		if (! (handler instanceof HandlerMethod))
			return true;

		// (2) @Auth가 있는 method인가?
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		if (auth == null) {
			log.debug("No auth interceptor");
			return true;
		}

		// (3) 여기에 Controller의 method 실행 전에 실행할 코드를 넣는다.
		//     - 예: 로그인 인증 처리 등
		log.info("Executed the AuthInterceptor before running Controller's method.");

        // Controller 내 Method로 값을 보내려면, request.setAttribute()를 사용한다.
		// request.setAttribute("userId", id);

		// 4. 사용자 인증 성공
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
		log.info("AuthInterceptor.postHandle");

        // Controller 내 메소드가 실행된 후, JSON Response가 Frontend로 전달되기 전에 항상 실행될 코드가 있다면 여기에 추가한다.
		log.info("Executed the AuthInterceptor after running Controller's method.");

	}

}