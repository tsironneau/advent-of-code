package y2021.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day6_1 {

    public static void main(String[] args) {
        String allFishes = Input.INPUT.lines().toList().get(0);
        List<Fish> fishes = Arrays.stream(allFishes.split(",")).map(Integer::parseInt)
                .map(Fish::new)
                .collect(Collectors.toList());

        for (int i = 0; i < 80; i++) {
            List<Fish> toAdd = new ArrayList<>();
            for (Fish fish : fishes) {
                if (fish.nbDays == 0) {
                    toAdd.add(new Fish(8));
                    fish.setNbDays(6);
                } else {
                    fish.setNbDays(fish.nbDays - 1);
                }
            }
            fishes.addAll(toAdd);
        }

        System.out.println(fishes.size());
    }

    static final class Fish {
        private int nbDays;

        Fish(int nbDays) {
            this.nbDays = nbDays;
        }

        public int nbDays() {
            return nbDays;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Fish) obj;
            return this.nbDays == that.nbDays;
        }

        public void setNbDays(int nbDays) {
            this.nbDays = nbDays;
        }

        @Override
        public int hashCode() {
            return Objects.hash(nbDays);
        }

        @Override
        public String toString() {
            return "Fish[" +
                    "nbDays=" + nbDays + ']';
        }

    }


}
