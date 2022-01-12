package edu.upc.dsa;

import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;



public class TestDBDriver {
    @Before
    public void testConn(){
        Session session = SessionImpl.getInstance();
        Assert.assertTrue(session.isOpen());
    }

    @Test
    public void testGet(){

        Session session = SessionImpl.getInstance();
        Object obj = session.get(User.class, "A");
        assertThat(obj.getClass().equals(User.class));


    }

    @Test
    public void testSave(){
        User u = new User("IdPau", "Pau3", "Pau");
        Session session = SessionImpl.getInstance();
        String id = session.save(u);
        System.out.println("Introduced id: " + id);

    }
}
