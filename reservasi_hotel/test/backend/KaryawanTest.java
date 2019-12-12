/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LiliCantik
 */
public class KaryawanTest extends TestCase{
    Karyawan instance;
    
    public KaryawanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Karyawan("Yaya", "Perempuan", "Malang", "OB");
        System.out.format("Start Testing : %s \n", this.getName());
    }
    
    @After
    public void tearDown() {
        System.out.format("Finish Test %s\n", this.getName());
    }

    @Test
    public void testSearch() {
        System.out.println("search test Karyawan");
        String keyword = "Yaya";
        ArrayList<Karyawan> result = instance.search(keyword);
        ArrayList<Karyawan> expResult = instance.getByNamaJKAlamatAndPosisi(keyword, "", "", "");
        assertEquals(expResult.size(), result.size());
    }

    @Test
    public void testSave() {
        System.out.println("save test Karyawan");
        this.instance.save();
        ArrayList<Karyawan> expResult = instance.getByNamaJKAlamatAndPosisi(instance.getNama(), instance.getJenis_kelamin(), instance.getAlamat(), instance.getPosisi());
        assertTrue(expResult.size()>0);
    }

   /* @Test
    public void testDelete() {
        System.out.println("delete test Karyawan");
        Karyawan instance = new Karyawan();
        instance.delete();
        fail("The test case is a prototype.");
    }*/
    
}
