import java.rmi.*;
import java.net.*;
import java.io.*;


public class HelloWorldServer {

    public static void main(String[] args) {
        try {
            HelloWorldImpl localobj=new HelloWorldImpl();
            Naming.rebind("rmi:///HelloWorld",localobj);
        } catch (RemoteException re) {
           
            re.printStackTrace();
        }
        catch(MalformedURLException mfe){
            mfe.printStackTrace();
        }
    }
}
