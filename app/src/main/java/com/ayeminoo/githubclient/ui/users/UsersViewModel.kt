package com.ayeminoo.githubclient.ui.users

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ViewModel(){



}