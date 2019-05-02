package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;


    private MockMvc mockMvc;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc.perform(
     MockMvcRequestBuilders.get("/user")
   .contentType(MediaType.APPLICATION_JSON_UTF8))
    .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))

        ;

    }


}
