package com.kabz.springboot.infrastructure;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RestController
@RequestMapping
public @interface RespApiController {

    /** This allows us to pass the parameter to another annotation, in this case
     *  we simply forward the value to the RequestMapping class. */
    @AliasFor(annotation = RequestMapping.class, attribute = "value")
    String[] value();
}
