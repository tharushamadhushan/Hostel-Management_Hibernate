package lk.ijse.model;

import javafx.scene.control.Label;
import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class ReservationDTO {
    private String res_id;
    private LocalDate date;
    private Double keyMoney;
    private Double balance;

    private RoomDTO roomDTO;
    private StudentDTO studentDTO;

    public ReservationDTO(Double balance, Double keyMoney) {
        this.keyMoney = keyMoney;
        this.balance = balance;
    }
}
