package cn.junone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.junone.repository.HelloDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JunOneApplicationTests {
    @Autowired
    HelloDao hello;

    @Test
    public void contextLoads() {
        System.out.println(hello.say());
    }

}
