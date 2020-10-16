package db;

import modules.Chat;
import modules.Message;
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

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT id, email, password, full_name, birth_date, picture_url " +
                    " FROM users");

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static ArrayList<User> getAllUserFriendsById(Long id) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT u.id, u.email, u.password, u.full_name, u.birth_date, u.picture_url," +
                    " f.friend_id " +
                    " FROM users u " +
                    " INNER JOIN friends f ON f.friend_id = u.id" +
                    " WHERE f.user_id = ?");

            st.setLong(1, id);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static ArrayList<User> getAllRequestingUsersById(Long id) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT u.id, u.email, u.password, u.full_name, u.birth_date, u.picture_url " +
                    " FROM users u " +
                    " INNER JOIN friends_requests fr ON fr.request_sender_id = u.id" +
                    " WHERE fr.user_id = ?");

            st.setLong(1, id);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static ArrayList<User> searchRequestedUsersByUserId(Long id, String full_name) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT u.id, u.email, u.password, u.full_name, u.birth_date, u.picture_url " +
                    " FROM users u " +
                    " INNER JOIN friends_requests fr ON fr.user_id = u.id" +
                    " WHERE fr.request_sender_id = ? AND " +
                    " LOWER(full_name) LIKE LOWER(?) ");

            st.setLong(1, id);
            st.setString(2, "%" + full_name + "%");
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static ArrayList<User> searchUserFriends(Long id, String full_name) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT u.id, u.email, u.password, u.full_name, u.birth_date, u.picture_url" +
                    " FROM users u " +
                    " INNER JOIN friends f ON f.friend_id = u.id" +
                    " WHERE f.user_id = ? AND " +
                    " LOWER(full_name) LIKE LOWER(?) ");

            st.setLong(1, id);
            st.setString(2, "%" + full_name + "%");
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static ArrayList<User> getUsersByFullNamePart(String full_name) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT id, email, password, full_name, birth_date, picture_url " +
                    " FROM users" +
                    " WHERE LOWER(full_name) LIKE LOWER(?)"); //WHERE CHARINDEX(LOWER(?), LOWER(full_name)) > 0

            st.setString(1, "%" + full_name + "%");
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User getUser(Long id) {
        User user = new User();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT id, email, password, full_name, birth_date, picture_url " +
                    " FROM users WHERE id = ? LIMIT 1");

            st.setLong(1, id);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url"));
            }

            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User getUserByEmail(String email) {
        User user = new User();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT id, email, password, full_name, birth_date, picture_url " +
                    " FROM users WHERE email = ? LIMIT 1");

            st.setString(1, email);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url"));
            }

            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User getUserByEmailAndPassword(String email, String password) {
        User user = new User();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT id, email, password, full_name, birth_date, picture_url " +
                    " FROM users WHERE email = ? and password = ? LIMIT 1");

            st.setString(1, email);
            st.setString(2, password);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("birth_date"),
                        resultSet.getString("picture_url"));
            }

            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static boolean addUser(User user) {   // here instead of checking if email is unique I let MySQL do it
        int rows = 0; // Check later if this is the best way to do it or not
        try {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean updateUser(User user) {      //Same thing like with user additions
        int rows = 0;
        try {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean deleteUser(Long id) {
        int rows = 0;
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " DELETE FROM users" +
                    " WHERE id = ?");
            st.setLong(1, id);
            rows = st.executeUpdate();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean addFriendRequest(Long acceptor_id, Long requester_id) {
        int rows = 0;
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " INSERT INTO friends_requests (id, user_id, request_sender_id)" +
                    " VALUES (NULL, ?, ?) ");

            st.setLong(1, acceptor_id);
            st.setLong(2, requester_id);
            rows = st.executeUpdate();

            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean acceptFriendRequest(Long acceptor_id, Long requester_id) {
        int rows1 = 0;
        try {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows1 > 0;
    }

    public static boolean rejectFriendRequest(Long acceptor_id, Long requester_id) {
        int rows = 0;
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " DELETE FROM friends_requests" +
                    " WHERE user_id = ? AND request_sender_id = ? ");

            st.setLong(1, acceptor_id);
            st.setLong(2, requester_id);

            rows = st.executeUpdate();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean deleteFriend(Long user_id, Long friend_id) {
        int rows = 0;
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " DELETE FROM friends" +
                    " WHERE user_id IN (?,?) AND friend_id IN (?,?) ");

            st.setLong(1, user_id);
            st.setLong(2, friend_id);

            st.setLong(3, user_id);
            st.setLong(4, friend_id);

            rows = st.executeUpdate();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }


    public static ArrayList<Post> getAllPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT p.id, p.author_id, p.title, p.short_content, p.content, p.post_date, " +
                    " u.id AS user_id, u.email, u.password, u.full_name, u.birth_date, u.picture_url " +
                    " FROM posts p " +
                    " INNER JOIN users u ON p.author_id = u.id ");
            ResultSet resultSet = st.executeQuery();

            User user;
            while (resultSet.next()) {

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

        } catch (Exception e) {
            e.printStackTrace();
        }

        posts.sort(Comparator.comparing(Post::getPost_date).reversed());


        return posts;
    }


    public static Post getPostById(Long id) {
        Post post = new Post();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT p.id, p.author_id, p.title, p.short_content, p.content, p.post_date, " +
                    " u.id AS user_id, u.email, u.password, u.full_name, u.birth_date, u.picture_url " +
                    " FROM posts p " +
                    " INNER JOIN users u ON p.author_id = u.id " +
                    " WHERE p.id = ? LIMIT 1 ");

            st.setLong(1, id);

            ResultSet resultSet = st.executeQuery();


            User user;
            while (resultSet.next()) {

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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public static ArrayList<Post> getPostsByAuthorId(Long id) {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT p.id, p.author_id, p.title, p.short_content, p.content, p.post_date, " +
                    " u.id AS user_id, u.email, u.password, u.full_name, u.birth_date, u.picture_url " +
                    " FROM posts p " +
                    " INNER JOIN users u ON p.author_id = u.id " +
                    " WHERE author_id = ? ");

            st.setLong(1, id);

            ResultSet resultSet = st.executeQuery();


            User user;
            while (resultSet.next()) {

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

        } catch (Exception e) {
            e.printStackTrace();
        }
        posts.sort(Comparator.comparing(Post::getPost_date).reversed());
        return posts;
    }

    public static boolean addPost(Post p) {   // here instead of checking if email is unique I let MySQL do it
        int rows = 0;                           // Check later if this is the best way to do it or not
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " INSERT INTO posts (id, author_id, title, short_content, content, post_date)" +
                    " VALUES (NULL, ?, ?, ?, ?, NULL)");

            st.setLong(1, p.getAuthor().getId());
            st.setString(2, p.getTitle());
            st.setString(3, p.getShort_content());
            st.setString(4, p.getContent());

            rows = st.executeUpdate();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }


    public static boolean updatePost(Post p) {      //Same thing like with user additions
        int rows = 0;
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " UPDATE posts SET title = ?, short_content = ?, content = ?" +
                    " WHERE id = ?");
            st.setString(1, p.getTitle());
            st.setString(2, p.getShort_content());
            st.setString(3, p.getContent());
            st.setLong(4, p.getId());
            rows = st.executeUpdate();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }


    public static boolean deletePost(Long id) {
        int rows = 0;
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " DELETE FROM posts" +
                    " WHERE id = ?");
            st.setLong(1, id);
            rows = st.executeUpdate();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    //CHAT & Messages
    public static boolean addOrUpdateChat(Long user_id, Long opponent_id, String latest_message_text) {
        int rows = 0;
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT  ch.user_id, ch.opponent_user_id " +
                    " FROM chats ch" +
                    " WHERE (ch.user_id = ? AND ch.opponent_user_id = ?) OR (ch.user_id = ? AND ch.opponent_user_id = ?) ");

            st.setLong(1, user_id);
            st.setLong(2, opponent_id);

            st.setLong(3, opponent_id);
            st.setLong(4, user_id);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                rows++;
            }

            if (rows > 0) {
                st = connection.prepareStatement("" +
                        " UPDATE chats SET latest_message_text = ?, latest_message_time = CURRENT_TIMESTAMP " +
                        " WHERE (user_id = ? AND opponent_user_id = ?) OR (user_id = ? AND opponent_user_id = ?) ");
                st.setString(1, latest_message_text);

                st.setLong(2, user_id);
                st.setLong(3, opponent_id);

                st.setLong(4, opponent_id);
                st.setLong(5, user_id);
                rows = st.executeUpdate();
            } else {
                st = connection.prepareStatement("" +
                        " INSERT INTO chats (id, user_id, opponent_user_id, create_date, latest_message_text, latest_message_time) " +
                        " VALUES (NULL, ?, ?, NULL, ?, NULL)");

                st.setLong(1, user_id);
                st.setLong(2, opponent_id);
                st.setString(3, latest_message_text);

                rows = st.executeUpdate();
            }

            st = connection.prepareStatement("" +
                    " SELECT  ch.id " +
                    " FROM chats ch" +
                    " WHERE (ch.user_id = ? AND ch.opponent_user_id = ?) OR (ch.user_id = ? AND ch.opponent_user_id = ?) ");
            st.setLong(1, user_id);
            st.setLong(2, opponent_id);

            st.setLong(3, opponent_id);
            st.setLong(4, user_id);

            resultSet = st.executeQuery();

            Long chat_id = 0L;
            while (resultSet.next()) {
                chat_id = resultSet.getLong("id");
            }

            st.close();

            addMessage(chat_id, user_id, opponent_id, latest_message_text);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean addMessage(Long chat_id, Long sender_id, Long user_id, String latest_message_text) {
        int rows = 0;
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " INSERT INTO messages (id, chat_id, user_id, sender_id, message_text, read_by_receiver, send_date )" +
                    " VALUES (NULL, ?, ?, ?, ?, false, NULL )");

            st.setLong(1, chat_id);
            st.setLong(2, user_id);
            st.setLong(3, sender_id);
            st.setString(4, latest_message_text);

            rows = st.executeUpdate();
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<Message> getChatMessages(Long sender_id, Long user_id) {
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT id, chat_id, user_id, sender_id, message_text, read_by_receiver, send_date " +
                    " FROM messages " +
                    " WHERE (user_id = ? AND sender_id = ?) OR (user_id = ? AND sender_id = ?) ");

            st.setLong(1, user_id);
            st.setLong(2, sender_id);

            st.setLong(3, sender_id);
            st.setLong(4, user_id);

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {

                messages.add(new Message(
                        resultSet.getLong("id"),
                        resultSet.getLong("chat_id"),
                        resultSet.getLong("user_id"),
                        resultSet.getLong("sender_id"),
                        resultSet.getString("message_text"),
                        resultSet.getBoolean("read_by_receiver"),
                        resultSet.getTimestamp("send_date")
                ));
            }

            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        messages.sort(Comparator.comparing(Message::getSent_date).reversed());


        return messages;
    }

    public static boolean readChatMessage(Long id) {
        int rows = 0;
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " UPDATE messages SET read_by_receiver = TRUE " +
                    " WHERE id = ?");

            st.setLong(1, id);

            rows = st.executeUpdate();

            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows>0;
    }



    public static Message getLastChatMessage(Long sender_id, Long user_id) {
        ArrayList<Message> messages = getChatMessages(sender_id, user_id);
        return messages.get(0);
    }


    public static ArrayList<Chat> getChats(Long user_id) {
        ArrayList<Chat> chats = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT ch.id, ch.user_id, ch.opponent_user_id, ch.create_date, " +
                    " ch.latest_message_text, ch.latest_message_time " +
                    " FROM chats ch" +
                    " WHERE ch.user_id = ? OR ch.opponent_user_id = ? ");

            st.setLong(1, user_id);
            st.setLong(2, user_id);

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {

                chats.add(new Chat(
                        resultSet.getLong("id"),
                        resultSet.getLong("user_id"),
                        resultSet.getLong("opponent_user_id"),
                        resultSet.getTimestamp("create_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getTimestamp("latest_message_time")
                ));
            }

            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        chats.sort(Comparator.comparing(Chat::getLatest_message_time).reversed());

        return chats;
    }

    public static int newUserMessageNumber(Long user_id) {
        int rows = 0;
        ArrayList<Message> messages = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("" +
                    " SELECT * " +
                    " FROM messages " +
                    " WHERE user_id = ?  ");

            st.setLong(1, user_id);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                if( !resultSet.getBoolean("read_by_receiver") )
                    rows++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows;
    }

/**
 INSERT INTO table (id, name, age) VALUES(1, "A", 19) ON DUPLICATE KEY UPDATE
 name="A", age=19

 " SELECT p.id, p.author_id, p.title, p.short_content, p.content, p.post_date, " +
 " u.id AS user_id, u.email, u.password, u.full_name, u.birth_date, u.picture_url " +
 " FROM posts p " +
 " INNER JOIN users u ON p.author_id = u.id " +
 " WHERE author_id = ?
 **/

}
