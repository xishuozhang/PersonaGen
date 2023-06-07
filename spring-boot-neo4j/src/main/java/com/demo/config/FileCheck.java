package com.demo.config;



import com.demo.enums.FileTypeEnum;

import java.lang.annotation.*;

//该注解用于文件校验
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface FileCheck {

    //校验不通过提示信息
    String message() default "格式有误，仅支持头文件为XLS格式或XLSX格式";

    //校验方式
    CheckType type() default CheckType.SUFFIX;

    //支持的文件后缀
    String[] supportedSuffixes() default {};

    //支持的文件类型
    FileTypeEnum[] supportedFileTypes() default {};

    enum CheckType {

        //仅校验后缀
        SUFFIX,

        //校验文件头(魔数)
        MAGIC_NUMBER

    }
}
