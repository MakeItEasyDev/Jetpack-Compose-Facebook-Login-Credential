package com.jetpack.facebookintegration

import androidx.compose.runtime.Immutable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.Profile
import com.facebook.ProfileTracker

class LoginViewModel: ViewModel() {
    private val profileTracker =
        object : ProfileTracker() {
            override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {
                if (currentProfile != null) {
                    this@LoginViewModel.updateProfile(currentProfile)
                } else {
                    this@LoginViewModel.resetProfile()
                }
            }
        }

    private val _profileViewState = MutableLiveData(ProfileViewState(Profile.getCurrentProfile()))

    val profileViewState: LiveData<ProfileViewState> = _profileViewState

    override fun onCleared() {
        profileTracker.stopTracking()
        super.onCleared()
    }

    private fun updateProfile(profile: Profile) {
        _profileViewState.value = _profileViewState.value?.copy(profile = profile)
    }

    private fun resetProfile() {
        _profileViewState.value = _profileViewState.value?.copy(profile = null)
    }
}

@Immutable
data class ProfileViewState(
    val profile: Profile? = null
)






