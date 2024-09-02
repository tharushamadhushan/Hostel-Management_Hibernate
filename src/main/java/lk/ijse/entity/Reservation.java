package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.model.RoomDTO;
import lk.ijse.model.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservation {
    @Id
    private String res_id;
    private LocalDate date;
    private Double keyMoney;
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "sId")
    private Student student;



}

