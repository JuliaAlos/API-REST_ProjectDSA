package edu.upc.dsa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GameManagerTest {
    @After
    public void tearDown() {
        GameManagerImpl.getInstance().clear();
        GameManagerImpl.delete();
    }


    @Before
    public void setUp() {

    }



}
