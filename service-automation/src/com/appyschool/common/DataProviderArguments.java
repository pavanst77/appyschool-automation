package com.appyschool.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation for feeding arguments to methods conforming to the "@DataProvider"
 * annotation type.
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DataProviderArguments {  String value(); }