package pt.ua.homework2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ua.homework2.databinding.FragmentCitiesBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CitiesFragment : Fragment() {

    private var _binding: FragmentCitiesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCitiesBinding.inflate(inflater, container, false)


        val viewModelFactory = CitiesViewModelFactory()
        val viewModel = ViewModelProvider(this, viewModelFactory).get(CitiesViewModel::class.java)

        val list = viewModel.generateDummyList(100)

        val recyclerView = binding.recyclerView
        recyclerView.adapter = CitiesListAdapter(list)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root

    }

/*    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}