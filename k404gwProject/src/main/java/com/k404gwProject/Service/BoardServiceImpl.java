package com.k404gwProject.Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.k404gwProject.Dto.BoardDto;
import com.k404gwProject.Dto.QnaBoardFileDto;
import com.k404gwProject.Entity.Board;
import com.k404gwProject.Entity.Member;
import com.k404gwProject.Entity.QnaBoardFile;
import com.k404gwProject.Repository.BoardRepository;
import com.k404gwProject.Repository.MemberRepository;
import com.k404gwProject.Repository.QnaBoardFileRepository;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
@Service
@RequiredArgsConstructor
@ToString
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private QnaBoardFileRepository qnaBoardFileRepository;
	
	@Autowired
	private FileService fileService;

	public void createQboard(String title, String content, String name, HttpSession session, BoardDto boardDto, MultipartFile file, HttpServletRequest request) {
		Member sessionMember = (Member) session.getAttribute("email");
		String email = sessionMember.getEmail();
		Member LoginMember = memberRepository.findByEmail(email);
		Board newBoard = new Board(LoginMember, title, content, name);		
		String[] files = boardDto.getFiles();
		 boardRepository.save(newBoard);
		 
		
		
		 if(files == null)
			 return;
		 
		 for (String fullName : files){
			 String testFileName = fullName.substring(14);
			 QnaBoardFile test22 = new QnaBoardFile(newBoard, testFileName, fullName);
		 	qnaBoardFileRepository.save(test22);
		 }
//		List<QnaBoardFile> files = boardDto.getFiles();
//		 System.out.println("보드 서비스의 files에 들어있는 것 " + files);
//		 if (files == null) {
//			 boardRepository.save(newBoard);
//			 return;
//		 } else {
//			 for (QnaBoardFile file : files) {
//				 System.out.println("if문의 else 실행 시 file에 들어있는 것 : " + file);
//				 qnaBoardFileRepository.save(file);
//			 }
//				 
//				
//		 }
	
	}
	
	public List<BoardDto> findAll() {
		List<Board> boardList = boardRepository.findAll();
		System.out.println("boardList에는 뭐가 들어있니?"+boardList);
		List<BoardDto> boardDtoList = new ArrayList();
		
		for (Board board : boardList) {
			boardDtoList.add(BoardDto.toBoardDto(board));
		}
		return boardDtoList;
	}

	@Override
	public BoardDto boardDtl(Long id) {
		Board board = boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		BoardDto boardDtl = BoardDto.toBoardDto(board);
		return boardDtl;
	}

	@Override
	@Transactional
	public Long updateQboard(BoardDto boardDto) {
		Board board = boardRepository.findById(boardDto.getId()).orElseThrow(EntityNotFoundException::new);	
		board.updateBoard(boardDto);
//		boardRepository.save(board2);
		
		return board.getId();
	}

	@Override
	public void deleteQboard(BoardDto boardDto) {
		boardRepository.deleteById(boardDto.getId());
		
	}
	
	
	
	
	
	
	
	
	
//	@Override
//	public Long createQnaBoard(BoardDto boardDto, String email) throws Exception {
//			System.out.println("메서드 실행은 했니?");
//			QnaBoard qnaBoard = new QnaBoard();
//			qnaBoard.createQboard(boardDto);
////			
////		Long test1 = qnaBoard.getMember().getId();
////			QnaBoard test2 = boardRepository.findById(test1).orElseThrow(EntityNotFoundException::new);
////			boardRepository.save(qnaBoard);
//		System.out.println("엔티티맵핑전 qndBoard: ");
//		System.out.println("멤버추가전 qndBoard: "+qnaBoard);
//		Member member = memberRepository.findByEmail(email);
//		System.out.println("member: "+ member);
//		qnaBoard.setMember(member);
//		System.out.println("멤버추가후 qndBoard: "+qnaBoard);
//		boardRepository.save(qnaBoard);	;
//		
//		return qnaBoard.getMember().getId();
//	}
//
//	
	
}
