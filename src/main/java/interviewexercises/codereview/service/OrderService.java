package interviewexercises.codereview.service;

import interviewexercises.codereview.model.OrdersDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    public List<OrdersDto> aggregateOrdersByPhone(String orderId, List<OrdersDto> orderList) {
        final Map<String, OrdersDto> calculatedOrders = new HashMap<>();

        orderList.forEach(ordersDto -> {
            if (orderId.equals(ordersDto.getOrderId())) {
                final String phone = ordersDto.getPhone();
                if (calculatedOrders.containsKey(phone)) {
                    OrdersDto mapDto = calculatedOrders.get(phone);
                    mapDto.setAmount(mapDto.getAmount() + ordersDto.getAmount());
                    calculatedOrders.put(phone, mapDto);
                } else {
                    calculatedOrders.put(phone, ordersDto);
                }
            }
        });
        return new ArrayList<>(calculatedOrders.values());
    }
}
