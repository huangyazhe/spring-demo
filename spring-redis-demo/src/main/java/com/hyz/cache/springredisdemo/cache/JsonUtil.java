package com.hyz.cache.springredisdemo.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: huangyazhe
 * Date: 2018/7/3
 * Time: 22:01
 */
public class JsonUtil {
    //这里写一些什么
    private static final Log log = LogFactory.getLog(JsonUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();
    static{
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);//设置序列化配置，为null的属性不加入到json中
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);//兼容单引号 但单引号不属于json标准 不建议使用
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);//允许出现特殊字符和转义符
        mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE));
        //反序列化时遇上未知字段不抛异常，主要防止新增字段后，通过缓存获取再反序列化时出现异常
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            SimpleModule module = new SimpleModule("ValueReplaceModule");
            module.addSerializer(new ReplaceJsonValueSerializer());
            mapper.registerModule(module);
        } catch (Throwable t) {
            log.error("", t);
        }

    }
    /**
     * 将对象转换成json字符串,如果转换失败则返回null
     * @author zhaoyunxiao
     * @param o 需要转换为json的对象
     * @return String 转换后的json字符串
     *
     *
     * */
    public static String write2JsonStr(Object o){
        String jsonStr = "";
        try {
            jsonStr = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("write2JsonStr() exception: " + e.getMessage());
        }
        return jsonStr;
    }

    /**
     * 将json转换为对象 如果对象模版为内部类会出现问题，所以不要使用内部类
     * @author zhaoyunxiao
     * @param json 要转换的json
    //     * @param 要映射的类型
     * @return 转换成的目标对象，如果转换失败返回null
     * */
    public static <T> T json2Object(String json,Class<T> clazz){
        try {
            if (!StringUtils.isEmpty(json)) {
                return mapper.readValue(json, clazz);
            }
        } catch (JsonParseException e) {
            log.error("json2Object() parseException: " + e.getMessage());
        } catch (JsonMappingException e) {
            log.error("json2Object() mappingException: " + e.getMessage());
        } catch (IOException e) {
            log.error("json2Object() IOException: " + e.getMessage());
        } catch (Exception e) {
            log.error(json, e);
        }
        return null;
    }

    /**
     * 将json字符串转换为Map
     * @author zhaoyunxiao
     * @param  json 需要转换为Map的json字符串 {}开头结尾的
     * @return 转换后的map 如果转换失败返回null
     * */
    @SuppressWarnings("unchecked")
    public static Map<String,Object> json2Map(String json){
        try {
            if(StringUtils.isBlank(json)) {
                return new HashMap<String,Object>() ;
            }
            return mapper.readValue(json,Map.class);
        } catch (Exception e) {
            log.error(json, e);
        }
        return new HashMap<String,Object>() ;
    }

    /**
     * 将json数组转换为List<Map<String,Object>> json数组格式[{},{}]
     * @author zhaoyunxiao
    //     * @param  需要转换的json数组
     * @return 转换后的列表   如果转换失败返回null
     * */
    @SuppressWarnings("unchecked")
    public static List<Map<String,Object>> jsonArray2List(String jsonArray){
        try {
            if (StringUtils.isNotEmpty(jsonArray)) {
                return mapper.readValue(jsonArray, List.class);
            }
        } catch (JsonParseException e) {
            log.error("jsonArray2List() exception, 异常字符串: " + jsonArray, e);
        } catch (JsonMappingException e) {
            log.error("jsonArray2List() exception, 异常字符串: " + jsonArray, e);
        } catch (IOException e) {
            log.error("jsonArray2List() exception",e);
        } catch (Exception e) {
            log.error(jsonArray, e);
        }
        return new ArrayList<Map<String,Object>>();
    }

    /**
     * @param jsonArray
     * @param clazz
     * @author wutong
     * @return
     */
    public static List<Map<String,Object>> jsonStr2Array(String jsonArray, Class clazz){
        try {
            JavaType jt = mapper.getTypeFactory().constructParametricType(List.class, clazz);
            return mapper.readValue(jsonArray,jt);
        } catch (JsonParseException e) {
            log.error("jsonArray2List() exception, 异常字符串: " + jsonArray, e);
        } catch (JsonMappingException e) {
            log.error("jsonArray2List() exception, 异常字符串: " + jsonArray, e);
        } catch (IOException e) {
            log.error("jsonArray2List() exception",e);
        }
        return new ArrayList<Map<String,Object>>();
    }

    /**
     * 将json数组转换为List<Map<String,Object>> json数组格式[{},{}]
     * @author zhaoyunxiao
    //     * @param  需要转换的json数组
     * @return 转换后的列表   如果转换失败返回null
     * */
    @SuppressWarnings("unchecked")
    public static List<Map<String,Object>> jsonArray2List(String jsonArray,String keyword){
        try {
            return mapper.readValue(jsonArray, List.class);
        } catch (JsonParseException e) {
            log.error("JsonUtil exception, keyword: "+keyword+", 异常字符串: " + jsonArray, e);
        } catch (JsonMappingException e) {
            log.error("JsonUtil exception, keyword: "+keyword+", 异常字符串: " + jsonArray, e);
        } catch (IOException e) {
            log.error("JsonUtil exception",e);
        }
        return new ArrayList<Map<String,Object>>();
    }

    /**
     * 字符串转list
     * @param jsonArray
     * @param clazz
     * @return
     */
    public  static List jsonArray2List(String jsonArray,Class clazz){

        try {
            JSONArray json = JSONArray .fromObject(jsonArray);
            return (List) JSONArray.toCollection(json,clazz);

        } catch (Exception e) {
            log.error("jsonArray2List() exception: " + e.getMessage());
        }
        return null;
    }

    /**
     * for example：
     * TypeReference type = new TypeReference<Map<String, Integer>>() {}
     *
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String jsonString, TypeReference<T> typeReference) {
        if (org.apache.commons.lang.StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return (T) mapper.readValue(jsonString, typeReference);
        } catch (IOException e) {
            log.error("parse json string error:" + jsonString, e);
        }
        return null;
    }

}
