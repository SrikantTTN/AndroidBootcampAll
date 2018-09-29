package com.example.srikant.day2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        RecyclerView rv = findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        NewsAdapter newsAdapter = new NewsAdapter(this);

        rv.setAdapter(newsAdapter);
        ArrayList<NewsModel> sendData = new ArrayList<NewsModel>();
        NewsModel n1 = new NewsModel();
        n1.setDatePosted("2018-09-27");
        n1.setDescription("U.S. aid to Taiwan, sanctions over China's Russian arms purchases provoke Beijing's anger");
        n1.setMainImageUrl("https://cbsnews3.cbsistatic.com/hub/i/r/2016/01/10/27d6feec-c259-445c-8ae1-b3eb5012659b/thumbnail/1200x630/c95e11919231e873383844f099294f1d/b-52-bomber.jpg");
        n1.setTitle("China calls American B-52 flights near disputed islands provocative");
        NewsModel n2 = new NewsModel();
        n2.setDatePosted("2018-09-27");
        n2.setDescription("Free agent Eric Reid, the first player to join Colin Kaepernick in kneeling to protest racial equality and police brutality in 2016, has been signed by the Panthers.");
        n2.setMainImageUrl("http://a4.espncdn.com/combiner/i?img=%2Fphoto%2F2018%2F0509%2Fr368221_1296x729_16%2D9.jpg");
        n2.setTitle("Panthers, in need of safety, sign free agent Reid");
        NewsModel n3 = new NewsModel();
        n3.setDatePosted("2018-09-27");
        n3.setDescription("The meeting could be rescheduled, as the president will be monitoring testimony from Judge Brett M. Kavanaugh and the woman who accused the Supreme Court nominee of sexual assault.");
        n3.setMainImageUrl("https://static01.nyt.com/images/2018/09/28/us/politics/28DC-ROSENSTEIn5/28DC-ROSENSTEIn5-facebookJumbo.jpg");
        n3.setTitle("Trump’s Meeting With Rod Rosenstein May Be Delayed");
        NewsModel n4 = new NewsModel();
        n4.setDatePosted("2018-09-27");
        n4.setDescription("Supreme Court nominee Brett M. Kavanaugh faces multiple allegations of sexual misconduct that have plunged his confirmation process into chaos. Kavanaugh, who strongly denies the allegations, will testify after Ford on Thursday.");
        n4.setMainImageUrl("https://www.washingtonpost.com/resizer/obx97kbnhm-aTlQBq6I8xHKKFUI=/1484x0/arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/GDPFO3GBXAI6RFCR5B4PS27BTM.jpg");
        n4.setTitle("Kavanaugh hearing: Christine Blasey Ford gives Senate testimony about sexual assault allegation");

        NewsModel n5 = new NewsModel();
        n5.setDatePosted("2018-09-27");
        n5.setDescription("U.S. aid to Taiwan, sanctions over China's Russian arms purchases provoke Beijing's anger");
        n5.setMainImageUrl("https://cbsnews3.cbsistatic.com/hub/i/r/2016/01/10/27d6feec-c259-445c-8ae1-b3eb5012659b/thumbnail/1200x630/c95e11919231e873383844f099294f1d/b-52-bomber.jpg");
        n5.setTitle("China calls American B-52 flights near disputed islands provocative");
        NewsModel n6 = new NewsModel();
        n6.setDatePosted("2018-09-27");
        n6.setDescription("Free agent Eric Reid, the first player to join Colin Kaepernick in kneeling to protest racial equality and police brutality in 2016, has been signed by the Panthers.");
        n6.setMainImageUrl("http://a4.espncdn.com/combiner/i?img=%2Fphoto%2F2018%2F0509%2Fr368221_1296x729_16%2D9.jpg");
        n6.setTitle("Panthers, in need of safety, sign free agent Reid");
        NewsModel n7 = new NewsModel();
        n7.setDatePosted("2018-09-27");
        n7.setDescription("The meeting could be rescheduled, as the president will be monitoring testimony from Judge Brett M. Kavanaugh and the woman who accused the Supreme Court nominee of sexual assault.");
        n7.setMainImageUrl("https://static01.nyt.com/images/2018/09/28/us/politics/28DC-ROSENSTEIN1/28DC-ROSENSTEIN1-facebookJumbo.jpg");
        n7.setTitle("Trump’s Meeting With Rod Rosenstein May Be Delayed");
        NewsModel n8 = new NewsModel();
        n8.setDatePosted("2018-09-27");
        n8.setDescription("Supreme Court nominee Brett M. Kavanaugh faces multiple allegations of sexual misconduct that have plunged his confirmation process into chaos. Kavanaugh, who strongly denies the allegations, will testify after Ford on Thursday.");
        n8.setMainImageUrl("https://www.washingtonpost.com/resizer/obx97kbnhm-aTlQBq6I8xHKKFUI=/1484x0/arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/GDPFO3GBXAI6RFCR5B4PS27BTM.jpg");
        n8.setTitle("Kavanaugh hearing: Christine Blasey Ford gives Senate testimony about sexual assault allegation");
        sendData.add(n1);
        sendData.add(n2);
        sendData.add(n3);
        sendData.add(n4);
        sendData.add(n5);
        sendData.add(n6);
        sendData.add(n7);
        sendData.add(n8);
        newsAdapter.addNews(sendData);
        newsAdapter.notifyDataSetChanged();
    }

}
