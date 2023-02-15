package com.k404gwProject.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.k404gwProject.Dto.BoardDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@SequenceGenerator(
		name="board_seq_gen", //시퀀스 제너레이터 이름
		sequenceName="board_seq", //시퀀스 이름
		initialValue=1, // 시작값
		allocationSize=1 // 메모리 통해 할당할 범위 사이즈, 1로 설정시 insert할때마다 시퀀스 호출
		)
@Table(name="board")
@Getter @Setter
@ToString
public class Board extends BaseEntity {
	
	@Id
	@Column(name="qna_no")
	@GeneratedValue(strategy = GenerationType.AUTO,
			generator="board_seq_gen"
			)	
	private Long id;
	
	@Column(name="qna_title")
	private String title;
	
	@Column(name="qna_content")
	private String content;
	
	@Column(name="qna_hit")
	private Long hit;
	
	@Column(name="qna_reply")
	private Long reply;
	
	@Column(name="qna_name")
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mem_id")
	private Member member;
	
	@Column(name="qna_fileCnt")
	@ColumnDefault("0")
	private int fileCnt;
	
	@OneToMany(mappedBy = "qnaNo",  orphanRemoval=true)
	private List<QnaBoardFile> qnaBoardFileList;
//	@Enumerated(EnumType.STRING)
//	private Subject subject;	
	
	public Board() {};
	
	public Board(Member member, String title, String content, String name) {
		this.member = member;
		this.title = title;
		this.content = content;
		this.name = name;
	}
	
	public void updateBoard(BoardDto boardDto ) {
		this.title = boardDto.getTitle();
		this.content = boardDto.getContent();
		this.setUpdateTime(boardDto.getRegTime());		
	}
}
