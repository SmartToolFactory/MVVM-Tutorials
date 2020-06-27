package com.example.test.mvvmsampleapp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.example.test.mvvmsampleapp.R
import com.example.test.mvvmsampleapp.databinding.FragmentProjectListBinding
import com.example.test.mvvmsampleapp.service.model.Project
import com.example.test.mvvmsampleapp.view.adapter.ProjectAdapter
import com.example.test.mvvmsampleapp.view.callback.ProjectClickCallback
import com.example.test.mvvmsampleapp.viewmodel.ProjectListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectListFragment : Fragment() {

    private val viewModel by viewModels<ProjectListViewModel>()

    lateinit var projectAdapter: ProjectAdapter
    lateinit var binding: FragmentProjectListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)
        projectAdapter = ProjectAdapter(projectClickCallback)
        binding.projectList.adapter = projectAdapter
        binding.setIsLoading(true)
        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectListViewModel) {
        // Update the list when the data changes
        viewModel.projectListObservable.observe(viewLifecycleOwner, Observer<List<Project?>?> { projects ->
            if (projects != null) {
                binding.isLoading = false
                projectAdapter.setProjectList(projects)
            }
        })
    }

    private val projectClickCallback = ProjectClickCallback { project ->
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            (activity as MainActivity?)!!.show(project)
        }
    }

    companion object {
        const val TAG = "ProjectListFragment"
    }
}