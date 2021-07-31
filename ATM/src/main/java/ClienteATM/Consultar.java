package ClienteATM;

import RMI_ATM.ATM;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.table.DefaultTableModel;


public class Consultar extends javax.swing.JFrame {

    String doc_id;
    String[] cuentas;
    String cuenta;
    DefaultTableModel modelo;
    String nombre;
    
    public Consultar(String doc) {
        this.doc_id = doc;
        initComponents();
        cuentas();
        nombre();
    }

    public void cuentas(){
        try{
            modelo = new DefaultTableModel();
            modelo.addColumn("N° cuenta");
            modelo.addColumn("Saldo");
            Registry r = LocateRegistry.getRegistry("127.0.0.1", 1234);
            ATM bank = (ATM) r.lookup("ATM");
            this.cuentas = bank.consultarcuentas(this.doc_id);
            String datos[] = new String[2];
            int i = 0;
            while (i !=  6){
                if (this.cuentas[i]!=null){
                    datos[0]=this.cuentas[i];
                    datos[1]=this.cuentas[i+1];
                    modelo.addRow(datos);
                }
                i = i + 2;
            }
            tbcuentas.setModel(modelo);
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
    }
    
    public void nombre(){
        try{
            this.nombre ="";
            Registry r = LocateRegistry.getRegistry("127.0.0.1", 1234);
            ATM bank = (ATM) r.lookup("ATM");
            this.nombre = bank.consultarusuario(this.doc_id);
            this.nombre = this.nombre.substring(0,1).toUpperCase()+this.nombre.substring(1);
            lnombre.setText(this.nombre);
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JLabel();
        lnombre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbcuentas = new javax.swing.JTable();
        volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("Consultar cuentas");

        txtnombre.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtnombre.setText("Nombre propietario");

        lnombre.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        tbcuentas.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tbcuentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "N° cuenta", "Saldo"
            }
        ));
        jScrollPane1.setViewportView(tbcuentas);

        volver.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtnombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(volver)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombre))
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(volver)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        MenuT mt = new MenuT(this.doc_id);
        mt.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_volverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lnombre;
    private javax.swing.JTable tbcuentas;
    private javax.swing.JLabel txtnombre;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
