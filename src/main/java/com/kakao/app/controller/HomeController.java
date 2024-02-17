package com.kakao.app.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kakao.app.api.KakaoAPI;
import com.kakao.app.dto.KakaoDto;
import com.kakao.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class HomeController {


	private final UserService userService;

	KakaoAPI kakaoApi = new KakaoAPI();

	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam("code") String code, HttpSession session) {
		System.out.println("code = " + code);
		log.info("Reqeust login");

		ModelAndView mav = new ModelAndView();
		// 1번 인증코드 요청 전달

		log.info("Reqeust getAccessToken()");

		String accessToken = kakaoApi.getAccessToken(code);

		log.info("Response getAccessToken : {}", accessToken);

		log.info("Request getUserInfo()");
		// 2번 인증코드로 토큰 전달
		KakaoDto kakaoDto = kakaoApi.getUserInfo(accessToken);

		log.info("Response getUserInfo");

		String nickname = userService.save(kakaoDto.getNickname());

		System.out.println(nickname);

		mav.addObject("TOKEN", accessToken);
		mav.setViewName("index");
		return ResponseEntity.ok(accessToken);
	}

	@CrossOrigin
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session, HttpServletRequest httpServletRequest) {

		String authorizationHeader = httpServletRequest.getHeader("Authorization");
		session.setAttribute("accessToken", authorizationHeader);

		ModelAndView mav = new ModelAndView();

		log.info("accessToken : {}", session.getAttribute("accessToken"));

		kakaoApi.logout((String) session.getAttribute("accessToken"));
		session.removeAttribute("accessToken");
		session.removeAttribute("userId");
		mav.setViewName("index");
		return ResponseEntity.ok().body("{\"message\": \"success\"}");
	}
}
