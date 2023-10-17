package com.UserRegistration.Repository;

import com.UserRegistration.Model.Role;
import com.UserRegistration.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT u FROM Role u WHERE u.name = ?")
    Role findByName(String name);
}
