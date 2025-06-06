package com.example.elexaamart.views.dashboard.seller.upload



import android.Manifest
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.elexaamart.base.BaseFragment
import com.example.elexaamart.core.areAllPermissionsGranted
import com.example.elexaamart.core.extract
import com.example.elexaamart.core.requestPermission
import com.example.elexaamart.data.Product
import com.example.elexaamart.databinding.FragmentUploadProductBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UploadProductFragment : BaseFragment<FragmentUploadProductBinding> (
    FragmentUploadProductBinding::inflate
){
    override fun setListner() {

        permissionRequest = getPermissionRequest()

        binding.apply {
            ivProduct.setOnClickListener {
                requestPermission(permissionRequest, permissionList)
            }

            btnUploadProduct.setOnClickListener {
                val name = etProductName.extract()
                val price = etProductPrice.extract()
                val description =etProductDescription.extract()
                val amount = etProductAmount.extract()

                val product = Product(
                    name = name,
                    description = description,
                    price = price.toDouble(),
                    amount = amount.toInt()
                )

                uploadProduct(product)
            }
        }


    }

    private fun getPermissionRequest(): ActivityResultLauncher<Array<String>> {
        return registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){

            if(areAllPermissionsGranted(permissionList)){
                Toast.makeText(requireContext(), "Granted", Toast.LENGTH_LONG).show()

                ImagePicker.with(this)
                    .compress(1024)         //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(512,512)  //Final image resolution will be less than 1080 x 1080(Optional)
                    .createIntent { intent ->
                        startForProfileImageResult.launch(intent)
                    }


            }else{
                Toast.makeText(requireContext(), "Not Granted", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun uploadProduct(product: Product) {

    }

    override fun allObserber() {

    }

    companion object{
        private val permissionList = arrayOf(
           Manifest.permission.READ_EXTERNAL_STORAGE,
           Manifest.permission.CAMERA
        )
    }

    private lateinit var permissionRequest: ActivityResultLauncher<Array<String>>

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!
                Log.d("TAG", "$fileUri")
                binding.ivProduct.setImageURI(fileUri)


            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

}