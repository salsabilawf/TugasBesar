/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author LiliCantik, 
 */
import java.util.ArrayList;
import java.sql.*;
public class Karyawan {
    private int id_karyawan;
    private String nama, jenis_kelamin, alamat, posisi;

    public Karyawan() {
    }

    public Karyawan(String nama, String jenis_kelamin, String alamat, String posisi) {
        this.nama = nama;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
        this.posisi = posisi;
    }

    public int getId_karyawan() {
        return id_karyawan;
    }

    public void setId_karyawan(int id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }
    
    public Karyawan getById(int id)
    {
        Karyawan kar = new Karyawan();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM karyawan" 
           + " WHERE id_karyawan = '" + id + "'");
        try
    {
     while(rs.next())
     {
         kar = new Karyawan();
         kar.setId_karyawan(rs.getInt("id_karyawan"));
         kar.setNama(rs.getString("nama"));
         kar.setJenis_kelamin(rs.getString("jenis_kelamin"));
         kar.setAlamat(rs.getString("alamat"));
         kar.setPosisi(rs.getString("posisi"));
     }
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    return kar;

    }

    public ArrayList<Karyawan> getAll()
    {
        ArrayList<Karyawan> ListKaryawan = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM karyawan");
        try
        {
            while(rs.next())
            {
                Karyawan kar = new Karyawan();
                kar = new Karyawan();
                kar.setId_karyawan(rs.getInt("id_karyawan"));
                kar.setNama(rs.getString("nama"));
                kar.setJenis_kelamin(rs.getString("jenis_kelamin"));
                kar.setAlamat(rs.getString("alamat"));
                kar.setPosisi(rs.getString("posisi"));

                ListKaryawan.add(kar);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ListKaryawan;
    }
    public ArrayList<Karyawan> search(String keyword)
    {
        ArrayList<Karyawan> ListKaryawan = new ArrayList();

        String sql = "SELECT * FROM karyawan WHERE "
                + " nama LIKE '%" + keyword + "%'" 
                + " OR jenis_kelamin LIKE '%" + keyword + "%' "
                + " OR alamat LIKE '%" + keyword + "%' "
                + " OR posisi LIKE '%" + keyword + "%' ";

        ResultSet rs = DBHelper.selectQuery(sql);

        try
        {
            while(rs.next())
            {
                Karyawan kar = new Karyawan();
                kar = new Karyawan();
                kar.setId_karyawan(rs.getInt("id_karyawan"));
                kar.setNama(rs.getString("nama"));
                kar.setJenis_kelamin(rs.getString("jenis_kelamin"));
                kar.setAlamat(rs.getString("alamat"));
                kar.setPosisi(rs.getString("posisi"));

                ListKaryawan.add(kar);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ListKaryawan;
    }
    public void save()
    {
        if(getById(id_karyawan).getId_karyawan() == 0)
        {
            String SQL = "INSERT INTO karyawan (nama, jenis_kelamin, alamat, posisi) VALUES(" 
                    + "  '" + this.nama + "', "
                    + "  '" + this.jenis_kelamin + "', "
                    + "  '" + this.alamat + "', "
                    + "  '" + this.posisi + "' "
                    + " )";
            this.id_karyawan = DBHelper.insertQueryGetId(SQL);
        }   
        else{
            String SQL = "UPDATE karyawan SET "
                    + " nama = '" + this.nama + "', "
                    + " jenis_kelamin = '" + this.jenis_kelamin + "', "
                    + " alamat = '" + this.alamat+ "',"
                    + " posisi = '" + this.posisi + "' "
                    + " WHERE id_karyawan = '" + this.id_karyawan + "' ";
            DBHelper.executeQuery(SQL);
        }
    }
    public void delete()
    {
        String SQL = "DELETE FROM karyawan WHERE id_karyawan = '" + this.id_karyawan + "'";
        DBHelper.executeQuery(SQL);
    }
    
    public ArrayList<Karyawan>getByNamaJKAlamatAndPosisi(String nama, String jenis_kelamin, String alamat, String posisi) {
        ArrayList<Karyawan> ListKategori = new ArrayList();
        ResultSet rs;
        
        if((nama.trim().length()>0) && (jenis_kelamin.trim().length()>0) && (alamat.trim().length()>0) && (posisi.trim().length()>0)) {
            rs = DBHelper.selectQuery("SELECT * FROM karyawan WHERE nama = '" + nama + "' AND jenis_kelamin = '" + jenis_kelamin + "' AND alamat = '"+ alamat + "' AND posisi = '"+ posisi +"'");
        }else if((nama.trim().length()>0) && (alamat.trim().length()>0) && (posisi.trim().length()==0)) {
            rs = DBHelper.selectQuery("SELECT * FROM karyawan WHERE nama = '" + nama + "' AND alamat = '"+ alamat + "'");
        }else if((nama.trim().length()>0) && (alamat.trim().length()==0) && (posisi.trim().length()>0)) {
            rs = DBHelper.selectQuery("SELECT * FROM karyawan WHERE nama = '" + nama + "' AND posisi = '"+ posisi + "'");
        }else if((nama.trim().length()==0) && (alamat.trim().length()>0) && (posisi.trim().length()>0)) {
            rs = DBHelper.selectQuery("SELECT * FROM karyawan WHERE alamat = '" + alamat + "' AND posisi = '"+ posisi + "'");
        }else if((nama.trim().length()>0) && (alamat.trim().length()==0) && (posisi.trim().length()==0)) {
            rs = DBHelper.selectQuery("SELECT * FROM karyawan WHERE nama = '" + nama + "'");
        }else if((nama.trim().length()==0) && (alamat.trim().length()>0) && (posisi.trim().length()==0)) {
            rs = DBHelper.selectQuery("SELECT * FROM karyawan WHERE alamat = '" + alamat + "'");
        }else if((nama.trim().length()==0) && (alamat.trim().length()>0) && (posisi.trim().length()==0)) {
            rs = DBHelper.selectQuery("SELECT * FROM karyawan WHERE jenis_kelamin = '" + jenis_kelamin + "'");
        }else if((nama.trim().length()==0) && (alamat.trim().length()>0) && (posisi.trim().length()==0)) {
            rs = DBHelper.selectQuery("SELECT * FROM karyawan WHERE posisi = '" + posisi + "'");
        }else {
            rs = DBHelper.selectQuery("SELECT * FROM kategori WHERE posisi = '" + posisi + "'");
        }
        
        try {
            while(rs.next()) {
                Karyawan kar = new Karyawan();
                kar.setId_karyawan(rs.getInt("id_karyawan"));
                kar.setNama(rs.getString("nama"));
                 kar.setJenis_kelamin(rs.getString("jenis_kelamin"));
                kar.setAlamat(rs.getString("alamat"));
                kar.setPosisi(rs.getString("posisi"));
                ListKategori.add(kar);
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ListKategori;
    }
}
