package org.example.superbnb.repositories;

import org.example.superbnb.entities.users.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
