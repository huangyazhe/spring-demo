package com.hyz.cache.springredisdemo;

/**
 * Created with IntelliJ IDEA.
 * User: huangyazhe
 * Date: 2018/7/3
 * Time: 22:17
 */
public final class ConfigUtils {

    private final static int DEFAULT_CACHE_TIME = 300;

    public static int getCacheTime(String key) {
        return configCenter.getCacheConfigValueByKey(key, DEFAULT_CACHE_TIME, Integer.class);
    }

    public static int getTimeout(String key) {
        return 0;
    }
}
