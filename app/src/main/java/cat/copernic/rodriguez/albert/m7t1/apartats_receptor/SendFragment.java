package cat.copernic.rodriguez.albert.m7t1.apartats_receptor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private EditText mComentari;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_send, container, false);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mStorage = FirebaseStorage.getInstance().getReference();
        Button mSubir = root.findViewById(R.id.btnSubir);
        mImageview = root.findViewById(R.id.ivImg1);
        mComentari = root.findViewById(R.id.editText);
        mProgressDialog = new ProgressDialog(getActivity());


        mSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference DRComentaris = database.getReference("Ofertes/");
                DRComentaris.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue() != null){
                            int posicio = (int) dataSnapshot.getChildrenCount();
                            String comentari = mComentari.getText().toString().trim();
                            DatabaseReference DRComentari = database.getReference("Comentaris/"+posicio);
                            DRComentari.setValue(comentari);

                        }
                        else{
                            int posicio = 0;
                            String comentari = mComentari.getText().toString().trim();
                            DatabaseReference DRComentari = database.getReference("Comentaris/"+posicio);
                            DRComentari.setValue(comentari);
                        }
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, GALLERY_INTENT);
                        System.out.println("Fet");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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