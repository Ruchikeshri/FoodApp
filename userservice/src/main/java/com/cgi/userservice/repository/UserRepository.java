package com.cgi.userservice.repository;

import com.cgi.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findByEmailAndPassword(String emailId, String password);
}
