package com.inthub.eventmonitor.controller;

import com.inthub.eventmonitor.MonitoringEventApplicationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MonitoringEventControllerTest extends MonitoringEventApplicationTest{

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private MonitoringEventController eventConroller;

    @org.junit.Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getEventsCount() throws Exception{

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/events/count"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.count").value(127280));

    }

    @org.junit.Test
    public void getByGlobalTransactionId() throws Exception{

        /*this.mockMvc.perform(MockMvcRequestBuilders.get("api/events/txnIdGlobal/APPIAN11195"))
                //.param("txnIdGlobalValue", "APPIAN11195"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.global_Transaction_Id").value("APPIAN11195"));*/
    }
}