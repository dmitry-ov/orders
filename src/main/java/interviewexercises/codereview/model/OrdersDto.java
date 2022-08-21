package interviewexercises.codereview.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDto {
    private String orderId;
    private String phone;
    private long amount;
    private Boolean isExpired;
}
