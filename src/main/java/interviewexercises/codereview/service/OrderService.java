package interviewexercises.codereview.service;

import interviewexercises.codereview.model.OrdersDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {

    public List<OrdersDto> aggregateOrdersByPhone(String orderId, List<OrdersDto> orderList) {
        return new ArrayList<>(orderList.stream()
                .filter(ordersDto -> orderId.equals(ordersDto.getOrderId()))
                .collect(Collectors.toMap(
                        OrdersDto::getPhone,
                        Function.identity(),
                        (existEntry, newEntry) -> new OrdersDto(
                                existEntry.getOrderId(),
                                existEntry.getPhone(),
                                existEntry.getAmount() + newEntry.getAmount(),
                                existEntry.getIsExpired())))
                .values());
    }
}
