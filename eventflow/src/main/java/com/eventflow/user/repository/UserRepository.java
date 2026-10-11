package com.eventflow.user.repository;


import com.eventflow.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;



public interface  UserRepository extends JpaRepository<User,Long>{
    
    // this interface connects DB to user and  "jpaRepo" tells spring that this repo is connected to User Entity and primary key is long.
    // save(user)
    // findById(id)
    // findAll()
    // existsById(id)
    // deleteById(id)
    // count()
   // spring then provides all this methods to use . 

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    
}
