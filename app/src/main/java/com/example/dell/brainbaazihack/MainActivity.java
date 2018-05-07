package com.example.dell.brainbaazihack;

import android.Manifest;

import android.content.pm.PackageManager;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.util.SparseArray;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;




import java.io.IOException;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {






    @BindView(R.id.button2)
    Button button2;



    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;
    CameraSource cameraSource;
    final int REQUEST_CAMERA = 1001;
    public static final String AutoFocus = "AutoFocus";

    @BindView(R.id.textView)
    TextView textView;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode)
        {
            case REQUEST_CAMERA:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return;

                    }
                    try {
                        cameraSource.start(surfaceView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("sdsdssd","Entered  in Oncreate");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        button2.setVisibility(View.VISIBLE);
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if (!textRecognizer.isOperational()) {
            Toast.makeText(this, "Dependencies Not Setup Correctly", Toast.LENGTH_SHORT).show();
        } else {
          //  Log.d("MAkjs","Enteredd in Else");
            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer).
                    setFacing(CameraSource.CAMERA_FACING_BACK).
                    setAutoFocusEnabled(true).
                    setRequestedPreviewSize(1280, 1024).
                    setRequestedFps(8.0f).


                    build();


            surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(MainActivity.this,new String []{Manifest.permission.CAMERA},REQUEST_CAMERA);
                        return;
                    }
                    try {
                        cameraSource.start(surfaceView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        cameraSource.stop();
                }
            });

           textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                final SparseArray<TextBlock> items=detections.getDetectedItems();
                if(items.size()!=0)
                {

/*

       */                textView.post(new Runnable() {
                            @Override
                            public void run() {
                                 StringBuilder string=new StringBuilder();
                                 for(int i=0;i<items.size();++i)

                                 {
                                    TextBlock textBlock=items.valueAt(i);

                                    string.append(textBlock.getValue());
                                    string.append("\n");
                                 }

                                 textView.setText(string.toString());





                            }
                        });
                }
                }
            });
        }



             //   startActivity(new Intent(Intent.ACTION_VOICE_COMMAND,Uri.parse(url)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
             /*   Map<String, Object> params = new HashMap<>();
                params.put("package", "com.google.android.googlequicksearchbox");
                params.put("activity", "com.google.android.googlequicksearchbox.SearchActivity");
                params.put("action", "android.intent.action.WEB_SEARCH");
                params.put("arguments", "-e query send-email-hi-to-john");
                driver.executeScript("mobile:activity:open", params);
*/
             /*   try {
                    Intent j = new Intent("android.intent.action.MAIN");
                    j.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                    j.addCategory("android.intent.category.LAUNCHER");
                    j.setData(Uri.parse(url));
                    startActivity(j);
                }
                catch(ActivityNotFoundException e) {

                    Toast.makeText(MainActivity.this, "Not found", Toast.LENGTH_SHORT).show();
                    // Chrome is not installed

                }*/


       button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://google.co.in/search?q="+textView.getText();
             //   Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                   //  startActivity(i);
              //  Intent j=new Intent (MainActivity.this,Main2Activity.class);
             //   j.putExtra("url",textView.getText());
             //   startActivity(j);
                Process fragment = new Process();
                Bundle bundle=new Bundle();
                bundle.putString("url", (String) textView.getText());
                fragment.setArguments(bundle);

            loadFragment(fragment);
          //      button2.setVisibility(View.GONE);
            }
        });

    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction=getSupportFragmentManager().executePendingTransactions();
        transaction.replace(R.id.container,fragment);
          transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onStart() {
        button2.setVisibility(View.VISIBLE);
        super.onStart();
    }

    @Override
    protected void onRestart() {
        button2.setVisibility(View.VISIBLE);
        super.onRestart();
    }

    @Override
    protected void onResume() {
        button2.setVisibility(View.VISIBLE);
        super.onResume();
    }
}
