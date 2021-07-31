package RMI_ATM;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ATM extends Remote{
    
    public String consultarusuario(String doc) throws RemoteException; 
    public String login(String user, String pass) throws RemoteException;
    public void crearprimcuenta(String name, String user, String pass, String doc_id, String monto) throws RemoteException;
    public void crearcuenta(String doc_id, String monto) throws RemoteException;
    public String[] consultarcuentas(String doc_id) throws RemoteException;
    public void depositar(String cuenta, String monto) throws RemoteException;
    public void retirar(String cuenta, String monto) throws RemoteException;
    public void transferir(String cuenta, String monto, String cuentab, String dos_id, String desc) throws RemoteException;
    public int verifcuenta(String doc_id) throws RemoteException;
    public boolean verifdoccuenta(String doc_id, String cuenta) throws RemoteException;
    
}
