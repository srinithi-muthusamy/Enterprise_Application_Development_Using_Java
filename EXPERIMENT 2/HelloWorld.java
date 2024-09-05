import java.rmi.*;

 
public interface HelloWorld extends Remote {
     
    public String display() throws RemoteException;
}
