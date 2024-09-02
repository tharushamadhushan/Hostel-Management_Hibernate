package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Reservation;

import java.util.List;

public interface ReservationDAO extends SuperDAO {

    String getNextId();

    List<String> loadStudentID();

    List<String> loadRoomID();

    boolean save(Reservation reservation);

    Reservation search(String id);

    boolean Update(Reservation reservation);

    List<Reservation> getAll();

    boolean delete(String id);

    Double getkeymoney(String selectedItem);

    boolean UpdatePayment(Double newBalance, String text);
}
