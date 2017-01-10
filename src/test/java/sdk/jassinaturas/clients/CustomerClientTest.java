package sdk.jassinaturas.clients;

import com.rodrigosaito.mockwebserver.player.Play;
import com.rodrigosaito.mockwebserver.player.Player;
import org.junit.Rule;
import org.junit.Test;
import sdk.jassinaturas.Assinaturas;
import sdk.jassinaturas.clients.attributes.Address;
import sdk.jassinaturas.clients.attributes.Authentication;
import sdk.jassinaturas.clients.attributes.BillingInfo;
import sdk.jassinaturas.clients.attributes.Birthdate;
import sdk.jassinaturas.clients.attributes.Country;
import sdk.jassinaturas.clients.attributes.CreditCard;
import sdk.jassinaturas.clients.attributes.Customer;
import sdk.jassinaturas.clients.attributes.Month;
import sdk.jassinaturas.clients.attributes.State;
import sdk.jassinaturas.communicators.ProductionCommunicator;
import sdk.jassinaturas.communicators.SandboxCommunicator;
import sdk.jassinaturas.exceptions.ApiResponseErrorException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class CustomerClientTest {

    private final Assinaturas assinaturas = new Assinaturas(new Authentication("JOSOAPZJ4JI3IQTRUUTIGWQEPRPMDW58",
            "Q1MSGUKMXXQTKO4W7OHHINJNFYSOCT4FJLJKYXKH"), new SandboxCommunicator());

    @Test
    public void shouldCreateANewCustomer() {
        Customer toCreate = new Customer();
        toCreate.withCode("customer000000001" + System.currentTimeMillis())
                .withBirthdate(new Birthdate().withDay(13).withMonth(Month.OCTOBER).withYear(1989))
                .withCpf("12312312312")
                .withEmail("teste@teste.com")
                .withFullname("Danillo Souza")
                .withPhoneAreaCode("11")
                .withPhoneNumber("912341234")
                .withAddress(
                        new Address().withCity("São Paulo").withComplement("Apto").withCountry(Country.BRA)
                                .withDistrict("Centro").withNumber("1000").withState(State.SP).withStreet("9 de Julho")
                                .withZipcode("10012345"))
                .withBillingInfo(
                        new BillingInfo().withCreditCard(new CreditCard().withExpirationMonth("10")
                                .withExpirationYear("18").withHolderName("Danillo Souza")
                                .withNumber("4111111111111111")));

        Customer created = assinaturas.customers().create(toCreate);

        assertEquals("Cliente criado com sucesso", created.getMessage());
    }

    @Test
    public void shouldCreateANewCustomerWithoutCreditCard() {
        Customer toCreate = new Customer();
        toCreate.withCode("customer_without_credit_card_"+System.currentTimeMillis())
                .withBirthdate(new Birthdate().withDay(13).withMonth(Month.OCTOBER).withYear(1989))
                .withCpf("12312312312")
                .withEmail("teste@teste.com")
                .withFullname("Danillo Souza")
                .withPhoneAreaCode("11")
                .withPhoneNumber("912341234")
                .withAddress(
                        new Address().withCity("São Paulo").withComplement("Apto").withCountry(Country.BRA)
                                .withDistrict("Centro").withNumber("1000").withState(State.SP).withStreet("9 de Julho")
                                .withZipcode("10012345"));

        Customer created = assinaturas.customers().create(toCreate);

        assertEquals("Cliente criado com sucesso", created.getMessage());
    }

    @Test
    public void shouldListAllCustomers() {
        List<Customer> customers = assinaturas.customers().list();
        assertNotNull(customers);
        assertEquals(10, customers.get(0).getBirthdate().getBirthdateMonth());
        assertEquals(13, customers.get(0).getBirthdate().getBirthdateDay());
        assertEquals(1989, customers.get(0).getBirthdate().getBirthdateYear());
        assertEquals("11", customers.get(0).getPhoneAreaCode());
        assertEquals("912341234", customers.get(0).getPhoneNumber());
        assertEquals("teste@teste.com", customers.get(0).getEmail());
        assertEquals("12312312312", customers.get(0).getCpf());
        assertEquals("Danillo Souza", customers.get(0).getFullname());
    }

    @Play("CREATE_CUSTOMER_RETURNED_ERROR")
    @Test
    public void shouldReturnErrors() {
        Customer toCreate = new Customer();
        toCreate.withCode("customer000000001_no_creditCard")
                .withBirthdate(new Birthdate().withDay(13).withMonth(Month.OCTOBER).withYear(1989))
                .withCpf("12312312312")
                .withEmail("teste@teste.com")
                .withFullname("Danillo Souza")
                .withPhoneAreaCode("11")
                .withPhoneNumber("912341234")
                .withAddress(
                        new Address().withCity("São Paulo").withComplement("Apto").withCountry(Country.BRA)
                                .withDistrict("Centro").withNumber("1000").withState(State.SP).withStreet("9 de Julho")
                                .withZipcode("10012345"));

        try {
            Customer created = assinaturas.customers().create(toCreate);
            fail("Should return error");
        } catch (ApiResponseErrorException e) {
            assertEquals("Erro na requisição", e.getApiResponseError().getMessage());
            assertEquals("Código do cliente já utilizado. Escolha outro código.", e.getApiResponseError().getErrors()
                    .get(0).getDescription());
            assertEquals("MA33", e.getApiResponseError().getErrors().get(0).getCode());
        }

    }

    @Play("GET_SINGLE_CUSTOMER")
    @Test
    public void shouldShowACustomer() {
        Customer customer = assinaturas.customers().show("customer000000001");

        assertEquals("customer000000001", customer.getCode());
        assertEquals("Nome Sobrenome", customer.getFullname());
        assertEquals("nome@exemplo.com.br", customer.getEmail());
        assertEquals("22222222222", customer.getCpf());
        assertEquals("11", customer.getPhoneAreaCode());
        assertEquals("934343434", customer.getPhoneNumber());
        assertEquals(4, customer.getBirthdate().getBirthdateMonth());
        assertEquals(26, customer.getBirthdate().getBirthdateDay());
        assertEquals(1980, customer.getBirthdate().getBirthdateYear());
        assertEquals("Rua Nome da Rua", customer.getAddress().getStreet());
        assertEquals("100", customer.getAddress().getNumber());
        assertEquals("Casa", customer.getAddress().getComplement());
        assertEquals("Nome do Bairro", customer.getAddress().getDistrict());
        assertEquals("São Paulo", customer.getAddress().getCity());
        assertEquals("SP", customer.getAddress().getState().toString());
        assertEquals("BRA", customer.getAddress().getCountry().toString());
        assertEquals("05015010", customer.getAddress().getZipcode());
        assertEquals("VISA", customer.getBillingInfo().getCreditCards().get(0).getBrand());
        assertEquals("411111", customer.getBillingInfo().getCreditCards().get(0).getFirstSixDigits());
        assertEquals("1111", customer.getBillingInfo().getCreditCards().get(0).getLastFourDigits());
        assertEquals("04", customer.getBillingInfo().getCreditCards().get(0).getExpirationMonth());
        assertEquals("18", customer.getBillingInfo().getCreditCards().get(0).getExpirationYear());
        assertEquals("Nome Completo", customer.getBillingInfo().getCreditCards().get(0).getHolderName());
        assertEquals("caca7794-c088-4c08-8089-88d106c3b6c4", customer.getBillingInfo().getCreditCards().get(0).getVault());

    }

    @Test
    public void shouldUpdateACreditCard() {
        Customer toUpdate = new Customer();
        toUpdate.withCode("customer0000000011484070865657").withBillingInfo(
                new BillingInfo().withCreditCard(new CreditCard().withExpirationMonth("10").withExpirationYear("18")
                        .withHolderName("Danillo Souza").withNumber("4111111111111111")));

        Customer updated = assinaturas.customers().updateVault(toUpdate);

        assertEquals("Dados alterados com sucesso", updated.getMessage());

    }

    @Test
    public void shouldUpdateACustomer() {
        Customer toUpdate = new Customer();
        toUpdate.withCode("customer0000000011484070865657")
                .withBirthdate(new Birthdate().withDay(13).withMonth(Month.OCTOBER).withYear(1989))
                .withCpf("32132132132")
                .withEmail("etset@etset.com")
                .withFullname("Souza Danillo")
                .withPhoneAreaCode("11")
                .withPhoneNumber("912341234")
                .withAddress(
                        new Address().withCity("São Paulo").withComplement("Apto").withCountry(Country.BRA)
                                .withDistrict("Centro").withNumber("1000").withState(State.SP).withStreet("9 de Julho")
                                .withZipcode("10012345"));

        Customer created = assinaturas.customers().update(toUpdate);

        // There isn't any response from Moip Assinaturas when plan is activated
        // So, I didn't do any assert

    }

    @Test
    public void shouldGetResultFromToString() {
        String customer = assinaturas.customers().show("customer000000001").toString();

        assertEquals(
                "Customer [address=Address [city=São Paulo, complement=Casa, country=BRA, district=Nome do Bairro, number=100, state=SP, street=Rua Nome da Rua, zipcode=05015010], billingInfo=BillingInfo [creditCard=null, creditCards=[CreditCard [brand=VISA, expirationMonth=04, expirationYear=18, firstSixDigits=411111, holderName=Nome Completo, lastFourDigits=1111, number=null, vault=caca7794-c088-4c08-8089-88d106c3b6c4]]], birthdate=null, code=customer000000001, cpf=22222222222, customers=null, email=nome@exemplo.com.br, fullname=Nome Sobrenome, message=null, phoneAreaCode=11, phoneNumber=934343434, birthdateDay=26, birthdateMonth=4, birthdateYear=1980]",
                customer);

    }
}
