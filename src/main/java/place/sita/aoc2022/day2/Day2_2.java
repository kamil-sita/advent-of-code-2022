package place.sita.aoc2022.day2;

import pl.ksitarski.quickparse.QuickParse;
import place.sita.aoc2022.utils.CollectionUtils;
import place.sita.aoc2022.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day2_2 {

    public static void main(String[] args) {

        int sum = Utils.readFile("day2.txt")
            .stream()
            .map(string -> {
                RockPaperScissorsHolder rockPaperScissorsHolder =
                    QuickParse.parseToObject("${played}$ ${response}$", string, new RockPaperScissorsHolder(), RockPaperScissorsHolder.class);
                String played = rockPaperScissorsHolder.played;
                String outcome = rockPaperScissorsHolder.response;
                String response = getShapeForExpectedResult(played, outcome);
                int scoreForResponse = pointsForType(response);
                int scoreForResult = pointsForResult(played, response);
                return scoreForResult + scoreForResponse;
            })
            .reduce(Integer::sum)
            .get();

        System.out.println(sum);
    }

    public static String getShapeForExpectedResult(String played, String outcome) {
        switch (outcome) {
            case "X":
                return losingShape(played);
            case "Y":
                return tieShape(played);
            case "Z":
                return winningShape(played);
        }
        throw new RuntimeException("Unrecognized type: " + played);
    }

    public static String winningShape(String played) {
        switch (played) {
            case "A":
                return "Y";
            case "B":
                return "Z";
            case "C":
                return "X";
        }
        throw new RuntimeException("Unrecognized type: " + played);
    }

    public static String tieShape(String played) {
        switch (played) {
            case "A":
                return "X";
            case "B":
                return "Y";
            case "C":
                return "Z";
        }
        throw new RuntimeException("Unrecognized type: " + played);
    }

    public static String losingShape(String played) {
        switch (played) {
            case "A":
                return "Z";
            case "B":
                return "X";
            case "C":
                return "Y";
        }
        throw new RuntimeException("Unrecognized type: " + played);
    }

    private static int pointsForResult(String played, String response) {
        if (played.equals("A") && response.equals("X")) {
            return 3;
        }
        if (played.equals("B") && response.equals("Y")) {
            return 3;
        }
        if (played.equals("C") && response.equals("Z")) {
            return 3;
        }

        if (played.equals("A") && response.equals("Y")) {
            return 6;
        }

        if (played.equals("B") && response.equals("Z")) {
            return 6;
        }

        if (played.equals("C") && response.equals("X")) {
            return 6;
        }

        return 0;
    }

    private  static int pointsForType(String type) {
        switch (type) {
            case "X":
                return 1;
            case "Y":
                return 2;
            case "Z":
                return 3;
        }
        throw new RuntimeException("Unrecognized type: " + type);
    }

    private static class RockPaperScissorsHolder {
        private String played;
        private String response;
    }

}
