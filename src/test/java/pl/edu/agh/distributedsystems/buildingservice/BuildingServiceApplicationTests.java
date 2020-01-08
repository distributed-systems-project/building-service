package pl.edu.agh.distributedsystems.buildingservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.distributedsystems.buildingservice.building.Building;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuildingServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String getURL() {
		return "http://localhost:" + port + "/";
	}

	@Test
	public void test_i_can_get_building() {
		Building result = restTemplate.getForObject(getURL() + "/buildings/1", Building.class);
		assertThat(result).isNotNull();
	}

	@Test
	public void test_i_can_get_list() {
		Building[] result = restTemplate.getForObject(getURL() + "/buildings", Building[].class);
		assertThat(result.length).isGreaterThan(0);
	}

	@Test
	public void  test_i_can_add_item() {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Building> request = new HttpEntity<>(new Building("ABC", 1));
		ResponseEntity<Building> response = restTemplate
				.exchange(getURL() + "/buildings", HttpMethod.POST, request, Building.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		Building created = response.getBody();
		assertThat(created).isNotNull();
		assertThat(created.getName()).isEqualTo("ABC");
	}

}
