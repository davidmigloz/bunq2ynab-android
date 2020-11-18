package app.bunq4ynab.bunq.connect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import app.bunq4ynab.bunq.connect.ConnectBunqViewModel.ViewModelParams
import app.bunq4ynab.databinding.ConnectBunqFragmentBinding
import app.bunq4ynab.domain.model.observeEvent
import app.bunq4ynab.utils.openUrlInCustomTab
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConnectBunqFragment : Fragment() {

    private val viewModel: ConnectBunqViewModel by viewModels()
    private val args: ConnectBunqFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = ConnectBunqFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        setupViews(binding)
        observeData(binding)
        observeEvents()
        viewModel.start(
            ViewModelParams(
                code = args.code,
                state = args.state
            )
        )
        return binding.root
    }

    private fun setupViews(binding: ConnectBunqFragmentBinding) {
        binding.btnConnectBunq.setOnClickListener { viewModel.onConnectClicked() }

    }

    private fun observeData(binding: ConnectBunqFragmentBinding) {
        viewModel.connectionState.observe(viewLifecycleOwner) { state ->
            binding.state.text = "State: $state"
        }
    }

    private fun observeEvents() {
        viewModel.openOAuthFlowEvent.observeEvent(viewLifecycleOwner) { url ->
            openUrlInCustomTab(requireContext(), url)
        }
    }
}
