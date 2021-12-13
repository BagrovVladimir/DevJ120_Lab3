
package devj120_lab3;


public class DevJ120_Lab3 {

    public static void main(String[] args) {
        
        DataConverter dc = new DataConverter();
        try {
            dc.toBinary("Origin.txt", "Binary.txt", "ASCII");
            dc.toText("Binary.txt", "Text.txt", "ASCII");
        } catch (ConverterException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
}
