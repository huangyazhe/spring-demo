package com.hyz.cache.springredisdemo.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: huangyazhe
 * Date: 2018/7/3
 * Time: 21:54
 */
public class CacheUtils {
    private final static Log log = LogFactory.getLog(CacheUtils.class);
    private final static String DEFAULT_CACHE_TYPE = "redis";

    private Map<String, CacheManager> cacheManagerMap;

    public CacheManager getCacheManager(String prefix) {
        String cacheType = getCfgValue(KeyPrefix.CACHE_TYPE + KeyPrefix.SPLIT + prefix);
        CacheManager cacheManager = cacheManagerMap.get(cacheType);
        if (cacheManager == null) {
            cacheManager = cacheManagerMap.get(DEFAULT_CACHE_TYPE);
        }
        return cacheManager;
    }

    /**
     * get value
     *
     * @param prefix key prefix
     * @param key    key
     * @return value
     */
    public String get(String prefix, String key) {
        String value =  getCacheManager(prefix).get(prefix, key);
        log.debug("key = " + key + ", value = " + value);
        return value;
    }
    public Map<String,String> mGet(String prefix, String[] keys) {

        return getCacheManager(prefix).mGet(prefix, keys);
    }
    /**
     * get and deserialize
     *
     * @param prefix key prefix
     * @param key    key
     * @param clazz  clazz
     */
    public <T> T getObj(String prefix, String key, Class<T> clazz) {
        String json = get(prefix, key);
        return JsonUtil.json2Object(json, clazz);
    }

    /**
     * get and deserialize
     *
     * @param prefix key prefix
     * @param key    key
     * @param type   type
     */
    public <T> T getObj(String prefix, String key, TypeReference<T> type) {
        String json = get(prefix, key);
        return JsonUtil.fromJson(json, type);
    }

    /**
     * set value
     *
     * @param prefix key prefix
     * @param key    key
     * @param value  value
     */
    public void set(String prefix, String key, String value) {
        if (value != null) {
            getCacheManager(prefix).set(prefix, key, value);
        }
    }

    public void setNoExpire(String prefix, String key, String value) {
        if (value != null) {
            getCacheManager(prefix).setNoExpire(prefix, key, value);
        }
    }

    /**
     * serialize and set
     *
     * @param prefix key prefix
     * @param key    key
     * @param value  value
     */
    public void setObj(String prefix, String key, Object value) {
        if (value != null) {
            String valueStr = JsonUtil.write2JsonStr(value);
            set(prefix, key, valueStr);
        }
    }
    public void hMset(String prefix, String key, Map<String, String> hashes){
        if(hashes !=null ){
            getCacheManager(prefix).hMset(prefix,key,hashes);
        }
    }
    /**
     * delete from redis
     *
     * @param prefix key prefix
     * @param key    key
     */
    public void delete(String prefix, String key) {
        getCacheManager(prefix).delete(prefix, key);
    }

    private String getCfgValue(String key) {
        return ConfigUtils.getValue(key);
    }

    public void setCacheManagerMap(Map<String, CacheManager> cacheManagerMap) {
        this.cacheManagerMap = cacheManagerMap;
    }


}
