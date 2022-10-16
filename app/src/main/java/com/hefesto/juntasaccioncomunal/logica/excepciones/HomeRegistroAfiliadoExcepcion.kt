package com.hefesto.juntasaccioncomunal.logica.excepciones

import com.hefesto.juntasaccioncomunal.R

class NoHaCreadoModeloDeDatosBasicosParaRegistroExcepcion : LogicaExcepcion(stringResTitulo = R.string.registro_afiliado_jac, stringResMensaje = R.string.no_ha_ingresado_nombre, mensaje = "No se ha creado el modelo")
class NoHaCreadoModeloDeContactoAfiliadoParaRegistroExcepcion: LogicaExcepcion(stringResTitulo = R.string.registro_afiliado_jac, stringResMensaje = R.string.el_campo_del_correo_vacio, mensaje = "No se ha creado el modelo")
//region datos basicos
//region nombres
class NoHaIngresadoNombreRegistroHomeExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado nombre",
    stringResTitulo = R.string.registro_afiliado_jac,
    stringResMensaje = R.string.no_ha_ingresado_nombre,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NombreAfiliadoNoValidoHomeExcepcion : LogicaExcepcion(
    mensaje = "El nombre del afiliado no es valido",
    stringResMensaje = R.string.el_nombre_afiliado_invalido,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)
//endregion

//region Apellidos
class NoHaIngresadoApellidoRegistroHomeExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado apellido",
    stringResTitulo = R.string.registro_afiliado_jac,
    stringResMensaje = R.string.no_ha_ingresado_apellidos,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class ApellidoAfiliadoNoValidoHomeExcepcion : LogicaExcepcion(
    mensaje = "El apellido del afiliado no es valido",
    stringResMensaje = R.string.apellido_invalido,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)
//endregion

//region fechaNacimiento
class FechaNacimientoAfiliadoNoIngresadoHomeExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado una fecha de nacimiento",
    stringResMensaje = R.string.no_ha_ingresado_una_fecha_nacimiento,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class FechaNacimientoAfiliadoNoValidoHomeExcepcion : LogicaExcepcion(
    mensaje = "Fecha nacimiento invalido",
    stringResMensaje = R.string.fecha_nacimiento_invalido,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)
//endregion

//region documento
class NoHaIngresadoTipoDocumentoHomeExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado un tipo de documento",
    stringResMensaje = R.string.no_ha_ingresado_tipo_documento,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoHaIngresadoNumeroDocumentoHomeExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado numero documento",
    stringResMensaje = R.string.no_ha_ingresado_numero_documento,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class NoHaNumeroDocumentoInvalidoHomeExcepcion : LogicaExcepcion(
    mensaje = "No ha ingresado numero documento",
    stringResMensaje = R.string.numero_documento_invalido,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

//endregion
//endregion

//region contacto
//region correo
class SinCorreoRegistrarAfiliadHomeoExcepcion : LogicaExcepcion (
    mensaje = "No ha ingresado un correo electronico valido",
    stringResMensaje = R.string.el_campo_del_correo_vacio,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class CorreoInvalidoLoginHomeExcepcion :  LogicaExcepcion(
    mensaje = "No es un correo valido",
    stringResMensaje = R.string.no_ha_ingresado_un_correo_electronico_valido,
    stringResTitulo = R.string.iniciar_sesion,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)
//endregion

//region direccion

class NoHaIngresadoUnaDireccionAfiliadoHomeExcepcion : LogicaExcepcion(
    mensaje = "no ha ingresado una direccoin",
    stringResMensaje = R.string.no_ha_ingresado_una_direccion,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class DireccinoInvalidoRegistroAfiliadoHomeExcepcion :  LogicaExcepcion(
    mensaje = "No es un correo valido",
    stringResMensaje = R.string.no_ingreso_una_direccion_correcta,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)
//endregion

//region telefono
class CampoTelefonoVacioRegistroAfiliadosHomeExcepcion : LogicaExcepcion(
    mensaje = "No ha llenado el campo de nombres",
    stringResMensaje = R.string.campo_telefono_vacio,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

class ElTelefonoNoEsValidoRegistroAfiliadoHomeExcepcion : LogicaExcepcion(
    mensaje = "El telefono no es valido",
    stringResMensaje = R.string.el_telefono_no_es_valido,
    stringResTitulo = R.string.registro_afiliado_jac,
    tipoExcepcion = TiposExcepciones.GENERADO_USUARIO
)

//endregion

//endregion