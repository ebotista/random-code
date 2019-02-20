package cisco.words;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by edgarbautista on 3/10/17.
 */
public class WordUtil {

    public void wordCounter() {

        Map<String, Integer> counter = new HashMap();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("file/words.txt").getFile());

        try(Scanner scanner = new Scanner(file)) {

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\s+");

                for(String word : words) {
                    if(counter.containsKey(word)) {
                        Integer count = counter.get(word);
                        counter.put(word, ++count);
                    } else {
                        counter.put(word, new Integer(1));
                    }
                }
            }

            for(Map.Entry<String, Integer> word : counter.entrySet()) {
                System.out.println(word.getValue() +" "+ word.getKey());
            }

            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
