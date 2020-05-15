package com.calvin.vacationexperience;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mDescriptions= new ArrayList<>();


    Context context;
    FirebaseAuth auth;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");


        initImageBitmaps();


        context =this;
        auth=FirebaseAuth.getInstance();
        toolbar=findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

    }
    @Override
    protected void onStart() {
        super.onStart();

        checkAuth();
    }

    private void checkAuth() {
        FirebaseUser currentUser=auth.getCurrentUser();
        if (currentUser==null){

            Intent intent=new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(context,"Settings",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context,SettingsActivity.class));
                //finish();
                break;
            case R.id.logout:
                auth.signOut();
                startActivity(new Intent(context,LoginActivity.class));
                Toast.makeText(context,"You have logged out",Toast.LENGTH_SHORT).show();
                //finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }



    private void initImageBitmaps(){


        mImageUrls.add("https://www.discoverwalks.com/blog/wp-content/uploads/2019/12/exploringnairobibynight-816x486.jpg");
        mNames.add("Nairobi");
        mDescriptions.add("Kenya’s capital city has risen in a single century from a brackish uninhabited swampland to a thriving modern capital.\n" +
                "\n" +
                "Modern Nairobi is still the safari capital of the Africa, but the modern world has quickly caught up with the city. A frontier town no more, Nairobi is one of Africa’s largest, and most interesting cities.\n" +
                "\n" +
                "Nairobi is a city that never seems to sleep. The entire town has a boundless energy, and is thriving place where all of human life can be found.\n" +
                "\n" +
                "This is a place of great contrasts where race, tribe and origin all become facets of a unique Nairobi character.\n" +
                "\n" +
                "The city has not lost its sense of the past, with an excellent museum and the historical home of Karen Blixen, author of Out of Africa open to visitors.\n" +
                "\n" +
                "This is not a modern capital separated from the great wilderness that surrounds it. Just outside the city is Nairobi National Park, 113 sq. kms of plains, cliffs and forest. The park is home to large herds of zebra, Wildebeest, Buffalo, Giraffe and more. Rhino, Cheetah, and a large number of Lions are all found here, living wild within 20 minutes of the centre of town.");

        mImageUrls.add("https://www.nation.co.ke/image/view/-/4200130/highRes/1818462/-/maxw/600/-/13axuuwz/-/Flamingos+pic.jpg");
        mNames.add("Nakuru");
        mDescriptions.add("Nakuru provides the visitor with one of Kenya’s best known images. Lake Nakuru has thousands of flamingo, joined into a massive flock, fringe the shores of this soda lake. A pulsing pink swathe of life that carpets the water, the flamingo are a breathtaking sight.\n" +
                "\n" +
                "The lake has become world famous for these birds, who visit the lake to feed on algae that forms on the lake bed. They move back and forth, feeding and occasionally and spectacularly taking to flight, filling the sky over the lake with color.\n" +
                "\n" +
                "Nakuru has more than just flamingos. This is a major National Park and an important sanctuary for Rhino. Both Black and White Rhino are found here, and are often seen resting under acacias by the Lake shore.\n" +
                "\n" +
                "The park abounds with game. There are huge herds of waterbuck, zebra, buffalo, the endangered Rothschild Giraffe and more.\n" +
                "\n" +
                "This is one of your best chances of seeing Leopard in Kenya, and there are several large prides of Lion.\n" +
                "\n" +
                "Exploring beyond the lake is always rewarding and there are forests, cliffs, waterfalls and more to be found here");

        mImageUrls.add("https://3dwnh01icn0h133s00sokwo1-wpengine.netdna-ssl.com/wp-content/uploads/2017/03/Lake-Naivasha-e1490773324410.jpg");
        mNames.add("Naivasha");
        mDescriptions.add("Naivasha has the reknown lake Naivasha which a beautiful freshwater lake, fringed by thick papyrus. The lake is almost 13kms across, but its waters are shallow with an average depth of five meters.\n" +
                "\n" +
                "Lake area varies greatly according to rainfall, with an average range between 114 and 991 sq. kms. At the beginning of the 20th Century, Naivasha completely dried up and effectively disappeared. The resulting open land was farmed, until heavy rains a few years later caused the lake to return to existence, swallowing up the newly established estates.\n" +
                "\n" +
                "Afternoon wind and storms can cause the Lake to become suddenly rough and produce high waves. For this reason, the local Maasai christened the lake Nai’posha meaning ‘rough water’.\n" +
                "\n" +
                "The lake and its surroundings are rich in natural bounty, and the fertile soils and water supply have made this one of Kenya’s prime agricultural regions. Much of the lake is surrounded by forests of the yellow barked Acacia Xanthophlea, known as the yellow fever tree.\n" +
                "\n" +
                "These forests abound with bird life, and Naivasha is known as a world class birding destination. The waters of the lake draw a great range of game to these shores. Giraffes wander among the acacia, Buffalo wallow in the swamps and Colobus monkeys call from the treetops while the Lakes large hippo population sleep the day out in the shallows.\n" +
                "\n" +
                "The region surrounding the Lake is well worth exploring. There are two smaller lakes nearby, Oloidien, and Sonachi, a bright green crater lake.");

        mImageUrls.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRfCzNJwXUVVl615-TwGvA2QKM6MLXfwXMJDED19XrrHsmrhaw5&usqp=CAU");
        mNames.add("Kisumu");
      mDescriptions.add("Kisumu City is a quiet port town on the shores of Lake Victoria with wide streets and fine colonial architecture.\n" +
              "\n" +
              "Kisumu was awarded City Status in 2001 and has since grown into an attractive urban centre, with an excellent museum, one of Kenya’s largest open markets and excellent facilities for visitors.\n" +
              "\n" +
              "Located just a Kilometer from Kisumu’s central business district is the Kisumu Impala Sanctuary a walking sanctuary and holding area for animals which require special protection in this densely populated area. Herds of impalas and zebras roam freely in the sanctuary.\n" +
              "\n" +
              "Within the sanctuary is an animal orphanage that contains a collection of caged lions, leopard, cheetahs, baboons, hyena, jackals, bush duikers, bush buck and buffaloes.\n" +
              "\n" +
              "To the south of Kisumu City fishing villages line Lake Victoria towards the broad waters of Homa Bay. This area is home to Ruma National Park, a small but attractive park with many unique species.\n" +
              "\n" +
              "The best way to appreciate the beauty of this region is on the lake itself. The sun shines brightly, and gentle breezes rise from the water.\n" +
              "\n" +
              "In trees along the shore, Fish Eagles call to each other with long haunting cries. Sunsets turn the water to gold, as the local fisherman in their canoes pull in their nets and slowly turn for home.");


        mImageUrls.add("https://bountifulsafaris.com/wp-content/uploads/2019/04/mombasa-beach-betty2.jpg");
        mNames.add("Mombasa");
        mDescriptions.add("Mombasa is a place steeped in history, yet at the same time fascinating commercial and cosmopolitan port town.\n" +
                "\n" +
                "Mombasa is an island connected to the mainland by bridges and ferries. The town overlooks a wide harbor, where commercial shipping mingles with traditional sailing dhows.\n" +
                "\n" +
                "The true heart of Mombasa is found in the exotic old town, among the narrow winding streets and Arab architecture. The air here is always heavy with the scent of spices.\n" +
                "\n" +
                "At the water’s edge is Fort Jesus, an imposing fort that stands watch over the harbor.\n" +
                "\n" +
                "The high gun turrets, battlements and underground passages of this 16th Century Fort were the centre of a historic struggle for control of the Kenya coast between the Portuguese army and the Shirazi Arabs. This war was waged around Mombasa over hundreds of years and countless battles, and the Fort stands as a testament to this tumultuous past.\n" +
                "\n" +
                "Modern Mombasa is a city of great diversity and life. This is a town were all are welcomed and quickly absorbed into this great coastal melting pot.\n" +
                "\n" +
                "Mombasa is a place where both history and progress are greatly valued, where a busy harbor existence is lived at its own unique, tropical pace.");

        mImageUrls.add("https://cdn-travel.jumia.com/web_hotel_detail_gallery/kivulini-luxury-resort-1784-afb29752cc0c869e22992dd9842ecc00cf10cbb5.jpeg");
        mNames.add("Malindi");
        mDescriptions.add("The small town of Malindi is at the centre of a strip of idyllic tropical beaches offering the visitor a range of world class resorts and quiet relaxing hideaways. Further south, the sleepy village of Watamu is fronted by wide white beaches. This tranquil haven is home to several well established resorts, and many private guesthouses scattered through the forest along the deserted shore.");


        mImageUrls.add("https://image.shutterstock.com/image-photo/turtle-beach-watamu-kenya-260nw-568660963.jpg");
        mNames.add("Watamu");
        mDescriptions.add("At Watamu a Marine National Park has been established, an ideal day trip for divers and snorkellers alike.");

        mImageUrls.add("https://www.telegraph.co.uk/content/dam/Travel/hotels/africa/kenya/lamu-island-getty.jpg");
        mNames.add("Lamu");
        mDescriptions.add("Lamu is a place like no other, a peaceful tropical island where life is lived at its own relaxed rhythm, but a place whose history is as mysterious and fascinating as the winding streets of its medieval stone town.\n" +
                "\n" +
                "The island itself is a beautiful place of rolling dunes and endless beaches, where tiny villages nestle among coconut and mango plantations and lateen sailed dhows ply the waters. But Lamu’s real attraction is its Old town.\n" +
                "\n" +
                "The town of Lamu began life as a 14th century Swahili settlement, but the island has seen many visitors and influences, including Portuguese explorers, Turkish traders and the Omani Arabs. All left their mark, but Lamu developed its own particular culture, which has ultimately endured.\n" +
                "\n" +
                "Lamu’s narrow streets remain unchanged, and in the markets and squares around the fort life moves at the same pace as it always has. There are no vehicles on this island, and the donkey and the dhow remain the dominant form of transport.\n" +
                "\n" +
                "The people of Lamu are great believers in tradition and custom, and this is a strong society built on a respect for the past.\n" +
                "For the traveller, Lamu is a hypnotically exotic experience, made even more enjoyable by the relaxed and welcoming attitudes of the locals. To visit Lamu is to enter another world, and the visitor finds themselves becoming a part of this world. Life slows down, and long days are spent strolling along the waterfront, exploring the town or relaxing on the beaches.\n" +
                "\n" +
                "Dhow safaris can take you beyond Lamu into the surrounding archipelago, where isolated villages, ancient ruins and a few luxurious and exclusive resorts lie hidden among the islands of Manda, Siyu, Pate and Kiwayu.\n" +
                "\n" +
                "This idyllic island speaks to the heart and soul, and a trip to Lamu is a romantic experience that can become a lifelong affair.\n" +
                "\n");

        initRecyclerView();
    }


    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls,mDescriptions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}



