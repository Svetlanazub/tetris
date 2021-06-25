package com.epam.prejap.tetris;

import com.epam.prejap.tetris.SavedScore;
//import com.epam.prejap.tetris.SavedScoreIOUtility;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


/**
 * These tests check:
 * <p><ul>
 * <li>1. Writing a new line to the file
 * <li>2. Generating 3 chars
 * <li>3. toString method for printing 25 lines
 * <li>4. sorting scores from max to min
 * </ul><p>
 *
 * @author Svetlana_Zubkova
 */
public class SavedScoreTest {
    private SavedScore savedScore;
    private String filePath;


    @BeforeClass(groups = {"IntegrationSavedScoreTests"})
    public void setUp() {
        filePath = "resources/test_score_list.json";
        savedScore = new SavedScore(filePath);
    }

    @Test(groups = {"IntegrationSavedScoreTests"})
    public void shouldAddStringToFile() {
        int score = 25000;
        savedScore.writeSavedScore(score);
        savedScore = new SavedScore(filePath);
        String test = savedScore.toString();
        assertTrue(test.contains("25000"));
    }

    @Test(groups = {"SavedScoreTests"})
    public void shouldGenerateThreeCharsName() {
        String testName = SavedScore.generateName();
        assertEquals(testName.length(), 3);
    }

    @Test(groups = {"IntegrationSavedScoreTests"})
    public void shouldPrint25Lines() {
        String[] sizeOfPrinting = savedScore.toString().split("/n");
        if (sizeOfPrinting.length > 25) {
            fail();
        }
    }

    @Test(groups = {"IntegrationSavedScoreTests"})
    public void shouldSortFromMaxScore() {
        savedScore.writeSavedScore(100000, "BBB");
        savedScore = null;
        savedScore = new SavedScore(filePath);
        String test = savedScore.toString();
        assertTrue(test.startsWith("BBB 100000"));
    }

    @AfterClass(groups = {"IntegrationSavedScoreTests"})
    void tearDown() throws Exception {
        savedScore = null;
    }
}
