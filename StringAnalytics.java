import java.util.*;

/**
 * Class of operations on ordered lists of strings.
 * You must not change the signatures of the methods supplied. 
 * You must not change the signatures of the methods supplied. 
 * You must not change the signatures of the methods supplied. 
 */
public class StringAnalytics {

    /**
     * Returns the shortest string in the list. If there are several
     * strings of the same shortest length, the one that occurs earliest
     * is returned.
     * @param a list of strings, in ascending order
     * @return the shortest string in the list.
     */
    public String shortestWord(StringList a) {
        String shortestWord = a.get(0);

        // Invariant: loop counter (i) is less than the size of the list (a)
        for (int i = 0; i < a.size(); i++) {
            String currentWord = a.get(i);

            if (shortestWord.length() > currentWord.length()) {
               shortestWord = currentWord;
            }
        }

        return shortestWord;
    }


    /**
     * Returns the number of unique elements in the list
     * @param a list of strings, in ascending order
     * @return number of unique elements in the list.
     */
    public int countUnique(StringList a) {
        int uniqueCount = 1;
        String previousString = a.get(0);

        // Invariant: loop counter (i) is less than the size of the list (a)
        for (int i = 1; i < a.size(); i++) {
            String currentString = a.get(i);

            if (!currentString.equals(previousString)) {
                uniqueCount++;
                previousString = currentString;
            }
        }

        return uniqueCount;
    }
	

    /**
     * Returns the number of palindrome strings in the list.
     * @param a collection of strings, in ascending order
     * @return number of palindrome strings in the list. 
     */
    public int countPalindrome(StringList a) {
        int palindromeCount = 0;

        // Invariant: loop counter (i) is less than the size of the list (a)
        for (int i = 0; i < a.size(); i++) {
            String stringCheck = a.get(i);
            boolean palindrome = true;

            // Invariant: loop counter (j) is less than half of the length of the string to check (stringCheck)
            for (int j = 0; j < stringCheck.length() / 2; j++) {
                if (stringCheck.charAt(j) != stringCheck.charAt(stringCheck.length() - j - 1)) {
                    palindrome = false;
                    break;
                }
            }

            if (palindrome) {
                palindromeCount++;
            }
        }
        return palindromeCount;
    }

    /**
     * Search for a string in an ordered collection
     * @param a collection of strings, in ascending order
     * @return element that occurs the least frequent in the list. If two or more are equally least frequent, return the one that comes earliest
     */
    public String leastFrequent (StringList a) {
        TreeMap<String, Integer> leastFrequent = new TreeMap<>();

        // Invariant: loop counter (i) is less than the size of the list (a)
        for (int i = 0; i < a.size(); i++) {
            String currentString = a.get(i);
            leastFrequent.put(currentString, leastFrequent.getOrDefault(currentString,0) + 1);
        }

        String leastElement = leastFrequent.firstKey();
        int lowestValue = leastFrequent.get(leastElement);

        // Invariant: loop has not iterated over every element in the set (leastFrequent.keySet()) and an element of length 1 has not been found
        for (String i: leastFrequent.keySet()) {
            int currentValue = leastFrequent.get(i);

            if (currentValue < lowestValue) {
                lowestValue = currentValue;
                leastElement = i;
            } if (lowestValue == 1) {
                return leastElement;
            }
        }

        return leastElement;
    }

    /**
     * Search for a string in an ordered collection
     * @param a collection of strings, in ascending order
     * @param str string to search for
     * @return number of strings less than k in order
     */
    public int countLess(StringList a, String str) {
        int low = 0;
        int high = a.size() - 1;

        // Invariant: the list's upper pointer (high) is equal or greater than the list's lower pointer (low) and the string to search for has not been found
        while (low <= high) {
            int mid = (low + high) / 2;

            if (a.get(mid).compareTo(str) == 0) {
                return mid;
            } else if (a.get(mid).compareTo(str) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Search for a string in an ordered collection
     * @param a collection of strings, in ascending order
     * @param k integer - top k
     * @return k most frequent elements in the list
     */
    public String [] topKFrequent (StringList a, int k) {
        HashMap<String, Integer> mostFrequent = new HashMap<>();
        ArrayList<String> topK = new ArrayList<>();

        // Invariant: loop counter (i) is less than the size of the list (a)
        for (int i = 0; i < a.size(); i++) {
            String currentString = a.get(i);
            mostFrequent.put(currentString, mostFrequent.getOrDefault(currentString,0) + 1);
        }

        int maxFrequency = Collections.max(mostFrequent.values());

        // Invariant: the size of the ArrayList is less or equal to the number of sought after most frequent values (k)
        while (topK.size() < k) {
            // Invariant: loop has not iterated over every element in the set (mostFrequent.keySet()) and the size of the ArrayList does not equal the number of sought after most frequent values (k)
            for (String i : mostFrequent.keySet()) {
                if (mostFrequent.get(i) == maxFrequency) {
                    topK.add(i);
                } if (topK.size() == k) {
                    break;
                }
            }
            maxFrequency--;
        }

        return topK.toArray(new String[topK.size()]);
    }
	

    /**
     * Search for a string in an ordered collection
     * @param a collection of strings, in ascending order
     * @param str string to search for
     * @return number of strings greater than or equal to k in order
     */
    public int countGreaterOrEqual(StringList a, String str) {
        int low = 0;
        int high = a.size() - 1;

        // Invariant: the list's upper pointer (high) is equal or greater than the list's lower pointer (low) and the string to search for has not been found
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a.get(mid).compareTo(str) == 0) {
                return a.size() - 1 - mid;
            } else if (a.get(mid).compareTo(str) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return 0;
    }

    /**
     * Search for a string in an ordered collection
     * @param a - collection of strings, in ascending order
     * @param prefix - prefix string
     * @return number of strings having prefix in collection of strings
     */
    public int countPrefix(StringList a, String prefix) {
        int prefixCount = 0;

        // Invariant: loop counter (i) is less than the size of the list (a)
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).startsWith(prefix)) {
                prefixCount++;
            }
        }
        return prefixCount;
    }

    /**
     * Count number of unmatches in an ordered collection
     * @param a collection of strings, in ascending order
     * @param prefix first string to search for
     * @param substring second string to search for
     * @return number of elements that neither start with the specified prefix nor contain the given substring
     */
    public int countUnMatches(StringList a, String prefix, String substring) {
        int unmatchesCount = 0;

        // Invariant: loop counter (i) is less than the size of the list (a)
        for (int i = 0; i < a.size(); i++) {
            String currentString = a.get(i);

            if (!(currentString.startsWith(prefix) || currentString.contains(substring))) {
                unmatchesCount++;
            }
        }

        return unmatchesCount;
    }

}
