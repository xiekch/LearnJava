package com.example.demo.annotation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target({ TYPE, METHOD, PARAMETER, CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
@MyAnnotation
public @interface MyAnnotation {
    String value() default "hello";
}