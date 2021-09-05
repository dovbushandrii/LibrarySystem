/**
 * @file ClientControllerTest.java
 * @brief This file contains tests for ClientController controller
 *
 * @author Andrii Dovbush
 */

package webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-client-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-client-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void showAllTest() throws Exception {
        this.mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='client-table']/tr").nodeCount(3));
    }

    @Test
    public void getRegisterPageTest() throws Exception {
        this.mockMvc.perform(get("/clients/new"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Date of birthday: </td>")));
    }

    @Test
    public void registerClientTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/clients")
                .param("firstName", "Andrew")
                .param("lastName", "Testing")
                .param("email", "wow@gmail.com")
                .param("dateOfBirth", "2002-01-29");
        this.mockMvc.perform(multipart)
                .andExpect(status().is3xxRedirection());
        this.mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='client-table']/tr").nodeCount(4));
    }

    @Test
    public void registerClientWithInvalidInfoTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/clients")
                .param("firstName", "A")            //Name should be between 2 and 30 characters
                .param("lastName", "Testing")
                .param("email", "wow@gmail.com")
                .param("dateOfBirth", "");          //dateOfBirth should not be empty
        this.mockMvc.perform(multipart)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Name should be between 2 and 30 characters")))
                .andExpect(content().string(containsString("Date field should not be empty")));
    }

    @Test
    public void deleteClientTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/clients/2")
                .param("_method", "delete");
        this.mockMvc.perform(multipart)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clients"));

        this.mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='client-table']/tr").nodeCount(2));

        /*
            Is there are no clients, table
            of clients should not be written in html code
         */
        multipart = multipart("/clients/1")
                .param("_method", "delete");
        this.mockMvc.perform(multipart)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clients"));
        this.mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='client-table']").doesNotExist());
    }

}
