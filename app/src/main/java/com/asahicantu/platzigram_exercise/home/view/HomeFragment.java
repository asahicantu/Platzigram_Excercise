package com.asahicantu.platzigram_exercise.home.view;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.asahicantu.platzigram_exercise.R;
import com.asahicantu.platzigram_exercise.adapter.PictureAdapterRecyclerView;
import com.asahicantu.platzigram_exercise.model.ModelUtils;
import com.asahicantu.platzigram_exercise.model.Picture;
import com.asahicantu.platzigram_exercise.view.BaseFragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements HomeView {

    private static final int REQUEST_CAMERA = 0;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FloatingActionButton _fabPicture;
    private AppCompatActivity _activity;
    private String _photoPathTemp;
    private View _view;
    // TODO: Rename and change types of parameters
    private String mParam1;

    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _activity = (AppCompatActivity) getActivity();
        _view = inflater.inflate(R.layout.fragment_home, container, false);
        initializeControls();

        return _view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()                  + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void initializeControls() {
        RecyclerView picturesRecycler = (RecyclerView) _view.findViewById(R.id.pictureRecycler);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(_activity);
        picturesRecycler.setLayoutManager(mLayoutManager);

        initializeFabPicture();

        ArrayList<Picture> pics = ModelUtils.buildPictures();
        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(pics, R.layout.cardview_client, getActivity());
        picturesRecycler.setAdapter(pictureAdapterRecyclerView);
        String title = getString(R.string.tab_home);
        showToolbar(title, false, _view, _activity);

    }

    private void initializeFabPicture() {
        _fabPicture = (FloatingActionButton) _view.findViewById(R.id.fabCamera);
        _fabPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int canUseCamera = ContextCompat.checkSelfPermission(_activity, Manifest.permission.CAMERA);
                if (canUseCamera != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(_activity,
                            Manifest.permission.READ_CONTACTS)) {
                        String message = getString(R.string.message_request_camera);
                        Toast.makeText(_activity, message, Toast.LENGTH_SHORT);
                    } else {
                        ActivityCompat.requestPermissions(_activity, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                    }
                } else {
                    newPost();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    newPost();
                } else {
                    String message = getString(R.string.message_function_not_available);
                    Toast.makeText(_activity, message, Toast.LENGTH_SHORT);
                }
        }

    }

    public void newPost() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ComponentName componentName = intent.resolveActivity(getActivity().getPackageManager());
        if (componentName != null) {
            File photoFile = null;
            try {

                photoFile = createImageFile();
                if (photoFile != null) {
                    String packageName = _activity.getApplicationContext().getPackageName();
                    Uri uri = FileProvider.getUriForFile(_activity, packageName, photoFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }
            } catch (Exception e) {
                Log.d("ERROR", e.getMessage());
            }
        } else {
            String message = getString(R.string.message_cameraNotAvailable);
            Toast.makeText(_activity, message, Toast.LENGTH_SHORT);
        }
    }


    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = _activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File photoFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        _photoPathTemp = "file:" + photoFile.getAbsolutePath();
        return photoFile;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    Log.d("HomeFragment", "Camera is working");
                    Intent intent = new Intent(_activity, NewPostActivity.class);
                    intent.putExtra(NewPostActivity.IMAGE_PATH, _photoPathTemp);
                    startActivity(intent);
                }
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
