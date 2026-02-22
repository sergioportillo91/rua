package com.aquacarburos.rua.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FormRuaRequest {

    @NotBlank(message = "El NIT es obligatorio")
    private String nit;

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    private String nombreEmpresa;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;

    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @NotNull(message = "La fecha de inicio de actividades es obligatoria")
    private LocalDate fechaInicioActividades;

    @NotBlank(message = "El usuario activo en RUA es obligatorio")
    private String usuarioActivosRua;

    @NotBlank(message = "La contraseña activa en RUA es obligatoria")
    private String contrasenaActivosRua;

    @NotNull(message = "El área total en metros cuadrados es obligatoria")
    private BigDecimal areaTotalMetrosCuadrados;

    @NotNull(message = "El número de empleados es obligatorio")
    private Integer numeroEmpleados;

    private String contingenciasAnoReporte;

    private BigDecimal promedioHorasDiaFuncionamiento;

    private String numeroSemanasFuncionamientoPeriodoBalance;

    private String promedioNumeroDiasSemanaFuncionamiento;

    private String promedioNumeroTurnosDia;

    private String tipoTramite;

    private String tramiteTitularidadTercero;

    private String ventasCombustibleGalonesAnoMiles;

    private String comprasCombustibleGalonesAnoMiles;

    private String existenciasCombustible31Diciembre;

    private String otrasMateriasPrimasAceitesConsumiblesUreaComprasVentas;

    private String consumoAnualEnergiaElectricaKwh;

    private String equiposCombustionDescripcion;

    private String nombreEquipo;

    private String descripcionEquipo;

    private String capacidadNominal;

    private String unidadMedida;

    private String tiempoOperacionHorasAno;

    private String marcaEquipo;

    private String modeloFabricacion;

    private String anoFabricacion;

    private String fuenteCaptacionAgua;

    private String consumoAnualAguaM3;

    private String nombreEmpresaAcueducto;

    private String salidasAgua;

    private String tramiteAmbientalReportado;

    private String nombreReceptorDescargaAgua;

    private String nombreEmpresaTerceroKg;

    private String areaTratamientoSuelo;

    private String descripcionPuntoDescarga;

    private String coordenadasPuntoDescarga;

    private String tipoSalida;

    private String procedenciaVertimientoDescarga;

    private String claseVertimientoDescarga;

    private String periodoDescargaDiasAno;

    private String horasVertimientoPeriodoBalance;

    private String volumenMensualVertidoM3;

    private String metodoDeterminacionVolumenVertido;

    private String tieneSistemaTratamiento;

    private String volumenTotalTratadoAnualM3;

    private String sistemaTratamiento;

    private String informeCaracterizacion;

    private String sistemaTratamientoAguasResiduales;

    private String trampaGrasasCaudal;

    private String caudalEntradaLs;

    private String caudalSalidaLs;

    private String longitudRejillasPerimetrales;

    private String areaCanopy;

    private String pozoSepticoEntradaSalidaAgua;

    private String areaDisposicionFinal;

    private String reporteFiltrosContaminados;

    private String reporteTraposAbsorbentesContaminados;

    private String medioAlmacenamientoResiduosPeligrosos;

    private String medioTransporteResiduos;

    private String planGestionIntegralResiduosPeligrosos;

    private String realizaEmisionesAire;

    private String aprovechamientoForestal;
}
