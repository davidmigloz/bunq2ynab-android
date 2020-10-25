package app.bunq2ynab.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.bunq2ynab.databinding.StartFragmentBinding
import app.bunq2ynab.start.StartFragmentDirections.Companion.actionStartFragmentToOnboardingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment() {

    private val vm: StartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        StartFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = vm
            setupViews(this)
            return root
        }
    }

    private fun setupViews(binding: StartFragmentBinding) {
        binding.btnStart.setOnClickListener {
            findNavController().navigate(actionStartFragmentToOnboardingFragment())
        }
    }
}
