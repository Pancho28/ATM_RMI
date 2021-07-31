package Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Conexion {
    
    Connection con;
    String driver  = "com.mysql.cj.jdbc.Driver";
    String user = "root";
    String pass = "admin";
    String url = "jdbc:mysql://localhost:3306/atm?serverTimezone=UTC";
    
    public void Conexion(){
        
    }

    public void conectar() throws ClassNotFoundException{
    con = null;    
        try{
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url, user, pass);
            if (con != null){
                System.out.println("Conectando...");
            }
        }catch (SQLException e){
                System.out.println("Conexión no exitosa "+e.getMessage());
        
        }
    }
    
    public String consultarusuariobd(String doc) throws ClassNotFoundException{
        conectar();
        String query = "Select name_ from User where document_id="+doc;
        Statement ejecutor = null;
        ResultSet rs;
        String result = "";
        try{
            ejecutor = con.createStatement();
            ejecutor.setQueryTimeout(20);
            rs = ejecutor.executeQuery(query);
            while (rs.next()==true){
                result = rs.getString("name_");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (result);
    }
    
    public String loginbd(String user, String pass) throws ClassNotFoundException{
        conectar();
        String query = "Select document_id from User where username = '"+user+"' and password_ = '"+pass+"'";
        Statement ejecutor = null;
        ResultSet rs;
        String result = "";
        boolean lg = false;
        try{
            ejecutor = con.createStatement();
            ejecutor.setQueryTimeout(20);
            rs = ejecutor.executeQuery(query);
            while (rs.next()==true){
                result = rs.getString("document_id");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (result);
    }
    
    public void crearprimcuentabd(String name, String user, String pass, String doc_id, String monto) throws ClassNotFoundException{
        conectar();
        String query = "insert into User values ("+ doc_id +",'"+ name +"','"+ user +"','"+ pass +"')";
        Statement ejecutor = null;
        try{
            ejecutor = con.createStatement();
            ejecutor.setQueryTimeout(20);
            ejecutor.executeUpdate(query);
            crearcuentabd(doc_id,monto);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public String ultimacuenta() throws ClassNotFoundException{
        conectar();
        String query = "SELECT MAX(number_) AS id FROM Account";
        Statement ejecutor = null;
        ResultSet rs;
        String result = "";
        int num = 0;
        try{
            ejecutor = con.createStatement();
            ejecutor.setQueryTimeout(20);
            rs = ejecutor.executeQuery(query);
            while (rs.next()==true){
                result = rs.getString("id");
            }
            if (result!=""){
                result = rs.getString("id");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (result);
    }
    
    public void crearcuentabd(String doc_id, String monto) throws ClassNotFoundException{
        conectar();
        String query = "insert into Account(current_balace,fk_user) values ("+ 0 +",'"+ doc_id +"')";
        Statement ejecutor = null;
        String cuenta = "";
        try{
            ejecutor = con.createStatement();
            ejecutor.setQueryTimeout(20);
            ejecutor.executeUpdate(query);
            cuenta = ultimacuenta();
            depositarbd(cuenta,monto);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public String[] consultarcuentasbd(String doc_id) throws ClassNotFoundException{
        conectar();
        String query = "select number_,current_balace from account where fk_user="+ doc_id;
        Statement ejecutor = null;
        ResultSet rs;
        String[] result = new String[6];
        int i = 0;
        try{
            ejecutor = con.createStatement();
            ejecutor.setQueryTimeout(20);
            rs = ejecutor.executeQuery(query);
            while (rs.next()==true){
                result[i] = rs.getString("number_");
                result[i+1] = rs.getString("current_balace");
                i = i + 2;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (result);
    }

    public void depositarbd(String cuenta, String monto) throws ClassNotFoundException{
        conectar();
        String date = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        String query = "insert into Transaction(amount,date_,description_,tipe,fk_d,fk_f) values ("+ monto +",'"+ date +"','Deposito','d','"+ cuenta +"',null)";
        Statement ejecutor = null;
        try{
            ejecutor = con.createStatement();
            ejecutor.setQueryTimeout(20);
            ejecutor.executeUpdate(query);
            query = "update Account set current_balace = current_balace+"+monto+" where number_="+cuenta;
            ejecutor.executeUpdate(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void retirarbd(String cuenta, String monto) throws ClassNotFoundException{
        conectar();
        String date = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        String query = "insert into Transaction(amount,date_,description_,tipe,fk_d,fk_f) values ("+ monto +",'"+ date +"','Retiro','w','"+ cuenta +"',null)";
        Statement ejecutor = null;
        try{
            ejecutor = con.createStatement();
            ejecutor.setQueryTimeout(20);
            ejecutor.executeUpdate(query);
            query = "update Account set current_balace = current_balace-"+monto+" where number_="+cuenta;
            ejecutor.executeUpdate(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void transferirbd(String cuenta, String monto, String cuentab, String doc_id, String desc) throws ClassNotFoundException{
        conectar();
        String date = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        String query = "insert into Transaction(amount,date_,description_,tipe,fk_d,fk_f) values ("+ monto +",'"+ date +"','"+ desc +"','t','"+ cuenta +"','"+ cuentab +"')";
        Statement ejecutor = null;
        try{
            ejecutor = con.createStatement();
            ejecutor.setQueryTimeout(20);
            ejecutor.executeUpdate(query);
            query = "update Account set current_balace = current_balace+"+monto+" where number_="+cuentab;
            ejecutor.executeUpdate(query);
            query = "update Account set current_balace = current_balace-"+monto+" where number_="+cuenta;
            ejecutor.executeUpdate(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int verifcuentabd(String doc_id) throws ClassNotFoundException{
        conectar();
        String query = "Select count(number_) from Account where fk_user = "+doc_id;
        Statement ejecutor = null;
        ResultSet rs;
        String result = "";
        int num = 0;
        try{
            ejecutor = con.createStatement();
            ejecutor.setQueryTimeout(20);
            rs = ejecutor.executeQuery(query);
            while (rs.next()==true){
                result = rs.getString("count(number_)");
            }
            num = Integer.parseInt(result);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return (num);
    } 
    
    public boolean verifdoccuentabd(String doc_id, String cuenta)throws ClassNotFoundException{
        conectar();
        boolean result = false; 
        String query = "Select * from account where number_ ="+cuenta+" and fk_user ="+doc_id;
        Statement ejecutor = null;
        ResultSet rs;
        try{
            ejecutor = con.createStatement();
            ejecutor.setQueryTimeout(20);
            rs = ejecutor.executeQuery(query);
            String r;
            while (rs.next()==true){
                r = rs.getString("number_");
                if (!r.isEmpty()){
                    result = true;
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return (result);
    }
}
