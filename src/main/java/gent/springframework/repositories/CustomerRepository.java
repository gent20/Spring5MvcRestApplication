package gent.springframework.repositories;

import gent.springframework.domain.Category;
import gent.springframework.domain.Customer;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
