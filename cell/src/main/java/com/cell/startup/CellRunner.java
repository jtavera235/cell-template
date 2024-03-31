package com.cell.startup;

import com.cell.common.persist.SessionFactoryUtil;
import com.cell.restapi.handlers.TestHandler;
import com.cell.services.tests.TestProxy;
import com.cell.services.tests.TestProxyImpl;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Environment;

public class CellRunner extends Application<CellConfiguration> {
  @Override
  public void run(CellConfiguration cellConfiguration, Environment environment) {
    SessionFactoryUtil.init();
    final TestProxy testProxy = new TestProxyImpl();
    environment.jersey().register(new TestHandler(testProxy));
  }

}
