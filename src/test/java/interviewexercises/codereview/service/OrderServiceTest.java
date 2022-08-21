package interviewexercises.codereview.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import interviewexercises.codereview.model.OrdersDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    private List<OrdersDto> orderList;

    @Value("${correctResponse}")
    private Resource correctResponse;

    @BeforeEach
    void init() {
        OrdersDto order1 = new OrdersDto(
                "1",
                "+79261111111",
                100L,
                false
        );
        OrdersDto order2 = new OrdersDto(
                "1",
                "+79261111111",
                200L,
                false
        );
        OrdersDto order3 = new OrdersDto(
                "1",
                "+79262222222",
                1000L,
                false
        );
        OrdersDto order4 = new OrdersDto(
                "2",
                "+79261111111",
                5000L,
                null
        );
        OrdersDto order5 = new OrdersDto(
                null,
                "+79261111111",
                5000L,
                true
        );
        orderList = asList(order1, order2, order3, order4, order5);
    }

    @Test
    void testAggregationOrders() throws IOException {
        List<OrdersDto> expectedResult = getExpectedResult();
        List<OrdersDto> result = orderService.aggregateOrdersByPhone("1", orderList);
        Assertions.assertEquals(expectedResult, result);
    }

    private List<OrdersDto> getExpectedResult() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(correctResponse.getInputStream(), new TypeReference<>() {
        });
    }
}