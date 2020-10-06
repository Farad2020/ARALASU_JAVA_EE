package example_pages;

public class Language {
    Long id;
    String name;
    String code;

    public Language(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Language(String name, String code) {
        id = null;
        this.name = name;
        this.code = code;
    }

    public Language() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
