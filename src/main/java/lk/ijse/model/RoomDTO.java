package lk.ijse.model;

import lk.ijse.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private String room_type_id;
    private String type;
    private Double key_money;
    private Integer qty;

    private final List<Reservation> resList = new ArrayList<>();

    public RoomDTO(String rid) {
        this.room_type_id = rid;
    }
}
