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

@Test (groups =  {"SavedScoreTests"})
public class SavedScoreTest {

    private SavedScore savedScore;
    private String filePath;

    @BeforeClass
    public void setUp() {
        filePath = "resources/test_score_list.json";
        savedScore = new SavedScore(filePath);
    }

    public void shouldAddStringToFile() {
        String name = "AAA";
        int score = 25000;
        savedScore.writeSavedScore(score);
        savedScore = new SavedScore(filePath);
        String test = savedScore.toString();
        assertTrue(test.contains("AAA 25000"));
    }

    public void shouldGenerateThreeCharsName() {
        String testName = SavedScoreIOUtility.generateName();
        assertEquals(testName.length(), 3);
    }

    public void shouldPrint25Lines() {
        String[] sizeOfPrinting = savedScore.toString().split("/n");
        if (sizeOfPrinting.length > 25) {
            Assert.fail();
        }
    }

    public void shouldSortFromMaxScore() {
        savedScore.writeSavedScore(9900, "AAA");
        savedScore.writeSavedScore(10000, "BBB");
        savedScore = new SavedScore(filePath);
        String test = savedScore.toString();
        if (test.startsWith("BBB 10000")) {
            Assert.assertTrue(true);
        }
        Assert.fail();
    }
}
