package com.woodo.homework.api.config;

import com.woodo.homework.core.config.CoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CoreConfig.class})
public class ApiDbConfig {
}
