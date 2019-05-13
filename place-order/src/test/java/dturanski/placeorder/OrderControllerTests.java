package dturanski.placeorder;

import dturanski.manufacturing.util.Utils;
import dturanski.placeorder.domain.VehicleSpec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OrderService orderService;


    @Test
    public void validOrderShouldReturnAVin() throws Exception {

        when(orderService.orderVehicle(any(VehicleSpec.class))).thenReturn("v123");
        MvcResult result = this.mockMvc.perform(
                post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.getResourceContents("VehicleSpecs.json")))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("{\"vin\":\"v123\"}");
    }

}
