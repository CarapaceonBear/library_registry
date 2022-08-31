package Utilities;

import org.json.CDL;
import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CsvToJsonConverter {

    private final File originalFile;
    private final File newFile;
    private final File originalFileModified;

    public CsvToJsonConverter(File originalFile, File newFile, File originalFileModified) {
        this.originalFile = originalFile;
        this.newFile = newFile;
        this.originalFileModified = originalFileModified;
    }

    public void convert() {
        prepareCsvKeys(originalFile, originalFileModified);
        if (createNewFile(newFile)) {
            String csv = readFileToString(originalFileModified);
            JSONArray jsonArray = createJsonArray(csv);
            writeToFile(newFile, jsonArray);
        } else {
            System.out.println("unable to create new file");
        }
    }

    private String readFileToString(File file) {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String dataStream = "";
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            dataStream += data + "\n";
        }
        fileReader.close();
        return dataStream;
    }

    private JSONArray createJsonArray(String csv) {
        return CDL.toJSONArray(csv);
    }

    private boolean createNewFile(File newFile) {
        try {
            return newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void writeToFile(File newFile, JSONArray data ) {
        try {
            FileWriter fileWriter = new FileWriter(newFile);
            for (Object json : data) {
                fileWriter.write(json.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareCsvKeys(File file, File fileCopy) {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String dataStream = "";
        int count = 0;
        while (fileReader.hasNextLine()) {
            if (count == 0) {
                dataStream += "Number,Title,Author,Genre,SubGenre,Publisher,TimesLoaned,IsOnLoan\n";
                fileReader.nextLine();
                count++;
            } else {
                String data = fileReader.nextLine();
                dataStream += data + ",0,false\n";
            }
        }
        fileReader.close();
        try {
            FileWriter fileWriter = new FileWriter(fileCopy);
            fileWriter.write(dataStream);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
