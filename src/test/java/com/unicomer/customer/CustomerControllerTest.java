package com.unicomer.customer;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicomer.customer.dto.CustomerDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    String uriTest = "/v1/customer/";

    @Test
    public void shouldSaveCustomer() throws Exception {
        CustomerDto customer=getCustomerDto();
        String json = mapper.writeValueAsString(customer);
        mvc.perform( MockMvcRequestBuilders
                .post(uriTest)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(status().is(201));
    }

    @Test
    public void shouldReturnBadFormatLastNameCustomer() {
        try {
            CustomerDto customer = getCustomerDto();
            customer.setLastName("123456");
            String json = mapper.writeValueAsString(customer);
            mvc.perform(MockMvcRequestBuilders
                    .post(uriTest)
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(status().is(500));
            Assert.fail("");
        }catch (Exception ex){
            String cause = ex.getMessage();
            boolean isErrorFormat = cause.contains("Not match format last name")?true:false;
            Assert.assertTrue(isErrorFormat);
        }
    }

    @Test
    public void shouldReturnBadFormatFirstNameNullCustomer() {
        try {
            CustomerDto customer = getCustomerDto();
            customer.setFirstName("");
            String json = mapper.writeValueAsString(customer);
            mvc.perform(MockMvcRequestBuilders
                    .post(uriTest)
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(status().is(500));
            Assert.fail("");
        }catch (Exception ex){
            String cause = ex.getMessage();
            boolean isErrorFormat = cause.contains("Not match format first name")?true:false;
            Assert.assertTrue(isErrorFormat);
        }
    }

    @Test
    public void shouldReturnHttpCode200() throws Exception {
        final ResultActions result =
                mvc.perform(
                        get(uriTest + 1).contentType(MediaType.APPLICATION_JSON));
        result
                .andDo(print())
                .andExpect(status().is(200));
    }


    @Test
    public void shouldReturnHttpCode400() throws Exception {
        final ResultActions result =
                mvc.perform(
                        get(uriTest + "holaMundo").contentType(MediaType.APPLICATION_JSON));
        result
                .andDo(print())
                .andExpect(status().is(400));
    }


    private static CustomerDto getCustomerDto() {
        CustomerDto customer = new CustomerDto();
        customer.setFirstName("testname");
        customer.setLastName("testlastname");
        customer.setGender("masculino");
        customer.setIncomes(250000.00);
        customer.setAddressHome("adress test");
        customer.setCellphone(123L);
        customer.setHomePhone(1111L);
        customer.setProfession("director");
        return customer;
    }

}
