package com.k404gwProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k404gwProject.Entity.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {	
	Board findByTitle(String title);
}
