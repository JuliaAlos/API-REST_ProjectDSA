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
}
