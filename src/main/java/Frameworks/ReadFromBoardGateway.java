package Frameworks;

import Use_Cases.ReadFromBoardGatewayBoundary;
import Use_Cases.ReadFromBoardResponseModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromBoardGateway implements ReadFromBoardGatewayBoundary {
    @Override
    public String readFromDatabase() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Frameworks/Leaderboard_File.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                return line;
            }
            // System.out.println(reader.readLine());
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "No Value Found!";
    }
}
