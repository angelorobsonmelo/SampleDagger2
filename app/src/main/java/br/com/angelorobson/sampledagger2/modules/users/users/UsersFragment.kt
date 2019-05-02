package br.com.angelorobson.sampledagger2.modules.users.users


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.angelorobson.sampledagger2.R
import br.com.angelorobson.sampledagger2.databinding.UsersFragmentBinding
import br.com.angelorobson.sampledagger2.di.components.users.DaggerUsersComponent
import br.com.angelorobson.sampledagger2.di.module.users.UserModule
import br.com.angelorobson.sampledagger2.modules.users.users.adapter.UsersAdapter
import javax.inject.Inject

class UsersFragment : Fragment() {

    private lateinit var binding: UsersFragmentBinding
    private lateinit var adapter: UsersAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.users_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectDependency()
        viewModel = ViewModelProviders.of(this, viewModelFactory)[UsersViewModel::class.java]

        setupBinding()
        initAdapter()
        setupRecyclerView()
        initObserveOnSuccess()
        initOserveOnError()
    }

    private fun injectDependency() {
        val usersFragment = DaggerUsersComponent.builder()
            .build()

        usersFragment.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.unsubscribe()
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initAdapter() {
        adapter = UsersAdapter(mutableListOf())
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.usersRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun initObserveOnSuccess() {
        viewModel.successObserver.observe(this, Observer {
            adapter.updateItems(it)
        })
    }

    private fun initOserveOnError() {
        viewModel.errorObserver.observe(this, Observer {

        })
    }

}
