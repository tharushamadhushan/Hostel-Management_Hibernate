package lk.ijse.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@Data
public class Student {
    @Id
    private String sId;
    private String sName;
    private String sAddress;
    private String sContact;
    private Date date;
    private String gender;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Reservation> resList = new ArrayList<>();

    public Student(){}


    public Student(String sId, String sName, String sAddress, String sContact, Date date, String gender) {
        this.sId = sId;
        this.sName = sName;
        this.sAddress = sAddress;
        this.sContact = sContact;
        this.date = date;
        this.gender = gender;
    }

    public Student(String sId) {
        this.sId = sId;
    }
}
