package app.bunq2ynab.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.bunq2ynab.databinding.OnboardingFragmentBinding
import app.bunq2ynab.onboarding.OnboardingFragmentDirections.Companion.actionOnboardingFragmentToConnectBunqFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {

    private val onboardingViewModel: OnboardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        OnboardingFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = onboardingViewModel
            setupViews(this)
            return root
        }
    }

    private fun setupViews(binding: OnboardingFragmentBinding) {
        binding.onboarding.setOnClickListener {
            findNavController().navigate(actionOnboardingFragmentToConnectBunqFragment())
        }
    }
}
