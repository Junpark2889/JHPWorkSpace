package com.k404gwProject.Dto;

import java.util.ArrayList;
import java.util.List;

import com.k404gwProject.Entity.QnaBoardFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class QnaBoardFileDto {
	private Long id;
	
	private String fileName;
	
	private String oriFileName;
	
	private String fileUrl;
	
	public static List<QnaBoardFileDto> toQnaBoardFileDto(QnaBoardFile qnaBoardFile) {
		List<QnaBoardFileDto> qnaBoardFileDto = new ArrayList<QnaBoardFileDto>();
		QnaBoardFileDto testDto = new QnaBoardFileDto();
		qnaBoardFileDto.add(testDto);
		for (QnaBoardFileDto testFileDto : qnaBoardFileDto) {
			testFileDto.setId(qnaBoardFile.getId());
			testFileDto.setFileName(qnaBoardFile.getFileName());
			testFileDto.setOriFileName(qnaBoardFile.getOriFileName());
			List<QnaBoardFileDto> testNewDtoList = new ArrayList<QnaBoardFileDto>();
			testNewDtoList.add(testFileDto);
			qnaBoardFileDto = testNewDtoList;
			
		}

		System.out.println("qnaBoardFileDto : " + qnaBoardFileDto);
		System.out.println("qnaBoardFileDto : " + qnaBoardFileDto.size());
		return qnaBoardFileDto;
	}
}
