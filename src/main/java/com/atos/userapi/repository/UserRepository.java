package com.atos.userapi.repository;

import com.atos.userapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface: {@code UserRepository}
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
