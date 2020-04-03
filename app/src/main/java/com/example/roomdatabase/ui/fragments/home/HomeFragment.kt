package com.example.roomdatabase.ui.fragments.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.MyApplication

import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.HomeFragmentBinding
import com.example.roomdatabase.db.entities.Note
import com.example.roomdatabase.ui.fragments.NotesViewModel
import com.example.roomdatabase.viewUtils.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class HomeFragment : Fragment(),IHomeListner {



    private lateinit var viewModel: NotesViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.home_fragment, container, false)
        viewModel = ViewModelProviders.of(this, MyApplication.notesViewModelFactory).get(NotesViewModel::class.java)
        binding.notesViewModel = viewModel
        viewModel.iHomeListner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllNotes()
    }
    override fun refreshList() {
        viewModel.getAllNotes()
    }
    fun initiRecyclerView(memberInfoItem: List<NotesInfoItem>)
    {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(memberInfoItem)
        }

        binding.notesRv.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }
    private fun List<Note>.toQuoteItem() : List<NotesInfoItem>{
        return this.map {
            NotesInfoItem(it,viewModel)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)
        // TODO: Use the ViewModel
    }
    override fun onAddNotesButtonClicked(view : View) {
        val action = HomeFragmentDirections.actionAddNote()
        Navigation.findNavController(view).navigate(action)
    }
    override fun allNotesResult(notes: List<Note>) {
        initiRecyclerView(notes.toQuoteItem())
    }
    override fun showToast(message: String) {
        activity!!.toast(message)
    }

}
