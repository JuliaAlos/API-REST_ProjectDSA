package edu.upc.dsa;

import edu.upc.dsa.models.User;
import edu.upc.dsa.transferObjects.RegisterUserTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class GameManagerDAOTest {
    @Test
    public void testExists(){
        GameManagerDAOImpl gameManagerDAO = GameManagerDAOImpl.getInstance();

        Boolean b = gameManagerDAO.existUser("Pau");
        Assert.assertTrue(b);

        Assert.assertFalse(gameManagerDAO.existUser("blabla"));
    }

    @Test
    public void testAddUser(){
        GameManagerDAOImpl gameManagerDAO = GameManagerDAOImpl.getInstance();

        Random r = new Random();
        Integer rand = r.nextInt(100-0)+0;

        RegisterUserTO registerUserTO = new RegisterUserTO("P"+ rand.toString(), "Pau", "P fullname", "pau@pau.pau");
        User u = gameManagerDAO.addUser(registerUserTO.toUser());



    }
}
