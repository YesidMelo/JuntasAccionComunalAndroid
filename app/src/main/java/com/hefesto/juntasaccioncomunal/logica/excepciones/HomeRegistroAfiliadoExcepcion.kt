package com.hefesto.juntasaccioncomunal.logica.excepciones

import com.hefesto.juntasaccioncomunal.R

class NoHaCreadoModeloDeDatosBasicosParaRegistroExcepcion : LogicaExcepcion(stringResTitulo = R.string.registro_afiliado_jac, stringResMensaje = R.string.no_ha_ingresado_nombre, mensaje = "No se ha creado el modelo")

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