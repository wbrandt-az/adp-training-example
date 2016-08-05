package com.innoq.schulung.spring.demo.framework;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Component
@Transactional
public @interface BusinessLayerComponent {
}
