package com.hanaset.onepiececommon.repository;

import com.hanaset.onepiececommon.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUserId(Long userId);
}
