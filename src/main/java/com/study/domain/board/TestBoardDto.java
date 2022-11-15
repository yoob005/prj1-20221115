package com.study.domain.board;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TestBoardDto {

	private int id;
	private String title;
	private String content;
	private String writer;
	private LocalDateTime inserted;
	
}
