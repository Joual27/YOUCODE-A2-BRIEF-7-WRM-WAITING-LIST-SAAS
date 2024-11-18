package org.youcode.WRM_V1.visit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.youcode.WRM_V1.app.ports.inbound.WaitingListService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableConfigurationProperties
@ActiveProfiles("uat")
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:db2://localhost:50000/wrmt_db",
        "spring.datasource.username=db2inst1",
        "spring.datasource.password=Fasha321@"
})
public class VisitServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WaitingListService waitingListService;

    @Test
    public void testCreateVisit() throws Exception{
        String req = """
        {
            "visitorId": 1,
            "waitingListId": 1,
            "arrivalTime": "2024-12-18T15:00:00",
            "priority": null,
            "estimatedVisitTime": null
        }
        """;
        mockMvc.perform(post("/api/v1/visits").
                contentType(MediaType.APPLICATION_JSON)
                .content(req))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateVisit_arrival_time_in_past() throws Exception{
        String req = """
        {
            "visitorId": 1,
            "waitingListId": 1,
            "arrivalTime": "2024-11-18T15:00:00",
            "priority": null,
            "estimatedVisitTime": null
        }
        """;
        mockMvc.perform(post("/api/v1/visits").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateVisit_non_existing_visitor() throws Exception{
        String req = """
        {
            "visitorId": 1546,
            "waitingListId": 1,
            "arrivalTime": "2024-11-18T15:00:00",
            "priority": null,
            "estimatedVisitTime": null
        }
        """;
        mockMvc.perform(post("/api/v1/visits").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testCreateVisit_non_existing_waiting_list() throws Exception{
        String req = """
        {
            "visitorId": 1,
            "waitingListId": 1546,
            "arrivalTime": "2024-11-18T15:00:00",
            "priority": null,
            "estimatedVisitTime": null
        }
        """;
        mockMvc.perform(post("/api/v1/visits").
                        contentType(MediaType.APPLICATION_JSON)
                        .content(req))
                .andExpect(status().isNotFound());
    }


}
