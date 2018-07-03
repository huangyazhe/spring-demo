package com.hyz.cache.springredisdemo.cache;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: huangyazhe
 * Date: 2018/7/3
 * Time: 21:47
 */
public interface CacheManager {

    /**
     * get value
     *
     * @param prefix key prefix
     * @param key key
     *
     * @return value
     */
    public String get(String prefix, String key);
    public Map<String,String> mGet(String prefix, String []keys);

    /**
     * set value
     *
     * @param prefix key prefix
     * @param key key
     * @param value value
     */
    public void set(String prefix, String key, String value);

    public void setNoExpire(String prefix, String key, String value);

    /**
     * delete (only redis)
     *
     * @param prefix key prefix
     * @param key key
     */
    public void delete(String prefix, String key);

    public void hMset(String prefix,String key, Map<String, String> hashes);
}
