package edu.upc.dsa;

import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
        Object obj = session.get(User.class, "762282d6-5a6f-11ec-b705-1cbfce539af0");
        assertThat(obj).usingRecursiveComparison().isEqualTo(new User("762282d6-5a6f-11ec-b705-1cbfce539af0","761d1966-5a6f-11ec-b705-1cbfce539af0", "Pau", "79fe6d5e658a2b2302aacd146c7bfb62"));


        Object obj2 = session.get(Player.class, "761d1966-5a6f-11ec-b705-1cbfce539af0");
        assertThat(obj2).usingRecursiveComparison().isEqualTo(new Player("761d1966-5a6f-11ec-b705-1cbfce539af0","pau player", 0, "ROOKIE", 0,0));

        User u = (User) obj;
        assertThat(u.getPlayer()).usingRecursiveComparison().isEqualTo(new Player("761d1966-5a6f-11ec-b705-1cbfce539af0","pau player", 0, "ROOKIE", 0,0));
    }

    @Test
    public void testSave(){
        User u = new User("761d1966-5a6f-11ec-b705-1cbfce539af0", "Pau3", "Pau");
        Session session = SessionImpl.getInstance();
        String id = session.save(u);
        System.out.println("Introduced id: " + id);

    }
}
