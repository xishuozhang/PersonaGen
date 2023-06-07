package com.demo.enums;

import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public enum FileTypeEnum {

    /**
     * JPEG  (jpg)
     */
    JPEG("JPEG", "image/jpeg"),

    JPG("JPG", "image/jpeg"),

    /**
     * WEBP
     */
    WEBP("WEBP", "image/webp"),

    /**
     * PNG
     */
    PNG("PNG", "image/png"),

    /**
     * Microsoft Word/Excel 注意：word 和 excel的文件头一样
     */
    XLS("XLS", "application/vnd.ms-excel"),

    /**
     * Microsoft Word/Excel 2007以上版本文件
     */
    XLSX("XLSX", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),

    /**
     * Microsoft Word/Excel 注意：word 和 excel的文件头一样
     */
    DOC("DOC", "application/msword"),

    /**
     * Microsoft Word/Excel 2007以上版本文件 注意：word 和 excel的文件头一样
     */
    DOCX("DOCX", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),

    /**
     * Adobe Acrobat (pdf) 255044462D312E
     */
    PDF("PDF", "application/pdf"),

    /**
     * Microsoft PPT
     */
    PPT("PPT", "application/vnd.ms-powerpoint"),

    /**
     * Microsoft PPTX
     */
    PPTX("PPTX", "application/vnd.openxmlformats-officedocument.presentationml.presentation");

    //后缀 大写字母
    private final String suffix;

    //魔数
    private final String magicNumber;

    FileTypeEnum(String suffix, String magicNumber) {
        this.suffix = suffix;
        this.magicNumber = magicNumber;
    }

    @NonNull
    public static FileTypeEnum getBySuffix(String suffix) {
        for (FileTypeEnum fileType : values()) {
            if (fileType.getSuffix().equals(suffix.toUpperCase())) {
                return fileType;
            }
        }
        throw new IllegalArgumentException("unsupported file suffix : " + suffix);
    }

}
