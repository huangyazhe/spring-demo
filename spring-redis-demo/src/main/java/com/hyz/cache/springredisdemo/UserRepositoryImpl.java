package com.hyz.cache.springredisdemo;

import com.hyz.cache.springredisdemo.cache.JsonUtil;
import com.hyz.cache.springredisdemo.cache.KeyPrefix;
import com.hyz.cache.springredisdemo.model.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: huangyazhe
 * Date: 2018/7/1
 * Time: 12:28
 */
@Repository
public class UserRepositoryImpl  extends BaseService implements UserRepository{

    private RedisTemplate<String,User> redisTemplate;

    private HashOperations hashOperations;

    public UserRepositoryImpl(RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(User user) {
        hashOperations.put("USER",user.getId(),user);
    }

    @Override
    public Map<String,User> findAll() {
        return hashOperations.entries("USER");
    }

    @Override
    public User findById(String id) {
        return (User) hashOperations.get("USER",id);
    }

    @Override
    public void update(User user) {
        save(user);
    }

    @Override
    public void delete(String id) {

        String keyPrefix = KeyPrefix.DETAIL_INFO_ONE;
        String key = KeyPrefix.buildKey(keyPrefix, String.valueOf(id));
        cacheUtils.delete(keyPrefix, key);

        hashOperations.delete("USER",id);
    }


    /**
     * 获取第一组缓存 短时间
     */
    private <T> T getCacheOne(String skuId) {
        String prefix = KeyPrefix.DETAIL_INFO_ONE;
        String key = KeyPrefix.buildKey(prefix, skuId);

        String strCache = cacheUtils.get(prefix, key);
        if (strCache != null) {
            return (T) JsonUtil.json2Object(strCache, SkuDetailCacheOne.class);
        }
        return null;
    }

    /**
     * 写入第一组缓存
     * @param event
     */
    private void setCacheOne(SkuDetailEvent event) {
        String prefix = KeyPrefix.DETAIL_INFO_ONE;
        String key = KeyPrefix.buildKey(prefix, event.getSkuId());

        SkuDetailCacheOne skuDetailCacheOne = new SkuDetailCacheOne();

        skuDetailCacheOne.setCommentNum(event.getCommentNum());
        skuDetailCacheOne.setScore(event.getScore());

        String strCache = JsonUtil.write2JsonStr(skuDetailCacheOne);
        cacheUtils.set(prefix, key, strCache);

    }
}
