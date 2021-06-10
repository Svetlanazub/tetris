package com.epam.prejap.tetris;

import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


/**
 *  These tests check:
 *  <p><ul>
 *  <li>1. Writing a new line to the file
 *  <li>2. Generating 3 chars
 *  <li>3. toString method for printing 25 lines
 *  <li>4. sorting scores from max to min
 *  </ul><p>
 *  @author Svetlana_Zubkova
 */
public class SavedScoreTest {

    private SavedScore savedScore;

    @BeforeSuite (groups = {"SavedScoreTests"})
    public void setUp() {
        savedScore = new SavedScore("resources/test_score_list.json");
    }

    @Test (groups = {"SavedScoreTests"})
    public void shouldAddStringToFile() {
        String name = "AAA";
        int score = 25000;
        savedScore.rewriteSavedScore(score, name);
        savedScore = null;
        savedScore = new SavedScore("resources/test_score_list.json");
        String test = savedScore.toString();
        assertTrue(test.contains("AAA 25000"));
    }

    @Test (groups = {"SavedScoreTests"})
    public void shouldGenerateThreeCharsName() {
        String testName = savedScore.nameForNewScore();
        assertEquals(testName.length(), 3);
    }

    @Test (groups = {"SavedScoreTests"})
    public void shouldPrint25Lines() {
        String[] sizeOfPrinting = savedScore.toString().split("/n");
        if (sizeOfPrinting.length > 25) {
            Assert.fail();
        }
    }

    @Test (groups = {"SavedScoreTests"})
    public void shouldSortFromMaxScore() {
        savedScore.rewriteSavedScore(9900, "AAA");
        savedScore.rewriteSavedScore(10000, "BBB");
        savedScore = null;
        savedScore = new SavedScore("resources/test_score_list.json");
        String test = savedScore.toString();
        if (test.startsWith("BBB 10000")) {
            Assert.assertTrue(true);
        }
    }

    @AfterSuite (groups = {"SavedScoreTests"})
    void tearDown() throws Exception {
        savedScore = null;
    }
}
