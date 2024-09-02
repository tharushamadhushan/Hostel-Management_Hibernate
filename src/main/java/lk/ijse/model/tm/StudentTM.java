package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@AllArgsConstructor
@Data
public class StudentTM {
    private String sId;
    private String sName;
    private String sAddress;
    private String sContact;
    private Date date;
    private String gender;
}
