package app.bunq2ynab.bunq.connect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import app.bunq2ynab.databinding.ConnectBunqFragmentBinding
import app.bunq2ynab.domain.model.observeEvent
import app.bunq2ynab.openUrlInCustomTab
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
        ConnectBunqFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            setupViews(this)
            return root
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.start(args.code, args.state)
    }

    private fun setupViews(binding: ConnectBunqFragmentBinding) {
        binding.bunq.setOnClickListener {
            viewModel.onConnectClicked()
        }
        viewModel.openOAuthFlowEvent.observeEvent(viewLifecycleOwner) { url ->
            openUrlInCustomTab(requireContext(), url)
        }
    }
}
