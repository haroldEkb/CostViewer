package com.haroldekb.costviewer.repositories;

import com.haroldekb.costviewer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
