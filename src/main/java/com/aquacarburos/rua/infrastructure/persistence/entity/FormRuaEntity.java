package com.aquacarburos.rua.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "form_rua", schema = "schema_rua")
@Data
public class FormRuaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nit", nullable = false)
    private String nit;

    @Column(name = "nombre_empresa", nullable = false)
    private String nombreEmpresa;

    @Column(name = "direccion", nullable = true)
    private String direccion;

    @Column(name = "telefono", nullable = true)
    private String telefono;

    @Column(name = "correo", nullable = true)
    private String correo;

    @Column(name = "fecha_inicio_actividades", nullable = false)
    private LocalDate fechaInicioActividades;

    @Column(name = "usuario_contrasena_activos_rua", nullable = false)
    private String usuarioActivosRua;

     @Column(name = "contrasena_activos_rua", nullable = false)
    private String contrasenaActivosRua;

    @Column(name = "area_total_metros_cuadrados", nullable = false, precision = 18, scale = 2)
    private BigDecimal areaTotalMetrosCuadrados;

    @Column(name = "numero_empleados", nullable = false)
    private Integer numeroEmpleados;

    @Column(name = "contingencias_ano_reporte", nullable = false)
    private String contingenciasAnoReporte;

    @Column(name = "promedio_horas_dia_funcionamiento", nullable = false, precision = 10, scale = 2)
    private BigDecimal promedioHorasDiaFuncionamiento;

    @Column(name = "numero_semanas_funcionamiento_periodo_balance")
    private String numeroSemanasFuncionamientoPeriodoBalance;

    @Column(name = "promedio_numero_dias_semana_funcionamiento")
    private String promedioNumeroDiasSemanaFuncionamiento;

    @Column(name = "promedio_numero_turnos_dia")
    private String promedioNumeroTurnosDia;

    @Column(name = "tipo_tramite")
    private String tipoTramite;

    @Column(name = "tramite_titularidad_tercero")
    private String tramiteTitularidadTercero;

    @Column(name = "ventas_combustible_galones_ano_miles")
    private String ventasCombustibleGalonesAnoMiles;

    @Column(name = "compras_combustible_galones_ano_miles")
    private String comprasCombustibleGalonesAnoMiles;

    @Column(name = "existencias_combustible_31_diciembre")
    private String existenciasCombustible31Diciembre;

    @Column(name = "otras_materias_primas_aceites_consumibles_urea_compras_ventas", columnDefinition = "TEXT")
    private String otrasMateriasPrimasAceitesConsumiblesUreaComprasVentas;

    @Column(name = "consumo_anual_energia_electrica_kwh")
    private String consumoAnualEnergiaElectricaKwh;

    @Column(name = "equipos_combustion_descripcion", columnDefinition = "TEXT")
    private String equiposCombustionDescripcion;

    @Column(name = "nombre_equipo")
    private String nombreEquipo;

    @Column(name = "descripcion_equipo", columnDefinition = "TEXT")
    private String descripcionEquipo;

    @Column(name = "capacidad_nominal")
    private String capacidadNominal;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @Column(name = "tiempo_operacion_horas_ano")
    private String tiempoOperacionHorasAno;

    @Column(name = "marca_equipo")
    private String marcaEquipo;

    @Column(name = "modelo_fabricacion")
    private String modeloFabricacion;

    @Column(name = "ano_fabricacion")
    private String anoFabricacion;

    @Column(name = "fuente_captacion_agua")
    private String fuenteCaptacionAgua;

    @Column(name = "consumo_anual_agua_m3")
    private String consumoAnualAguaM3;

    @Column(name = "nombre_empresa_acueducto")
    private String nombreEmpresaAcueducto;

    @Column(name = "salidas_agua")
    private String salidasAgua;

    @Column(name = "tramite_ambiental_reportado", columnDefinition = "TEXT")
    private String tramiteAmbientalReportado;

    @Column(name = "nombre_receptor_descarga_agua")
    private String nombreReceptorDescargaAgua;

    @Column(name = "nombre_empresa_tercero_kg")
    private String nombreEmpresaTerceroKg;

    @Column(name = "area_tratamiento_suelo")
    private String areaTratamientoSuelo;

    @Column(name = "descripcion_punto_descarga", columnDefinition = "TEXT")
    private String descripcionPuntoDescarga;

    @Column(name = "coordenadas_punto_descarga")
    private String coordenadasPuntoDescarga;

    @Column(name = "tipo_salida")
    private String tipoSalida;

    @Column(name = "procedencia_vertimiento_descarga")
    private String procedenciaVertimientoDescarga;

    @Column(name = "clase_vertimiento_descarga")
    private String claseVertimientoDescarga;

    @Column(name = "periodo_descarga_dias_ano")
    private String periodoDescargaDiasAno;

    @Column(name = "horas_vertimiento_periodo_balance")
    private String horasVertimientoPeriodoBalance;

    @Column(name = "volumen_mensual_vertido_m3")
    private String volumenMensualVertidoM3;

    @Column(name = "metodo_determinacion_volumen_vertido")
    private String metodoDeterminacionVolumenVertido;

    @Column(name = "tiene_sistema_tratamiento")
    private String tieneSistemaTratamiento;

    @Column(name = "volumen_total_tratado_anual_m3")
    private String volumenTotalTratadoAnualM3;

    @Column(name = "sistema_tratamiento")
    private String sistemaTratamiento;

    @Column(name = "informe_caracterizacion")
    private String informeCaracterizacion;

    @Column(name = "sistema_tratamiento_aguas_residuales")
    private String sistemaTratamientoAguasResiduales;

    @Column(name = "trampa_grasas_caudal")
    private String trampaGrasasCaudal;

    @Column(name = "caudal_entrada_ls")
    private String caudalEntradaLs;

    @Column(name = "caudal_salida_ls")
    private String caudalSalidaLs;

    @Column(name = "longitud_rejillas_perimetrales", columnDefinition = "TEXT")
    private String longitudRejillasPerimetrales;

    @Column(name = "area_canopy", columnDefinition = "TEXT")
    private String areaCanopy;

    @Column(name = "pozo_septico_entrada_salida_agua")
    private String pozoSepticoEntradaSalidaAgua;

    @Column(name = "area_disposicion_final", columnDefinition = "TEXT")
    private String areaDisposicionFinal;

    @Column(name = "reporte_filtros_contaminados")
    private String reporteFiltrosContaminados;

    @Column(name = "reporte_trapos_absorbentes_contaminados")
    private String reporteTraposAbsorbentesContaminados;

    @Column(name = "medio_almacenamiento_residuos_peligrosos", columnDefinition = "TEXT")
    private String medioAlmacenamientoResiduosPeligrosos;

    @Column(name = "medio_transporte_residuos")
    private String medioTransporteResiduos;

    @Column(name = "plan_gestion_integral_residuos_peligrosos", columnDefinition = "TEXT")
    private String planGestionIntegralResiduosPeligrosos;

    @Column(name = "realiza_emisiones_aire")
    private String realizaEmisionesAire;

    @Column(name = "aprovechamiento_forestal")
    private String aprovechamientoForestal;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion", nullable = false)
    private LocalDateTime fechaModificacion;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaModificacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.fechaModificacion = LocalDateTime.now();
    }
}
