package lk.ijse.model.tm;

import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import lk.ijse.model.ReservationDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Data
public class ReservationTM {
    private String res_id;
    private LocalDate date;
    private String rid;
    private String sid;
    private Double keyMoney ;
    private Double balance;


}
