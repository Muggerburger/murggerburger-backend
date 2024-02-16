package com.kakao.app.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.kakao.app.api.KakaoAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class HomeController {

	KakaoAPI kakaoApi = new KakaoAPI();
	
	@GetMapping("/login")
	public ModelAndView login(@RequestParam("code") String code, HttpSession session) {
		System.out.println("code = " + code);
		log.info("Reqeust login");

		ModelAndView mav = new ModelAndView();
		// 1번 인증코드 요청 전달

		log.info("Reqeust getAccessToken()");

		String accessToken = kakaoApi.getAccessToken(code);

		log.info("Response getAccessToken : {}", accessToken);

		log.info("Request getUserInfo()");
		// 2번 인증코드로 토큰 전달
		HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);

		log.info("Response getUserInfo");
		
		System.out.println("login info : " + userInfo.toString());

		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		kakaoApi.kakaoLogout((String)session.getAttribute("accessToken"));
		session.removeAttribute("accessToken");
		session.removeAttribute("userId");
		mav.setViewName("index");
		return mav;
	}
	//언니 바보
	
	
}
