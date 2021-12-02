package parser;

import day2.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdventParser {

    private File f;
    private Scanner s;

    public AdventParser(String fileName) {
        try {

            f = new File(fileName);
            s = new Scanner(f);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> day1() {

        List<Integer> l = new ArrayList<>();

        while (s.hasNextLine()) {
            l.add(Integer.parseInt(s.nextLine()));
        }

        return l;
    }

    public List<Command> day2() {

        List<Command> l = new ArrayList<>();

        while (s.hasNextLine()) {

            String[] tmp = s.nextLine().split("\\s+");

            l.add(new Command(
                    tmp[0],
                    Integer.parseInt(tmp[1])
            ));
        }

        return l;
    }

}
