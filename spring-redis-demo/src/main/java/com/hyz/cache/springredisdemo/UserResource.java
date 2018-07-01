package com.hyz.cache.springredisdemo;

import com.hyz.cache.springredisdemo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: huangyazhe
 * Date: 2018/7/1
 * Time: 12:35
 */
@RestController
@RequestMapping("/rest/user")
public class UserResource {

    private UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/add/{id}/{name}")
    public User add(@PathVariable("id") final String id,
                    @PathVariable("name") final String name){
        userRepository.save(new User(id,name,20000L));
        return userRepository.findById(id);
    }

    @GetMapping("/update/{id}/{name}")
    public User update(@PathVariable("id") final String id,
                    @PathVariable("name") final String name){
        userRepository.update(new User(id,name,20000L));
        return userRepository.findById(id);
    }


    @GetMapping("/all")
    public Map<String, User> all(){
        Map<String, User> map =  userRepository.findAll();
        return map;
    }


    @GetMapping("/delete/{id}")
    public Map<String, User> delete(@PathVariable("id") final String id){
        userRepository.delete(id);
        return all();
    }
}
