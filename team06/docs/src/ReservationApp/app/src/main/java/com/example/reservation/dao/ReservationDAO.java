package com.example.reservation.dao;

import java.util.List;
import com.example.reservation.domain.Reservation;

/** The DAO interface for the {@link Reservation} class.
        * Provides methods to interact with the underlying data source for Reservation objects.
        *
        * @author Your Name
 */
public interface ReservationDAO {

    /**
     * Finds a Reservation by its reservation ID.
     * @param reservationID The unique ID of the reservation.
     * @return The Reservation object, or {@code null} if not found.
     */
    Reservation find(int reservationID);

    /**
     * Saves a Reservation object to the data source.
     * If the object already exists, its state is updated.
     * @param entity The Reservation object to save.
     */
    void save(Reservation entity);

    /**
     * Deletes a Reservation object from the data source.
     * @param entity The Reservation object to delete.
     */
    void delete(Reservation entity);

    List<Reservation> getReservationsByStoreId(int storeId);

    /**
     * Retrieves all Reservation objects from the data source.
     * @return A list of all Reservation objects.
     */
    List<Reservation> findAll();
}
