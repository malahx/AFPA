/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterclimind;

import java.util.regex.Pattern;
import model.MasterMind;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gwenole
 */
public class MasterCLIMindTest {

    public MasterCLIMindTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of MasterMind object.
     */
    @Test
    public void testMasterMind() {
        MasterMind masterMind = new MasterMind("1234");
        assertEquals(masterMind.display("1234"), Color.GREEN + "****" + Color.RED);
        assertEquals(masterMind.display("1111"), Color.GREEN + "*" + Color.RED);
        assertEquals(masterMind.display("4321"), Color.GREEN + Color.RED + "****");
        assertEquals(masterMind.getSoluce(), "1234");
        assertEquals(masterMind.getLastGood(), 0);
        assertEquals(masterMind.getLastWrong(), 4);
    }

    /**
     * Test of the MasterMind random generator.
     */
    @Test
    public void testRand() {
        MasterMind masterMind = new MasterMind();
        assertTrue(Pattern.matches("^[1-8]{4}$", masterMind.getSoluce()));
    }
}
