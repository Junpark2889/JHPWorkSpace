package com.k404gwProject.Dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.k404gwProject.Entity.Board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BoardDto {
	private Long id;
	
	@NotBlank(message = "제목을 입력해주세요.")
	private String title;
	
	@NotEmpty(message = "내용을 입력해주세요.")
	private String content;	
	
	private String name;
	
//	private Subject subject;
	
    private String[] files;
    
    private LocalDateTime regTime;
    
    private int hit;
    
    private int fileCnt;
	
//	private List<QnaBoardFile> files = new ArrayList();
	
	private List<Long>QnaBoardImgId = new ArrayList();
	
    public void setFiles(String[] files) {
        this.files = files;
        setFileCnt(files.length);
    }
    
    public static BoardDto toBoardDto(Board board) {
    	BoardDto boardDto = new BoardDto();
    	boardDto.setId(board.getId());
//    	System.out.println("boardDto.getId()에는 뭐가들어있을까? :" +boardDto.getId());
    	boardDto.setContent(board.getContent());
    	boardDto.setTitle(board.getTitle());
    	boardDto.setName(board.getName());
    	boardDto.setRegTime(board.getRegTime());
    	
    	return boardDto;
    }    
//   public String[] getFiles() {
//	   int i;
//	   String[] fileList;
//	   for (i=0; i<5; i++) {
//		  fiieList = files[i];
//	   }
//	   
//	   return fileList;
//   }
    public static BoardDto toMdBoardDto(Board board) {
    	BoardDto boardDto = new BoardDto();
    	boardDto.setTitle(board.getTitle());
    	boardDto.setContent(boardDto.getContent());
    	boardDto.setRegTime(board.getUpdateTime());
    	return boardDto;
    }
//    public Member BoardDtoToGetId(Member member, BoardDto boardDto) {    	
//    	return  boardDto.getMember().getId();
//    }
//    
//    public static QnaBoard createTqboard(BoardDto boardDto){
//    	QnaBoard qnaBoard = new QnaBoard();
//    	qnaBoard.setTitle(boardDto.getTitle());
//    	qnaBoard.setContent(boardDto.getContent());
//    	qnaBoard.setName(boardDto.getName());
//    	System.out.println("boardDto.getMember(): 실행전");
//    	System.out.println("boardDto.getMember():"+boardDto.getMember());
//    	return qnaBoard;    	
//    }    

}
