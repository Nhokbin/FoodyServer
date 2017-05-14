package com.anhvu.foody.api;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anhvu.foody.entity.Category;
import com.anhvu.foody.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public List<Category> getAll() {
		return (List<Category>) categoryService.findAll();
	}

	@RequestMapping(value = "/getdata", method = RequestMethod.GET)
	private String getData() throws IOException {
		
		try {
		
			File htmlFile = new File("C:/Users/binhb/Desktop/123.html");
			Document doc = Jsoup.parse(htmlFile, "UTF-8");
			Element ul = doc.select("ul.category-choosers").first();
			System.out.println(ul);
			Elements lis = ul.getElementsByTag("li");
			for (Element li : lis) {
				int value = Integer.parseInt(li.getElementsByTag("a").first().attr("value"));
				int type = Integer.parseInt(li.getElementsByTag("a").first().attr("type"));
				Category category;
				System.out.println(value);
				if(categoryService.findByValue(value) == null){
					category = new Category();
					category.setValue(value);
					category.setType(type);
					category.setName(li.getElementsByTag("a").first().text());
				}else{
					category = categoryService.findByValue(value);
					category.setType(type);
				}
				categoryService.save(category);
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return	"Hello";
	}
}
