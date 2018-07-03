package com.hyz.cache.springredisdemo;

import com.hyz.cache.springredisdemo.cache.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础服务类放置服务的公共属性
 * User: huangyazhe
 * Date: 2018/7/3
 * @version V1.0
 */
public abstract class BaseService {

    @Autowired
    protected CacheUtils cacheUtils;

    /**
     * 获取过期时间
     *
     * @param key key
     */
    public int getTimeout(String key) {
        return ConfigUtils.getTimeout(key);
    }



}
