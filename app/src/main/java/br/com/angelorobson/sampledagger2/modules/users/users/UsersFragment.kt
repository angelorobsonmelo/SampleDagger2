package br.com.angelorobson.sampledagger2.modules.users.users


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.angelorobson.sampledagger2.R
import br.com.angelorobson.sampledagger2.databinding.UsersFragmentBinding
import br.com.angelorobson.sampledagger2.model.User
import br.com.angelorobson.sampledagger2.modules.users.users.adapter.UsersAdapter
import br.com.angelorobson.sampledagger2.service.remote.ApiDataSource.Companion.createService
import br.com.angelorobson.sampledagger2.service.remote.user.UserApiDataSource

class UsersFragment : Fragment() {

    lateinit var binding: UsersFragmentBinding
    lateinit var adapter: UsersAdapter

    private val viewModel: UsersViewModel by lazy {
        ViewModelProviders.of(
            this,
            UsersViewModel(createService(UserApiDataSource::class.java))
        ).get(UsersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.users_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupBinding()
        initAdapter()
        setupRecyclerView()
        initObserveOnSuccess()
        initOserveOnError()
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
