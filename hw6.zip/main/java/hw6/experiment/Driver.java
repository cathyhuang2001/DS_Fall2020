package hw6.experiment;

import hw6.Map;
//import hw6.bst.AvlTreeMap;
import hw6.bst.BinarySearchTreeMap;
//import hw6.bst.TreapMap;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Read text from a text file, count how often each unique word appears.
 *
 * <p>Note that the definition of "word" is rather arbitrary
 * and won't make the linguists among you very happy.</p>
 */
public class Driver {

  // Update this to any other data file for benchmarking experiments.
  private static String getDataFile() {
    return "pride_and_prejudice.txt";
  }

  // Change the return statement to other implementations of Map.
  private static Map<String, Integer> createDataStorage() {
    return new BinarySearchTreeMap<>();
  }

  /**
   * Execution starts here.
   * @param args command-line arguments; not used here.
   * @throws FileNotFoundException if the data file does not exist.
   */
  public static void main(String[] args) throws FileNotFoundException {
    Map<String, Integer> wordFrequencyData = createDataStorage();

    Path resourceDirectory = getPath();
    Scanner sc = new Scanner(resourceDirectory.toFile());

    BasicProfiler.reset();
    BasicProfiler.start();

    int wordCount = 0;
    while (sc.hasNext()) {
      String word = sc.next();
      if (isWord(word)) {
        addWord(wordFrequencyData, word);
        wordCount++;
      }
    }

    String description = String.format("Processed %d words using %s",
        wordCount, wordFrequencyData.getClass().getName());
    BasicProfiler.stop();
    System.out.println(BasicProfiler.getStatistics(description));
  }

  private static Path getPath() {
    URL url = Thread.currentThread().getContextClassLoader().getResource("");
    String path = url.getPath().replace("%20", " ")
        .replace("classes", "resources");

    Path resourceDirectory = Paths.get(path, getDataFile());
    return resourceDirectory;
  }

  private static boolean isWord(String word) {
    // The regular expression splits strings on whitespace and
    //   non-word characters (anything except [a-zA-Z_0-9]). Far
    //   from perfect, but close enough for this simple program.
    // Skip "short" words, most of which just "dirty up" the statistics.
    return word.matches("[a-zA-Z0-9]+") && word.length() > 1;
  }

  // Add word and update frequency count.
  private static void addWord(Map<String, Integer> data, String word) {
    if (data.has(word)) {
      data.put(word, data.get(word) + 1);
    } else {
      data.insert(word, 1);
    }
  }
}
