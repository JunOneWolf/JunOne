package cn.junone.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import cn.junone.entity.User;
import cn.junone.repository.UserRepository;
import cn.junone.util.Md5Util;

/**
 * 权限类
 * 
 * @author Administrator
 *
 */

@Component
public class ShiroConfiguration extends AuthorizingRealm {
    private static Logger log = LoggerFactory.getLogger(AuthorizingRealm.class);
    @Autowired
    private UserRepository userRepository;

    /**
     * 保存的用户信息
     */
    private Map<String, User> userMap = new HashMap<>();
    /**
     * 保存最新的用户
     */
    private Queue<String> s = new LinkedList<>();

    private void saveUser(String username) {
        if (userMap.containsKey(username)) {
            return;
        }
        List<User> users = userRepository.findByName(username);
        if (users.size() == 0) {
            return;
        }
        if (!userMap.containsKey(username)) {
            if (s.size() > 10) {
                userMap.remove(s.poll());
                s.offer(username);
            }
        }
        userMap.put(username, users.get(0));
    }

    @Bean
    public DefaultSecurityManager getDefaultSecurityManager() {
        DefaultSecurityManager security = new DefaultSecurityManager();
        security.setRealm(this);
        SecurityUtils.setSecurityManager(security);
        saveUser("JunOneWolf");
        saveUser("Root");
        // TODO 临时存储必要的用户和权限
        return security;
    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("权限认证");
        String loginName = (String) super.getAvailablePrincipal(principalCollection);
        // 到数据库查是否有此对象
        // 取得权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> userRoles = new HashSet<>();
        userRoles.add("system");
        info.addRoles(userRoles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        log.info("登录认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        if (!userMap.containsKey(username)) {
            saveUser(username);
            if (!userMap.containsKey(username)) {
                log.info("用户不存在");
                return null;
            }
        }
        User user = userMap.get(username);
        String password = Md5Util.md5(new String(token.getPassword()), user.getRandom());
        System.out.println(password);
        if (!password.equalsIgnoreCase(user.getPassword())) {
            log.info("密码不匹配");
            return null;
        }
        // 若存在，将此用户存放到登录认证info中
        // 第一个参数为成功后存储到session中的信息 获取通过 SecurityUtils.getSubject().getPrincipal()进行获取
        return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
    }

}
