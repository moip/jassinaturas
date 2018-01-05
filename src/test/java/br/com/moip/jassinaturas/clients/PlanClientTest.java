package br.com.moip.jassinaturas.clients;

import org.junit.Assert;
import org.junit.Test;
import br.com.moip.jassinaturas.Assinaturas;
import br.com.moip.jassinaturas.clients.attributes.Authentication;
import br.com.moip.jassinaturas.clients.attributes.Interval;
import br.com.moip.jassinaturas.clients.attributes.Plan;
import br.com.moip.jassinaturas.clients.attributes.PlanStatus;
import br.com.moip.jassinaturas.clients.attributes.Trial;
import br.com.moip.jassinaturas.clients.attributes.Unit;
import br.com.moip.jassinaturas.communicators.ProductionCommunicator;
import br.com.moip.jassinaturas.communicators.SandboxCommunicator;
import br.com.moip.jassinaturas.exceptions.ApiResponseErrorException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PlanClientTest {

    private final Assinaturas assinaturas = new Assinaturas(new Authentication("JOSOAPZJ4JI3IQTRUUTIGWQEPRPMDW58",
            "Q1MSGUKMXXQTKO4W7OHHINJNFYSOCT4FJLJKYXKH"), new SandboxCommunicator());

    @Test
    public void shouldActivateAPlan() {

        Plan plan = assinaturas.plans().active("plan003");

        // There isn't any response from Moip Assinaturas when plan is activated
        // So, I didn't do any assert
    }

    @Test
    public void shouldCreateANewPlan() {
        Plan toCreate = new Plan();
        toCreate.withCode("plan" + System.currentTimeMillis()).withDescription("Plano de Teste").withName("Plano de Teste").withAmount(1000)
                .withSetupFee(100).withBillingCycles(1).withPlanStatus(PlanStatus.ACTIVE).withMaxQty(10)
                .withInterval(new Interval().withLength(10).withUnit(Unit.MONTH))
                .withTrial(new Trial().withDays(10).enabled());

        Plan created = assinaturas.plans().create(toCreate);

        assertEquals("Plano criado com sucesso", created.getMessage());
    }

    @Test
    public void shouldInactivateAPlan() {

        Plan plan = assinaturas.plans().inactive("plan001");

        // There isn't any response from Moip Assinaturas when plan is
        // inactivated
        // So, I didn't do any assert
    }

    @Test
    public void shouldListAllPlans() {
        List<Plan> plans = assinaturas.plans().list();
        Assert.assertNotNull(plans);
    }

    @Test
    public void shouldReturnErrors() {
        Plan toCreate = new Plan();
        toCreate.withCode("plan001").withDescription("Plano de Teste").withName("Plano de Teste").withAmount(1000)
                .withSetupFee(100).withBillingCycles(1).withPlanStatus(PlanStatus.ACTIVE).withMaxQty(10)
                .withInterval(new Interval().withLength(10).withUnit(Unit.MONTH))
                .withTrial(new Trial().withDays(10).enabled());

        try {
            Plan created = assinaturas.plans().create(toCreate);
            Assert.fail("Should return error");
        } catch (ApiResponseErrorException e) {
            assertEquals("Erro na requisição", e.getApiResponseError().getMessage());
            assertEquals("Código do plano já utilizado. Escolha outro código",
                    e.getApiResponseError().getErrors().get(0).getDescription());
            assertEquals("MA6", e.getApiResponseError().getErrors().get(0).getCode());
        }
    }

    @Test
    public void shouldShowAPlan() {
        Plan plan = assinaturas.plans().show("plan003");

        assertEquals("plan003", plan.getCode());
        assertEquals("", plan.getDescription());
        assertEquals("Plano Especial", plan.getName());
        assertEquals(990, plan.getAmount());
        assertEquals(500, plan.getSetupFee());
        assertEquals(12, plan.getBillingCycles());
        assertEquals(PlanStatus.ACTIVE, plan.getStatus());
        assertEquals(0, plan.getMaxQuantity());
        assertEquals(100, plan.getInterval().getLength());
        assertEquals(Unit.DAY, plan.getInterval().getUnit());
        assertFalse(plan.getTrial().isEnabled());
        assertEquals(5, plan.getTrial().getDays());
    }

    @Test
    public void shouldUpdateAPlan() {
        Plan toUpdate = new Plan();
        toUpdate.withCode("plan001").withDescription("Plano de Teste Atualizado").withName("Plano de Teste Atualizado")
                .withAmount(10000).withSetupFee(1000).withBillingCycles(10).withPlanStatus(PlanStatus.INACTIVE)
                .withMaxQty(100).withInterval(new Interval().withLength(100).withUnit(Unit.DAY))
                .withTrial(new Trial().withDays(5).disabled());

        Plan plan = assinaturas.plans().update(toUpdate);

        // There isn't any response from Moip Assinaturas when plan is updated
        // So, I didn't do any assert

    }

    @Test
    public void shouldCreateANewPlanInProductionEnvironment() {
        Assinaturas assinaturas = new Assinaturas(new Authentication("SGPA0K0R7O0IVLRPOVLJDKAWYBO1DZF3",
                "QUJESGM9JU175OGXRFRJIYM0SIFOMIFUYCBWH9WA"), new ProductionCommunicator());

        Plan toCreate = new Plan();
        toCreate.withCode("plan_jassinaturas_production_" + System.currentTimeMillis()).withDescription("Plano de Teste")
                .withName("Plano de Teste").withAmount(1000).withSetupFee(100).withBillingCycles(1)
                .withPlanStatus(PlanStatus.ACTIVE).withMaxQty(10)
                .withInterval(new Interval().withLength(10).withUnit(Unit.MONTH))
                .withTrial(new Trial().withDays(10).enabled());

        Plan created = assinaturas.plans().create(toCreate);

        assertEquals("Plano criado com sucesso", created.getMessage());
    }

    @Test
    public void shouldGetResultFromToString() {
        String plan = assinaturas.plans().show("plan001").toString();

        assertEquals(
                "Plan [alerts=null, amount=10000, billingCycles=12, code=plan001, description=Plano de Teste Atualizado, interval=Interval [unit=YEAR, length=1], maxQty=100, message=null, name=Plano de Teste Atualizado, plans=null, setupFee=1000, status=INACTIVE, trial=Trial [days=5, enabled=false]]",
                plan);
    }
}
