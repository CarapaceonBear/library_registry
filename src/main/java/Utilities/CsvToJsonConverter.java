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

    public CsvToJsonConverter(File originalFile, File newFile) {
        this.originalFile = originalFile;
        this.newFile = newFile;
    }

    public void convert() {
        if (createNewFile(newFile)) {
            String csv = readFileToString(originalFile);
            JSONArray jsonArray = createJsonArray(csv);
            writeToFile(newFile, jsonArray);
        } else {
            System.out.println("unable to create new file");
        }
    }

    private String readFileToString(File originalFile) {
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(originalFile);
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








//    public void convert() throws FileNotFoundException {
//        Scanner fileReader = new Scanner(originalFile);
//        boolean isFirstLine = true;
//        List<String> keys = new ArrayList<>();
//        JSONArray jsonArray = new JSONArray();
//
//        while (fileReader.hasNextLine()) {
//            JSONObject jsonObject = new JSONObject();
//            String data = fileReader.nextLine();
//            if (isFirstLine) {
//                String[] firstLine = data.split(",");
//                keys.addAll(Arrays.asList(firstLine));
//                isFirstLine = false;
//            } else {
//                String[] currentLine = data.split(",");
//                List<String> values = new ArrayList<>(Arrays.asList(currentLine));
//                for (int i = 0; i < values.size(); i++) {
//                    if (values.get(i).charAt(0) == '\"') {
//                        String combined = values.get(i) + ", " + values.get(i + 1);
//                        values.set(i, combined);
//                        values.remove(i + 1);
//                    }
//                }
//                for (int i = 0; i < values.size(); i++) {
//                    jsonObject.put(keys.get(i), values.get(i));
//                }
//                jsonArray.put(jsonObject);
//            }
//        }
//        fileReader.close();
//
//        for (Object object : jsonArray) {
//            System.out.println(object);
//        }
//
//        try {
//            newFile.createNewFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
