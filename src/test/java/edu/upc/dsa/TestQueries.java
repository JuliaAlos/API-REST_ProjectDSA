package edu.upc.dsa;

import edu.upc.dsa.models.User;
import edu.upc.dsa.util.QueryHelper;
import org.junit.Assert;
import org.junit.Test;

public class TestQueries {

    @Test
    public void testSelect(){
        Assert.assertEquals("SELECT * FROM User WHERE id = '45170994-599e-11ec-ad3a-b0359fb64d3d'",
                QueryHelper.createQuerySELECT(
                         User.class,"45170994-599e-11ec-ad3a-b0359fb64d3d"));
    }

    @Test
    public void testInsert(){
        User u = new User("4505770b-599e-11ec-ad3a-b0359fb64d3d", "Pau2", "79fe6d5e658a2b2302aacd146c7bfb62");
        Assert.assertEquals("INSERT INTO User (userName, password, fullName, email, status, id, playerId) VALUES (?, ?, ?, ?, ?, ?, ?)", QueryHelper.createQueryINSERT(u));
    }
}
