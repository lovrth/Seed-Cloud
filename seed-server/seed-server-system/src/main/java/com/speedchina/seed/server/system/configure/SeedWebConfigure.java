package com.speedchina.seed.server.system.configure;


import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.speedchina.seed.server.system.properties.SeedServerSystemProperties;
import com.speedchina.seed.server.system.properties.SeedSwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * web配置类
 * @author suyuan
 * @date 2020/5/28 15:36
 */
@Configuration
@EnableSwagger2
public class SeedWebConfigure
{

    @Autowired
    private SeedServerSystemProperties properties;

    /**
     * MyBatis Plus分页插件配置
     * @author suyuan 
     * @date 2020/5/28 15:37
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

    @Bean
    public Docket swaggerApi() {
        SeedSwaggerProperties swagger = properties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(swagger))
                .securitySchemes(Collections.singletonList(securityScheme(swagger)))//安全策略
                .securityContexts(Collections.singletonList(securityContext(swagger)));//安全上下文
    }

    private ApiInfo apiInfo(SeedSwaggerProperties swagger) {
        return new ApiInfo(
                swagger.getTitle(),
                swagger.getDescription(),
                swagger.getVersion(),
                null,
                new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
                swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
    }

    private SecurityScheme securityScheme(SeedSwaggerProperties swagger) {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(swagger.getGrantUrl());//密码模式，认证地址为http://localhost:8301/auth/oauth/token

        return new OAuthBuilder()
                .name(swagger.getName())//名称
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes(swagger)))
                .build();
    }

    private SecurityContext securityContext(SeedSwaggerProperties swagger) {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference(swagger.getName(), scopes(swagger))))//名称关联了上面定义的安全策略
                .forPaths(PathSelectors.any())//设置所有API接口都用这个安全上下文
                .build();
    }

    private AuthorizationScope[] scopes(SeedSwaggerProperties swagger) {
        return new AuthorizationScope[]{
                new AuthorizationScope(swagger.getScope(), "")
        };
    }

}
