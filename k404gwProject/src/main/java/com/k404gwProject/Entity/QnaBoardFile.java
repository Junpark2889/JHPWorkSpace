package com.k404gwProject.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@SequenceGenerator(
		name="qBoardImg_seq_gen", //시퀀스 제너레이터 이름
		sequenceName="qBoardImg_seq", //시퀀스 이름
		initialValue=1, // 시작값
		allocationSize=1 // 메모리 통해 할당할 범위 사이즈, 1로 설정시 insert할때마다 시퀀스 호출
		)
@Table(name="qna_board_img")
@Getter @Setter
@ToString
public class QnaBoardFile extends BaseEntity {
	@Id
	@Column(name="qna_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO,
			generator = "qBoardImg_seq_gen"
			)
	private Long id;
	
	@Column(name="qna_img_name")
    private String fileName; //이미지 파일명
	
	@Column(name="qna_img_oriName")
    private String oriFileName; //원본 이미지 파일명
	
	@Column(name="qna_img_url")
    private String fileUrl; //이미지 조회 경로
//	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="qnaNo")
	private Board qnaNo;
	
	public QnaBoardFile() {}
	public QnaBoardFile(Board qnaNo, String fileName, String oriFileName) {
		this.qnaNo = qnaNo;
		this.fileName = fileName;
		this.oriFileName = oriFileName;
	}
	
	public void updateFile (String oriFileName, String fileName, String fileUrl) {
		this.oriFileName = oriFileName;
		this.fileName = fileName;
		this.fileUrl = fileUrl;
	}
}
