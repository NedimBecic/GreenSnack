import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.green_snack.R

class DishDetailFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_dish_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dishImageView: ImageView = view.findViewById(R.id.dish_image)
        val dishDescription: TextView = view.findViewById(R.id.dish_description)
        val viewRecipeButton: Button = view.findViewById(R.id.view_recipe_button)

        dishDescription.text = arguments?.getString("description") ?: "Nema opisa"

        viewRecipeButton.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        fun newInstance(description: String): DishDetailFragment {
            val fragment = DishDetailFragment()
            val args = Bundle().apply {
                putString("description", description)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
