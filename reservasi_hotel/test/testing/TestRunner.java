/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import backend.KaryawanTest;

/**
 *
 * @author LiliCantik
 */
public class TestRunner {
    public static void main(String[] args) {
        Result mResult = new JUnitCore().runClasses(KaryawanTest.class);
        showMessageResult(mResult, KaryawanTest.class.getSimpleName());
    }
  
    private static void showMessageResult(Result mresult, String className) {
        if(mresult.wasSuccessful()) {
            System.out.format("The Result Test From %s : %s\n", className, mresult.wasSuccessful());
        }else {
            for(Failure failure : mresult.getFailures()) {
                System.out.println(failure);
            }
        }
    }
}
