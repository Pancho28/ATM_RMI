package Bank;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import RMI_ATM.ATM;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bank extends UnicastRemoteObject implements ATM {

    Conexion con;
    
    public Bank()throws RemoteException{
    }
    
    @Override
    public String consultarusuario(String doc) throws RemoteException{
        con = new Conexion();
        String result = "";
        try {
            result = con.consultarusuariobd(doc);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (result);
    }
    
    @Override
    public String login(String user, String pass) throws RemoteException{
        con = new Conexion();
        String result = "";
        try {
            result = con.loginbd(user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    @Override
    public void crearprimcuenta(String name, String user, String pass, String doc_id, String monto) throws RemoteException{
        con = new Conexion();
        try {
            con.crearprimcuentabd(name, user, pass, doc_id, monto);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void crearcuenta(String doc_id, String monto) throws RemoteException{
        con = new Conexion();
        try {
            con.crearcuentabd(doc_id, monto);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String[] consultarcuentas(String doc_id) throws RemoteException{
        con = new Conexion();
        String[] result = {};
        try {
            result = con.consultarcuentasbd(doc_id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    @Override
    public void depositar(String cuenta, String monto) throws RemoteException{
        con = new Conexion();
        try {
            con.depositarbd(cuenta, monto);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void retirar(String cuenta, String monto) throws RemoteException{
        con = new Conexion();
        try {
            con.retirarbd(cuenta, monto);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void transferir(String cuenta, String monto, String cuentab, String doc_id, String desc) throws RemoteException{
        con = new Conexion();
        try {
            con.transferirbd(cuenta, monto,cuentab,doc_id,desc);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public int verifcuenta(String doc_id) throws RemoteException{
        con = new Conexion();
        int result = 0;
        try {
            result = con.verifcuentabd(doc_id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    
    }        
    
    @Override
    public boolean verifdoccuenta(String doc_id, String cuenta){
        con = new Conexion();
        boolean result = false;
        try {
            result = con.verifdoccuentabd(doc_id, cuenta);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
