package com.dacanh.SQANhapHang.controller;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dacanh.SQANhapHang.model.User;
import com.dacanh.SQANhapHang.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import com.dacanh.SQANhapHang.dto.ResponseObject;
import com.dacanh.SQANhapHang.model.ImportOrder;
import com.dacanh.SQANhapHang.repository.ImportOrderRepository;
import com.dacanh.SQANhapHang.service.ImportOrderService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@EnableTransactionManagement
@RequestMapping("/api")
@Slf4j
public class ImportOrderController {
	@Autowired
	private ImportOrderService importOrderService;
	@Autowired
	private ImportOrderRepository importOrderRepository;
	@Autowired
	private UserService userService;

	@PostMapping("/order")
	@Transactional
	ResponseEntity<ResponseObject> insertOrder(HttpServletRequest request, HttpServletResponse response,
											   @RequestBody ImportOrder order) throws ServletException, IOException {
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
			try{
				String token = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(token);
				String username = decodedJWT.getSubject();
				User user = userService.getUser(username);
				order.setStaff(user);

			}catch (Exception exception){
				log.error("Error logging in: {} ", exception.getMessage());
				response.setHeader("error", exception.getMessage());
				response.setStatus(FORBIDDEN.value());
				//response.sendError(FORBIDDEN.value());
				Map<String, String> error = new HashMap<>();
				error.put("error_message", exception.getMessage());
				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		}else{
			throw new RuntimeException("Refresh token is missing");
		}
		return ResponseEntity.ok().body(importOrderService.insertImportOrder(order));
	}

	@GetMapping("/order/{id}")
	ResponseEntity<ResponseObject> getOrder(@PathVariable int id) {
		return ResponseEntity.ok()
				.body(importOrderService.getImportOrder(id));
	}
	
	@GetMapping("/orders")
	ResponseEntity<ResponseObject> getOrder() {
		return ResponseEntity.ok()
				.body(importOrderService.getAllImportOrder());
	}
}
