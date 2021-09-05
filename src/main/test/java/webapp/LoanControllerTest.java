/**
 * @file LoanControllerTest.java
 * @brief This file contains tests for LoanController controller
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

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-loan-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-loan-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void showAllTest() throws Exception {
        this.mockMvc.perform(get("/loans"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='loan-table']/tr").nodeCount(3));
    }

    @Test
    public void showTest() throws Exception {
        this.mockMvc.perform(get("/loans/2"))
                .andExpect(status().isOk())
                .andExpect(xpath("//td[@id='loan-items']/div").nodeCount(2));
    }

    @Test
    public void getRegisterPageTest() throws Exception {
        this.mockMvc.perform(get("/loans/new"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Client:")));
    }

    @Test
    public void registerItemTest() throws Exception {

        String today = LocalDate.now().toString();
        String tomorrow = LocalDate.now().plusDays(1).toString();

        MockHttpServletRequestBuilder multipart = multipart("/loans")
                .param("client", "1")
                .param("_items", "1")
                .param("items", "4")
                .param("items", "5")
                .param("startDate", today)
                .param("endDate", tomorrow);
        this.mockMvc.perform(multipart)
                .andExpect(status().is3xxRedirection());
        this.mockMvc.perform(get("/loans"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='loan-table']/tr").nodeCount(4))
                .andExpect(content().string(containsString(today)))
                .andExpect(content().string(containsString(tomorrow)));
    }

    @Test
    public void registerItemWithInvalidInfoTest() throws Exception {
        String today = LocalDate.now().toString();
        String tomorrow = LocalDate.now().plusDays(1).toString();

        MockHttpServletRequestBuilder multipart = multipart("/loans")
                .param("client", "1")
                .param("_items", "1")
                .param("startDate", tomorrow)
                .param("endDate", today);
        this.mockMvc.perform(multipart)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("End date should not be after start date")))
                .andExpect(content().string(containsString("At least one item should be included")));
    }

    @Test
    public void deleteItemTest() throws Exception {

        //Check if items in loan are not available now from items list
        this.mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='item-table']/tr").nodeCount(3));

        //Delete loan
        MockHttpServletRequestBuilder multipart = multipart("/loans/2")
                .param("_method", "delete");
        this.mockMvc.perform(multipart)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/loans"));

        this.mockMvc.perform(get("/loans"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='loan-table']/tr").nodeCount(2));

        /*
            Is there are no loans, table
            of loans should not be written in html code
         */
        multipart = multipart("/loans/1")
                .param("_method", "delete");
        this.mockMvc.perform(multipart)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/loans"));
        this.mockMvc.perform(get("/loans"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='loan-table']").doesNotExist());

        //Check if all items are available now
        this.mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='item-table']/tr").nodeCount(6));
    }

    @Test
    public void deleteClientWithLoansTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/clients/2")
                .param("_method", "delete");
        this.mockMvc.perform(multipart)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clients"));

        this.mockMvc.perform(get("/loans"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='loan-table']/tr").nodeCount(2))
                .andExpect(xpath("//td[@data-id='2']").doesNotExist());
    }

}
