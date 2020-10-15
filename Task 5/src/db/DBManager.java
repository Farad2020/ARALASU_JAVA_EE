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
            PreparedStatement st = connection.prepareStatement("" +
                    " SET time_zone = '+6:00' ");

            ResultSet resultSet = st.executeQuery();

            resultSet.close();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public static ArrayList<User> getAllUserFriendsById(Long id){
        ArrayList<User> users = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT u.id, u.email, u.password, u.full_name, u.birth_date, u.picture_url," +
                    " f.friend_id " +
                    " FROM users u " +
                    " INNER JOIN friends f ON f.friend_id = u.id" +
                    " WHERE f.user_id = ?");

            st.setLong(1, id);
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

    public static ArrayList<User> getAllRequestingUsersById(Long id){
        ArrayList<User> users = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT u.id, u.email, u.password, u.full_name, u.birth_date, u.picture_url " +
                    " FROM users u " +
                    " INNER JOIN friends_requests fr ON fr.request_sender_id = u.id" +
                    " WHERE fr.user_id = ?");

            st.setLong(1, id);
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

    public static ArrayList<User> searchRequestedUsersByUserId(Long id, String full_name){
        ArrayList<User> users = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT u.id, u.email, u.password, u.full_name, u.birth_date, u.picture_url " +
                    " FROM users u " +
                    " INNER JOIN friends_requests fr ON fr.user_id = u.id" +
                    " WHERE fr.request_sender_id = ? AND " +
                    " LOWER(full_name) LIKE LOWER(?) ");

            st.setLong(1, id);
            st.setString(2, "%"+full_name+"%");
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

    public static ArrayList<User> searchUserFriends(Long id, String full_name){
        ArrayList<User> users = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT u.id, u.email, u.password, u.full_name, u.birth_date, u.picture_url" +
                    " FROM users u " +
                    " INNER JOIN friends f ON f.friend_id = u.id" +
                    " WHERE f.user_id = ? AND " +
                    " LOWER(full_name) LIKE LOWER(?) ");

            st.setLong(1, id);
            st.setString(2, "%"+full_name+"%");
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

    public static ArrayList<User> getUsersByFullNamePart(String full_name){
        ArrayList<User> users = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT id, email, password, full_name, birth_date, picture_url " +
                    " FROM users" +
                    " WHERE LOWER(full_name) LIKE LOWER(?)"); //WHERE CHARINDEX(LOWER(?), LOWER(full_name)) > 0

            st.setString(1, "%"+full_name+"%");
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
        int rows = 0; // Check later if this is the best way to do it or not
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

    public static boolean addFriendRequest(Long acceptor_id, Long requester_id){
        int rows = 0;
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " INSERT INTO friends_requests (id, user_id, request_sender_id)" +
                    " VALUES (NULL, ?, ?) ");

            st.setLong(1, acceptor_id);
            st.setLong(2, requester_id);
            rows = st.executeUpdate();

            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean acceptFriendRequest(Long acceptor_id, Long requester_id){
        int rows1 = 0;
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " DELETE FROM friends_requests" +
                    " WHERE user_id = ? AND request_sender_id = ? ");

            st.setLong(1, acceptor_id);
            st.setLong(2, requester_id);
            rows1 = st.executeUpdate();

            st = connection.prepareStatement("" +
                    " INSERT INTO friends (id, user_id, friend_id)" +
                    " VALUES (NULL, ?, ?), (NULL, ?, ?) ");
            st.setLong(1, acceptor_id);
            st.setLong(2, requester_id);

            st.setLong(3, requester_id);
            st.setLong(4, acceptor_id);

            rows1 += st.executeUpdate();

            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows1>0;
    }

    public static boolean rejectFriendRequest(Long acceptor_id, Long requester_id){
        int rows = 0;
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " DELETE FROM friends_requests" +
                    " WHERE user_id = ? AND request_sender_id = ? ");

            st.setLong(1, acceptor_id);
            st.setLong(2, requester_id);

            rows = st.executeUpdate();
            st.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }

    public static boolean deleteFriend(Long user_id, Long friend_id){
        int rows = 0;
        try{
            PreparedStatement st = connection.prepareStatement("" +
                    " DELETE FROM friends" +
                    " WHERE user_id IN (?,?) AND friend_id IN (?,?) ");

            st.setLong(1, user_id);
            st.setLong(2, friend_id);

            st.setLong(3, user_id);
            st.setLong(4, friend_id);

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
                    " INNER JOIN users u ON p.author_id = u.id ");
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
}
