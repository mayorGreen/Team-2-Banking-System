package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// class for parsing files with a comma delimeter

public abstract class Parser{

    // this is the parser for reading csv files. It reads the file line by line,
    // splits the input into a comma delimited list of list of strings
    public static List<List<String>> getRecords(String fileLocation){
        // create a list that contains lists of strings
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }

    // this method writes any serializable object to a file
    public static <T> void writeObjectRecords(T input, String fileName){
        try(FileOutputStream fout = new FileOutputStream(new File(fileName))) {
            ObjectOutputStream oout = new ObjectOutputStream(fout);

            oout.writeObject(input);
            oout.close();
            System.out.println("Write to file complete");
        } catch (Exception e) {
            System.out.println("Error in writeObjectRecords");
            e.printStackTrace();
        }
    }

    // this method reads list objects from a file and returns a list of type T
    // where T is the stored object type
    public static <T> List<T> readObjectRecords(String fileName){
        ArrayList<T> readList = new ArrayList<>();
        try (FileInputStream fin = new FileInputStream(new File(fileName))){
            ObjectInputStream oin = new ObjectInputStream(fin);

            readList = (ArrayList)oin.readObject();
            oin.close();
        } catch (Exception e) {
            System.out.println("Error in readObjectRecords");
            e.printStackTrace();
        }
        return readList;
    }

} //  end Parser
    
