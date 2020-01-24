package jUnit;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RestApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getSentOrders() throws Exception {

    // Create a get request with an accept header for application\json
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arch/rest/sentOrders")
        .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    // Assert that the return status is OK
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  public void getUnsentOrders() throws Exception {

    // Create a get request with an accept header for application\json
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/arch/rest/unsentOrders")
        .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    // Assert that the return status is OK
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

}
