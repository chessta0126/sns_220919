package com.sns.timeline;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

@RequestMapping("/post")
@Controller
public class TimelineRestController {

	@Autowired
	private PostBO postBO;
	
	@PostMapping("/add_post")
	public Map<String, Object> addPost(
			@RequestParam("userName") String userName 
			,@RequestParam("content") String content 
			,@RequestParam("file") MultipartFile file
			){
		Map<String, Object> result = new HashMap<>();
		boolean isAddSuccess = postBO.addPost(userName, content, file);
		if(isAddSuccess) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("result", "실패");
		}
		
		return result;
	}
}
