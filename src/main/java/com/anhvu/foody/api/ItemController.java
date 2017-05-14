package com.anhvu.foody.api;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anhvu.foody.entity.Category;
import com.anhvu.foody.entity.City;
import com.anhvu.foody.entity.District;
import com.anhvu.foody.entity.Item;
import com.anhvu.foody.entity.Review;
import com.anhvu.foody.entity.Street;
import com.anhvu.foody.entity.User;
import com.anhvu.foody.service.CategoryService;
import com.anhvu.foody.service.CityService;
import com.anhvu.foody.service.DistrictService;
import com.anhvu.foody.service.ItemService;
import com.anhvu.foody.service.ReviewService;
import com.anhvu.foody.service.StreetService;
import com.anhvu.foody.service.UserService;
import com.anhvu.foody.util.StringHelper;

@RestController
@RequestMapping("item")
public class ItemController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CityService cityService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private StreetService streetService;

	@Autowired
	private UserService userService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/getall", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public List<Item> getAll() {
		return (List<Item>) itemService.findAll();
	}

	@RequestMapping(value = "/get-by-params", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public List<Item> getByParams(@RequestBody String params) {
		System.out.println(params);

		JSONObject object = new JSONObject(params);
		int page = object.getInt("page");
		switch (object.getString("type")) {
		case "by-city":
			return (List<Item>) itemService.findByCity(Integer.parseInt(object.getString("cityId")), page);
		case "by-district":
			return (List<Item>) itemService.findByDistrict(Integer.parseInt(object.getString("districtId")), page);
		
		case "by-category":
			return (List<Item>) itemService.findByCategory(Integer.parseInt(object.getString("categoryId")), page);
		
		default:
			break;
		}

		return null;
	}
	
	@RequestMapping(value = "/get-by-address", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public List<Item> getByAddress(@RequestBody String params) {
		System.out.println(params);
		
		return itemService.findByNew(1);
	}
	
	@RequestMapping(value = "/get-by-categories", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public List<Item> getByCategory(@RequestBody String params) {
		System.out.println(params);

		JSONObject object = new JSONObject(params);
		int page = object.getInt("page");
		int categoryId = object.getInt("categoryId");
		switch (Integer.parseInt(object.getString("option"))) {
		case 1:
			return itemService.findByCategory(categoryId, page);
		case 3:
			return itemService.findByCategoryHot(categoryId, page);
		case 5:
			return itemService.findByCategoryPromotion(categoryId,page);
		case 6:
			return itemService.findByCategoryBooking(categoryId,page);
		case 7:
			return itemService.findByCategoryMemberCard(categoryId,page);
		case 8:
			return itemService.findByCategoryDelivery(categoryId,page);
		default:
			return null;
		}
	}
	@RequestMapping(value = "/get-by-options", method = RequestMethod.POST, produces = {
				MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_FORM_URLENCODED_VALUE 
			}, 
			consumes = { 
					MediaType.APPLICATION_JSON_VALUE 
			})
	public List<Item> getByOption(@RequestBody String params) {

		System.out.println(params);
		JSONObject object = new JSONObject(params);
		int page = object.getInt("page");
		switch (Integer.parseInt(object.getString("options"))) {
		case 1:
			System.out.println(itemService.findByNew(page));
			return itemService.findByNew(page);
		case 3:
			return itemService.findByHot(page);
		case 5:
			return itemService.findByPromotion(page);
		case 6:
			return itemService.findByBooking(page);
		case 7:
			return itemService.findByMemberCard(page);
		case 8:
			return itemService.findByDelivery(page);
		default:
			break;
		}
		return null;
	}

	@RequestMapping(value = "/getdata", method = RequestMethod.GET)
	private String getData() throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream("C:/Users/binhb/Desktop/data.txt", true), "UTF-8");
		BufferedWriter fbw = new BufferedWriter(writer);
		byte[] buf = new byte[1024];

		City hcm = cityService.findOne(1);
		List<Category> categories = (List<Category>) categoryService.findAll();

		String doc = null;
		Map<String, String> header = new HashMap();
		header.put("Cookie", "flg=vn; fbm_395614663835338=base_domain=.foody.vn;" + " fd.res.view.233=32282,32283;"
				+ " _v1EmaticSolutionsEI=%7B%22c_3582_1%22%3A%5B1%2C1487505468796%2C0%5D%7D;"
				+ " FOODY.AUTH.UDID=6148d4b8-4eca-4e44-b902-05d41c426320;"
				+ " FOODY.AUTH=F6E681E138B827D4B36CBBE6C23234A28A48773C7A85527D66870E39DD43C0DA42FEE7837481C228139FBDD762A93FCF974CD2C8259769A7AF3AFAA3AB746808B3307EEF6C7BB71FD4C73F14ED17585D6F7FE763CF49709BFDE0CBCD17A33A5DD59917359CB5B58CB4DB385FEAF0C3ED3CC65DEDBF618717D7B96A7534A8B7F2D509D2BF3E9F265F24A725006C0CF271064BE59082A11F5BBFEE4A130CBA70D706DC1CC32E89FB2DFC99616BC58EA4508B019D6CDD9DE964B93F421510BEFF91A891B6040643D145FDC56CD541DAB99C74516FFF2A8EA1B9480CC1665862A970; fd.res.view.217=123219,216973,77729,45109,129502,151813; fd.sk=; _gat=1; _gat_ads=1; floc=217; gcat=food; _ga=GA1.2.1628289182.1475762467; _v1EmaticSolutions=%5B%22b1d21c35-f385-11e6-aa53-0242ac110002%22%2C1489339164660%2C%5B%22A%22%2C%22%5Cn%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20TP.%20HCM%5Cn%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%2049.250%5Cn%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%20%22%2C1%2C%22ho-chi-minh%22%5D%5D; fbsr_395614663835338=TfCC90D1ME3fT4kx6gK5vWzUO_7QUvskPvbWa87y8Qs.eyJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImNvZGUiOiJBUUJRTGk3SDBhM2hKUFBSNTdNMlNSbFc3U2gzeTJCLVY3YUUzQ3JMd0t1TmVaMlZVbXZjbXd4VHhfbDEzX2p5d1pndjZRMTU4QWlMdFFrRTNDWEo1MzVlRmJ2dDFuODBFeEI1NzZLeXFWcmR3YlBzc3NuNHl5VFFSOThCNXdvVkNIMF9tVG1mTTFpOXFKVnlkQnB1RGNXN3BDei1MS25NQUZ5Qjc0OXAwNGVoTkctM2ZQVDRocXg3Q0lncXVYaG9sNHhrT1VWVFhMSlpBQWdfM0l3TlBqaU1DalJTaGFXRER4dmtCX0MyTHBVNHhGWU43c0d5R2RMY0ZFNjZTcEJzRzM0ZjhVUThfYW5reVd5ejJLNDBZSUhBR1Q1SXVkTFhZdEVvZzlDQlc1ajQ2b2JYNW5MbGtzeC1XbjdQM1hYUEctLTU4dEtyMVp4WEVaVVVxY0NYWE5xczhseTB3WEZhel9qRDRQZi1SX3ltTVIza1QyNm1fa0NBX3p5enlCcTBuVlEiLCJpc3N1ZWRfYXQiOjE0ODkzMzkxNjYsInVzZXJfaWQiOiI1NzU0MDQ0OTI1NjIwMjUifQ");
		header.put("Accept", "application/xml");
		header.put("Mimetype", "application/json");
		header.put("Accept-Language", "vi-VN,vi;q=0.8,fr-FR;q=0.6,fr;q=0.4,en-US;q=0.2,en;q=0.2");
		header.put("Connection", "keep-alive");
		header.put("Host", "www.foody.vn");
		header.put("Referer", "https://www.foody.vn/");
		header.put("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		header.put("X-Requested-With", "XMLHttpRequest");

		String result = null;
		List<District> districts = hcm.getDistricts();

		// Iterator<String> iterator = list.iterator(); iterator.hasNext();
		try {
			for (Iterator<District> iterator = districts.iterator(); iterator.hasNext();) {

				District district = iterator.next();

				if (district.getId() == 1) {
					continue;
				}

				System.out.println(district.getName() + " " + district.getValue());

				for (Category category : categories) {
					doc = Jsoup
							.connect("https://www.foody.vn/__get/Place/HomeListPlace?t=1493138254026&"
									+ "lat=10.823099&lon=106.629664&count=12&districtId=" + district.getValue()
									+ "&cityId=" + hcm.getDataId() + "&" + "cateId=" + category.getValue()
									+ "&cuisineId=&isReputation=&type=1")
							.headers(header).ignoreContentType(true).get().body().text();

					System.out.println(doc);

					JSONObject jsonObject = new JSONObject(doc.toString());

					JSONArray array = jsonObject.getJSONArray("Items");
					int count = array.length();
					for (int i = 0; i < count; i++) {

						JSONObject jsonItem = array.getJSONObject(i);
						Item item = new Item();
						try {
							item.setName(jsonItem.getString("Name"));
							item.setAddress(jsonItem.getString("Address"));
							item.setTotalReviews(jsonItem.getInt("TotalReviews"));
							item.setImage(jsonItem.getString("PhotoUrl"));
							item.setAvgrating(Double.parseDouble(jsonItem.getString("AvgRatingText")));
							item.setTotalPictures(jsonItem.getInt("TotalPictures"));
							item.setBookingUrl(jsonItem.getString("BookingUrl"));
							item.setHasMemberCard(jsonItem.getBoolean("HasMemberCard"));
							item.setHasPromotion(jsonItem.getBoolean("HasPromotion"));
							item.setIsAds(jsonItem.getBoolean("IsAds"));
							item.setIsBooking(jsonItem.getBoolean("IsBooking"));
							item.setIsDelivery(jsonItem.getBoolean("IsDelivery"));
							item.setIsRestaurantCollection(jsonItem.getBoolean("IsRestaurantCollection"));
							item.setResUrlAlbums(jsonItem.getString("ResUrlAlbums"));
							item.setResUrlReviews(jsonItem.getString("ResUrlReviews"));
							item.setValue(jsonItem.getInt("Id"));
							if (!jsonItem.isNull("Latitude"))
								item.setLatitude(jsonItem.getDouble("Latitude") + "");
							if (!jsonItem.isNull("Longitude"))
								item.setLongitude(jsonItem.getDouble("Longitude") + "");
							Street street = new Street();
							System.out.println(item.getAddress());
							String nameStreet = StringHelper.getStreetName(item.getAddress());
							if (nameStreet != null) {
								System.out.println(nameStreet);
								street = streetService
										.findOne(StringHelper.ToUnsignString(nameStreet + " " + district.getName()));
								// System.out.println(street.getId());
								/* if (streetService.isExists(street)) { */

								item.setStreet(street);
								item.setCategory(category);

								if (itemService.findByValue(item.getValue()) == null) {
									URL url = new URL(jsonItem.getString("PhotoUrl"));
									InputStream inputStream = new BufferedInputStream(url.openStream());
									ByteArrayOutputStream out = new ByteArrayOutputStream();

									int n = 0;
									while ((n = inputStream.read(buf)) != -1) {
										out.write(buf, 0, n);
									}
									out.close();
									inputStream.close();
									byte[] response = out.toByteArray();

									FileOutputStream fos = new FileOutputStream(
											"D:/project/FoodyServer/src/main/resources/static/image/item/"
													+ jsonItem.getInt("Id") + ".jpg");
									fos.write(response);
									fos.close();

									itemService.save(item);
								}
								JSONArray reviews = jsonItem.getJSONArray("LstReview");
								for (int j = 0; j < reviews.length(); j++) {
									JSONObject jsReview = reviews.getJSONObject(j);
									User user = new User();

									user.setValue(jsReview.getInt("UserId"));
									if (userService.findByValue(user.getValue()) == null) {
										user.setName(jsReview.getString("reviewUserDisplayName"));
										if (!jsonItem.isNull("UserGender"))
											user.setGender(jsReview.getString("UserGender"));
										user.setAvatar(jsReview.getString("UserAvatar"));
										URL url = new URL(jsReview.getString("reviewUserAvatar"));
										InputStream inputStream = new BufferedInputStream(url.openStream());
										ByteArrayOutputStream out = new ByteArrayOutputStream();
										int n = 0;
										while ((n = inputStream.read(buf)) != -1) {
											out.write(buf, 0, n);
										}
										out.close();
										inputStream.close();
										byte[] response = out.toByteArray();
										FileOutputStream fos = new FileOutputStream(
												"D:/project/FoodyServer/src/main/resources/static/image/avatar/"
														+ user.getAvatar());
										fos.write(response);
										fos.close();
										userService.save(user);
									}

									Review review = new Review();
									review.setValue(jsReview.getInt("ReviewId"));
									if (reviewService.findByValue(review.getValue()) == null) {
										review.setContent(jsReview.getString("Comment"));
										review.setUrl(jsReview.getString("reviewUrl"));
										review.setAvgRating(jsReview.getString("AvgRatingText"));
										review.setUser(userService.findByValue(jsReview.getInt("UserId")));
										review.setItem(itemService.findByValue(jsonItem.getInt("Id")));
										reviewService.save(review);
									}
								}

								/* } */

							}

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

		fbw.write(result);
		fbw.newLine();
		fbw.close();
		writer.close();

		return "hello";
	}

}
