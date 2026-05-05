package com.example.reservation.services;

import com.example.reservation.contact.EmailMessage;

public interface EmailProvider {
    void sendEmail(EmailMessage message);
}
