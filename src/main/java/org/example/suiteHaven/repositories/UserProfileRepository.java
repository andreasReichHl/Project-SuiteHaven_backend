package org.example.suiteHaven.repositories;

import org.example.suiteHaven.entities.users.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
