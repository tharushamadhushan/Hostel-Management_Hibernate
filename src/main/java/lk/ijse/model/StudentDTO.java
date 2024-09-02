package lk.ijse.model;

import lk.ijse.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Data
public class StudentDTO {
    private String sId;
    private String sName;
    private String sAddress;
    private String sContact;
    private Date date;
    private String gender;

    public StudentDTO(String sid) {
        this.sId = sid;
    }
}
