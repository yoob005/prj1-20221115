package com.study.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.domain.board.TestBoardDto;
import com.study.domain.board.testPageInfo;
import com.study.mapper.board.TestBoardMapper;

@Service
public class TestBoardService {
	
	@Autowired
	private TestBoardMapper mapper;
	
	public int register(TestBoardDto board) {
		return mapper.insert(board);
	}

	public List<TestBoardDto> listBoard(int page, testPageInfo pageInfo) {
		
		int records = 10; //보여지길 원하는 페이지 수
		int offset = (page - 1) * records ;
		// 총 게시글 수
		int countAll = mapper.countAll();
		int lastPage = (countAll - 1) / records + 1;
				
		int leftPageNumber = (page - 1) / 10 * 10 + 1;
		int rightPageNumber = leftPageNumber + 9;
		rightPageNumber = Math.min(rightPageNumber, lastPage);
		
		// 이전버튼 유무
		boolean hasPrevButton = page > 10;
		// 다음버튼 유무
		boolean hasNextButton = page <= ((lastPage - 1) / 10 * 10);
		
		// 이전버튼 눌렀을 때 가는 페이지 번호
		int jumpPrevPageNumber = (page - 1) / 10 * 10 - 9;
		int jumpNextPageNumber = (page - 1) / 10 * 10 + 11; 
		
		pageInfo.setJumpPrevPageNumber(jumpPrevPageNumber);
		pageInfo.setJumpNextPageNumber(jumpNextPageNumber);
		pageInfo.setHasPrevButton(hasPrevButton);
		pageInfo.setHasNextButton(hasNextButton);
		pageInfo.setCurrentPageNumber(page);
		pageInfo.setLeftPageNumber(leftPageNumber);
		pageInfo.setRightPageNumber(rightPageNumber);
		pageInfo.setLastPageNumber(lastPage);
		
		return mapper.list(offset, records);
	}

	public TestBoardDto get(int id) {
		return mapper.select(id);
	}

	public int update(TestBoardDto board) {
		
		return mapper.update(board);
	}
	
	public int remove(TestBoardDto board) {
		return mapper.delete(board);
	}
	
}
