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
import java.util.*;
import java.sql.*;
public class Pemesan {
    private int id_pemesan;
    private String nama, jenis_kelamin, alamat, email, no_hp, check_in, check_out;
    private Kamar kamar = new Kamar();

    public Pemesan() {
    }

    public Pemesan(String nama, String jenis_kelamin, String alamat, String email, String no_hp, Kamar kamar, String check_in, String check_out) {
        this.id_pemesan = id_pemesan;
        this.nama = nama;
        this.jenis_kelamin = jenis_kelamin;
        this.alamat = alamat;
        this.email = email;
        this.no_hp = no_hp;
        this.kamar = kamar;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public int getId_pemesan() {
        return id_pemesan;
    }

    public void setId_pemesan(int id_pemesan) {
        this.id_pemesan = id_pemesan;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public Kamar getKamar() {
        return kamar;
    }

    public void setKamar(Kamar kamar) {
        this.kamar = kamar;
    }
            
    public Pemesan getById(int id){
        Pemesan pemesan = new Pemesan();
        ResultSet rs = DBHelper.selectQuery("Select "
                + "p.id_pemesan as id_pemesan, "
                + "p.nama as nama, "
                + "p.jenis_kelamin as jenis_kelamin, "
                + "p.alamat as alamat, "
                + "p.email as email,"
                + "p.no_hp as no_hp,"
                + "p.check_in as check_in,"
                + "p.check_out as check_out,"
                + "k.id_kamar as id_kamar, "
                + "k.lantai as lantai, "
                + "k.tipe as tipe, "
                + "k.status as status, "
                + "k.keterangan as keterangan "
                + "from pemesan p left join kamar k on p.id_kamar = k.id_kamar where p.id_pemesan = '" + id + "'");
        try{
            while(rs.next()){
                pemesan = new Pemesan();
                pemesan.setId_pemesan(rs.getInt("id_pemesan"));
                pemesan.setNama(rs.getString("nama"));
                pemesan.setJenis_kelamin(rs.getString("jenis_kelamin"));
                pemesan.setAlamat(rs.getString("alamat"));
                pemesan.setEmail(rs.getString("email"));
                pemesan.setNo_hp(rs.getString("no_hp"));
                pemesan.getKamar().setId_kamar(rs.getInt("id_kamar"));
                pemesan.getKamar().setLantai(rs.getInt("lantai"));
                pemesan.getKamar().setTipe(rs.getString("tipe"));
                pemesan.getKamar().setKeterangan(rs.getString("keterangan"));
                pemesan.getKamar().setStatus(rs.getString("status"));
                pemesan.setCheck_in(rs.getString("check_in"));
                pemesan.setCheck_out(rs.getString("check_out"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return pemesan;
    }
    
    public ArrayList<Pemesan> getAll(){
        ArrayList<Pemesan> ListPemesan = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("Select "
                + "p.id_pemesan as id_pemesan, "
                + "p.nama as nama, "
                + "p.jenis_kelamin as jenis_kelamin, "
                + "p.alamat as alamat, "
                + "p.email as email,"
                + "p.no_hp as no_hp,"
                + "k.id_kamar as id_kamar, "
                + "p.check_in as check_in,"
                + "p.check_out as check_out,"
                + "k.lantai as lantai, "
                + "k.tipe as tipe, "
                + "k.status as status, "
                + "k.keterangan as keterangan "
                + "from pemesan p left join kamar k on p.id_kamar = k.id_kamar");
        try{
            while(rs.next()){
                Pemesan pemesan = new Pemesan();
                pemesan.setId_pemesan(rs.getInt("id_pemesan"));
                pemesan.setNama(rs.getString("nama"));
                pemesan.setJenis_kelamin(rs.getString("jenis_kelamin"));
                pemesan.setAlamat(rs.getString("alamat"));
                pemesan.setEmail(rs.getString("email"));
                pemesan.setNo_hp(rs.getString("no_hp"));
                pemesan.getKamar().setId_kamar(rs.getInt("id_kamar"));
                pemesan.getKamar().setLantai(rs.getInt("lantai"));
                pemesan.getKamar().setTipe(rs.getString("tipe"));
                pemesan.getKamar().setKeterangan(rs.getString("keterangan"));
                pemesan.getKamar().setStatus(rs.getString("status"));
                pemesan.setCheck_in(rs.getString("check_in"));
                pemesan.setCheck_out(rs.getString("check_out"));
                
                ListPemesan.add(pemesan);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ListPemesan;
    }
    
    public ArrayList<Pemesan> search(String keyword){
        ArrayList<Pemesan> ListPemesan = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("Select b.id_pemesan as id_pemesan, "
                + "p.nama as nama, "
                + "p.jenis_kelamin as jenis_kelamin, "
                + "p.alamat as alamat, "
                + "p.email as email,"
                + "p.no_hp as no_hp,"
                + "p.check_in as check_in,"
                + "p.check_out as check_out,"
                + "k.id_kamar as id_kamar, "
                + "k.lantai as lantai, "
                + "k.tipe as tipe, "
                + "k.status as status, "
                + "k.keterangan as keterangan "
                + "from pemesan p left join kamar k on p.id_kamar = k.id_kamar "
                + "where p.nama like '%" + keyword + "%' or p.jenis_kelamin like '%" + keyword + "%'"
                        + " or p.alamat like '%" + keyword + "%' or p.email like '%" + keyword + "%'"
                        + " or p.no_hp like '%" + keyword + "%' or p.check_in like '%" + keyword + "%'"
                        + " or p.check_out like '%" + keyword + "%'" );
        try{
            while(rs.next()){
                Pemesan pemesan = new Pemesan();
                pemesan.setId_pemesan(rs.getInt("id_pemesan"));
                pemesan.setNama(rs.getString("nama"));
                pemesan.setJenis_kelamin(rs.getString("jenis_kelamin"));
                pemesan.setAlamat(rs.getString("alamat"));
                pemesan.setEmail(rs.getString("email"));
                pemesan.setNo_hp(rs.getString("no_hp"));
                pemesan.getKamar().setId_kamar(rs.getInt("id_kamar"));
                pemesan.getKamar().setLantai(rs.getInt("lantai"));
                pemesan.getKamar().setTipe(rs.getString("tipe"));
                pemesan.getKamar().setKeterangan(rs.getString("keterangan"));
                pemesan.getKamar().setStatus(rs.getString("status"));
                pemesan.setCheck_in(rs.getString("check_in"));
                pemesan.setCheck_out(rs.getString("check_out"));
                
                ListPemesan.add(pemesan);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ListPemesan;
    }
    
    public void save(){
        if(getById(id_pemesan).getId_pemesan() == 0){
            String sql = "Insert into pemesan (nama, jenis_kelamin, alamat, email, no_hp, id_kamar, check_in, check_out) "
                    + "values ("
                    + "'" + this.nama + "', "
                    + "'" + this.jenis_kelamin + "', "
                    + "'" + this.alamat + "', "
                    + "'" + this.email + "', "
                    + "'" + this.no_hp + "', "
                    + "'" + this.getKamar().getId_kamar() + "',"
                    + "'" + this.check_in + "', "
                    + "'" +this.check_out + "' "
                    + ")";
            this.id_pemesan = DBHelper.insertQueryGetId(sql);
        }else{
            String sql = "Update pemesan set "
                    + "'" + this.nama + "', "
                    + "'" + this.jenis_kelamin + "', "
                    + "'" + this.alamat + "', "
                    + "'" + this.email + "', "
                    + "'" + this.no_hp + "', "
                    + "'" + this.getKamar().getId_kamar() + "',"
                    + "'" + this.check_in + "', "
                    + "'" +this.check_out + "' "
                    + "where id_pemesan = '" + this.id_pemesan + "'";
            DBHelper.executeQuery(sql);
        }
    }
    
    public void delete(){
        String sql = "Delete from pemesan where id_pemesan = '" + this.id_pemesan + "'";
        DBHelper.executeQuery(sql);
    }
}
