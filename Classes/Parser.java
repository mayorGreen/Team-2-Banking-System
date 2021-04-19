package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

// class for parsing files with a comma delimeter

public class Parser implements Runnable{

    // create a list that contains lists of strings
    List<List<String>> records = new ArrayList<>();

    // filename
    String file = "";

    // Parser constructor, accepts filename as input
    public Parser(String fileLocation){
        file = fileLocation;
        run();
    }

    public List<List<String>> getRecords(){return records;}
    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} //  end Parser
    
