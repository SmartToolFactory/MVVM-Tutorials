package com.example.test.mvvmsampleapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.test.mvvmsampleapp.service.model.Project
import com.example.test.mvvmsampleapp.service.repository.ProjectRepository

class ProjectViewModel @ViewModelInject constructor(projectRepository: ProjectRepository) : ViewModel() {
    val observableProject: LiveData<Project>
    private val projectID: MutableLiveData<String>
    var project = ObservableField<Project>()

    fun setProject(project: Project) {
        this.project.set(project)
    }

    fun setProjectID(projectID: String) {
        this.projectID.value = projectID
    }

    companion object {
        private val TAG = ProjectViewModel::class.java.name
        private val ABSENT = MutableLiveData<Project>()
    }

    init {
        ABSENT.value = null
    }

    init {
        projectID = MutableLiveData()
        observableProject = Transformations.switchMap(projectID) { input: String ->
            if (input.isEmpty()) {
                Log.i(TAG, "ProjectViewModel projectID is absent!!!")
                return@switchMap ABSENT
            }
            Log.i(TAG, "ProjectViewModel projectID is " + projectID.value)
            projectRepository.getProjectDetails("Google", projectID.value)
        }
    }
}