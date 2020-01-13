package cn.junone.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.junone.entity.User;
import cn.junone.repository.UserRepository;
import cn.junone.util.Md5Util;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 创建用户并且分配默认权限
     * 
     * @param username
     * @param password
     * @return
     */
    @Transactional
    public String create(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            return "username is null";
        }
        if (StringUtils.isEmpty(password)) {
            return "password is null";
        }
        List<User> userList = userRepository.findByName(username);
        if (userList.size() > 0) {
            return "username is use";
        }
        User u = new User();
        u.setNickname(username);
        u.setUsername(username);
        u.setCreateTime(new Timestamp(System.currentTimeMillis()));
        u.setLastTime(new Timestamp(System.currentTimeMillis()));
        String random = Md5Util.createRandom();
        u.setRandom(random);
        u.setPassword(Md5Util.md5(password,random));
        userRepository.save(u);
        // TODO 添加权限
        return "";
    }
}
