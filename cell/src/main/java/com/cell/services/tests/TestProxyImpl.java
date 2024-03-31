package com.cell.services.tests;

import com.cell.common.persist.SubTransactionalOperation;
import com.cell.common.persist.SessionFactoryUtil;
import com.cell.common.persist.StandaloneTransactionOperation;
import com.cell.common.utils.ModelEntityId;
import com.cell.services.accounts.persist.AccountModel;
import java.sql.Statement;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class TestProxyImpl implements TestProxy {

  public TestProxyImpl() {}

  @Override
  public void testEndpoint() {}

  private void createAccount() {
    final AccountModel accountModel = new AccountModel();
    final String uuid = UUID.randomUUID().toString();
    final String account = "accounts";
    final ModelEntityId modelId = ModelEntityId.from(String.format("%s_%s",account, uuid));
    accountModel.setId(modelId.getObjectId());
    accountModel.setEnabled(true);
    accountModel.setUsername("fnkwf");
    accountModel.setEmail("testnfjefn@gmail.com");
    accountModel.setRegisteredDate(Date.from(Instant.now()));
    SubTransactionalOperation.save(accountModel);
  }
}
