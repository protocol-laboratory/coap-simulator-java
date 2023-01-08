package io.github.protocol.coap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CoapConfigStorageTest {

    @Test
    public void testCoapConfig() {
        CoapConfig coapConfig = CoapConfigStorage.getCoapConfig();
        Assertions.assertEquals(coapConfig.getHost(), CoapConfig.DEFAULT_HOST);
    }

}
