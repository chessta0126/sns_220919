package com.sns.like;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeRestController {

	// /like?postId=13  -> @RequestParam
	// /like/{postId}   -> @PathVariable
	@GetMapping("/like/{postId}") // 와일드카드
	public Map<String,Object> like(
			@PathVariable int postId
			){
		
	}
	
}
