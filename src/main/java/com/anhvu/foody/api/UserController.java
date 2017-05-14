package com.anhvu.foody.api;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anhvu.foody.entity.City;
import com.anhvu.foody.entity.User;
import com.anhvu.foody.service.CityService;
import com.anhvu.foody.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public List<User> getAll() {
		return (List<User>) userService.findAll();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String,Boolean> createUser(@RequestBody User user) {
		Boolean result = true;
		Map<String, Boolean> map = new HashMap<>();

		if(!userService.findByEmail(user.getEmail())){
			map.put("success", result = false);
			return map;
		}
		
		try{
			userService.save(user);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = false;
		}
		map.put("success", result);
		return map;
	}
	
	@RequestMapping(value = "/check-login", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Boolean> checkLogin(@RequestBody User user) {
		System.out.println(user.getEmail());

		Boolean result = true;
		Map<String, Boolean> map = new HashMap<>();
		
		if(userService.findByEmail(user.getEmail())){
			map.put("success", result = false);
			return map;
		}
		if(!userService.checkLogin(user.getEmail(),user.getPassword())){
			map.put("success", result = false);
			return map;
		}
		
		map.put("success", result);
		return map;
	}

}
