import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Adder extends Remote {
    int add(int x, int y) throws RemoteException;
    void storeResult(int x, int y, int result) throws RemoteException;
}
