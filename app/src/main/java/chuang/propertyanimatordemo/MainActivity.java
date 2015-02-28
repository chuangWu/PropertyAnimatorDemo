package chuang.propertyanimatordemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"AnimateLayoutChanges", "AnimateFreeFall", "AnimateMoveInSecond", "AnimateMultiple", "AnimateLayoutTransition", "AnimateLayoutAnimation", "alpha_login", "translation_login"}));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, AnimateLayoutChanges.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, AnimateFreeFall.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, AnimateMoveInSecond.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, AnimateMultiple.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, AnimateLayoutTransition.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, AnimateLayoutAnimation.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, LoginAlphaAnimation.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, LoginTranslationAnim.class));
                        break;
                    default:
                        break;
                }


            }
        });
    }

}
