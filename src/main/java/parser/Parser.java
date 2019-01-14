package parser;

import mainlogic.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.Scanner;

public class Parser {
    public static Player readPlayerInfo(String fileName) throws FileNotFoundException, ParseException {

        Scanner input = new Scanner(new FileReader(fileName));
        String indexNumber, alias, name, surname, lunchCommand;

        File playerFolder = new File(fileName).getParentFile();
        System.out.println(playerFolder.getAbsolutePath());
        indexNumber = playerFolder.getName(); //Get name of parent of info.txt file

        try {
            if (input.hasNext())
                alias = input.nextLine();
            else throw new ParseException("No info found", 0);

            if (input.hasNext()) {
                name = input.next();
                if (!name.matches("\\p{L}+")) throw new ParseException("Invalid player name", 1);
            } else throw new ParseException("No player name found", 1);

            if (input.hasNext()) {
                surname = input.next();
                if (!surname.matches("\\p{L}+")) throw new ParseException("Invalid player surname", 2);
            } else throw new ParseException("No player surname found", 2);

            //Finish line with name and surname
            if (input.hasNext()) {
                input.nextLine();
            } else throw new ParseException("New line after surname expected", 2);

            if (input.hasNext()) {
                lunchCommand = input.nextLine();
                if (lunchCommand.length() <= 0) throw new ParseException("No lunch command", 3);
            } else throw new ParseException("No lunch command", 3);

            //TODO: Set full lunch command


        } catch (ParseException e) {
            input.close(); //Close input and rethrow exception
            throw e;
        }

        input.close();
        return new Player(indexNumber, alias, name, surname, lunchCommand);
    }

    @Contract(pure = true)
    public static boolean isJavaProgram(@NotNull String lunchCommand) {
        return lunchCommand.matches("java -jar .*.jar");
    }

    @Contract(pure = true)
    public static boolean isExeProgram(@NotNull String lunchCommand) {
        return lunchCommand.matches(".*.exe");
    }
}
