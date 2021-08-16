package com.somethingsimple.learnwords

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.somethingsimple.learnwords.databinding.ActivityMainBinding
import com.somethingsimple.learnwords.ui.words.list.WordlistFragment

private const val REQUEST_CODE = 42
private const val HISTORY_FRAGMENT_PATH = "com.somethingsimple.dynamichistory.ui.HistoryFragment"
private const val HISTORY_FEATURE_NAME = "dynamicHistory"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appUpdateManager: AppUpdateManager
    private lateinit var splitInstallManager: SplitInstallManager
    private val stateUpdatedListener: InstallStateUpdatedListener =
        InstallStateUpdatedListener { state ->
            state?.let {
                if (it.installStatus() == InstallStatus.DOWNLOADED) {
                    popupSnackbarForCompleteUpdate()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.fab.setOnClickListener {
            startHistoryScreen()
        }
        val view = binding.root
        setContentView(view)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, WordlistFragment.newInstance())
            .commit()
        checkForUpdates()
    }

    private fun startHistoryScreen() {

        splitInstallManager = SplitInstallManagerFactory.create(applicationContext)
        val request =
            SplitInstallRequest
                .newBuilder()
                .addModule(HISTORY_FEATURE_NAME)
                .build()

        splitInstallManager
            .startInstall(request)
            .addOnSuccessListener {
                val fragment = supportFragmentManager
                    .getFragment(HISTORY_FRAGMENT_PATH)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            }
            .addOnFailureListener {
                Toast.makeText(
                    applicationContext,
                    "Couldn't download feature: " + it.message,
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager
            .appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->
                if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                    popupSnackbarForCompleteUpdate()
                }
                if (appUpdateInfo.updateAvailability()
                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                ) {
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        this,
                        REQUEST_CODE
                    )
                }
            }
    }

    private fun checkForUpdates() {
        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateIntent ->
            if (appUpdateIntent.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateIntent.isUpdateTypeAllowed(IMMEDIATE)
            ) {
                appUpdateManager.registerListener(stateUpdatedListener)
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateIntent,
                    IMMEDIATE,
                    this,
                    REQUEST_CODE
                )
            }
        }
    }

    private fun popupSnackbarForCompleteUpdate() {
        Snackbar.make(
            binding.container,
            "An update has just been downloaded.",
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAction("RESTART") { appUpdateManager.completeUpdate() }
            show()
        }
    }
}

fun FragmentManager.getFragment(className: String, args: Bundle? = null): Fragment {
    val clazz = Class.forName(className)
    return findFragmentByTag(clazz.name) ?: return (clazz.getConstructor()
        .newInstance() as Fragment).apply {
        args?.let {
            it.classLoader = javaClass.classLoader
            arguments = args
        }
    }
}