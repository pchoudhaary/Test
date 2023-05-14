package com.ashokit.prop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;


@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix="planapi")
@Configuration
public class AppProperies {

   private	Map<String, String> map = new HashMap<>();
	
}
