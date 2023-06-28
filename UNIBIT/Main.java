import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static List<int[]> findPairs(int[] array, int target) {
        Map<Integer, Boolean> complements = new HashMap<>();
        List<int[]> pairs = new ArrayList<>();

        for (int num : array) {
            int comp = target - num;
            if (complements.containsKey(comp)) {
                pairs.add(new int[]{num, comp});
            }
            complements.put(num, true);
        }

        return pairs;
    }

    public static int[] mergeAndSort(List<int[]> pairs) {
        List<Integer> mergedArray = new ArrayList<>();
        for (int[] pair : pairs) {
            for (int num : pair) {
                mergedArray.add(num);
            }
        }

        int[] sortedArray = new int[mergedArray.size()];
        for (int i = 0; i < mergedArray.size(); i++) {
            sortedArray[i] = mergedArray.get(i);
        }
        Arrays.sort(sortedArray);

        return sortedArray;
    }

    public static List<int[]> findCombinations(int target, int[] array) {
        List<int[]> combinations = new ArrayList<>();

        findCombinationsHelper(target, new ArrayList<>(), 0, array, combinations);

        return combinations;
    }

    private static void findCombinationsHelper(int target, List<Integer> currCombination, int startIdx, int[] array, List<int[]> combinations) {
        if (target == 0) {
            int[] combinationArray = new int[currCombination.size()];
            for (int i = 0; i < currCombination.size(); i++) {
                combinationArray[i] = currCombination.get(i);
            }
            combinations.add(combinationArray);
            return;
        }

        if (target < 0 || startIdx == array.length) {
            return;
        }

        for (int i = startIdx; i < array.length; i++) {
            currCombination.add(array[i]);
            findCombinationsHelper(target - array[i], currCombination, i + 1, array, combinations);
            currCombination.remove(currCombination.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] inputArray = {1, 3, 2, 2, -4, -6, -2, 8};
        int targetValue = 4;

        List<int[]> pairs = findPairs(inputArray, targetValue);
        System.out.println("First Combination for " + targetValue + ": " + Arrays.deepToString(pairs));

        int[] mergedArray = mergeAndSort(pairs);
        System.out.println("Merge into a single array: " + Arrays.toString(mergedArray));

        List<int[]> combinations = findCombinations(targetValue * 2, mergedArray);
        System.out.println("Second Combination for " + (targetValue * 2) + ": " + Arrays.deepToString(combinations));
    }
}
