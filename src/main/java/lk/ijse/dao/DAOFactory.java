package lk.ijse.dao;

import lk.ijse.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.dao.custom.impl.RoomDAOImpl;
import lk.ijse.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory == null)? daoFactory = new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        ROOM, STUDENT, RESERVATION
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case ROOM:
                return new RoomDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            default:
                return null;
        }
    }
}
