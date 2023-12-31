package com.aniket777984.instagramsaver;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.lang3.StringUtils;

public class PhotoFragment extends Fragment {

    String URL="NULL";
    ImageView mparticularphoto;
    EditText getphotolink;
    Button getphoto;
    Button downloadphoto;
    String photourl="1";
    private Uri uri2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.photo_fragment,null);
        getphoto=v.findViewById(R.id.getphoto);
        getphotolink=v.findViewById(R.id.getphotolink);
        downloadphoto=v.findViewById(R.id.downloadphoto);
        mparticularphoto=v.findViewById(R.id.particularphoto);

        getphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                URL=getphotolink.getText().toString().trim();
                if(getphotolink.getText().equals("NULL"))
                {
                    Toast.makeText(getContext(), "First Enter Url", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String result2 = StringUtils.substringBefore(URL,"/?");
                    URL=result2+"/?__a=1&__d=dis";
                    processdata();
                }
            }
        });

        downloadphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!photourl.equals("1"))
                {
                    DownloadManager.Request request = new DownloadManager.Request(uri2);
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI|DownloadManager.Request.NETWORK_MOBILE);
                    request.setTitle("Download");
                    request.setDescription("........");
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM,""+System.currentTimeMillis()+".jpg");
                    DownloadManager manager = (DownloadManager)getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);
                    Toast.makeText(getContext(), "Downloaded", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "No Link Present", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private void processdata() {

        StringRequest request = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                ModelClass modelClass = gson.fromJson(response, ModelClass.class);
                photourl = modelClass.getGraphql().getShortcode_media().getDisplay_url();

                uri2 = Uri.parse(photourl);
                Glide.with(getContext()).load(uri2).into(mparticularphoto);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Not Fetched",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);


    }
}
