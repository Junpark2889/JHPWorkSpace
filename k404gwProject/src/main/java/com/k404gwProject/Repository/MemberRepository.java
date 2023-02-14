package com.k404gwProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.k404gwProject.Entity.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email);	
}
