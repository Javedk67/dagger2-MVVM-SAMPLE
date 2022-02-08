package com.example.dagger2sample.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import com.example.dagger2sample.R
import com.example.dagger2sample.data.model.MovieBean
import com.example.dagger2sample.databinding.FragmentImagesListBinding
import com.example.dagger2sample.ui.adapters.LoadingStateAdapter
import com.example.dagger2sample.ui.viewmodels.MovieListViewModel
import com.example.dagger2sample.utils.CommonUtils
import com.example.dagger2sample.utils.toast
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListFragment : DaggerFragment(R.layout.fragment_images_list) {


    private lateinit var binding: FragmentImagesListBinding

    private var job: Job? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

     private var list:ArrayList<MovieBean> = ArrayList()
     private val viewModel: MovieListViewModel by viewModels {
        viewModelFactory
    }

    private  var listAdapter:PostListAdapter?=null
    var mLayoutManager: LinearLayoutManager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagesListBinding.bind(view)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        initAdapter()
        setAdapter()
        fetchRetroInfo()

    }



    fun fetchRetroInfo(){

        if (CommonUtils.isInternetOn(requireContext()))
        {

            job?.cancel()
            job = lifecycleScope.launch {
                viewModel.getImages1().collect {
                    listAdapter!!.submitData(it)

                }
            }
        }
        else
        {

            requireContext().toast("Check You Internet Connection")
        }

    }

    private fun setAdapter(){
        mLayoutManager = LinearLayoutManager(requireContext())
        binding.imagesList.layoutManager = mLayoutManager
        binding.imagesList.adapter = listAdapter

        binding.imagesList.adapter = listAdapter!!.withLoadStateFooter(
            LoadingStateAdapter { listAdapter!!.retry() }
        )
        listAdapter!!.addLoadStateListener {
            binding.progress.isVisible = it.refresh is LoadState.Loading
            if (it.refresh is LoadState.Error) {
                requireContext().toast("There was a problem fetching data")
            }
        }
    }

    private fun initAdapter(){
        listAdapter = PostListAdapter(requireContext(),list)
    }
}