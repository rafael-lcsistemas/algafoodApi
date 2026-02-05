package com.algaworks.algafoodapi.core.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    private String[] allowedTypes;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.allowedTypes = constraintAnnotation.allowed();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null) {
            return true;
        }

        String contentType = file.getContentType();

        if (contentType == null || contentType.isEmpty()) {
            return false;
        }

        return Arrays.asList(allowedTypes).contains(contentType);
    }
}
