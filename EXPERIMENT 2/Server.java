import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdderImpl extends UnicastRemoteObject implements Adder {
    private Connection connection;

    protected AdderImpl() throws RemoteException {
        super();
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi_db", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int add(int x, int y) throws RemoteException {
        int result = x + y;
        storeResult(x, y, result);
        return result;
    }

    @Override
    public void storeResult(int x, int y, int result) throws RemoteException {
        String sql = "INSERT INTO addition_results (num1, num2, result) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, x);
            statement.setInt(2, y);
            statement.setInt(3, result);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
