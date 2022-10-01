package com.hefesto.juntasaccioncomunal.logica.componentes.login.repositorios

import javax.inject.Inject

interface LoginRepositorio {

}

class LoginRepositorioImpl constructor(
    @JvmField @Inject var loginApiDatasource: LoginApiDatasource,
    @JvmField @Inject var loginCacheDatasource: LoginCacheDatasource,
    @JvmField @Inject var loginDBDatasource: LoginDBDatasource,
    @JvmField @Inject var loginSharedPreferencesDatasource: LoginSharedPreferencesDatasource
) : LoginRepositorio {
}