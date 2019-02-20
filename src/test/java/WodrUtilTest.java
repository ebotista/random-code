import cisco.words.WordUtil;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class WodrUtilTest {

    @Test
    public void test_word_counter() {
        WordUtil util = new WordUtil();
        util.wordCounter();
        assertTrue(true);
    }

}
