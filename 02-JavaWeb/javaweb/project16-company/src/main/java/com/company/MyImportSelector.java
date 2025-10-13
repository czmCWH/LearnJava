package com.company;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ImportSelector 接口实现类，用于给 @import 导入
 */

public class MyImportSelector implements ImportSelector {

    /**
     * selectImports 返回的元素为 配置类全路径字符串的 数组，数组中所有元素全部都会被IOC容器加载
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {"com.company.HeaderConfig"};
    }
}
