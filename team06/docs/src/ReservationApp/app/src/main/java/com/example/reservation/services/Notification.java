package com.example.reservation.services;

import java.util.List;
import com.example.reservation.contact.EmailMessage;
import com.example.reservation.contact.EmailAddress;
import com.example.reservation.services.EmailProvider;
import com.example.reservation.domain.Customer;
import com.example.reservation.domain.Request;
import com.example.reservation.util.SimpleCalendar;

/**
 * Handles the notification process for customers by sending email messages.
 */
public class Notification {
    private EmailProvider provider;

    /**
     * Sets the email provider used to send email messages.
     *
     * @param provider The email provider to set.
     */
    public void setProvider(EmailProvider provider) {
        this.provider = provider;
    }

    /**
     * Sends notifications to a customer about their reservations.
     *
     * @param customer The customer to notify.
     */

    public void notifyCustomer(Customer customer) {
        List<Request> requests = customer.getRequests();
        for (Request req : requests) {
            String message = composeMessage(req.getRequestDate(), req.getRequestTime(), req.getStoreID());
            sendEmail(customer, message);
        }
    }

    /**
     * Composes a notification message based on reservation details.
     *
     * @param date    The date of the reservation.
     * @param time    The time of the reservation.
     * @param storeId The ID of the store associated with the reservation.
     * @return A formatted message with reservation details.
     */

    private String composeMessage(SimpleCalendar date, SimpleCalendar time, int storeId) {
        String message = "Your reservation is coming on ";
        message += date.getDayOfWeek();
        message += " at ";
        message += time.getHour();
        message += " for store with ID : ";
        message += storeId;
        message += " . So we are waiting you!";
        return message;
    }

    /**
     * Sends an email to the specified customer with the provided message.
     *
     * @param customer The customer to send the email to.
     * @param message  The body of the email message.
     */

    private void sendEmail(Customer customer, String message) {
        EmailAddress eMail = customer.getEmail();

        if (eMail != null && eMail.isValid()) {
            EmailMessage emailMessage = new EmailMessage();
            emailMessage.setTo(eMail);
            emailMessage.setBody(message);
            provider.sendEmail(emailMessage);
        }
    }
}

