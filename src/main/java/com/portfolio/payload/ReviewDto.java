package com.portfolio.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {

	private Integer id;
	private String comment;
	private Integer rating;
}
