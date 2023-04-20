package tech.getarrays.EncadrantMicroservice.email;

public interface EmailSender {
    void send(String to, String email);
}
