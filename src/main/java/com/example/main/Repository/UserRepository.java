package com.example.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.main.Model.Users;

public interface UserRepository extends JpaRepository<Users,Integer>{

}
