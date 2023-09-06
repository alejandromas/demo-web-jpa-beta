package com.alejandromas.demowebjpabeta.rest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {
    public static final String BASE_PATH = "${application.base-path}";
    public static final String API_VERSION = "${application.api-version}";
}
