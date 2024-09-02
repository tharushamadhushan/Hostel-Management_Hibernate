package lk.ijse.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
public class Room {
    @Id
    private String room_type_id;
    private String type;
    private Double key_money;
    private int qty;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Reservation> resList = new ArrayList<>();

    public Room(){}

    public Room(String room_type_id) {
        this.room_type_id = room_type_id;
    }
}
