package com.example.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.main.Model.Ratings;

public interface RatingRepository extends JpaRepository<Ratings,Integer>{

}
