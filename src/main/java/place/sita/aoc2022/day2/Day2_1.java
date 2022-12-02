package place.sita.aoc2022.day2;

import pl.ksitarski.quickparse.QuickParse;
import place.sita.aoc2022.utils.CollectionUtils;
import place.sita.aoc2022.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day2_1 {

    public static void main(String[] args) {

        int sum = Utils.readFile("day2.txt")
            .stream()
            .map(string -> {
                RockPaperScissorsHolder rockPaperScissorsHolder =
                    QuickParse.parseToObject("${played}$ ${response}$", string, new RockPaperScissorsHolder(), RockPaperScissorsHolder.class);
                int scoreForResponse = pointsForType(rockPaperScissorsHolder.response);
                int scoreForResult = pointsForResult(rockPaperScissorsHolder.played, rockPaperScissorsHolder.response);
                return scoreForResult + scoreForResponse;
            })
            .reduce(Integer::sum)
                .get();

        System.out.println(sum);
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
