package com.takusemba.jethub.developer

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.takusemba.jethub.base.ui.theme.JethubTheme
import com.takusemba.jethub.base.viewmodel.NavigationViewModel
import com.takusemba.jethub.base.viewmodel.SystemViewModel
import com.takusemba.jethub.di.DeveloperAppModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class DeveloperFragment : Fragment(R.layout.fragment_developer) {

  @Inject
  lateinit var developerViewModelProviderFactory: DeveloperViewModelProviderFactory

  private val developerViewModel: DeveloperViewModel by viewModels { developerViewModelProviderFactory }
  private val systemViewModel: SystemViewModel by activityViewModels()
  private val navigationViewModel: NavigationViewModel by activityViewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    DaggerDeveloperComponent.builder()
      .fragment(this)
      .appDependencies(
        EntryPointAccessors.fromApplication(
          requireContext().applicationContext,
          DeveloperAppModuleDependencies::class.java
        )
      )
      .build()
      .inject(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    view.findViewById<ComposeView>(R.id.compose_view).setContent {
      JethubTheme(systemViewModel.isNightMode()) {
        DeveloperScreen(developerViewModel, navigationViewModel)
      }
    }
  }
}
