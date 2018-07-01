package com.hyz.cache.springredisdemo;

import com.hyz.cache.springredisdemo.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: huangyazhe
 * Date: 2018/7/1
 * Time: 12:25
 */
public interface UserRepository {

    void save(User user);

    Map<String, User> findAll();

    User findById(String id);

    void update(User user);

    void delete(String id);

}
