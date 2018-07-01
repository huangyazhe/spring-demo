package com.hyz.cache.springredisdemo.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: huangyazhe
 * Date: 2018/7/1
 * Time: 12:22
 */
public class User implements Serializable {

    private String id;
    private String name;
    private Long salary;

    public User(String id, String name, Long salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
