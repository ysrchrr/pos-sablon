/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormSablon;

import Koneksi.Db_Koneksi;
import java.awt.Color;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Muhammad Yasir
 */
public class FormTransaksi extends javax.swing.JFrame {
    private DefaultTableModel model;
    String idTransaksi, namaPelanggan, telpPelanggan, jenisPesanan, bahanPesanan,
            tglMasuk, tglJadi;
    int biayaBahan, biayaJenis, totalBayar, jumlahPesanan, xtotalBayar, diskon, finBayar;
    /**
     * Creates new form FormTransaksi
     */
    public FormTransaksi() {
        initComponents();
        model = new DefaultTableModel();
        tblTransaksi.setModel(model);
        model.addColumn("ID Transaksi");
        model.addColumn("Nama Pelanggan");
        model.addColumn("No. HP");
        model.addColumn("Jenis Pesanan");
        model.addColumn("Nama Bahan");
        model.addColumn("Jumlah Pesanan");
        model.addColumn("Tgl Masuk");
        model.addColumn("Tgl Jadi");
        model.addColumn("Total Bayar");
        getData();
    }
    public void getData(){
        //ngreswiki tabel
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try{
            //gawe statentmnt nyeluk data tblGaji seko db
            Statement stat = (Statement) Db_Koneksi.getKoneksi().createStatement();
            String sql =  "SELECT * FROM transaksi";
            ResultSet res = stat.executeQuery(sql);
            
            //jikuk baris tblGaji
            while(res.next()){
                Object[] obj = new Object[9];
                obj[0] = res.getString("idTransaksi");
                obj[1] = res.getString("namaPelanggan");
                obj[2] = res.getString("telpPelanggan");
                obj[3] = res.getString("jenisPesanan");
                obj[4] = res.getString("namaBahan");
                obj[5] = res.getString("jmlPesanan");
                obj[6] = res.getString("tglPesananMasuk");
                obj[7] = res.getString("tglPesananJadi");
                obj[8] = res.getString("totalBayar");
                
                model.addRow(obj);
            }
        } catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    public void loadData(){
        idTransaksi = txtIdTrx.getText();
        namaPelanggan = txtNama.getText();
        telpPelanggan = txtHP.getText();
        jenisPesanan = (String) cmboxJenis.getSelectedItem();
        bahanPesanan = (String) cmboxBahan.getSelectedItem();
        tglMasuk = txtMasuk.getText();
        tglJadi = txtJadi.getText();
        jumlahPesanan = Integer.parseInt(txtJumlah.getText());
        totalBayar = Integer.parseInt(txtTotal.getText());
        finBayar = Integer.parseInt(txtTotal.getText());
    }
    public void saveData(){
        loadData();
        try{
            Statement stat = (Statement) Db_Koneksi.getKoneksi().createStatement();
            String sql = "INSERT INTO transaksi (idTransaksi, namaPelanggan, telpPelanggan, jenisPesanan, namaBahan"
                    + ", jmlPesanan, tglPesananMasuk, tglPesananJadi, totalBayar)"
                    + "VALUES ('"+idTransaksi+"','"+namaPelanggan+"','"+telpPelanggan+"','"+jenisPesanan+"','"+bahanPesanan+"',"
                    + "'"+jumlahPesanan+"','"+tglMasuk+"','"+tglJadi+"','"+finBayar+"')";
            
            PreparedStatement p = (PreparedStatement) Db_Koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            getData();
        } catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    public void reset(){
        //String idTransaksi, namaPelanggan, telpPelanggan, jenisPesanan, bahanPesanan,
          //  tglMasuk, tglJadi;
    //int biayaBahan, biayaJenis, totalBayar, jumlahPesanan, xtotalBayar, diskon, finBayar;
        idTransaksi = "";
        namaPelanggan = "";
        telpPelanggan = "";
        jenisPesanan = "";
        bahanPesanan = "";
        tglMasuk = "";
        tglJadi = "";
        biayaBahan = 0;
        biayaJenis = 0;
        jumlahPesanan = 0;
        xtotalBayar = 0;
        diskon = 0;
        finBayar = 0;
        txtIdTrx.setText("");
        txtNama.setText("");
        txtHP.setText("");
        txtJumlah.setText("");
        txtMasuk.setText("");
        txtJadi.setText("");
        txtTotal.setText("");
        cmboxJenis.setSelectedItem("Kaos");
        cmboxBahan.setSelectedItem("Plastisol");
    }
    public void dataSelect(){
        
        int i = tblTransaksi.getSelectedRow();
        if(i == -1){
            //tdk ada yg terpiid SAD
            return;
        }
        txtIdTrx.setText(""+model.getValueAt(i,0));
        txtNama.setText(""+model.getValueAt(i,1));
        txtHP.setText(""+model.getValueAt(i,2));
        cmboxJenis.setSelectedItem(""+model.getValueAt(i,3));
        cmboxBahan.setSelectedItem(""+model.getValueAt(i,4));
        txtJumlah.setText(""+model.getValueAt(i,5));
        txtMasuk.setText(""+model.getValueAt(i,6));
        txtJadi.setText(""+model.getValueAt(i,7));
        txtTotal.setText(""+model.getValueAt(i,8));
    }
    public void updateData(){
        //String idTransaksi, namaPelanggan, telpPelanggan, jenisPesanan, bahanPesanan,
          //  tglMasuk, tglJadi;
    //int biayaBahan, biayaJenis, totalBayar, jumlahPesanan, xtotalBayar, diskon, finBayar;
        loadData();
        try{
            Statement stat = (Statement) Db_Koneksi.getKoneksi().createStatement();
            String sql = "UPDATE transaksi SET namaPelanggan      = '"+ namaPelanggan +"',"
                                           +"telpPelanggan    = '"+ telpPelanggan +"',"
                                           +"jenisPesanan    = '"+ jenisPesanan +"',"
                                           +"namaBahan    = '"+ bahanPesanan +"',"
                                           +"jmlPesanan    = '"+ jumlahPesanan +"',"
                                           +"tglPesananMasuk    = '"+ tglMasuk +"',"
                                           +"tglPesananJadi    = '"+ tglJadi +"',"
                                           +"totalBayar      = '"+ finBayar +"' WHERE idTransaksi = '"+ idTransaksi +"'";
            PreparedStatement p = (PreparedStatement) Db_Koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            getData();
            reset();
            JOptionPane.showMessageDialog(null, "Update berhasil!");
        }
        catch(SQLException er){
            JOptionPane.showMessageDialog(null, er.getMessage());
        }
    }
    public void loadJenis(){
        jenisPesanan = ""+cmboxJenis.getSelectedItem();
        switch(jenisPesanan){
            case "Kaos":
                biayaJenis = 30000;
            break;
            case "Kaos kemeja":
                biayaJenis = 40000;
            break;
            case "Hoodie":
                biayaJenis = 70000;
            break;
            case "Masker":
                biayaJenis = 3000;
            break;
            case "Bendera":
                biayaJenis = 10000;
            break;
            case "Scarf":
                biayaJenis = 10000;
            break;
            case "Tottebag":
                biayaJenis = 30000;
            break;
            case "Plastik":
                biayaJenis = 500;
            break;
            case "-- Pilih satu --":
                JOptionPane.showMessageDialog(null, "Anda belum memilih bahan");
            break;
        }
    }
    public void loadBahan(){
        bahanPesanan = ""+cmboxBahan.getSelectedItem();
        switch(bahanPesanan){
            case "Plastisol":
                biayaBahan = 20000;
            break;
            case "Rubber":
                biayaBahan = 22000;
            break;
            case "Polyflex":
                biayaBahan = 15000;
            break;
            case "DTG":
                biayaBahan = 30000;
            break;
            case "-- Pilih satu --":
                JOptionPane.showMessageDialog(null, "Anda belum memilih bahan");
            break;
        }
    }
    public void deleteData(){
        loadData();
        int pesan = JOptionPane.showConfirmDialog(null, "Anda yakin menghapus data "+idTransaksi+"?", "Konfirmasi",
                    JOptionPane.OK_CANCEL_OPTION);
        if(pesan == JOptionPane.OK_OPTION){
            try{
            Statement stat = (Statement) Db_Koneksi.getKoneksi().createStatement();
            String sql = "DELETE FROM transaksi WHERE idTransaksi = '"+idTransaksi+"'";
            PreparedStatement p = (PreparedStatement) Db_Koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            getData();
            reset();
            JOptionPane.showMessageDialog(null, "Delete berhasil!");
            }
            catch(SQLException er){
                JOptionPane.showMessageDialog(null, er.getMessage());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtIdTrx = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtHP = new javax.swing.JTextField();
        txtJumlah = new javax.swing.JTextField();
        txtMasuk = new javax.swing.JTextField();
        txtJadi = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        cmboxJenis = new javax.swing.JComboBox<>();
        cmboxBahan = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        bersihkan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        asd1 = new javax.swing.JButton();
        delete1 = new javax.swing.JButton();
        keluar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Form Transaksi");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setText("ID Transaksi");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setText("Nama Pelanggan");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setText("No. HP Pelanggan");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setText("Jenis Pesanan");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setText("Bahan Pesanan");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setText("Jumlah Pesanan");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setText("Tanggal Masuk");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel9.setText("Tanggal Jadi");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setText("Total Bayar");

        txtIdTrx.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtIdTrx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdTrxActionPerformed(evt);
            }
        });

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtHP.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txtJumlah.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtJumlah.setText("1");
        txtJumlah.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtJumlahInputMethodTextChanged(evt);
            }
        });
        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });

        txtMasuk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMasuk.setText("YYYY-MM-DD");
        txtMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMasukMouseClicked(evt);
            }
        });

        txtJadi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtJadi.setText("YYYY-MM-DD");
        txtJadi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtJadiMouseClicked(evt);
            }
        });
        txtJadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJadiActionPerformed(evt);
            }
        });
        txtJadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJadiKeyPressed(evt);
            }
        });

        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cmboxJenis.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmboxJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih satu --", "Kaos", "Kaos kemeja", "Hoodie", "Masker", "Bendera", "Scarf", "Tottebag", "Plastik" }));
        cmboxJenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboxJenisActionPerformed(evt);
            }
        });

        cmboxBahan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmboxBahan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih satu --", "Plastisol", "Rubber", "Polyflex", "DTG" }));
        cmboxBahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboxBahanActionPerformed(evt);
            }
        });

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransaksi);

        bersihkan.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        bersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FormSablon/clear.png"))); // NOI18N
        bersihkan.setText("Bersihkan");
        bersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bersihkanActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FormSablon/simpan.png"))); // NOI18N
        jButton2.setText("Simpan");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        asd1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        asd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FormSablon/edit.png"))); // NOI18N
        asd1.setText("Update");
        asd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asd1ActionPerformed(evt);
            }
        });

        delete1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        delete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FormSablon/delete.png"))); // NOI18N
        delete1.setText("Delete");
        delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete1ActionPerformed(evt);
            }
        });

        keluar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FormSablon/keluar.png"))); // NOI18N
        keluar.setText("Keluar");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FormSablon/calc.png"))); // NOI18N
        jButton1.setText("Hitung");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtNama))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtHP)
                                                    .addComponent(cmboxJenis, 0, 146, Short.MAX_VALUE)
                                                    .addComponent(cmboxBahan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(asd1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(delete1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMasuk)
                                        .addComponent(txtJadi)
                                        .addComponent(txtTotal)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButton1)))
                                    .addComponent(bersihkan, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(191, 191, 191)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdTrx, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(282, 282, 282)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdTrx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(txtHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(txtJadi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmboxJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmboxBahan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(asd1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bersihkan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(694, 612));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmboxJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboxJenisActionPerformed
        loadJenis();
    }//GEN-LAST:event_cmboxJenisActionPerformed

    private void cmboxBahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboxBahanActionPerformed
        loadBahan();
    }//GEN-LAST:event_cmboxBahanActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        new FormMenu().setVisible(true);
        dispose();
    }//GEN-LAST:event_keluarActionPerformed

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed

        
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void txtJumlahInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtJumlahInputMethodTextChanged
        
    }//GEN-LAST:event_txtJumlahInputMethodTextChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jumlahPesanan = Integer.valueOf(txtJumlah.getText());
        if(jumlahPesanan >= 12){
            JOptionPane.showMessageDialog(null, "Selamat, anda mendapat diskon 10%");
            xtotalBayar = (int)(biayaJenis  + biayaBahan);
            totalBayar = (int) (xtotalBayar * jumlahPesanan);
            diskon = (int) (totalBayar * 0.1);
            finBayar = (int) (totalBayar - diskon);
            txtTotal.setText(""+ finBayar);
        } else {
            xtotalBayar = (int)(biayaJenis  + biayaBahan);
            finBayar = (int) (xtotalBayar * jumlahPesanan);
            txtTotal.setText(""+ finBayar);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtIdTrxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTrxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTrxActionPerformed

    private void txtMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMasukMouseClicked
        txtMasuk.setText("");
        txtMasuk.setForeground(Color.black);
    }//GEN-LAST:event_txtMasukMouseClicked

    private void txtJadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJadiActionPerformed
        
    }//GEN-LAST:event_txtJadiActionPerformed

    private void txtJadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJadiKeyPressed
        
    }//GEN-LAST:event_txtJadiKeyPressed

    private void txtJadiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtJadiMouseClicked
        txtJadi.setText("");
        txtJadi.setForeground(Color.black);
    }//GEN-LAST:event_txtJadiMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        saveData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bersihkanActionPerformed
        reset();
    }//GEN-LAST:event_bersihkanActionPerformed

    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
        dataSelect();
    }//GEN-LAST:event_tblTransaksiMouseClicked

    private void asd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asd1ActionPerformed
        updateData();
    }//GEN-LAST:event_asd1ActionPerformed

    private void delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete1ActionPerformed
        deleteData();
    }//GEN-LAST:event_delete1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton asd1;
    private javax.swing.JButton bersihkan;
    private javax.swing.JComboBox<String> cmboxBahan;
    private javax.swing.JComboBox<String> cmboxJenis;
    private javax.swing.JButton delete1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton keluar;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtHP;
    private javax.swing.JTextField txtIdTrx;
    private javax.swing.JTextField txtJadi;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtMasuk;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
