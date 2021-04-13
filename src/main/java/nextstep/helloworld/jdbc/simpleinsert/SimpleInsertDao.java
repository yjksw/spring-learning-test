package nextstep.helloworld.jdbc.simpleinsert;

import nextstep.helloworld.jdbc.Customer;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SimpleInsertDao {
    private SimpleJdbcInsert insertActor;

    public SimpleInsertDao(DataSource dataSource) {
        this.insertActor = new SimpleJdbcInsert(dataSource)
                .withTableName("customers")
                .usingGeneratedKeyColumns("id");
    }

    /**
     * Map +
     * insertActor.executeAndReturnKey
     */
    public Customer insertWithMap(Customer customer) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("first_name", customer.getFirstName());
        params.put("last_name", customer.getLastName());
        Number id = insertActor.executeAndReturnKey(params);
        return new Customer(id.longValue(), customer.getFirstName(), customer.getLastName());
    }

    /**
     * SqlParameterSource +
     * insertActor.executeAndReturnKey
     */
    public Customer insertWithBeanPropertySqlParameterSource(Customer customer) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(customer);
        Number id = insertActor.executeAndReturnKey(params);
        return new Customer(id.longValue(), customer.getFirstName(), customer.getLastName());
    }
}
