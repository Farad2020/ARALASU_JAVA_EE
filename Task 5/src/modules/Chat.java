package modules;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Chat {
    Long id;
    Long user_id;
    Long opponent_user_id;
    Timestamp create_date;
    String latest_message_text;
    Timestamp latest_message_time;

    public Chat(Long id, Long user_id, Long opponent_user_id, Timestamp create_date, String latest_message_text, Timestamp latest_message_time) {
        this.id = id;
        this.user_id = user_id;
        this.opponent_user_id = opponent_user_id;
        this.create_date = create_date;
        this.latest_message_text = latest_message_text;
        this.latest_message_time = latest_message_time;
    }

    public Chat() {
    }

    public String getFormattedLatest_message_time(){
        String s = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(latest_message_time);
        return s;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getOpponent_user_id() {
        return opponent_user_id;
    }

    public void setOpponent_user_id(Long opponent_user_id) {
        this.opponent_user_id = opponent_user_id;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public String getLatest_message_text() {
        return latest_message_text;
    }

    public void setLatest_message_text(String latest_message_text) {
        this.latest_message_text = latest_message_text;
    }

    public Timestamp getLatest_message_time() {
        return latest_message_time;
    }

    public void setLatest_message_time(Timestamp latest_message_time) {
        this.latest_message_time = latest_message_time;
    }
}
