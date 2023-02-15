package com.k404gwProject.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.k404gwProject.Dto.BoardDto;
import com.k404gwProject.Entity.Board;

public interface BoardService {
	
	
//	public Long createQnaBoard(BoardDto boardDto, String email)throws Exception;  //게시글 작성
//	public void insert(Board qnaBoard, Member member);
//	public void insert(Board board);
	public void createQboard(String title, String content, String name, HttpSession session, BoardDto boardDto, MultipartFile file, HttpServletRequest request);
	public List<BoardDto> findAll();
	public BoardDto boardDtl(Long id);
	public void updateQboard(BoardDto boardDto, Long id, Board qnaNo);
	public void deleteQboard(BoardDto boardDto);
}
