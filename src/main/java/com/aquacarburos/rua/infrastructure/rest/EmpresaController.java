package com.aquacarburos.rua.infrastructure.rest;

import com.aquacarburos.rua.application.dto.EmpresaRequest;
import com.aquacarburos.rua.application.dto.EmpresaResponse;
import com.aquacarburos.rua.application.dto.PageResponse;
import com.aquacarburos.rua.application.service.EmpresaService;
import com.aquacarburos.rua.domain.model.Empresa;
import com.aquacarburos.rua.infrastructure.rest.mapper.EmpresaRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empresas")
@RequiredArgsConstructor
@Tag(name = "Empresas", description = "Operaciones relacionadas con la gestión de empresas y gasolineras")
public class EmpresaController {

    private final EmpresaService empresaService;
    private final EmpresaRestMapper empresaRestMapper;

    @PostMapping
    @Operation(summary = "Crear una nueva empresa/gasolinera", description = "Permite registrar una estación de servicio")
    @ApiResponse(responseCode = "201", description = "Empresa creada exitosamente")
    public ResponseEntity<EmpresaResponse> crear(@Valid @RequestBody EmpresaRequest empresaRequest) {
        Empresa empresa = empresaRestMapper.toDomain(empresaRequest);
        Empresa creada = empresaService.crear(empresa);
        return new ResponseEntity<>(empresaRestMapper.toResponse(creada), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar empresas paginadas", description = "Retorna una lista paginada de las gasolineras registradas.")
    public ResponseEntity<PageResponse<EmpresaResponse>> listar(
            @Parameter(description = "Número de página (0-indexed)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Campo por el cual ordenar") @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Dirección del ordenamiento (asc/desc)") @RequestParam(defaultValue = "asc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") 
                ? Sort.by(sortBy).descending() 
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<Empresa> empresaPage = empresaService.listarTodas(pageable);
        
        PageResponse<EmpresaResponse> response = PageResponse.<EmpresaResponse>builder()
            .content(empresaRestMapper.toResponseList(empresaPage.getContent()))
                .page(empresaPage.getNumber())
                .size(empresaPage.getSize())
                .totalElements(empresaPage.getTotalElements())
                .totalPages(empresaPage.getTotalPages())
                .first(empresaPage.isFirst())
                .last(empresaPage.isLast())
                .build();
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener empresa por ID")
    @ApiResponse(responseCode = "200", description = "Encontrado")
    @ApiResponse(responseCode = "404", description = "No encontrado")
    public ResponseEntity<EmpresaResponse> obtener(@PathVariable Long id) {
        Empresa empresa = empresaService.buscarPorId(id);
        return ResponseEntity.ok(empresaRestMapper.toResponse(empresa));
    }

    @GetMapping("/nit/{nit}")
    @Operation(summary = "Obtener empresa por NIT")
    @ApiResponse(responseCode = "200", description = "Encontrado")
    @ApiResponse(responseCode = "404", description = "No encontrado")
    public ResponseEntity<EmpresaResponse> obtenerPorNit(@PathVariable String nit) {
        Empresa empresa = empresaService.buscarPorNit(nit);
        return ResponseEntity.ok(empresaRestMapper.toResponse(empresa));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de una empresa")
    public ResponseEntity<EmpresaResponse> actualizar(@PathVariable Long id,
            @Valid @RequestBody EmpresaRequest empresaRequest) {
        Empresa datosNuevos = empresaRestMapper.toDomain(empresaRequest);
        datosNuevos.setId(id);
        Empresa actualizada = empresaService.actualizar(id, datosNuevos);
        return ResponseEntity.ok(empresaRestMapper.toResponse(actualizada));
    }

}