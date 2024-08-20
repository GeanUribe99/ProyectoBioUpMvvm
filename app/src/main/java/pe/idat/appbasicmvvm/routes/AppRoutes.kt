package pe.idat.appbasicmvvm.routes

sealed class AppRoutes(val path: String) {
    object loginScreen: AppRoutes("loginScreen")
    object homeScreen: AppRoutes("homeScreen/{usuario}"){
        fun paramHome(usuario: String) = "homeScreen/$usuario"
    }
    object historiaclinicaScreen: AppRoutes("historiaclinicaScreen/{usuario}"){
        fun paramHc(usuario: String) = "historiaclinicaScreen/$usuario"
    }
    object historiadiariaScreen: AppRoutes("historiadiariaScreen/{dni}"){
    }
    object infouserScreen: AppRoutes("infouserScreen/{usuario}"){
        fun paramInfouser(usuario: String) = "infouserScreen/$usuario"
    }

}