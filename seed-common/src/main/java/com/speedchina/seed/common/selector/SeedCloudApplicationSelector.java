package com.speedchina.seed.common.selector;

import com.speedchina.seed.common.configure.SeedAuthExceptionConfigure;
import com.speedchina.seed.common.configure.SeedOAuth2FeignConfigure;
import com.speedchina.seed.common.configure.SeedServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 将多个配置类一次性都注册到IOC容器中
 */
public class SeedCloudApplicationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                SeedAuthExceptionConfigure.class.getName(),
                SeedOAuth2FeignConfigure.class.getName(),
                SeedServerProtectConfigure.class.getName()
        };
    }
}
