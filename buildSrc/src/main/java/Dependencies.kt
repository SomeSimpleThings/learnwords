import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.somethingsimple.learnwords"
    const val compile_sdk = 30
    const val min_sdk = 21
    const val target_sdk = 30
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"

    //Features
//    const val historyScreen = ":historyScreen"
}

object Versions {
    const val retrofit_version = "2.9.0"
    const val okhttp_version = "4.9.1"
    const val koin_version = "3.1.2"
    const val room_version = "2.3.0"
    const val coroutines_adapter_version = "0.9.2"
    const val coroutines_version = "1.4.1"
    const val glide = "4.9.0"
    const val jUnit: String = "4.13.2"
    const val runner: String = "1.1.3"
    const val espressoCore: String = "3.4.0"
    const val appcompat: String = "1.3.1"
    const val play: String = "1.8.1"
    const val material: String = "1.4.0"
    const val nav_version = "2.3.5"


}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
}

object Play {
    const val core = "com.google.android.play:core-ktx:${Versions.play}"
}

object Navigation {
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    const val navDynamic =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.nav_version}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object Retrofit {
    //retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val retrofitCoroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutines_adapter_version}"

}

object Coroutines {
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
}

object Koin {
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin_version}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room_version}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room_version}"
}

object OkHttp {
    const val okHttpBom = "com.squareup.okhttp3:okhttp-bom:${Versions.okhttp_version}"
    const val okhttp = "com.squareup.okhttp3:okhttp"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor"

}