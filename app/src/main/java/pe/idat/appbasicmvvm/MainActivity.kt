package pe.idat.appbasicmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import pe.idat.appbasicmvvm.auth.AuthViewModel
import pe.idat.appbasicmvvm.auth.loginScreen
import pe.idat.appbasicmvvm.core.routes.AppRoutes
import pe.idat.appbasicmvvm.home.ViewModel.HomeViewModel

import pe.idat.appbasicmvvm.home.view.homeScreen

import pe.idat.appbasicmvvm.ui.theme.AppbasicmvvmTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val homeViewModel: HomeViewModel by viewModels()
    val authViewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppbasicmvvmTheme {
                val navigation = rememberNavController()
                NavHost(navController = navigation,
                    startDestination = AppRoutes.loginScreen.path,
                    builder = {
                        composable(AppRoutes.loginScreen.path){
                            loginScreen(authViewModel, navigation)
                        }
                        /*composable(AppRoutes.historiadiariaScreen.path){
                            historiadiariaScreen(homeViewModel, navigation)
                        }
                        composable(AppRoutes.historiaclinicaScreen.path){
                            historiaclinicaScreen(homeViewModel, navigation)
                        }
                        composable(AppRoutes.historiadiariaScreen.path){
                            loginScreen(homeViewModel, navigation)
                        }*/
                        composable(
                            AppRoutes.homeScreen.path,
                            //AppRoutes.historiadiariaScreen.path,
                            arguments = listOf(navArgument("usuario")
                            {type = NavType.StringType} )
                        ){
                                params ->
                            homeScreen(homeViewModel,navigation,params.arguments?.getString("usuario") ?: "")
                        }
                    })


                /*
                val navigation = rememberNavController()
                    NavHost(navController = navigation,
                        startDestination = AppRoutes.loginScreen.path,
                        builder = {
                            composable(AppRoutes.loginScreen.path){
                                authScreen(AuthViewModel(), navigation)
                            }
                            composable(
                                AppRoutes.historiadiariaScreen.path,
                                arguments = listOf(navArgument("usuario")
                                {type = NavType.StringType} )
                            ){
                                params ->
                                historiadiariaScreen(homeViewModel,params.arguments?.getString("usuario") ?: ""
                                )
                            }
                        })
                */
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppbasicmvvmTheme {

    }
}
/*
@Composable
fun MainNavHost(
    loginViewModel: AuthViewModel,
    homeViewModel: HomeViewModel
){
    val navigation = rememberNavController()
    NavHost(navController = navigation, startDestination = Ruta.loginScreen.path
    ) {
        composable(Ruta.loginScreen.path) {
            loginScreen(loginViewModel, navigation)
        }
        composable(Ruta.registroScreen.path) {
            registroScreen(registroViewModel, navigation)
        }
        composable(Ruta.homeScreen.path) {
            homeScreen(homeViewModel, navigation)
        }
        composable(Ruta.reservarcitasScreen.path) {
            ReservarCitasScreen(context = LocalContext.current)
        }
    }
}

*/