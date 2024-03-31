package com.cell.restapi.handlers;

import com.cell.openapi.restapi.TestsApi;
import com.cell.services.tests.TestProxy;

public class TestHandler implements TestsApi  {

  private TestProxy testProxy;

  public TestHandler(TestProxy testProxy) {
    this.testProxy = testProxy;
  }

  @Override
  public void testEndpoint() {
    testProxy.testEndpoint();
  }
}
