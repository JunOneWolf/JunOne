package cn.junone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cn.junone.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("select u  from User u where username= :name ")
    List<User> findByName(@Param("name") String name);

}
