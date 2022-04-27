package com.example.main.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.main.Model.Ratings;
import com.example.main.Model.Users;
import com.example.main.Repository.RatingRepository;
import com.example.main.Repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RatingRepository ratingRepo;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public void insert(Users u) {
		userRepo.save(u);
	}
	
	public boolean isUserPresent(String uname) {
		String q="select count(u) from Users u where email='"+uname+"'";
		Long count=(Long)entityManager.createQuery(q).getSingleResult();
		return ((count.equals(0L)) ? false:true);
	}
	
	public boolean verifyUser(String uname,String password) {
		String q="select password from Users where email='"+uname+"'";
		String pass=(String) entityManager.createQuery(q).getSingleResult();
		if(password.equals(pass)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void insertRating(Ratings rating) {
		ratingRepo.save(rating);
	}
	
	public List<Ratings> getAllRatings(){
		List<Ratings> ratings=ratingRepo.findAll();
		return ratings;
	}
	
	public List<Ratings> fetchByRestrictions(String amb,String food,String drinks,String service,String clean) {
		String q="from Ratings where ambiance>"+amb+" and food>"+food+" and drinks>"+drinks+" and service>"+service+" and cleanliness>"+clean+"";
		List<Ratings> ratings=entityManager.createQuery(q).getResultList();
		return ratings;
	}
}
