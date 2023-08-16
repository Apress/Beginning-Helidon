package io.helidon.book.ch10testing;


import io.helidon.microprofile.server.JaxRsCdiExtension;
import io.helidon.microprofile.server.ServerCdiExtension;
import io.helidon.microprofile.tests.junit5.AddBean;
import io.helidon.microprofile.tests.junit5.AddExtension;
import io.helidon.microprofile.tests.junit5.DisableDiscovery;
import io.helidon.microprofile.tests.junit5.HelidonTest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.client.WebTarget;
import org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@HelidonTest
@DisableDiscovery
@AddExtension(ServerCdiExtension.class)
@AddExtension(JaxRsCdiExtension.class)
@AddExtension(CdiComponentProvider.class)
@AddBean(WizardNoDiscoveryTest.MiniWizard.class)
class WizardNoDiscoveryTest {
    @Inject
    private WebTarget injected;

    @Test
    void testSpell() {
        Assertions.assertNotNull(injected);
        String response = injected.path("/spell")
                .request()
                .get(String.class);
        Assertions.assertEquals(response,"I put a spell on you!");
    }

    @Path("/spell")
    public static class MiniWizard {
        @GET
        public String saySpell() {
            return "I put a spell on you!";
        }
    }
}