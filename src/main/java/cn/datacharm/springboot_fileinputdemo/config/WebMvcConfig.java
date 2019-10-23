package cn.datacharm.springboot_fileinputdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**\
 * 需要你自己创建文件夹  重写WebMvcConfigurer下addResourceHandlers改变资源访问路径
 * (实践证明改变这个类并没有改变路径)
 *
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");

        //如果是Windows系统
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/imgs/**")
                    //项目外路径
                    .addResourceLocations("file:G:/imgs/");

        } else {  //linux 和mac
            registry.addResourceHandler("/img/**")
                    .addResourceLocations("file:/webapps/img");
        }
    }


}
