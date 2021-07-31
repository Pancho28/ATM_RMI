package Bank;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorATM {
    public static void main (String arg[]){
        try{
            Registry r = LocateRegistry.createRegistry(1234);
            r.rebind("ATM", new Bank());
            System.out.print("Servidor activo...");
        }catch(Exception e){
            System.out.print("Exception "+e.getMessage());
        }
    }
}
