import java.rmi.*;
import java.net.*;
import java.io.*;


public class HelloWorldClient {

    public static void main(String[] args) {
        try {
           String host="localhost";
           HelloWorld remobject =(HelloWorld)Naming.lookup("rmi://"+host+"/HelloWorld");
           System.out.println(remobject.display());
        } catch (RemoteException re) {
            
            re.printStackTrace();
        }
        catch(NotBoundException nbe)
        {
            nbe.printStackTrace();
        }
        catch(MalformedURLException mfe)
        {
            mfe.printStackTrace();
        }
    }
}
