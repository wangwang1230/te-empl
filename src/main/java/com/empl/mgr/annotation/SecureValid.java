package com.empl.mgr.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.empl.mgr.constant.MethodType;

@Inherited
@Documented
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SecureValid {

	String[] code();

	String desc();

	MethodType type() default MethodType.FIND;

}
