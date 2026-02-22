package com.aquacarburos.rua.infrastructure.rest;

import com.aquacarburos.rua.application.dto.FormRuaRequest;
import com.aquacarburos.rua.application.dto.FormRuaResponse;
import com.aquacarburos.rua.application.dto.PageResponse;
import com.aquacarburos.rua.application.service.FormRuaService;
import com.aquacarburos.rua.domain.model.FormRua;
import com.aquacarburos.rua.infrastructure.rest.mapper.FormRuaRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/form-rua")
@RequiredArgsConstructor
@Tag(name = "Form RUA", description = "Operaciones para guardar y consultar formularios RUA")
public class FormRuaController {

    private final FormRuaService formRuaService;
    private final FormRuaRestMapper formRuaRestMapper;

    @PostMapping
    @Operation(summary = "Guardar formulario RUA")
    @ApiResponse(responseCode = "201", description = "Formulario creado exitosamente")
    public ResponseEntity<FormRuaResponse> crear(@Valid @RequestBody FormRuaRequest request) {
        String nitNormalizado = normalizarNit(request.getNit());
        request.setNit(nitNormalizado);

        FormRua formRua = formRuaRestMapper.toDomain(request);
        FormRua creado = formRuaService.crear(formRua);
        return new ResponseEntity<>(formRuaRestMapper.toResponse(creado), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar formularios RUA paginados")
    public ResponseEntity<PageResponse<FormRuaResponse>> listar(
            @Parameter(description = "Número de página (0-indexed)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Campo por el cual ordenar") @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Dirección del ordenamiento (asc/desc)") @RequestParam(defaultValue = "asc") String sortDir) {
        
        Sort sort = sortDir.equalsIgnoreCase("desc") 
                ? Sort.by(sortBy).descending() 
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<FormRua> formRuaPage = formRuaService.listarTodos(pageable);
        
        PageResponse<FormRuaResponse> response = PageResponse.<FormRuaResponse>builder()
                .content(formRuaRestMapper.toResponseList(formRuaPage.getContent()))
                .page(formRuaPage.getNumber())
                .size(formRuaPage.getSize())
                .totalElements(formRuaPage.getTotalElements())
                .totalPages(formRuaPage.getTotalPages())
                .first(formRuaPage.isFirst())
                .last(formRuaPage.isLast())
                .build();
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener formulario RUA por ID")
    @ApiResponse(responseCode = "200", description = "Encontrado")
    @ApiResponse(responseCode = "404", description = "No encontrado")
    public ResponseEntity<FormRuaResponse> obtener(@PathVariable Long id) {
        FormRua formulario = formRuaService.buscarPorId(id);
        return ResponseEntity.ok(formRuaRestMapper.toResponse(formulario));
    }

    private String normalizarNit(String nit) {
        if (nit == null) {
            return "";
        }
        return nit.replaceAll("\\D", "");
    }
}
