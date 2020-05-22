package com.example.springcloud.provider.dao;

import com.example.springcloud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tanbb
 * @create 2020-05-22 15:18
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {


}
