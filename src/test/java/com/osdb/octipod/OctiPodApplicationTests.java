package com.osdb.octipod;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@WebMvcTest
//@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
class OctiPodApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
		System.out.println("---------------------------");

		mockMvc.perform(get("/api/v1/hello"))
				//.andDo(print())
				.andExpect(jsonPath("message", is("Hello message")))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello")));


		System.out.println("============================");
	}

}
