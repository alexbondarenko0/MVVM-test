package com.alexeybondarenko.mvvmtest2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexeybondarenko.mvvmtest2.databinding.FragmentUsersBinding
import com.alexeybondarenko.mvvmtest2.utils.UsersAdapter
import com.alexeybondarenko.mvvmtest2.viewmodel.UsersViewModel

class UsersFragment : Fragment() {

    private var viewBinding: FragmentUsersBinding? = null
    private val binding get() = viewBinding!!

    private val viewModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUsersList()
        setOnClickListeners()
        setUsersListUpdateObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    private fun initUsersList() {
        binding.rvUsersList.layoutManager = LinearLayoutManager(context)
        binding.rvUsersList.adapter = UsersAdapter(listOf(), context)
    }

    private fun setOnClickListeners() {
        binding.btnUpdate.setOnClickListener { viewModel.updateUsersList() }
        binding.btnDeleteRandomUser.setOnClickListener { viewModel.deleteRandomUser() }
    }

    private fun setUsersListUpdateObserver() {
        viewModel.usersListLiveData.observe(viewLifecycleOwner, Observer { updatedUsersModel ->
            binding.tvEmptyListWarning.isVisible = updatedUsersModel.list.isEmpty()
            binding.rvUsersList.adapter = UsersAdapter(updatedUsersModel.list, context)
        })

        viewModel.showUpdatingProcess.observe(viewLifecycleOwner, Observer { updatedVisible ->
            binding.pbUsersList.isVisible = updatedVisible
        })
    }
}