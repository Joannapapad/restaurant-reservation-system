package com.example.reservation.dao;

import java.util.List;
import com.example.reservation.domain.Request;

/**
 * The DAO interface for the {@link Request} class.
 * Provides methods to interact with the underlying data source for Request objects.
 *
 * @author Your Name
 */
public interface RequestDAO {

    /**
     * Finds a Request by its reservation ID.
     * @param reservationID The unique ID of the reservation.
     * @return The Request object, or {@code null} if not found.
     */
    Request find(int reservationID);

    List<Request> getRequestsByStoreId(int storeId);

    List<Request> getRequestsByCustomerId(int customerid);

    /**
     * Saves a Request object to the data source.
     * If the object already exists, its state is updated.
     * @param entity The Request object to save.
     */
    void save(Request entity);

    /**
     * Deletes a Request object from the data source.
     * @param entity The Request object to delete.
     */
    void delete(Request entity);

    /**
     * Retrieves all Request objects from the data source.
     * @return A list of all Request objects.
     */
    List<Request> findAll();
}
