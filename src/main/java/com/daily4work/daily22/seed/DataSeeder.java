package com.daily4work.daily22.seed;

import com.daily4work.daily22.entity.Company;
import com.daily4work.daily22.entity.Role;
import com.daily4work.daily22.entity.User;
import com.daily4work.daily22.enums.CompanyStatus;
import com.daily4work.daily22.enums.UserStatus;
import com.daily4work.daily22.repository.CompanyRepository;
import com.daily4work.daily22.repository.RoleRepository;
import com.daily4work.daily22.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.admin.name}")
    private String adminName;

    @Value("${app.admin.surname}")
    private String adminSurname;

    public DataSeeder(RoleRepository roleRepository,
                      UserRepository userRepository,
                      CompanyRepository companyRepository,
                      PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (roleRepository.existsByName("SUPER_ADMIN")) {
            log.info("Seed data already exists, skipping.");
            return;
        }

        log.info("Starting data seeding...");

        Role superAdminRole = roleRepository.save(new Role("SUPER_ADMIN"));
        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));

        log.info("Roles created: SUPER_ADMIN, ADMIN, USER");

        Company defaultCompany = new Company();
        defaultCompany.setFactoryName("Default Company");
        defaultCompany.setStatus(CompanyStatus.ACTIVE);
        defaultCompany = companyRepository.save(defaultCompany);
        log.info("Default company created with id: {}", defaultCompany.getId());

        User admin = new User();
        admin.setEmail(adminEmail);
        admin.setPassword(passwordEncoder.encode(adminPassword));
        admin.setName(adminName);
        admin.setSurname(adminSurname);
        admin.setStatus(UserStatus.ACTIVE);
        admin.setCompany(defaultCompany);
        admin.setRoles(Set.of(superAdminRole));
        userRepository.save(admin);

        log.info("Super admin user created: {}", adminEmail);
        log.info("Data seeding completed.");
    }
}
