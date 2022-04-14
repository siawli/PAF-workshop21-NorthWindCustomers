package northwind.workshop1.northwind44;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.StringReader;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void shouldReturnCorrectJsonArraySize() {

		int limit = 5;
		int offset = 0;
		
		RequestBuilder req = MockMvcRequestBuilders.get("/api/customers")
								.queryParam("limit", "" + limit)
								.queryParam("offset", "" + offset)
								.accept(MediaType.APPLICATION_JSON);

		MvcResult result = null;
		try {
			result = mvc.perform(req).andReturn();
		} catch (Exception ex) {
			fail("fail to start controller", ex);
			return;
		}

		MockHttpServletResponse response = result.getResponse();
		String payload = null;
		try {
			payload = response.getContentAsString();
			System.out.println(">>>>>>>>>> payload: " + payload);
		} catch (Exception ex) {
			fail("fail to get payload", ex);
			return;
		}

		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonArray jsonArr = reader.readArray();

		assertEquals(limit, jsonArr.size());

	}
}
// 	@Autowired
//     private NorthwindService northwindSvc;

//     @Test
//     void shouldReturn5Customers() {
//         Integer limit = 5;
//         JsonArray customers = northwindSvc.getCustomerList(5, 0);
//         assertEquals(limit, customers.size());
//     }

// }
