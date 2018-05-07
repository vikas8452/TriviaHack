package com.example.dell.brainbaazihack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Process extends Fragment {



    @BindView(R.id.btnId)
    Button btnId;

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.webView1)
    WebView webView1;
    @BindView(R.id.webView2)
    WebView webView2;
    //final Context myApp = this;
    List<String> arr;
    int size;
    int count0=0;
    int count1=1;
    int count2=2;
    int optionA;
    int optionB;
    int optionC;
    String optiona;
    String optionb;
    String optionc;
    int DURATION=500;
   
    public Process() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_process, container, false);

        final WebView webView = (WebView) view.findViewById(R.id.webView);
        final WebView webView1 = (WebView) view.findViewById(R.id.webView1);
        final WebView webView2 = (WebView) view.findViewById(R.id.webView2);
        //  StringExtractor se = new StringExtractor("http://www.google.com");
        TextView contentView = (TextView) view.findViewById(R.id.contentView);

        String url="";
        Bundle args = getArguments();
         url = args.getString("url");
      //  Toast.makeText(getActivity(),url,Toast.LENGTH_SHORT).show();

     //   Toast.makeText(getActivity(),url,Toast.LENGTH_SHORT).show();
     //   Intent intent = getIntent();
       // String url = intent.getStringExtra("url");
        arr= Arrays.asList(url.split("\n"));
        ButterKnife.bind(this,view);
     //   disable.setEnabled(false);
        btnId.setEnabled(true);

        /* An instance of this class will be registered as a JavaScript interface */
        class MyJavaScriptInterface
        {
            private TextView contentView;

            public MyJavaScriptInterface()
            {
                Log.d("sdssdecd","Enter in Constructor");
                //  contentView = aContentView;
                //   Toast.makeText(getActivity().getBaseContext().this, (CharSequence) contentView, Toast.LENGTH_SHORT).show();
            }


            @SuppressWarnings("unused")
            public void processContent(String aContent)
            {
                Log.d("dsdsd","Enteredd in the Process Content");
                final String content = aContent;
                contentView.post(new Runnable()
                {
                    public void run()
                    {
                        contentView.setText(content);
                    }
                });
            }
        }

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "INTERFACE");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                // view.loadUrl("javascript:window.INTERFACE.processContent(document.getElementsByTagName('head')[0].innerText);");
                Log.d("jhdhdc","Entered int the On page Finished");
                webView.loadUrl("javascript:window.INTERFACE.processContent('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }
        });


        webView1.getSettings().setJavaScriptEnabled(true);
        webView2.getSettings().setJavaScriptEnabled(true);

        webView1.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //  Log.i("shouldOverrideUrlLoading", url.toString());
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i("onPageFinished", url.toString());
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.i("onPageStarted", url.toString());
                super.onPageStarted(view, url, favicon);
            }



        });

        webView2.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //  Log.i("shouldOverrideUrlLoading", url.toString());
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i("onPageFinished", url.toString());
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.i("onPageStarted", url.toString());
                super.onPageStarted(view, url, favicon);
            }



        });




        size=arr.size();



        //webView.findAllAsync("sc");
        // webView.findAllAsync("Online");

        String newUrl="http://google.co.in/search?q=";
        for(int i=0;i<size;i++)
        {
            String s = arr.get(i);
            int counter = 0;
            for( int j=0; j<s.length(); j++ ) {
                if( s.charAt(j) ==' ' ) {
                    counter++;
                }
            }
            if(arr.get(i).contains("?")||counter>1)
            {
                newUrl+=arr.get(i);

                //   Toast.makeText(getActivity().getBaseContext().this, newUrl, Toast.LENGTH_SHORT).show();
            }

        }

        for(int i=0;i<size;i++)
        {
            String s = arr.get(i);
            int counter = 0;
            for( int j=0; j<s.length(); j++ ) {
                if( s.charAt(j) ==' ' ) {
                    counter++;
                }
            }

            if(!(arr.get(i).contains("?")||counter>1))
            {
                String arr1[]=arr.get(i).split(" ");
                //   ArrayList<String> arr1=new ArrayList<arr[i].split(" ")>();

                // count0=1;
                webView.findAllAsync(arr1[0]);
                Log.d("dfdfdf",arr1[0]);
                optiona=arr1[0];
                arr.set(i,"t t t tt t ttt t tt ");


                break;
            }
        }
        webView.loadUrl(newUrl);



        webView.setFindListener(new WebView.FindListener() {
            @Override
            public void onFindResultReceived(int i, int i1, boolean b) {
                //  Toast.makeText(getActivity().getBaseContext().this,"OPTIONS A:"+String.valueOf(i1),Toast.LENGTH_SHORT).show();
                optionA=i1;
                if ( optionA > optionB && optionA > optionC ) {

                    final Toast toast= Toast.makeText(getActivity(), "option   A:  " + optionA + " "+optiona, Toast.LENGTH_SHORT);

                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, DURATION);
                }   else if ( optionB > optionA && optionB > optionC ) {
                    final Toast toast=   Toast.makeText(getActivity(), "option  B:  " + optionB + "    "+optionb, Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, DURATION);
                }    else if ( optionC > optionA && optionC > optionB ) {
                    final Toast toast=  Toast.makeText(getActivity(), "option    C:  " + optionC + "   "+optionc, Toast.LENGTH_SHORT);
                    // Log.d("ffdfdfd", "Entered in the webview 2");
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, DURATION);
                }

            }
        });
        for(int i=0;i<size;i++)
        {
            String s = arr.get(i);
            int counter = 0;
            for( int j=0; j<s.length(); j++ ) {
                if( s.charAt(j) ==' ' ) {
                    counter++;
                }
            }

            if(!(arr.get(i).contains("?")||counter>1))
            {
                String arr1[]=arr.get(i).split(" ");

                count1=2;
                webView1.findAllAsync(arr1[0]);
                Log.d("dff",arr1[0]);

                optionb=arr1[0];


                arr.set(i,"r r r r r r r r ");


                break;
            }
        }
        webView1.setFindListener(new WebView.FindListener() {
            @Override
            public void onFindResultReceived(int i, final int i1, boolean b) {
                //       btn4.setText(String.valueOf(i1));
                // Toast.makeText(getActivity().getBaseContext().this,"OPTION B:"+String.valueOf(i1),Toast.LENGTH_SHORT).show();
                optionB=i1;     //  btn3.setText(String.valueOf(i1));
                if ( optionA > optionB && optionA > optionC ) {

                    final Toast toast= Toast.makeText(getActivity(), "option   A:  " + optionA + " "+optiona, Toast.LENGTH_SHORT);

                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, DURATION);
                }   else if ( optionB > optionA && optionB > optionC ) {
                    final Toast toast=   Toast.makeText(getActivity(), "option  B:  " + optionB + "    "+optionb, Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, DURATION);
                }    else if ( optionC > optionA && optionC > optionB ) {
                    final Toast toast=  Toast.makeText(getActivity(), "option    C:  " + optionC + "   "+optionc, Toast.LENGTH_SHORT);
                    // Log.d("ffdfdfd", "Entered in the webview 2");
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, DURATION);
                }

            }
        });

        webView1.loadUrl(newUrl);


        for(int i=0;i<size;i++)
        {
            String s = arr.get(i);
            int counter = 0;
            for( int j=0; j<s.length(); j++ ) {
                if( s.charAt(j) ==' ' ) {
                    counter++;
                }
            }
            Log.d("d","Enetred in for");


            if(!(arr.get(i).contains("?")||counter>1))
            {
                String arr1[]=arr.get(i).split(" ");


                webView2.findAllAsync(arr1[0]);
                optionc=arr1[0];
                Log.d("df",arr1[0]);

                arr.set(i,"w w w w ");

                break;
            }
        }

        webView2.setFindListener(new WebView.FindListener() {
            @Override
            public void onFindResultReceived(int i, final int i1, boolean b) {
                //       btn4.setText(String.valueOf(i1));
                //Toast.makeText(getActivity().getBaseContext().this,"OPTION C:"+String.valueOf(i1),Toast.LENGTH_SHORT).show();
                //  btn3.setText(Integer.toString(i1));
                optionC=i1;
                if ( optionA > optionB && optionA > optionC ) {

                    final Toast toast= Toast.makeText(getActivity(), "option   A:  " + optionA + " "+optiona, Toast.LENGTH_SHORT);

                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, DURATION);
                }   else if ( optionB > optionA && optionB > optionC ) {
                    final Toast toast=   Toast.makeText(getActivity(), "option  B:  " + optionB + "    "+optionb, Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, DURATION);
                }    else if ( optionC > optionA && optionC > optionB ) {
                    final Toast toast=  Toast.makeText(getActivity(), "option    C:  " + optionC + "   "+optionc, Toast.LENGTH_SHORT);
                    // Log.d("ffdfdfd", "Entered in the webview 2");
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, DURATION);
                }

            }
        });

        webView2.loadUrl(newUrl);
        // Log.d("ffdfdfd","Load Url");
        //        webView.loadDataWithBaseURL("x-data://base", newUrl, "text/html", "UTF-8", null);

        btnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.findNext(true);
                webView1.findNext(true);
                webView2.findNext(true);
            }
        });





        return view;
    }
    

   
}
