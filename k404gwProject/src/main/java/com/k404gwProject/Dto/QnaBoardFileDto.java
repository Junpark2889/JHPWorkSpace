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
	
}
