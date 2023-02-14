package com.k404gwProject.Service;

import java.util.List;

import com.k404gwProject.Dto.QnaBoardFileDto;
import com.k404gwProject.Entity.Board;

public interface BoardFileService {
	public List<QnaBoardFileDto>	getBoardFile (Board qnaNo);
}
