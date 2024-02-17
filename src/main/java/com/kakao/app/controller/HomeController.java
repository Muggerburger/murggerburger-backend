package com.kakao.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kakao.app.entity.UserEntity;
import com.kakao.app.api.KakaoAPI;
import com.kakao.app.dto.KakaoDto;
import com.kakao.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeController {
	private final UserService userService;

	KakaoAPI kakaoApi = new KakaoAPI();

	@GetMapping("/login")
	public ResponseEntity<KakaoDto> login(@RequestParam("code") String code, HttpSession session) {
		System.out.println("code = " + code);
		log.info("Reqeust login");

		log.info("Reqeust getAccessToken()");

		String accessToken = kakaoApi.getAccessToken(code);

		log.info("Response getAccessToken : {}", accessToken);

		log.info("Request getUserInfo()");
		// 2번 인증코드로 토큰 전달
		KakaoDto kakaoDto = kakaoApi.getUserInfo(accessToken);
		kakaoDto.setAccessToken(accessToken);

		log.info("Response getUserInfo");

		UserEntity userEntity = userService.save(kakaoDto.getNickname());

		log.info("save nickname");

		return ResponseEntity.ok().body(kakaoDto);
	}

	@CrossOrigin
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session, HttpServletRequest httpServletRequest) {

		String authorizationHeader = httpServletRequest.getHeader("Authorization");
		session.setAttribute("accessToken", authorizationHeader);

		log.info("accessToken : {}", session.getAttribute("accessToken"));

		kakaoApi.logout((String) session.getAttribute("accessToken"));
		session.removeAttribute("accessToken");
		session.removeAttribute("userId");
		return ResponseEntity.ok().body("{\"message\": \"success\"}");
	}
}