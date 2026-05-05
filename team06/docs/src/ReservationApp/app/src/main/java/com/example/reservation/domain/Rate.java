package com.example.reservation.domain;
import com.example.reservation.util.SimpleCalendar;
import com.example.reservation.util.SystemDate;

/**
 * Represents a rating provided by a customer for a store.
 * Includes information about the store ID, customer ID, rating score, feedback, and the date of the rating.
 */
public class Rate {

    private int storeId;
    private int customerId;
    private int rating;
    private String feedback;
    public SimpleCalendar ratingDate;


    /**
     * Constructs a new Rate object with the specified details.
     *
     * @param storeId    The ID of the store being rated.
     * @param customerId The ID of the customer providing the rating.
     * @param rating     The rating score (should be between 0 and 5).
     * @param feedback   Feedback provided by the customer.
     */
    public Rate(int storeId, int customerId, int rating, String feedback) {
        this.storeId = storeId;
        this.customerId = customerId;
        this.rating = rating;
        this.feedback = feedback;
        SimpleCalendar currentDate = SystemDate.now();

        this.ratingDate = new SimpleCalendar(
                currentDate.getYear(),
                currentDate.getMonth(),
                currentDate.getDayOfMonth(),
                currentDate.getHour(),
                currentDate.getMinute()
        );
    }

    /**
     * @return The ID of the store being rated.
     */
    public int getStoreId() {
        return storeId;
    }

    /**
     * Sets the ID of the store being rated.
     *
     * @param storeId The store ID to set.
     */
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    /**
     * @return The ID of the customer providing the rating.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the ID of the customer providing the rating.
     *
     * @param customerId The customer ID to set.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return The rating score.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating score.
     *
     * @param rating The rating score to set (should be between 0 and 5).
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return The feedback provided by the customer.
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * Sets the feedback provided by the customer.
     *
     * @param feedback The feedback to set.
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    /**
     * @return The date and time when the rating was created.
     */
    public SimpleCalendar getRatingDate() {
        return ratingDate;
    }

    /**
     * Validates the rating score.
     *
     * @param rating The rating score to validate.
     * @return True if the rating is between 0 and 5, false otherwise.
     */
    public boolean checkRateScore(int rating) {
        boolean flag = true;
        if (rating < 0) {
            System.out.println("Rating cant be a negative number.");
            flag = false;
        }
        if (rating > 5) {
            System.out.println("rating cant be over 5.");
            flag = false;
        }
        return flag;
    }

    /**
     * Provides a string representation of the Rate object.
     *
     * @return A string describing the rating details.
     */
    @Override
    public String toString() {
        return "Rate{" +
                "storeId=" + storeId +
                ", customerId=" + customerId +
                ", rating=" + rating +
                ", feedback='" + feedback + '\'' +
                ", ratingDate=" + ratingDate +
                '}';
    }
}
