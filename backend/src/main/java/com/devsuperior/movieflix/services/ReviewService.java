package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private AuthService authService;
	

	@Transactional(readOnly = true)
	public ReviewDTO findById(Long id) {

		Optional<Review> obj = repository.findById(id);
		Review entity = obj.orElseThrow(() -> new ResourceNotFoundException("Review não Encontrado"));

		return new ReviewDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<ReviewDTO> findAllPaged(PageRequest pageRequest) {
		Page<Review> list = repository.findAll(pageRequest);
		return list.map(x -> new ReviewDTO(x));
	}

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		
		
		
		Movie movie = movieRepository.getOne(dto.getMovieId());
		User user = authService.authenticated();
		
		authService.validateSelfOrAdmin(user.getId());
		
		Review entity = new Review();		
		entity.setText(dto.getText());
		entity.setMovie(movie);
		entity.setUser(user);
		
		entity = repository.save(entity);
		
		return new ReviewDTO(entity);
	}

}
