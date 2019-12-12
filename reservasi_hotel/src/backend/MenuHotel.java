/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author LiliCantik
 */
public class MenuHotel extends Menu{
    private int no, harga;
    private String nama;

    public MenuHotel() {
    }

    public MenuHotel(int harga, String nama) {
        this.harga = harga;
        this.nama = nama;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public MenuHotel tampilById(int id)
    {
        MenuHotel menu = new MenuHotel();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM menu" 
           + " WHERE no = '" + id + "'");
        try
    {
     while(rs.next())
     {
         menu = new MenuHotel();
         menu.setNo(rs.getInt("no"));
         menu.setNama(rs.getString("nama"));
         menu.setHarga(rs.getInt("harga"));
     }
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    return menu;

    }

    @Override
    public ArrayList<MenuHotel> tampil()
    {
        ArrayList<MenuHotel> ListMenuHotel = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM menu");
        try
        {
            while(rs.next())
            {
                MenuHotel menu = new MenuHotel();
                menu.setNo(rs.getInt("no"));
                menu.setNama(rs.getString("nama"));
                menu.setHarga(rs.getInt("harga"));

                ListMenuHotel.add(menu);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ListMenuHotel;
    }
    public ArrayList<MenuHotel> search(String keyword)
    {
        ArrayList<MenuHotel> ListMenuHotel = new ArrayList();

        String sql = "SELECT * FROM menu WHERE "
                + " nama LIKE '%" + keyword + "%'" 
                + " OR harga LIKE '%" + keyword + "%' ";

        ResultSet rs = DBHelper.selectQuery(sql);

        try
        {
            while(rs.next())
            {
                MenuHotel menu = new MenuHotel();
                menu.setNo(rs.getInt("no"));
                menu.setNama(rs.getString("nama"));
                menu.setHarga(rs.getInt("harga"));

                ListMenuHotel.add(menu);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ListMenuHotel;
    }
    
    
    public void save()
    {
        if(tampilById(no).getNo() == 0)
        {
            String SQL = "INSERT INTO menu (nama, harga) VALUES(" 
                    + "  '" + this.nama + "', "
                    + "  '" + this.harga + "' "
                    + " )";
            this.no = DBHelper.insertQueryGetId(SQL);
        }   
        else{
            String SQL = "UPDATE menu SET "
                    + " nama = '" + this.nama + "', "
                    + " harga = '" + this.harga + "' "
                    + " WHERE no = '" + this.no + "' ";
            DBHelper.executeQuery(SQL);
        }
    }
    public void delete()
    {
        String SQL = "DELETE FROM menu WHERE no = '" + this.no + "'";
        DBHelper.executeQuery(SQL);
    }
    
}
