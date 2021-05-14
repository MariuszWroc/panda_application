package pl.pandait.panda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest{
    MockMvc mockMvc;
    @BeforeEach
    public void beforeSetUp(){
        this.mockMvc = standaloneSetup(new HelloController()).build();
    }
    @Test
    public void whenGivenAttributeNameThenItIsInModel() throws Exception{
    this.mockMvc.perform(get("/greeting?name=Panda")
	.accept(MediaType.ALL))
	.andExpect(status().isOk())
	.andExpect(model().attribute("name", "Panda"));
    }
}

