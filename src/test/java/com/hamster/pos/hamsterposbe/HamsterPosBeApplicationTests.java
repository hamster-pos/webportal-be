package com.hamster.pos.hamsterposbe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HamsterPosBeApplicationTests {

	@Test
	public void contextLoads() {
	}

//	@Test
//	public void getLicenseDetails() {
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.set("Content-Type", "application/json");
//		HttpEntity<String> httpEntity = new HttpEntity<String>("", httpHeaders);
//		String response = restTemplate.getForObject("http://localhost:8080/customer/licenses/2", String.class);
//		System.out.println("response------------------");
//		System.out.println(response);
//	}

}
