/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author LiliCantik
 */
import java.util.ArrayList;
import java.sql.*;
public class Kamar implements IKamar{
   private int id_kamar, lantai;
   private String status, keterangan, tipe;

    public Kamar() {
    }

    public Kamar(int lantai, String status, String keterangan, String tipe) {
        this.lantai = lantai;
        this.status = status;
        this.keterangan = keterangan;
        this.tipe = tipe;
    }

    public int getId_kamar() {
        return id_kamar;
    }

    public void setId_kamar(int id_kamar) {
        this.id_kamar = id_kamar;
    }

    public int getLantai() {
        return lantai;
    }

    public void setLantai(int lantai) {
        this.lantai = lantai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
    
    public void getById(){
        System.out.println("Mendapatkan by id ");
    }
    
    public Kamar getById(int id)
    {
        Kamar kam = new Kamar();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kamar" 
           + " WHERE id_kamar = '" + id + "'");
        try
    {
     while(rs.next())
        {
         kam = new Kamar();
         kam.setId_kamar(rs.getInt("id_kamar"));
         kam.setLantai(rs.getInt("lantai"));
         kam.setTipe(rs.getString("tipe"));
         kam.setKeterangan(rs.getString("keterangan"));
         kam.setStatus(rs.getString("status"));
        }
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    return kam;

    }

    public ArrayList<Kamar> getAll()
    {
        ArrayList<Kamar> ListKamar = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kamar");
        try
        {
            while(rs.next())
            {
                Kamar kam = new Kamar();
                kam.setId_kamar(rs.getInt("id_kamar"));
                kam.setLantai(rs.getInt("lantai"));
                kam.setTipe(rs.getString("tipe"));
                kam.setKeterangan(rs.getString("keterangan"));
                kam.setStatus(rs.getString("status"));

                ListKamar.add(kam);
            } 
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ListKamar;
    }
    public ArrayList<Kamar> search(String keyword)
    {
        ArrayList<Kamar> ListKamar = new ArrayList();

        String sql = "SELECT * FROM kamar WHERE "
                + " lantai LIKE '%" + keyword + "%'" 
                + " OR tipe LIKE '%" + keyword + "%' "
                + " OR keterangan LIKE '%" + keyword + "%' "
                + " OR status LIKE '%" + keyword + "%' ";

        ResultSet rs = DBHelper.selectQuery(sql);

        try
        {
            while(rs.next())
            {
                Kamar kam = new Kamar();
                kam.setId_kamar(rs.getInt("id_kamar"));
                kam.setLantai(rs.getInt("lantai"));
                kam.setTipe(rs.getString("tipe"));
                kam.setKeterangan(rs.getString("keterangan"));
                kam.setStatus(rs.getString("status"));

                ListKamar.add(kam);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ListKamar;
    }
    
    //interface
    @Override
    public void save()
    {
        if(getById(id_kamar).getId_kamar() == 0)
        {
            String SQL = "INSERT INTO kamar (lantai, tipe, keterangan, status) VALUES(" 
                    + "  '" + this.lantai + "', "
                    + "  '" + this.tipe + "', "
                    + "  '" + this.keterangan + "', "
                    + "  '" + this.status + "' "
                    + " )";
            this.id_kamar = DBHelper.insertQueryGetId(SQL);
        }   
        else{
            String SQL = "UPDATE kamar SET "
                    + " lantai = '" + this.lantai + "', "
                    + " tipe = '" + this.tipe + "', "
                    + " keterangan = '" + this.keterangan+ "',"
                    + " status = '" + this.status + "' "
                    + " WHERE id_kamar = '" + this.id_kamar + "' ";
            DBHelper.executeQuery(SQL);
        }
    }
    public void delete()
    {
        String SQL = "DELETE FROM kamar WHERE id_kamar = '" + this.id_kamar + "'";
        DBHelper.executeQuery(SQL);
    }
}
