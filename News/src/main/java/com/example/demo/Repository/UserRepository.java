package com.example.demo.Repository;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer>
{

    @Query(value = "select * from users where username = ?1 ",nativeQuery = true)
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);

}