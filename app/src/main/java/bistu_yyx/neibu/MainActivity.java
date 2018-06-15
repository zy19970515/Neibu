package bistu_yyx.neibu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    FileOutputStream out = null;
    BufferedWriter writer = null;
    FileInputStream in = null;
    BufferedReader reader = null;
    private EditText edit;
    private TextView text;
    private Button btn_readFile;
    private Button btn_writeFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText)findViewById(R.id.edit) ;
        text = (TextView)findViewById(R.id.text);
        btn_readFile = (Button) findViewById(R.id.btn_read_file);
        btn_writeFile = (Button) findViewById(R.id.btn_write_file);

        btn_readFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = load();
                if(!TextUtils.isEmpty(text1)){
                    text.setText(text1);
                }
            }
        });

        btn_writeFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = edit.getText().toString();
                save(input);
            }
        });
    }

    public void save(String input){
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(input);
            Toast.makeText(this, "存储成功", Toast.LENGTH_SHORT).show();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String load(){
        StringBuilder content = new StringBuilder();
        try{
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(reader != null){
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return content.toString();
    }
}