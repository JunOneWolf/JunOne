package cn.junone;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JunOneApplication.class)
public class JunOneApplicationTests {

    @Test
    public void test1() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken("JunOneWolf", "JunOneWolf"));
            System.out.println("login:"+subject.isAuthenticated());
            subject.logout();
            subject.login(new UsernamePasswordToken("JunOneWolf", "JunOneWol1f"));
            System.out.println(subject.isAuthenticated());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

}
