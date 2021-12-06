package edu.upc.dsa;

import edu.upc.dsa.models.User;
import edu.upc.dsa.util.QueryHelper;
import org.junit.Assert;
import org.junit.Test;

public class TestQueries {

    @Test
    public void testSelect(){
        Assert.assertEquals("SELECT * FROM User WHERE id = 1",
                QueryHelper.createQuerySELECT(
                         User.class,1));
    }

    @Test
    public void testInsert(){
        User u = new User(1, "Pau2", "79fe6d5e658a2b2302aacd146c7bfb62");
        Assert.assertEquals("INSERT INTO User (userName, password, fullName, email, status, id, playerId) VALUES ('Pau2', '79fe6d5e658a2b2302aacd146c7bfb62', null, null, false, null, 1)", QueryHelper.createQueryINSERT(u));
    }
}
