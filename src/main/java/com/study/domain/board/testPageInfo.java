package com.study.domain.board;

import lombok.Data;

@Data
public class testPageInfo {
	private boolean hasPrevButton;
	private boolean hasNextButton;
	private int jumpPrevPageNumber;
	private int jumpNextPageNumber;
	private int currentPageNumber;
	private int lastPageNumber;
	private int leftPageNumber;
	private int rightPageNumber;
	private int searchRightPageNumber;
}
