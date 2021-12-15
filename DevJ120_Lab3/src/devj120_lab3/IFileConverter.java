
package devj120_lab3;

public interface IFileConverter {
    
    String toBinary(String inputFileName, String outputFileName, String charSet)throws ConverterException;
    String toText(String inputFileName, String outputFileName, String charSet) throws ConverterException;
}
