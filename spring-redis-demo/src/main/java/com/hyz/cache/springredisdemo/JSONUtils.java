package com.hyz.cache.springredisdemo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jd.noah.base.Constants;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * JSON转换工具
 * @author wangneng
 *
 */
public class JSONUtils {

	private static final ObjectMapper om = new ObjectMapper();
    private static final String JSON_CHARSET = "UTF-8";

    static{
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    private JSONUtils() {
	}
	

	public static String toJSON(Object obj) throws Exception {
		if ( obj == null ) {
			return null;
		}

        if(obj instanceof String) {
            return (String)obj;
        }

		return new String(om.writeValueAsBytes(obj), JSON_CHARSET);
	}

    public static byte[] toJSONAsBytes(Object obj) throws Exception {
        if ( obj == null ) {
            return null;
        }
        return toJSON(obj).getBytes(Constants.CHARSET);
    }


    public static <T> T fromJson(byte[] data, Class<T> javaType) throws Exception {
        if ( data == null ) {
            return null;
        }

        return fromJson(new String(data, Constants.CHARSET), javaType);
    }


    public static <T> T fromJson(String jsonAsString, Class<T> valueType) {
        if(org.apache.commons.lang.StringUtils.isBlank(jsonAsString)) {
            return null;
        }
        if(String.class.isAssignableFrom(valueType)) {
            return (T)jsonAsString;
        }
        try {
            return om.readValue(jsonAsString, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String jsonAsString, JavaType javaType) {
        if(org.apache.commons.lang.StringUtils.isBlank(jsonAsString)) {
            return null;
        }
        try {
            return (T) om.readValue(jsonAsString, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws Exception {

//        User user = new User();
//        user.setName(new String("中文".getBytes(), "GBK"));
//
//        System.out.println(fromJson(toJSONAsBytes(user), User.class).getName());

        System.out.println(fromJson("aa", String.class));

    }

    public static final String CHARSET_NAME = "GBK";
    public static final Charset CHARSET = Charset.forName(CHARSET_NAME);
}
