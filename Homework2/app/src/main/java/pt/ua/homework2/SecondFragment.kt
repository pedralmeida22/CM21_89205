package pt.ua.homework2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import pt.ua.homework2.databinding.FragmentSecondBinding
import pt.ua.homework2.datamodel.CallResponse
import pt.ua.homework2.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    val args: SecondFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        val cityCode = args.city
        Log.e("Info", "api call")

        cityCode?.let<@Nullable String, Call<CallResponse.City>> { ApiInterface.create().getData(it) }
            ?.enqueue(object : Callback<CallResponse.City> {
                override fun onResponse(
                    call: Call<CallResponse.City>,
                    response: Response<CallResponse.City>
                ) {
                    if (response.body() != null) {
                        Log.e("Info", "api response")
                        binding.textviewSecond.text = response.body().toString()
                    }
                }

                override fun onFailure(call: Call<CallResponse.City>, t: Throwable) {
                    Log.e("Info", "api rip")
                }
            })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_CitiesFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}