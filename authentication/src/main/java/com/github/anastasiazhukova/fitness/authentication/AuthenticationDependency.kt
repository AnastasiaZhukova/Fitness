package com.github.anastasiazhukova.fitness.authentication

import com.github.anastasiazhukova.fitness.authentication.auth.FirebaseAuthenticationManager
import com.github.anastasiazhukova.fitness.authentication.auth.IAuthenticationManager
import com.github.anastasiazhukova.fitness.authentication.user.FirebaseUserIdHolder
import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import org.koin.dsl.module

object AuthenticationDependency {
    val module = module {
        factory<IAuthenticationManager> { FirebaseAuthenticationManager() }
        factory<IUserIdHolder> { FirebaseUserIdHolder() }
    }
}
