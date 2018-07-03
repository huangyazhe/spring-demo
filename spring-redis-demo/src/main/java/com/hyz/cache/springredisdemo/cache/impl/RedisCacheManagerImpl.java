package com.hyz.cache.springredisdemo.cache.impl;


import com.hyz.cache.springredisdemo.ConfigUtils;
import com.hyz.cache.springredisdemo.cache.CacheManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: huangyazhe
 * Date: 2018/7/3
 * Time: 21:48
 */
public class RedisCacheManagerImpl implements CacheManager {

    private final static Log log = LogFactory.getLog(RedisCacheManagerImpl.class);


    @Override
    public String get(String prefix, String key) {
        return null;
    }

    @Override
    public Map<String, String> mGet(String prefix, String[] keys) {
        return null;
    }

    @Override
    public void set(String prefix, String key, String value) {

    }

    @Override
    public void setNoExpire(String prefix, String key, String value) {

    }

    @Override
    public void delete(String prefix, String key) {

    }

    @Override
    public void hMset(String prefix, String key, Map<String, String> hashes) {

    }
}
