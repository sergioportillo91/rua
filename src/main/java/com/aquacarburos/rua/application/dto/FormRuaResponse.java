package com.aquacarburos.rua.application.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class FormRuaResponse {

    private Long id;

    private String nit;

    private String nombreEmpresa;

    private String direccion;

    private String telefono;

    private String correo;

    private LocalDate fechaInicioActividades;

    private String usuarioActivosRua;

    private String contrasenaActivosRua;

    private BigDecimal areaTotalMetrosCuadrados;

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

    private String pozoSepticoEntradaSalidaAgua;

    private String areaDisposicionFinal;

    private String reporteFiltrosContaminados;

    private String reporteTraposAbsorbentesContaminados;

    private String medioTransporteResiduos;

    private String realizaEmisionesAire;

    private String aprovechamientoForestal;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;
    private String usoSuelo;
    private String diagramaActividadPdf;
    private String certificadoGeneradorRespel2025;
    private String planGestionIntegralResiduosPeligrosos;
}
