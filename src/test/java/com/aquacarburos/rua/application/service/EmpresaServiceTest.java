package com.aquacarburos.rua.application.service;

import com.aquacarburos.rua.domain.model.Empresa;
import com.aquacarburos.rua.domain.model.RolNombre;
import com.aquacarburos.rua.domain.model.exception.EmpresaNotFoundException;
import com.aquacarburos.rua.domain.repository.EmpresaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaService empresaService;

    private Empresa empresa;

    @BeforeEach
    void setUp() {
        empresa = new Empresa();
        empresa.setId(1L);
        empresa.setNit("123456789");
        empresa.setNombre("Empresa Test");
        empresa.setUsuario("usuario_test");
        empresa.setClave("password123");
        empresa.setRol(RolNombre.ROL_CLIENTE);
        empresa.setPago(true);
    }

    @Test
    @DisplayName("Crear empresa asigna rol por defecto ROL_CLIENTE y guarda clave en texto plano")
    void crear_sinRol_asignaRolClienteYGuardaClavePlano() {
        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setNit("987654321");
        nuevaEmpresa.setNombre("Nueva Empresa");
        nuevaEmpresa.setUsuario("nuevo_usuario");
        nuevaEmpresa.setClave("mi_clave");

        when(empresaRepository.save(any(Empresa.class))).thenAnswer(i -> {
            Empresa e = i.getArgument(0);
            e.setId(1L);
            return e;
        });

        Empresa resultado = empresaService.crear(nuevaEmpresa);

        assertEquals(RolNombre.ROL_CLIENTE, resultado.getRol());
        assertEquals("mi_clave", resultado.getClave());
        verify(empresaRepository).save(any(Empresa.class));
    }

    @Test
    @DisplayName("Crear empresa con rol específico mantiene ese rol y guarda clave en texto plano")
    void crear_conRolAdmin_mantieneRolAdminYGuardaClavePlano() {
        Empresa adminEmpresa = new Empresa();
        adminEmpresa.setNit("111222333");
        adminEmpresa.setNombre("Admin Empresa");
        adminEmpresa.setUsuario("admin_user");
        adminEmpresa.setClave("admin_pass");
        adminEmpresa.setRol(RolNombre.ROL_ADMIN);

        when(empresaRepository.save(any(Empresa.class))).thenAnswer(i -> i.getArgument(0));

        Empresa resultado = empresaService.crear(adminEmpresa);

        assertEquals(RolNombre.ROL_ADMIN, resultado.getRol());
        assertEquals("admin_pass", resultado.getClave());
    }

    @Test
    @DisplayName("Buscar empresa por ID existente retorna la empresa")
    void buscarPorId_existente_retornaEmpresa() {
        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));

        Empresa resultado = empresaService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Empresa Test", resultado.getNombre());
    }

    @Test
    @DisplayName("Buscar empresa por ID inexistente lanza EmpresaNotFoundException")
    void buscarPorId_noExiste_lanzaExcepcion() {
        when(empresaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EmpresaNotFoundException.class, () -> empresaService.buscarPorId(99L));
    }

    @Test
    @DisplayName("Buscar empresa por NIT existente retorna la empresa")
    void buscarPorNit_existente_retornaEmpresa() {
        when(empresaRepository.findByNit("123456789")).thenReturn(Optional.of(empresa));

        Empresa resultado = empresaService.buscarPorNit("123456789");

        assertNotNull(resultado);
        assertEquals("123456789", resultado.getNit());
    }

    @Test
    @DisplayName("Listar todas las empresas retorna lista completa")
    void listarTodas_retornaLista() {
        Empresa otra = new Empresa();
        otra.setId(2L);
        otra.setNombre("Otra Empresa");

        when(empresaRepository.findAll()).thenReturn(Arrays.asList(empresa, otra));

        List<Empresa> resultado = empresaService.listarTodas();

        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Actualizar empresa cambia campos y guarda nueva clave en texto plano")
    void actualizar_conNuevaClave_guardaClavePlano() {
        Empresa datosActualizados = new Empresa();
        datosActualizados.setNit("nuevo_nit");
        datosActualizados.setNombre("Nombre Actualizado");
        datosActualizados.setUsuario("usuario_nuevo");
        datosActualizados.setClave("nueva_clave");
        datosActualizados.setPago(false);

        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresa));
        when(empresaRepository.save(any(Empresa.class))).thenAnswer(i -> i.getArgument(0));

        Empresa resultado = empresaService.actualizar(1L, datosActualizados);

        assertEquals("nuevo_nit", resultado.getNit());
        assertEquals("Nombre Actualizado", resultado.getNombre());
        assertEquals("nueva_clave", resultado.getClave());
        assertFalse(resultado.isPago());
    }
}
