package com.example.test.mvvmsampleapp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.test.mvvmsampleapp.R
import com.example.test.mvvmsampleapp.databinding.FragmentProjectDetailsBinding
import com.example.test.mvvmsampleapp.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectFragment : Fragment() {
    private lateinit var binding: FragmentProjectDetailsBinding

    private val viewModel by viewModels<ProjectViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate this data binding layout
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false)

        // Create and set the adapter for the RecyclerView.
        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.setProjectID(requireArguments().getString(KEY_PROJECT_ID))
        binding.projectViewModel = viewModel
        binding.isLoading = true
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectViewModel) {
        // Observe project data
        viewModel.observableProject.observe(viewLifecycleOwner, Observer { project ->
            if (project != null) {
                binding.isLoading = false
                viewModel.setProject(project)
            }
        })
    }

    companion object {
        private const val KEY_PROJECT_ID = "project_id"

        /**
         * Creates project fragment for specific project ID
         */
        @JvmStatic
        fun forProject(projectID: String?): ProjectFragment {
            val fragment = ProjectFragment()
            val args = Bundle()
            args.putString(KEY_PROJECT_ID, projectID)
            fragment.arguments = args
            return fragment
        }
    }
}