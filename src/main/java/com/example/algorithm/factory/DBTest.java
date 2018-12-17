package com.example.algorithm.factory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class DBTest {

  abstract class DBDriver {

    abstract String getName();
  }

  class MysqlDriver extends DBDriver {

    @Override
    String getName() {
      return "mysql";
    }
  }

  class OracleDriver extends DBDriver {

    @Override
    String getName() {
      return "oracle";
    }
  }

  class MongoDBDriver extends DBDriver {

    @Override
    String getName() {
      return "mongo";
    }
  }

  class PostgresQlDriver extends DBDriver {

    @Override
    String getName() {
      return "postgresQl";
    }
  }

  class Service {

    DBDriver driver = null;

    private void setDriver(String db) {

      if (db.equals("mysql")) {
        this.driver = new MysqlDriver();
      } else if (db.equals("oracle")) {
        this.driver = new OracleDriver();
      } else if (db.equals("mongo")) {
        this.driver = new MongoDBDriver();
      } else if (db.equals("postgresql")) {
        this.driver = new PostgresQlDriver();
      }
    }

    public String doSomthing() {
      return driver.getName();
    }
  }

  @Test
  public void test() {

    Service myservice = new Service();
    myservice.setDriver("oracle");

    assertThat(myservice.doSomthing()).isEqualTo("oracle");
  }

}
