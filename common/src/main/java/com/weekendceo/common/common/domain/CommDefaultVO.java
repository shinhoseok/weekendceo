package com.weekendceo.common.common.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@NoArgsConstructor
@Getter
@Setter
public class CommDefaultVO implements Serializable {
	//검색조건
	private String searchCondition;
	//검색keyword
	private String searchKeyword;
	//정렬조건
	private String sortSubject;
	//정렬방식
	private String sortDescend;
	//페이지 인덱스
	private int page;
}
