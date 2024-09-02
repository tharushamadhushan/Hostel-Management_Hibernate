package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RoomTM {
    private String room_type_id;
    private String type;
    private Double key_money;
    private Integer qty;
}