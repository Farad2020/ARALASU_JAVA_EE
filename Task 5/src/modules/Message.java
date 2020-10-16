package modules;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Message {
    Long id;
    Long chat_id;
    Long user_id;
    Long sender_id;
    String message_text;
    boolean read_by_receiver;
    Timestamp sent_date;

    public Message(Long id, Long chat_id, Long user_id, Long sender_id, String message_text, boolean read_by_receiver, Timestamp sent_date) {
        this.id = id;
        this.chat_id = chat_id;
        this.user_id = user_id;
        this.sender_id = sender_id;
        this.message_text = message_text;
        this.read_by_receiver = read_by_receiver;
        this.sent_date = sent_date;
    }

    public Message() {
    }

    public String getFormattedSent_date(){
        String s = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(sent_date);
        return s;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChat_id() {
        return chat_id;
    }

    public void setChat_id(Long chat_id) {
        this.chat_id = chat_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getSender_id() {
        return sender_id;
    }

    public void setSender_id(Long sender_id) {
        this.sender_id = sender_id;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public boolean isRead_by_receiver() {
        return read_by_receiver;
    }

    public void setRead_by_receiver(boolean read_by_receiver) {
        this.read_by_receiver = read_by_receiver;
    }

    public Timestamp getSent_date() {
        return sent_date;
    }

    public void setSent_date(Timestamp sent_date) {
        this.sent_date = sent_date;
    }
}
