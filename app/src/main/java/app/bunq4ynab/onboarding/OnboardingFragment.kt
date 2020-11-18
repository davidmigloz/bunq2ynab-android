package app.bunq4ynab.onboarding

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.bunq4ynab.databinding.OnboardingFragmentBinding
import app.bunq4ynab.designsystem.R as RDS
import app.bunq4ynab.onboarding.OnboardingFragmentDirections.Companion.actionOnboardingFragmentToConnectBunqFragment
import com.google.android.material.shape.CornerFamily.ROUNDED
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
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
        val shapeAppearanceModel = ShapeAppearanceModel.Builder()
            .setBottomLeftCorner(ROUNDED, 100f)
            .setBottomRightCorner(ROUNDED, 100f)
            .build()
        val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            fillColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), RDS.color.blue_800_dark))
        }
        binding.header.background = shapeDrawable


        binding.btnConnectBunq.setOnClickListener {
            findNavController().navigate(actionOnboardingFragmentToConnectBunqFragment())
        }
    }
}
