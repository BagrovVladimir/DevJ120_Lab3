
package devj120_lab3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;


public class DataConverter implements IFileConverter{
    
    File file;  

    @Override
    public String toBinary(String inputFileName, String outputFileName, String charSet) throws ConverterException{
        String line = "";
        char[] buffer = new char[1024];
        
        try(FileReader fr = new FileReader(inputFileName)){
            int c;
            while((c=fr.read(buffer))!=-1){
                if (c<1024) {
                   buffer = Arrays.copyOf(buffer, c);
                }
                line += new String(buffer);
            }
        }catch(IOException io){System.out.println("Error: "+io.getMessage());}
        
//        System.out.println(line);
//        System.out.println(line.length());
        
        String s = " ";
        Charset ch = Charset.forName(charSet);
        byte[] bytes = line.getBytes(ch);
        String[] binary = new String[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            binary[i]=String.format("%8s", Integer.toBinaryString(bytes[i])).replace(" ", "0");
        s = s + " "+ binary[i];
        }

        //System.out.println(s);
        
        file = new File(outputFileName);
            if(!file.exists()) try {
                file.createNewFile();
        } catch (IOException io) {
            System.out.println("Error: "+io.getMessage());
        }
            
        try(FileWriter fw = new FileWriter(outputFileName, false)){
            fw.write(s);
        }catch(IOException io){System.out.println("Error: "+io.getMessage());}

        return outputFileName;
    }

    @Override
    public String toText(String inputFileName, String outputFileName, String charSet) throws ConverterException{
        String line = "";
        char[] buffer = new char[1024];
        
        try(FileReader fr = new FileReader(inputFileName)){
            int c;
            while((c=fr.read(buffer))!=-1){
                if (c<1024) {
                   buffer = Arrays.copyOf(buffer, c);
                }
                line += new String(buffer);
            }   
        }catch(IOException io){System.out.println("Error: "+io.getMessage());}
        
        line=line.trim();
//        System.out.println(line);
//        System.out.println(line.length());
        String [] sS = line.split(" ");
//        System.out.println("split size = "+sS.length);
        
        byte [] textBytes = new byte[sS.length];
        for (int i = 0; i < textBytes.length; i++) {
            textBytes[i]=Byte.parseByte(sS[i], 2);  
        }
        
        Charset ch = Charset.forName(charSet);
        String rez = new String(textBytes, ch);
        
        file = new File(outputFileName);
            if(!file.exists()) try {
                file.createNewFile();
        } catch (IOException io) {
            System.out.println("Error: "+io.getMessage());
        }
            
        try(FileWriter fw = new FileWriter(outputFileName, false)){
            fw.write(rez);
        }catch(IOException io){System.out.println("Error: "+io.getMessage());}
       return outputFileName;   
    }
}
