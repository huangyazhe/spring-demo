package com.hyz.cache.springredisdemo.cache;

/**
 * Created with IntelliJ IDEA.
 * User: huangyazhe
 * Date: 2018/7/3
 * Time: 21:57
 */
public final class KeyPrefix {

    public static String buildKey(String prefix, Object... args) {
        StringBuilder sb = new StringBuilder(prefix);
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                sb.append(SPLIT).append(arg);
            }
        }
        return sb.toString();
    }

    //key分隔符
    public final static String SPLIT = "_";
    //读
    public final static String OPERATION_READ = "get";
    //批量读
    public final static String OPERATION_MREAD = "mGet";
    //写
    public final static String OPERATION_WRITE = "set";
    //删除
    public final static String OPERATION_DELETE = "delete";
    //缓存类型
    public final static String CACHE_TYPE = "cache_type";
    //redis实例
    public final static String REDIS_INDEX = "redis_index";
    //静态接口缓存一
    public final static String DETAIL_INFO_ONE = "XingHuo_Hongbao_CacheOne";
    //静态接口缓存二
    public final static String DETAIL_INFO_TWO = "XingHuo_Hongbao_CacheTwo";

}
