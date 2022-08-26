package com.testehan.tests.extensions.lifecyclecallbacks;

import org.junit.jupiter.api.extension.*;

public class LifecycleExtention implements
        BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        System.out.println("1. LifecycleExtention - This can be a good point to create a table/database");
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        System.out.println("3. LifecycleExtention - Create a savepoint that will be used to roll back changes to");
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        System.out.println("7. LifecycleExtention - Roll back changes that occured during test to clean up");
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        System.out.println("9. LifecycleExtention - Delete the table/DB ..close DB connection as the test is complete...");
    }


}
