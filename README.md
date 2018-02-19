[![CircleCI](https://circleci.com/gh/moip/jassinaturas.svg?style=shield)](https://circleci.com/gh/moip/jassinaturas)

# JAsssinaturas - Java library to integrate with Moip Assinaturas

Moip Assinaturas is a product that manages recurring fees and charges. It allows you to create plans, customers, subscriptions, coupons etc.

More info available at: https://moip.com.br/solucoes-assinaturas/

API Documentation/Reference: https://dev.moip.com.br/v1.5/docs

# Maven dependency
```java
<dependency>
   <groupId>br.com.moip</groupId>
   <artifactId>jassinaturas</artifactId>
   <version>1.1.7</version>
</dependency>
```

# Using

First of all, you'll have to instanciate a new Assinaturas object passing your credentials and the environment you want to connect.

```java
Assinaturas assinaturas = new Assinaturas(new Authentication("YOUR_TOKEN", "YOUR_SECRET"), new (SandboxCommunicator|ProductionCommunicator)());
```

### Plans

Creating a new plan:

```java
Plan toCreate = new Plan();
toCreate
    .withCode("YOR_PLAN_CODE")
    .withDescription("YOUR_PLAN_DESCRIPTION")
    .withName("YOUR_PLAN_NAME")
    .withAmount(1000)
    .withSetupFee(100)
    .withBillingCycles(1)
    .withPlanStatus(PlanStatus.ACTIVE)
    .withMaxQty(10)
    .withInterval(new Interval()
        .withLength(10)
        .withUnit(Unit.MONTH))
    .withTrial(new Trial()
        .withDays(10)
        .enabled());
```

After that you have to call following method:

```java
Plan created = assinaturas.plan().create(toCreate);
```

Listing all plans:

```java
List<Plan> plans = assinaturas.plan().list();
```

Getting a single plan:

```java
Plan plan = assinaturas.plan().show("YOUR_PLAN_CODE");
```

Updating a plan:

To update a plan, you should inform your new plan params, creating a new object of Plan and passing it to update method

```java
Plan toUpdate = new Plan();
toUpdate.withCode("PLAN_CODE_TO_UPDATE")
    .withDescription("PLAN_NEW_DESCRIPTION")
    .withName("PLAN_NEW_NAME")
    .withAmount(10000)
    .withSetupFee(1000)
    .withBillingCycles(10)
    .withPlanStatus(PlanStatus.INACTIVE)
    .withMaxQty(100)
    .withInterval(new Interval()
        .withLength(100)
        .withUnit(Unit.DAY))
    .withTrial(new Trial()
        .withDays(5)
        .disabled());
```

After informing what you want to update, just call the following method:

```java
Plan plan = assinaturas.plan().update(toUpdate);
```

Activating a plan:

```java
Plan plan = assinaturas.plan().active("YOUR_PLAN_CODE");
```

Inactivating a plan:

```java
Plan plan = assinaturas.plan().inactive(toInactivate);
```

### Subscriptions

Creating a new subscription

```java
Subscription toBeCreated = new Subscription();
toBeCreated
    .withCode("YOUR_SUBSCRIPTION_CODE")
    .withAmount(100)
    .withPlan(
        new Plan()
            .withCode("YOUR_PLAN_CODE"))
    .withCustomer(
        new Customer()
            .withCode("YOUR_CUSTOMER_CODE")
            .withBirthdate(
                new Birthdate()
                    .withDay(13)
                    .withMonth(Month.OCTOBER)
                    .withYear(1989))
            .withCpf("CPF_NUMBER")
            .withEmail("some@email.com")
            .withFullname("CUSTOMER_NAME")
            .withPhoneAreaCode("CUSTOMER_PHONE_AREA_CODE")
            .withPhoneNumber("CUSTOMER_PHONE_NUMBER")
            .withAddress(
                new Address()
                    .withCity("São Paulo")
                    .withComplement("Apto")
                    .withCountry(Country.BRA)
                    .withDistrict("Centro")
                    .withNumber("1000")
                    .withState(State.SP)
                    .withStreet("Some Street")
                    .withZipcode("00000-000"))
            .withBillingInfo(
                new BillingInfo()
                    .withCreditCard(new CreditCard()
                        .withExpirationMonth("10")
                        .withExpirationYear("18")
                        .withHolderName("Some Name")
                        .withNumber("4111111111111111"))));
```

You can create a subscription just informing an already created customer:

```java
Subscription toBeCreated = new Subscription();
toBeCreated
    .withCode("SUBSCRIPTION_CODE")
    .withAmount(100)
    .withCustomer(new Customer()
        .withCode("YOUR_CUSTOMER_CODE"))
    .withPlan(new Plan()
        .withCode("YOUR_PLAN_CODE"));
```

You can also create a subscription with pro-rata. It's obligatory inform a best Invoide Date.
For annualy plans month is required. set day is optional.
For monthly day is required.

There's no best invoice date in daily plans.

```java
Subscription toBeCreated = new Subscription();
toBeCreated
    .withCode("SUBSCRIPTION_CODE")
    .withAmount(100)
    .withCustomer(new Customer()
        .withCode("YOUR_CUSTOMER_CODE"))
    .withPlan(new Plan()
        .withCode("YOUR_PLAN_CODE"))
        .withProRata(true)
        .withBestInvoiceDate(new BestInvoiceDate()
                                .withDayOfMonth(10)
                                .withMonthOfYear(10));
```

Then you'll need to call the following method:

```java
	Subscription created = assinaturas.subscription().create(toBeCreated);
```

Updating a subscription

```java
Subscription toUpdate = new Subscription();
toUpdate
    .withCode("SUBSCRIPTION_CODE")
    .withPlan(new Plan()
        .withCode("plano01"))
    .withAmount(990)
    .withNextInvoiceDate(new NextInvoiceDate()
        .withDay(20)
        .withMonth(Month.OCTOBER)
        .withYear(2015));
```

Then call the following method:

```java
Subscription subscription = assinaturas.subscription().update(toUpdate);
```

Activating a subscription:

```java
Subscription subscription = assinaturas.subscription().activate("SUBSCRIPTION_CODE");
```

Cancelling a subscription:

```java
Subscription subscription = assinaturas.subscription().cancel("SUBSCRIPTION_CODE");
```

Suspending a subscription:

```java
Subscription subscription = assinaturas.subscription().suspend("SUBSCRIPTION_CODE");
```

Getting a single subscription:

```java
Subscription subscription = assinaturas.subscription().show("SUBSCRIPTION_CODE");
```

Listing all subscriptions:

```java
List<Subscription> subscriptions = assinaturas.subscription().list();
```

Listing invoices from a subscription:

```java
List<Invoice> invoices = assinaturas.subscription().invoices("SUBSCRIPTION_CODE");
```

### Customers

Creating a new customer:

```java
Customer toCreate = new Customer();
toCreate.withCode("CUSTOMER_CODE")
    .withBirthdate(
        new Birthdate()
            .withDay(13)
            .withMonth(Month.OCTOBER)
            .withYear(1989))
    .withCpf("CUSTOMER_CPF")
    .withEmail("CUSTOMER_EMAIL")
    .withFullname("CUSTOMER_NAME")
    .withPhoneAreaCode("CUSTOMER_PHONE_AREA_CODE")
    .withPhoneNumber("CUSTOMER_PHONE_NUMBER")
    .withAddress(
        new Address()
            .withCity("CUSTOMER_CITY")
            .withComplement("CUSTOMER_COMPLEMENT")
            .withCountry(Country.BRA)
            .withDistrict("CUSTOMER_DISTRICT")
            .withNumber("CUSTOMER_ADDRESS_NUMBER")
            .withState(State.SP)
            .withStreet("CUSTOMER_STREET")
            .withZipcode("CUSTOMER_ZIPCODE"))
    .withBillingInfo(
        new BillingInfo()
            .withCreditCard(
                new CreditCard()
                    .withExpirationMonth("10")
                    .withExpirationYear("18")
                    .withHolderName("CARD_HOLDER_NAME")
                    .withNumber("4111111111111111")));
```

You can also create a customer without credit card information:

```java
Customer toCreate = new Customer();
toCreate.withCode("CUSTOMER_CODE")
    .withBirthdate(
        new Birthdate()
            .withDay(13)
            .withMonth(Month.OCTOBER)
            .withYear(1989))
    .withCpf("CUSTOMER_CPF")
    .withEmail("CUSTOMER_EMAIL")
    .withFullname("CUSTOMER_NAME")
    .withPhoneAreaCode("CUSTOMER_PHONE_AREA_CODE")
    .withPhoneNumber("CUSTOMER_PHONE_NUMBER")
    .withAddress(
        new Address()
            .withCity("CUSTOMER_CITY")
            .withComplement("CUSTOMER_COMPLEMENT")
            .withCountry(Country.BRA)
            .withDistrict("CUSTOMER_DISTRICT")
            .withNumber("CUSTOMER_ADDRESS_NUMBER")
            .withState(State.SP)
            .withStreet("CUSTOMER_STREET")
            .withZipcode("CUSTOMER_ZIPCODE"));
```

After creating the customer object, you'll have to call the following method:

```java
Customer created = assinaturas.customer().create(toCreate);
```

Updating a customer:

```java
Customer toUpdate = new Customer();
toUpdate
    .withCode("CUSTOMER_CODE")
    .withBirthdate(
        new Birthdate()
            .withDay(13)
            .withMonth(Month.OCTOBER)
            .withYear(1989))
    .withCpf("CUSTOMER_CPF")
    .withEmail("NEW_EMAIL")
    .withFullname("CUSTOMER_NAME")
    .withPhoneAreaCode("PHONE_AREA_CODE")
    .withPhoneNumber("PHONE_NUMBER")
    .withAddress(
        new Address()
            .withStreet("CUSTOMER_STREET_NAME")
            .withCity("CUSTOMER_CITY")
            .withComplement("CUSTOMER_COMPLEMENT_IF_EXISTS")
            .withCountry(Country.BRA)
            .withDistrict("CUSTOMER_DISTRICT")
            .withNumber("CUSTOMER_STREET_NUMBER")
            .withState(State.SP)
            .withZipcode("CUSTOMER_ZIP_CODE"));
```

After that just call the method:

```java
Customer created = assinaturas.customer().update(toUpdate);
```

Updating only credit card vault:

```java
Customer toUpdate = new Customer();
toUpdate
    .withCode("CUSTOMER_CODE")
    .withBillingInfo(
        new BillingInfo()
            .withCreditCard(
                new CreditCard()
                    .withExpirationMonth("10")
                    .withExpirationYear("18")
                    .withHolderName("CARD_HOLDER_NAME")
                    .withNumber("4111111111111111")));
```

After that just call the method:

```java
Customer updated = assinaturas.customer().updateVault(toUpdate);
```

Listing all customers:

```java
List<Customer> customers = assinaturas.customer().list();
```

Showing a single customer:

```java
Customer customer = assinaturas.customer().show("CUSTOMER_CODE");
```

### Invoices

Getting single invoice:

```java
Invoice invoice = assinaturas.invoices().show(INVOICE_ID);
```

Getting payments from an invoice:

```java
List<Payment> payments = assinaturas.invoices().payments(INVOICE_ID);
```

Retring an invoice:

```java
Invoice invoice = assinaturas.invoices().retry(INVOICE_ID);
```

### Coupons

Creating coupon:

```java
toBeCreated.withCode("COUPON_CODE")
        .withName("COUPON_NAME")
        .withDescription("COUPON_DESCRIPTION")
        .withDiscount(new Discount()
                    .withValue(1000)
                    .withType(DiscountType.PERCENT))
        .withStatus(CouponStatus.ACTIVE)
        .withDuration(new Duration()
                .withType(DurationType.REPEATING)
                .withOccurrences(1))
        .withExpirationDate(new ExpirationDate()
                .withDay(10)
                .withMonth(Month.OCTOBER)
                .withYear(2020))
        .withMaxRedemptions(1000);
```

Then call:

```java
Coupon coupon = assinaturas.coupons().create(toBeCreated);
```

To activate a coupon:

```java
Coupon coupon = assinaturas.coupons().inactivate(couponCode);
```

To inactive a coupon:

```java
Coupon coupon = assinaturas.coupons().inactivate(couponCode);
```

## Contributing

Bug reports and pull requests are welcome on GitHub at [https://github.com/moip/jassinaturas](https://github.com/moip/jassinaturas). This project is intended to be a safe, welcoming space for collaboration, and contributors are expected to adhere to the [Contributor Covenant](http://contributor-covenant.org) code of conduct.

1. Fork it (https://github.com/moip/jassinaturas/fork)
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Add some feature')
4. Push to the branch (git push origin my-new-feature)
5. Create a new Pull Request

## License

This lib is available as open source under the terms of the [MIT License](http://opensource.org/licenses/MIT).
