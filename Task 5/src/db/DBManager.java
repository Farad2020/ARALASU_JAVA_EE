package db;

import modules.Post;
import modules.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class DBManager {
    private static Connection connection;

    static {
        connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_task_5?useUnicode=true&serverTimezone=UTC",
                    "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Students
    public static ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT id, email, password, full_name, birth_date, picture_url " +
                    " FROM users");

            ResultSet resultSet = st.executeQuery();

            while(resultSet.next()){
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url")
                ));
            }

            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public static User getUser(Long id){
        User user = new User();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT id, email, password, full_name, birth_date, picture_url " +
                    " FROM users WHERE id = ? LIMIT 1");

            st.setLong(1, id);
            ResultSet resultSet = st.executeQuery();

            if(resultSet.next()){
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url"));
            }

            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static User getUserByEmail(String email){
        User user = new User();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT id, email, password, full_name, birth_date, picture_url " +
                    " FROM users WHERE email = ? LIMIT 1");

            st.setString(1, email);
            ResultSet resultSet = st.executeQuery();

            if(resultSet.next()){
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url"));
            }

            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static User getUserByEmailAndPassword(String email, String password){
        User user = new User();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT id, email, password, full_name, birth_date, picture_url " +
                    " FROM users WHERE email = ? and password = ? LIMIT 1");

            st.setString(1, email);
            st.setString(2, password);
            ResultSet resultSet = st.executeQuery();

            if(resultSet.next()){
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url"));
            }

            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static boolean addUser(User user){   // here instead of checking if email is unique I let MySQL do it
        int rows = 0;                           // Check later if this is the best way to do it or not
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " INSERT INTO users (id, email, password, full_name, birth_date, picture_url)" +
                    " VALUES (NULL, ?, ?, ?, ?, ?)");

            st.setString(1, user.getEmail());
            st.setString(2, user.getPassword());
            st.setString(3, user.getFull_name());
            st.setString(4, user.getBirth_date());
            st.setString(5, user.getPicture_url());
            rows = st.executeUpdate();
            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean updateUser(User user){      //Same thing like with user additions
        int rows = 0;
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " UPDATE users SET email = ?, password = ?, full_name = ?, birth_date = ?, picture_url = ?" +
                    " WHERE id = ?");

            st.setString(1, user.getEmail());
            st.setString(2, user.getPassword());
            st.setString(3, user.getFull_name());
            st.setString(4, user.getBirth_date());
            st.setString(5, user.getPicture_url());
            st.setLong(6, user.getId());
            rows = st.executeUpdate();
            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean deleteUser(Long id){
        int rows = 0;
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " DELETE FROM users" +
                    " WHERE id = ?");
            st.setLong(1, id);
            rows = st.executeUpdate();
            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }


    public static ArrayList<Post> getAllPosts(){
        ArrayList<Post> posts = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT p.id, p.author_id, p.title, p.short_content, p.content, p.post_date, " +
                    " u.id AS user_id, u.email, u.password, u.full_name, u.birth_date, u.picture_url " +
                    " FROM posts p " +
                    " INNER JOIN users u ON p.author_id = u.id");
            ResultSet resultSet = st.executeQuery();

            User user;
            while(resultSet.next()){

                user = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url"));

                posts.add(new Post(
                        resultSet.getLong("id"),
                        user,
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date")
                ));
            }

            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        posts.sort(Comparator.comparing(Post::getPost_date).reversed());


        return posts;
    }


    public static Post getPostById(Long id){
        Post post = new Post();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT p.id, p.author_id, p.title, p.short_content, p.content, p.post_date, " +
                    " u.id AS user_id, u.email, u.password, u.full_name, u.birth_date, u.picture_url " +
                    " FROM posts p " +
                    " INNER JOIN users u ON p.author_id = u.id " +
                    " WHERE p.id = ? LIMIT 1 ");

            st.setLong(1, id);

            ResultSet resultSet = st.executeQuery();


            User user;
            while(resultSet.next()){

                user = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url"));

                post = new Post(
                        resultSet.getLong("id"),
                        user,
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date")
                );
            }

            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return post;
    }

    public static ArrayList<Post> getPostsByAuthorId(Long id){
        ArrayList<Post> posts = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT p.id, p.author_id, p.title, p.short_content, p.content, p.post_date, " +
                    " u.id AS user_id, u.email, u.password, u.full_name, u.birth_date, u.picture_url " +
                    " FROM posts p " +
                    " INNER JOIN users u ON p.author_id = u.id " +
                    " WHERE author_id = ? ");

            st.setLong(1, id);

            ResultSet resultSet = st.executeQuery();



            User user;
            while(resultSet.next()){

                user = new User(
                        resultSet.getLong("user_id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url"));

                posts.add(new Post(
                        resultSet.getLong("id"),
                        user,
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date")
                ));
            }

            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        posts.sort(Comparator.comparing(Post::getPost_date).reversed());
        return posts;
    }

    public static boolean addPost(Post p){   // here instead of checking if email is unique I let MySQL do it
        int rows = 0;                           // Check later if this is the best way to do it or not
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " INSERT INTO posts (id, author_id, title, short_content, content, post_date)" +
                    " VALUES (NULL, ?, ?, ?, ?, NULL)");

            st.setLong(1, p.getAuthor().getId());
            st.setString(2, p.getTitle());
            st.setString(3, p.getShort_content());
            st.setString(4, p.getContent());

            rows = st.executeUpdate();
            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }


    public static boolean updatePost(Post p){      //Same thing like with user additions
        int rows = 0;
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " UPDATE posts SET title = ?, short_content = ?, content = ?" +
                    " WHERE id = ?");
            st.setString(1, p.getTitle());
            st.setString(2, p.getShort_content());
            st.setString(3, p.getContent());
            st.setLong(4, p.getId());
            rows = st.executeUpdate();
            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }


    public static boolean deletePost(Long id){
        int rows = 0;
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " DELETE FROM posts" +
                    " WHERE id = ?");
            st.setLong(1, id);
            rows = st.executeUpdate();
            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }
    /*
    id – int(11)
    authod_id – int(11) - foreign key with table users.id
    title – text
    short_content – text
    content – text
    post_date – timestamp

    public static Post getPost(Long id){
        Post post = new Post();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT n.id, n.title, n.short_content, n.content, n.post_date, n.picture_url, n.language_id, n.publication_id, " +
                    " p.name AS pub_name, p.description AS pub_description, p.rating," +
                    " l.name AS lang_name, l.code " +
                    " FROM news n " +
                    " INNER JOIN publications p ON n.publication_id = p.id" +
                    " INNER JOIN languages l ON n.language_id = l.id"+
                    " WHERE n.id = ? LIMIT 1");

            st.setLong(1, id);
            ResultSet resultSet = st.executeQuery();

            if(resultSet.next()){
                news = new News(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        resultSet.getString("picture_url"),
                        new Language(
                                resultSet.getLong("language_id"),
                                resultSet.getString("lang_name"),
                                resultSet.getString("code") ),
                        new Publication(resultSet.getLong("publication_id"),
                                resultSet.getString("pub_name"),
                                resultSet.getString("pub_description"),
                                resultSet.getDouble("rating"))
                );
            }

            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }
    

    public static ArrayList<News> getAllNewsByLangCode(String code){
        ArrayList<News> news = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT n.id, n.title, n.short_content, n.content, n.post_date, n.picture_url, n.language_id, n.publication_id, " +
                    " p.name AS pub_name, p.description AS pub_description, p.rating," +
                    " l.name AS lang_name, l.code " +
                    " FROM news n " +
                    " INNER JOIN publications p ON n.publication_id = p.id" +
                    " INNER JOIN languages l ON n.language_id = l.id");

            ResultSet resultSet = st.executeQuery();

            while(resultSet.next()){
                if(resultSet.getString("code").equals(code)){
                    news.add(new News(
                            resultSet.getLong("id"),
                            resultSet.getString("title"),
                            resultSet.getString("short_content"),
                            resultSet.getString("content"),
                            resultSet.getTimestamp("post_date"),
                            resultSet.getString("picture_url"),
                            new Language(
                                    resultSet.getLong("language_id"),
                                    resultSet.getString("lang_name"),
                                    resultSet.getString("code") ),
                            new Publication(resultSet.getLong("publication_id"),
                                    resultSet.getString("pub_name"),
                                    resultSet.getString("pub_description"),
                                    resultSet.getDouble("rating"))
                    ));

                }
            }

            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return news;
    }



    public static boolean addNews(News news){
        int rows = 0;
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " INSERT INTO news (id, title, short_content, content, post_date, picture_url, language_id, publication_id)" +
                    " VALUES (NULL, ?, ?, ?, ?, ? , ?, ?)");

            st.setString(1, news.getTitle());
            st.setString(2, news.getShort_content());
            st.setString(3, news.getContent());
            st.setTimestamp(4, news.getPost_date());
            st.setString(5, news.getPicture_url());
            st.setLong(6, news.getLanguage().getId());
            st.setLong(7, news.getPublication().getId());
            rows = st.executeUpdate();
            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean updateNews(News news){
        int rows = 0;
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " UPDATE news SET title = ?, short_content = ?, content = ?, post_date = ?," +
                    " picture_url = ?, language_id = ?, publication_id = ? " +
                    " WHERE id = ?");

            st.setString(1, news.getTitle());
            st.setString(2, news.getShort_content());
            st.setString(3, news.getContent());
            st.setTimestamp(4, news.getPost_date());
            st.setString(5, news.getPicture_url());
            st.setLong(6, news.getLanguage().getId());
            st.setLong(7, news.getPublication().getId());
            st.setLong(8, news.getId());
            rows = st.executeUpdate();
            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean deleteNews(Long id){
        int rows = 0;
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " DELETE FROM news" +
                    " WHERE id = ?");
            st.setLong(1, id);
            rows = st.executeUpdate();
            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }
    */
}
