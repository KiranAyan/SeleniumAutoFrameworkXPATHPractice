package com.practice.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to link Test IDs to Extent Reports
 */
@Retention(RetentionPolicy.RUNTIME) // Critical: Must be available at Runtime for Reflection
@Target(ElementType.METHOD)        // Can only be applied to methods
public @interface TestCaseID {
    String id() default "N/A";     // Default value if no ID is provided
}