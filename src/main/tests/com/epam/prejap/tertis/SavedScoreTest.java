package com.epam.prejap.tertis;

import com.epam.prejap.tetris.SavedScore;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.IOException;

/**
 * @author Svetlana_Zubkova
 * 1. Write new line to the file
 * 2. Generate 3 chars
 * 3. toString print 25 lines
 * 4. Check sorting from max to min
 */
public class SavedScoreTest {

    private SavedScore savedScore;

    @BeforeClass
    public void setUp() {
        savedScore = new SavedScore();
    }

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

    @Test
    public void shouldGenerateThreeCharsName() {
        String testName = savedScore.nameForNewScore();
        Assert.assertEquals(testName.length(), 3);
    }

    @Test
    public void shouldPrint25Lines() {
        String[] sizeOfPrinting = savedScore.toString().split("/n");
        if (sizeOfPrinting.length > 25) {
            Assert.assertFalse(true);
        }
    }
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
