import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class randomAccessFiles {

    public static void main(String[] args) {
        String filepath = "product2.dat";
        String delimiter = "///!///";

        String dialog = readRandomAccessFile(filepath, 1, 4, 100, delimiter);
        System.out.println(dialog);
    }

    private static String readRandomAccessFile(String filepath, int lineStart, int lineEnd, int charsPerLine, String delimiter) {
        File file = new File(filepath);
        String data = "";

        ArrayList<String> dialogLinesRead = new ArrayList<String>();
        int bytesPerLine = charsPerLine + 2;

        try {
            RandomAccessFile RandomAccessFile = new RandomAccessFile(file, "rw");

            for (int i = lineStart; i <= lineEnd; i++) {
                RandomAccessFile.seek(bytesPerLine * i);
                data = RandomAccessFile.readLine();
                dialogLinesRead.add(data);
            }

            RandomAccessFile.close();
        }
        catch (Exception e) {
            System.out.println("Error occurred");
        }

        String returnData = "";

        for (int i = 0; i < dialogLinesRead.size(); i++) {
            returnData += dialogLinesRead.get(i);
            returnData += delimiter;
        }

        if (dialogLinesRead.isEmpty()) {
            data = "No data found";
        }

        return returnData;
    }
}
