package ci.orange.digital.center.ahazzran.webservice.configs;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration(proxyBeanMethods = false)
@SuppressWarnings("null")
public class WebConfig implements WebMvcConfigurer{

  
    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      String uploadDir = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "uploads" + File.separator;
      registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadDir);
    }

}


