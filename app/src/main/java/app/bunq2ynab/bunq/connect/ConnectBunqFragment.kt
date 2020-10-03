package app.bunq2ynab.bunq.connect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.bunq2ynab.databinding.ConnectBunqFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConnectBunqFragment : Fragment() {

    private val connectBunqViewModel: ConnectBunqViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        ConnectBunqFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = connectBunqViewModel
            setupViews(this)
            return root
        }
    }

    private fun setupViews(binding: ConnectBunqFragmentBinding) {

    }
}
