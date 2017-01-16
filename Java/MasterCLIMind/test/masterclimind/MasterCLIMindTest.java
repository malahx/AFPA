/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterclimind;

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

    MasterMind masterMind;

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
        masterMind = new MasterMind("1234");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of MasterMind object.
     */
    @Test
    public void testMasterMind() {
        assertEquals(this.masterMind.display("1234"), Color.GREEN + "****" + Color.RED);
        assertEquals(this.masterMind.display("1111"), Color.GREEN + "*" + Color.RED);
        assertEquals(this.masterMind.display("4321"), Color.GREEN + Color.RED + "****");
        assertEquals(this.masterMind.getSoluce(), "1234");
        assertEquals(this.masterMind.getLastGood(), 0);
        assertEquals(this.masterMind.getLastWrong(), 4);
    }

}
