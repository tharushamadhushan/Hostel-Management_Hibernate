package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.model.ReservationDTO;

import java.util.List;

public interface ReservationBO extends SuperBo {

    String getNextResId();

    boolean addRevervation(ReservationDTO reservationDTO);

    ReservationDTO search(String id);

    boolean Update(ReservationDTO reservation);

    List<ReservationDTO> getAllRevervation();

    boolean delete(String id);

    Double getKeymoney(String selectedItem);

    boolean UpdatePayment(Double newBalance, String text);
}
