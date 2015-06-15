import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseUtility {

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = null;
        dbUri = URI.create("postgres://lywqlcyaeqavwa:s3OzRPS0vn7kgqALqd9DqltoDt@ec2-54-243-51-102.compute-1.amazonaws.com:5432/dcot06n9g1qdeb");

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

        System.out.println("Connecting to ShipWars Cloud database");

        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static int getStatsCount(String username, String column) {
        int count = 0;
        Connection conn = null;
        try {
            conn = getConnection();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT " + column + " FROM swusers WHERE username = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(column);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return count;
    }

    public static String getAuthentication(String username, String password) throws URISyntaxException, SQLException {
        String name = null;
        Connection conn = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT username, name FROM swusers WHERE password = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("username").equals(username)) {
                    name = resultSet.getString("name");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return name;
    }

    public static boolean forgotPassword(String username, String password) throws URISyntaxException, SQLException {
        boolean flag = false;
        Connection conn = getConnection();
        PreparedStatement statement = null;
        flag = checkUserExists(conn, username);
        if (flag) {
            try {
                String sql = "Update swusers SET password = ? where username = ?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, password);
                statement.setString(2, username);
                statement.executeUpdate();
                flag = true;
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            return flag;
        }
        return flag;
    }

    public static boolean registerUser(String name, String username, String password) throws URISyntaxException, SQLException {
        boolean flag = false;
        Connection conn = getConnection();
        PreparedStatement preparedStatement = null;
        flag = checkUserExists(conn, username);
        if (!flag) {
            try {
                String sql = "Insert into swusers (username, password, name, plays, won, lost) values "
                        + "(?,?,?,0,0,0)";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, name);
                preparedStatement.executeUpdate();
                flag = true;
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            return flag;
        }
        return flag;
    }

    public static boolean checkUserExists(Connection conn, String username) {
        boolean flag = false;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.createStatement();
            String sql = "SELECT username FROM swusers";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (resultSet.getString("username").equals(username)) {
                    flag = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return flag;
    }

    public static boolean updateScore (String username, String column) {

        boolean flag = false;
        Connection conn = null;
        try {
            conn = getConnection();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = null;
        flag = checkUserExists(conn, username);
        if (flag!=false) {
            try {
                String sql = "UPDATE swusers SET " + column + " = " + column + " + 1 WHERE username = ?";
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.executeUpdate();
                flag = true;
                System.out.println("Updating scores to ShipWars Cloud database");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseUtility.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            return flag;
        }
        return flag;
    }
}
