package com.hyz.cache.springredisdemo.cache.impl;

import com.hyz.cache.springredisdemo.cache.CacheManager;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: huangyazhe
 * Date: 2018/7/3
 * Time: 21:50
 */
public class LocalCacheManagerImpl implements CacheManager {
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
