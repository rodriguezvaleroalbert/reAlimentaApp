package cat.copernic.rodriguez.albert.m7t1.apartats_receptor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

import cat.copernic.rodriguez.albert.m7t1.R;
//Tener encuenta que hay que usar ciertas dependencias
public class SendFragment extends Fragment {
    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 1;
    private ImageView mImageview;
    private ProgressDialog mProgressDialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_send, container, false);

        mStorage = FirebaseStorage.getInstance().getReference();
        //
        /* private GoogleMap mMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        return inflater.inflate(R.layout.fragment_botigues, container, false);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    } */
        Button mSubir = root.findViewById(R.id.btnSubir);
        mImageview = root.findViewById(R.id.ivImg1);
        mProgressDialog = new ProgressDialog(getActivity());


        mSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);


            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT) {
            mProgressDialog.setTitle("Subiendo...");
            mProgressDialog.setMessage("Subiendo foto a firebase");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();


            Uri uri = null;
            if (data != null) {
                uri = data.getData();
            }
            StorageReference filePath = null;
            if (uri != null) {
                filePath = mStorage.child("fotos").child(Objects.requireNonNull(uri.getLastPathSegment()));
            }

            if (filePath != null) {
                filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        mProgressDialog.dismiss();
                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!urlTask.isSuccessful());
                        Uri downloadUrl = urlTask.getResult();
                        //Aqui usamos una libreria externa para mostrar la imagen
                        Glide.with(SendFragment.this)
                                .load(downloadUrl)
                                .into(mImageview);



                        Toast.makeText(getActivity(), "Se subio con exito", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    }
}