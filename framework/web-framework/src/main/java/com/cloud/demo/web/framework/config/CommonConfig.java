package com.cloud.demo.web.framework.config;

import com.cloud.demo.web.framework.encoder.FeignModelAttributeEncoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
  * @Author JackZhou
  * @Description
  * @Date 2020/2/11 14:11
 **/
public class CommonConfig {

    @Value("${spring.swagger.package:com.cloud.demo}")
    private String swaggerPackage;

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

	 /** 
     * 创建API应用 
     * apiInfo() 增加API相关信息 
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现， 
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。 
     */
     @Bean
     public Docket api() {
         return new Docket(DocumentationType.SWAGGER_2)
                 .apiInfo(apiInfo())
                 .select()
                 .apis(RequestHandlerSelectors.basePackage(swaggerPackage))
                 .paths(PathSelectors.any())
                 .build();
     }


    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中） 
     * 访问地址：http://项目实际地址/swagger-ui.html
     * demo
     */
    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2构建RESTful APIs")
                .description("Technology Corp., Ltd")
                .termsOfServiceUrl("http://www.zhou.com.cn/")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Encoder encoder() {
        return new FeignModelAttributeEncoder(new SpringEncoder(messageConverters));
    }
}
