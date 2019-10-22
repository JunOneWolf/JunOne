package cn.junone.repository;

import org.springframework.stereotype.Repository;

@Repository
public class HelloDao {

    public String say() {
        return "hello";
    }
}
