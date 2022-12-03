package com.bodyhealth.implement;

import com.bodyhealth.model.Administrador;
import com.bodyhealth.model.Usuario;
import com.bodyhealth.repository.AdminRepository;
import com.bodyhealth.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("userDetailsService")
@Slf4j
public class AdminServiceImpl implements AdminService, UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Administrador> listarAdministradores() {
        return (List<Administrador>) adminRepository.findAll();
    }

    @Override
    public void guardar(Administrador administrador) {
        adminRepository.save(administrador);
    }

    @Override
    public void eliminar(Administrador administrador) {
        adminRepository.delete(administrador);
    }

    @Override
    public Administrador encontrarAdministrador(Administrador administrador) {

        return adminRepository.findById(administrador.getId_admin()).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //SOLO ADMINS
        log.info("ENTREEE");
        Administrador admin = adminRepository.findByEmail(email);



        //log.info("Admin obtenido: "+admin.toString());

        List<GrantedAuthority> rol = new ArrayList<>();
        rol.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        UserDetails userDet = new User(admin.getEmail(), admin.getPassword(), rol);


        log.info("ENTREEE2");
        return userDet;

    }
}
