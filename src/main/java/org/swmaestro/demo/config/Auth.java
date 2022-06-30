package org.swmaestro.demo.config;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Auth 어노테이션
 * - Controller에서 @AuthSession 어노테이션이 붙은 메소드는 AuthInterceptor에서 인증 여부 체크를 한다.
 *
 * @since	2022-06-29
 * @author	ywkim
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface Auth {
	public enum Role { SIGNED }
	public Role role() default Role.SIGNED;
}