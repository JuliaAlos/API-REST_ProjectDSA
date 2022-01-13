package edu.upc.dsa.transferObjects;

import io.swagger.models.auth.In;

public class SetBitcoinsTO {
    private Integer bitcoins;

    public SetBitcoinsTO() {
    }

    public Integer getBitcoins() {
        return bitcoins;
    }

    public void setBitcoins(Integer bitcoins) {
        this.bitcoins = bitcoins;
    }
}
