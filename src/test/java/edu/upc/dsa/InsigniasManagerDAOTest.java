package edu.upc.dsa;

import org.junit.Test;

public class InsigniasManagerDAOTest {
    @Test
    public void addInsigniaToPlayer(){
        InsigniasManager insigniasManager = InsigniasManagerDAOImpl.getInstance();

        insigniasManager.addInsigniaToPlayer("Insignia 1","pau player");
    }
}
