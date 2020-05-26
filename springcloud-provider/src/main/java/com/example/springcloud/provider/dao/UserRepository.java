package com.example.springcloud.provider.dao;

import com.example.springcloud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tanbb
 * @create 2020-05-22 15:18
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {


    @Query("select u from User u where u.id in (:idList)")
    List<User> getUsersByIds(@Param("idList") List<String> idList);
}
