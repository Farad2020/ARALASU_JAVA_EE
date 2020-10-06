package modules;

import java.sql.Timestamp;

public class Post {
    private Long id;
    private Long authod_id;
    private String title;
    private String short_content;
    private String content;
    private Timestamp post_date;

    public Post(Long id, Long authod_id, String title, String short_content, String content, Timestamp post_date) {
        this.id = id;
        this.authod_id = authod_id;
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.post_date = post_date;
    }

    public Post(Long authod_id, String title, String short_content, String content, Timestamp post_date) {
        id = null;
        this.authod_id = authod_id;
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.post_date = post_date;
    }

    public Post() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthod_id() {
        return authod_id;
    }

    public void setAuthod_id(Long authod_id) {
        this.authod_id = authod_id;
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
                ", authod_id=" + authod_id +
                ", title='" + title + '\'' +
                ", short_content='" + short_content + '\'' +
                ", content='" + content + '\'' +
                ", post_date=" + post_date;
    }
}
