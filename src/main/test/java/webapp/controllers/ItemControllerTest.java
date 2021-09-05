/**
 * @file ClientControllerTest.java
 * @brief This file contains tests for ItemController controller
 *
 * @author Andrii Dovbush
 */

package webapp.controllers;

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
@Sql(value = {"/create-item-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-item-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void showAllTest() throws Exception {
        this.mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='item-table']/tr").nodeCount(5));
    }

    @Test
    public void getRegisterPageTest() throws Exception {
        this.mockMvc.perform(get("/items/new"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Item name:</td>")));
    }

    @Test
    public void registerItemTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/items")
                .param("name", "Another book")
                .param("type", "BOOK");
        this.mockMvc.perform(multipart)
                .andExpect(status().is3xxRedirection());
        this.mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='item-table']/tr").nodeCount(6))
                .andExpect(content().string(containsString("Another book")));
    }

    @Test
    public void registerItemWithInvalidInfoTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/items")
                .param("name", "")          //Name should not be empty
                .param("type", "BOOK");
        this.mockMvc.perform(multipart)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Name should not be empty")));
    }

    @Test
    public void deleteItemTest() throws Exception {
        MockHttpServletRequestBuilder multipart;

        for(int i = 4; i > 1; i--){
            multipart = multipart("/items/" + i)
                    .param("_method", "delete");

            this.mockMvc.perform(multipart)
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/items"));

            this.mockMvc.perform(get("/items"))
                    .andExpect(status().isOk())
                    .andExpect(xpath("//table[@id='item-table']/tr").nodeCount(i));
        }

        multipart = multipart("/items/1")
                .param("_method", "delete");

        this.mockMvc.perform(multipart)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/items"));

        /*
            Is there are no items, table
            of items should not be written in html code
         */

        this.mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(xpath("//table[@id='item-table']").doesNotExist());
    }

}
