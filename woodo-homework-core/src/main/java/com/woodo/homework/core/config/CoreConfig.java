package com.woodo.homework.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CoreJpaConfig.class)
public class CoreConfig {
}
