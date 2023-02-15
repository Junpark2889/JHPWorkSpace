package com.k404gwProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.k404gwProject.Entity.Board;
import com.k404gwProject.Entity.QnaBoardFile;

@Repository
public interface QnaBoardFileRepository extends JpaRepository<QnaBoardFile, Long>{
	List<QnaBoardFile> findByQnaNoOrderByIdAsc(Board qnaNo);
	QnaBoardFile findByOriFileName(String oriFileName);
}
