package com.study.mapper.board;

import java.util.List;

import com.study.domain.board.TestBoardDto;

public interface TestBoardMapper {

	int insert(TestBoardDto board);

	List<TestBoardDto> list(int offset, int records);

	TestBoardDto select(int id);

	int update(TestBoardDto board);
	
	int delete(TestBoardDto board);

	int countAll();

}
