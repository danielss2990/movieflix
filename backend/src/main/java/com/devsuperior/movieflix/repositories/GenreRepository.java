package com.devsuperior.movieflix.repositories;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{
	
	@Query(" SELECT obj FROM Genre obj")
	Page<Genre> search (Pageable pageable);
}
