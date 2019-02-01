package store.dataset;

import store.model.OnlineShoppingMall;

import javax.xml.bind.JAXB;
import java.io.File;

public class OnlineStore {

    protected final OnlineShoppingMall mall =
            JAXB.unmarshal(new File("src/test/resources/data"), OnlineShoppingMall.class);
}

