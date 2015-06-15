import java.net.URISyntaxException;
import java.sql.SQLException;

public class UserStatistics {

   private static UserStatistics singleton = new UserStatistics( );

   private UserStatistics() { }
   
   public static UserStatistics getInstance( ) {
      return singleton;
   }
   
   public int getStatsCount(String username, String column) {
       return DatabaseUtility.getStatsCount(username, column);
   }
}
