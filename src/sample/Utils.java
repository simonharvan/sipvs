package sample;

import org.apache.commons.io.FileUtils;

import java.io.*;

public class Utils {

    static public String readResource(String name) throws IOException {
        InputStream is = getResourceAsStream(name);
        byte[] data = new byte[is.available()];
        is.read(data);
        is.close();
        return new String(data, "UTF-8");
    }
    static public InputStream getResourceAsStream(String name) {
        File path = new File(name);
        try {
            InputStream is = new FileInputStream(path);
            return is;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Nepodarilo sa otvorit zdroj: " + path);
        }
    }

    public static void saveFile(String fileName, String data) throws IOException {
        FileUtils.writeStringToFile(new File(fileName), data);
    }
}
