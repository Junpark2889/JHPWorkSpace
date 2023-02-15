package com.k404gwProject.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.k404gwProject.Dto.QnaBoardFileDto;
import com.k404gwProject.Entity.Board;
import com.k404gwProject.Entity.QnaBoardFile;
import com.k404gwProject.Repository.QnaBoardFileRepository;

@Service
@Transactional
public class BoardFileServiceImpl implements BoardFileService {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	QnaBoardFileRepository qnaBoardFileRepository;
	
	public List<QnaBoardFileDto> getBoardFile (Board qnaNo) {
		List<QnaBoardFile> qnaBoardFile = qnaBoardFileRepository.findByQnaNoOrderByIdAsc(qnaNo);
		List<QnaBoardFileDto> boardFileDtlTest = new ArrayList<QnaBoardFileDto>();
		
		for (QnaBoardFile boardFile : qnaBoardFile) {
			QnaBoardFileDto boardFileDtl = new QnaBoardFileDto();
			boardFileDtl.setFileName(boardFile.getFileName());
			boardFileDtl.setOriFileName(boardFile.getOriFileName());
			boardFileDtlTest.add(boardFileDtl);
			
		}
		System.out.println("boardFileDtlTest :" + boardFileDtlTest);
		System.out.println("boardFileDtlTest의 길이 :" + boardFileDtlTest.size());
		return boardFileDtlTest;
	}
}