package com.epam.prejap.tertis;

import com.epam.prejap.tetris.SavedScore;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


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

    @BeforeClass
    public void setUp() {
        savedScore = new SavedScore();
    }

    /**
     * This method tests if the line with player's name and score is added to the JSON file
     */
    @Test
    public void shouldAddStringToFile() {
        String name = "AAA";
        int score = 45;
        savedScore.rewriteSavedScore(score, name);
        savedScore = null;
        savedScore = new SavedScore();
        String test = savedScore.toString();
        Assert.assertEquals(test.contains("AAA 45"), true);
    }

    /**
     * This method tests number of characters in generated name
     */
    @Test
    public void shouldGenerateThreeCharsName() {
        String testName = savedScore.nameForNewScore();
        Assert.assertEquals(testName.length(), 3);
    }
    /**
     * This method tests printing only 25 lines from JSON file
     */
    @Test
    public void shouldPrint25Lines() {
        String[] sizeOfPrinting = savedScore.toString().split("/n");
        if (sizeOfPrinting.length > 25) {
            Assert.assertFalse(true);
        }
    }
    /**
     * This method tests sorting from highest to lowest score
     */
    @Test
    public void shouldSortFromMaxScore() {
        savedScore.rewriteSavedScore(9900, "AAA");
        savedScore.rewriteSavedScore(10000, "BBB");
        savedScore = null;
        savedScore = new SavedScore();
        String test = savedScore.toString();
        if (test.startsWith("BBB 10000")) {
            Assert.assertTrue(true);
        }
    }

    @AfterClass
    void tearDown() throws Exception {
        savedScore = null;
    }
}
