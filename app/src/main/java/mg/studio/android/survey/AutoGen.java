package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AutoGen extends AppCompatActivity {

    Button load,save;
    File file;
    RelativeLayout rlayout;

    TextView q1,q2;
    RadioButton q1r1,q1r2,q2r1,q2r2,q2r3,q2r4,q2r5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_gen);
        load = findViewById(R.id.load);
        save =findViewById(R.id.save);
        rlayout=findViewById(R.id.rel_autogen);

        q1=findViewById(R.id.que1);
        q2=findViewById(R.id.que2);
        q1r1=findViewById(R.id.q1r1);
        q1r2=findViewById(R.id.q1r2);
        q2r1=findViewById(R.id.q2r1);
        q2r2=findViewById(R.id.q2r2);
        q2r3=findViewById(R.id.q2r3);
        q2r4=findViewById(R.id.q2r4);
        q2r5=findViewById(R.id.q2r5);




        load.setOnClickListener(new Button.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                try {
                    JSONObject root = new JSONObject();
                    root.put("id","1234");
                    root.put("len",2);

                    JSONArray m_questions=new JSONArray();

                    JSONObject question1=new JSONObject();
                    question1.put("type","single");
                    question1.put("quest","How well do the professors teach at this university?");
                    JSONArray option=new JSONArray();
                    option.put(0,"Extremely well");
                    option.put(1,"Very well");
                    question1.put("option",option);
                    m_questions.put(0,question1);


                    JSONObject question2=new JSONObject();
                    question2.put("type","single");
                    question2.put("quest","How effective is the teaching outside yur major at the univesrity?");
                    JSONArray option2=new JSONArray();
                    option2.put(0,"Extremetly effective");
                    option2.put(1,"Very effective");
                    option2.put(2,"Somewhat  effective");
                    option2.put(3,"Not so effective");
                    option2.put(4,"Not at all effective");
                    question2.put("option",option2);
                    m_questions.put(1,question2);


                    root.put("questions",m_questions);

                    file = new File(getFilesDir(),"Readable.json");

                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(root.toString().getBytes());
                    fos.close();

                    FileInputStream fis = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader bf = new BufferedReader(isr);

                    String line;
                    StringBuilder strb = new StringBuilder();


                    while((line = bf.readLine()) != null){
                        strb.append(line);
                    }

                    fis.close();
                    isr.close();
                    bf.close();


                    RelativeLayout rlayout = new RelativeLayout(getApplicationContext());
                    rlayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,dip2px(120)));
                    rlayout.setId(0);


                    JSONObject parse = new JSONObject(strb.toString());
                    int length = 0;
                    length = parse.getInt("len");

                    JSONArray array = parse.getJSONArray("questions");

                    JSONObject obj = array.getJSONObject(0);
                    String q_str1=obj.getString("quest");

                    JSONArray array_opt1 = obj.getJSONArray("option");
                    String q_1_o_1 = array_opt1.getString(0);
                    String q_1_o_2 = array_opt1.getString(1);


                    JSONObject obj2 = array.getJSONObject(1);
                    String q_str2=obj2.getString("quest");

                    JSONArray array_opt2 = obj2.getJSONArray("option");
                    String q_2_o_1 = array_opt2.getString(0);
                    String q_2_o_2 = array_opt2.getString(1);
                    String q_2_o_3 = array_opt2.getString(2);
                    String q_2_o_4 = array_opt2.getString(3);
                    String q_2_o_5 = array_opt2.getString(4);


                    //Toast.makeText(getApplicationContext(),q_2_o_5,Toast.LENGTH_SHORT).show();
                    q1.setText(q_str1);
                    q2.setText(q_str1);
                    q1r1.setText(q_1_o_1);
                    q1r2.setText(q_1_o_2);
                    q2r1.setText(q_2_o_1);
                    q2r2.setText(q_2_o_2);
                    q2r3.setText(q_2_o_3);
                    q2r4.setText(q_2_o_4);
                    q2r5.setText(q_2_o_5);



                    q1.setVisibility(View.VISIBLE);
                    q2.setVisibility(View.VISIBLE);
                    q1r1.setVisibility(View.VISIBLE);
                    q1r2.setVisibility(View.VISIBLE);
                    q2r1.setVisibility(View.VISIBLE);
                    q2r2.setVisibility(View.VISIBLE);
                    q2r3.setVisibility(View.VISIBLE);
                    q2r4.setVisibility(View.VISIBLE);
                    q2r5.setVisibility(View.VISIBLE);

              Toast.makeText(getApplicationContext(),"Load！",Toast.LENGTH_SHORT).show();

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }


            }
        });

        save.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Results saved！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int dip2px(float dp){
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int)(dp*scale+0.5f);

    }
















}
