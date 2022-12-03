package com.bodyhealth;

import com.bodyhealth.model.Administrador;
import com.bodyhealth.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BodyhealthApplicationTests {

	@Autowired
	private AdminRepository adminRepository;


	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	public void crearAdminTest() {
		Administrador admin = new Administrador();
		admin.setId_admin(99);
		admin.setEmail("admin@prueba.com");
		admin.setPassword(encoder.encode("1234"));

		Administrador ret = adminRepository.save(admin);

		assertTrue(ret.getPassword().equalsIgnoreCase(admin.getPassword()));
	}

}
