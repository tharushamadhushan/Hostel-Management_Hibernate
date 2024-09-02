package lk.ijse.bo;

import lk.ijse.bo.custom.impl.ReservationBOImpl;
import lk.ijse.bo.custom.impl.RoomBOImpl;
import lk.ijse.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        ROOM, STUDENT, RESERVATION
    }

    public SuperBo getBo(BOTypes types){
        switch (types){
            case ROOM:
                return new RoomBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }
}
