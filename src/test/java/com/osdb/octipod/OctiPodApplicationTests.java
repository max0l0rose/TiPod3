package com.osdb.octipod;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@WebMvcTest
@AutoConfigureRestDocs(
		//outputDir = "target/generated-snippets"
		outputDir = "src/docs/asciidoc"
)
class OctiPodApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testHello() throws Exception {
		System.out.println("---------------------------");

		mockMvc.perform(get("/api/v1/hello"))
				//.andDo(print())
				.andExpect(jsonPath("message", is("Hello message")))
				.andExpect(jsonPath("value", is(1)))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello")))
				.andDo(document("{class-name}/{method-name}"
//						, responseFields(
//								fieldWithPath("message").description("The message.")
//						)
				));


		System.out.println("============================");
	}

}
