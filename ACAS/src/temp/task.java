/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

/**
 *
 * @author irv
 */
public class task implements Runnable {

    private volatile boolean isRunning = true;
    
    @Override
    public void run() {
        
        while (isRunning) {
         System.err.println("#");
     }
        
    }
    
}
