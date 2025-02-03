package com.lambdatest.tutorial.config;

public class TestConfig {
    public static final String USERNAME = System.getenv("LT_USERNAME");
    public static final String ACCESS_KEY = System.getenv("LT_ACCESS_KEY");
    public static final String GRID_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.lambdatest.com/wd/hub";
    
    public static class Browser {
        public static final String CHROME = "chrome";
        public static final String FIREFOX = "firefox";
        public static final String SAFARI = "safari";
        public static final String EDGE = "edge";
    }
    
    public static class Version {
        public static final String LATEST = "latest";
        public static final String CHROME_114 = "114.0";
        public static final String FIREFOX_113 = "113.0";
    }
    
    public static class Platform {
        public static final String WINDOWS_10 = "Windows 10";
        public static final String WINDOWS_11 = "Windows 11";
        public static final String MACOS_MONTEREY = "macOS Monterey";
    }
    
    public static class Status {
        public static final String PASSED = "passed";
        public static final String FAILED = "failed";
    }
} 