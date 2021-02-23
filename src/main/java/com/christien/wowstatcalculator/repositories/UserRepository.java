package com.christien.wowstatcalculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.christien.wowstatcalculator.entities.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
}
