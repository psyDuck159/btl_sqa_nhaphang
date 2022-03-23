package com.dacanh.SQANhapHang.repository;

import com.dacanh.SQANhapHang.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
