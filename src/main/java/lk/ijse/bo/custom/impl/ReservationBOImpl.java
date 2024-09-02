package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ReservationBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.entity.Reservation;
import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import lk.ijse.model.ReservationDTO;
import lk.ijse.model.RoomDTO;
import lk.ijse.model.StudentDTO;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO=(ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);


    @Override
    public String getNextResId() {
        return reservationDAO.getNextId();    }

    @Override
    public boolean addRevervation(ReservationDTO reservationDTO) {
        Student student = new Student(reservationDTO.getStudentDTO().getSId());
        Room room = new Room(reservationDTO.getRoomDTO().getRoom_type_id());
        return reservationDAO.save(new Reservation(reservationDTO.getRes_id(),reservationDTO.getDate(),reservationDTO.getKeyMoney(),reservationDTO.getBalance(),room,student));
    }

    @Override
    public ReservationDTO search(String id) {
        Reservation reservation=reservationDAO.search(id);
        System.out.println(reservation.getBalance()+" "+reservation.getKeyMoney());
        return new ReservationDTO(reservation.getBalance(),reservation.getKeyMoney());
    }

    @Override
    public boolean Update(ReservationDTO reservation) {
//        return reservationDAO.Update(new Reservation(reservation.getRes_id(),reservation.getDate(),reservation.getStatus(),reservation.getStudent(),reservation.getRoom()));
        return false;
    }

    @Override
    public List<ReservationDTO> getAllRevervation() {
        List<ReservationDTO> allReservation= new ArrayList<>();
        List<Reservation> all = reservationDAO.getAll();
        for (Reservation reservation : all) {
            StudentDTO studentDTO=new StudentDTO(reservation.getStudent().getSId());
            RoomDTO roomDTO = new RoomDTO(reservation.getRoom().getRoom_type_id());
            allReservation.add(new ReservationDTO(reservation.getRes_id(),reservation.getDate(),reservation.getKeyMoney(),reservation.getBalance(),roomDTO,studentDTO));
        }
        return allReservation;
    }

    @Override
    public boolean delete(String id) {
        return reservationDAO.delete(id);
    }

    @Override
    public Double getKeymoney(String selectedItem) {
        return reservationDAO.getkeymoney(selectedItem);
    }

    @Override
    public boolean UpdatePayment(Double newBalance, String text) {
        return reservationDAO.UpdatePayment(newBalance,text);
    }
}
