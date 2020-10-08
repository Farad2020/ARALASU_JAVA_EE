package modules;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Post{
    private Long id;
    private User author;
    private String title;
    private String short_content;
    private String content;
    private Timestamp post_date;

    public Post(Long id, User author, String title, String short_content, String content, Timestamp post_date) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.post_date = post_date;
    }

    public Post(User author, String title, String short_content, String content, Timestamp post_date) {
        id = null;
        this.author = author;
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.post_date = post_date;
    }

    public Post(User author, String title, String short_content, String content) {
        id = null;
        this.author = author;
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        post_date = null;
    }

    public Post() {
    }

    public String getFormattedPost_date(){
        String s = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(post_date);
        return s;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_content() {
        return short_content;
    }

    public void setShort_content(String short_content) {
        this.short_content = short_content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPost_date() {
        return post_date;
    }

    public void setPost_date(Timestamp post_date) {
        this.post_date = post_date;
    }

    @Override
    public String toString() {
        return "Post =>" +
                "id=" + id +
                ", authod_id=" + author +
                ", title='" + title + '\'' +
                ", short_content='" + short_content + '\'' +
                ", content='" + content + '\'' +
                ", post_date=" + post_date;
    }
}
