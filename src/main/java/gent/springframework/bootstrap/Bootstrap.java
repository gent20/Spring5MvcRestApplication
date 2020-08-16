package gent.springframework.bootstrap;

import gent.springframework.domain.Category;
import gent.springframework.domain.Customer;
import gent.springframework.domain.Vendor;
import gent.springframework.repositories.CategoryRepository;
import gent.springframework.repositories.CustomerRepository;
import gent.springframework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    public void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories loaded : " + categoryRepository.count());
    }

    private void loadCustomers() {
        Customer gent = new Customer();
        gent.setId(1L);
        gent.setFirstName("Gent");
        gent.setLastName("Kastrati");
        customerRepository.save(gent);

        Customer rinor = new Customer();
        rinor.setId(2L);
        rinor.setFirstName("Rinor");
        rinor.setLastName("Krasniqi");
        customerRepository.save(rinor);

        System.out.println("Customers loaded : " + customerRepository.count());
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");
        vendorRepository.save(vendor2);

    }
}
