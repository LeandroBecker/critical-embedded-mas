/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package exemplo_gradle;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
	CommPortIdentifier portId = null;
	System.out.println("Funcionou...");
    }
}
