package com.example.ande_ca2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.ande_ca2.adapters.CommentsAdapter;
import com.example.ande_ca2.adapters.ImagePagerAdapter;
import com.example.ande_ca2.R;
import com.example.ande_ca2.adapters.IngredientsAdapter;
import com.example.ande_ca2.adapters.InstructionsAdapter;
import com.example.ande_ca2.models.Comment;
import com.example.ande_ca2.models.Ingredient;
import com.example.ande_ca2.models.Instruction;
import com.example.ande_ca2.models.Recipe;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ViewRecipeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private TextView title;
    private TextView name;
    private TextView username;
    private TextView description;
    private TextView timeTextView;
    private TextView servingTextView;
    private TextView originTextView;
    private RecyclerView instructionsRecyclerView;
    private RecyclerView commentsRecyclerView;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewrecipe_page);

        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.ll_dots);
        title = findViewById(R.id.title);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        description = findViewById(R.id.recipe_description);
        timeTextView = findViewById(R.id.time_text_view);
        servingTextView = findViewById(R.id.serving_text_view);
        originTextView = findViewById(R.id.origin_text_view);
        RecyclerView ingredientsRecyclerView = findViewById(R.id.ingredientsRecyclerView);
        instructionsRecyclerView = findViewById(R.id.instructionsRecyclerView);
        commentsRecyclerView = findViewById(R.id.commentsRecyclerView);
        TextView commentsTitleTextView = findViewById(R.id.commentsTitle);


        Recipe recipe = getRecipeDetails();

        String commentsTitle = String.format(Locale.getDefault(), "Comments (%d)", recipe.getComments().size());
        commentsTitleTextView.setText(commentsTitle);

        title.setText(recipe.getTitle());
        name.setText(recipe.getAuthor());
        username.setText("@" + recipe.getAuthor());
        description.setText(recipe.getDescription());
        timeTextView.setText(recipe.getCookTime());
        servingTextView.setText(String.valueOf(recipe.getServings()) + " serves");
        originTextView.setText(recipe.getOrigin());

        // Assume your ViewPager adapter is set up correctly
        setupViewPager(viewPager, recipe);

        // Set up the pagination dots
        setupPaginationDots(recipe.getImageUrls().size());

        // Add page change listener to update the dots
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // Initialize the "Show More" button
        ImageView showMoreImageView = findViewById(R.id.ivViewAllComments);
        showMoreImageView.setOnClickListener(view -> {
            Intent intent = new Intent(ViewRecipeActivity.this, AllCommentsActivity.class);

            // Convert the list of comments to a JSON string
            String commentsJson = convertCommentsListToJson(recipe.getComments());

            // Pass the JSON string to the AllCommentsActivity
            intent.putExtra("comments_json", commentsJson);
            startActivity(intent);
        });

        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(recipe.getIngredients());
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        InstructionsAdapter instructionsAdapter = new InstructionsAdapter(recipe.getInstructions());
        instructionsRecyclerView.setAdapter(instructionsAdapter);
        instructionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        CommentsAdapter commentsAdapter = new CommentsAdapter(recipe.getComments().subList(0, 3));
        commentsRecyclerView.setAdapter(commentsAdapter);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupPaginationDots(int numImages) {
        dots = new TextView[numImages];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 0, 8, 0);

        for (int i = 0; i < numImages; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.white, getTheme()));
            dots[i].setLayoutParams(params);
            dotsLayout.addView(dots[i]);
        }
        selectDot(0); // Initial selected dot
    }

    private void selectDot(int index) {
        for (int i = 0; i < dots.length; i++) {
            dots[i].setBackgroundResource(i == index ? R.drawable.ic_dot_active : R.drawable.ic_dot_inactive);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            if (i == index) {
                params.width = (int) getResources().getDimension(R.dimen.active_dot_width);
                params.height = (int) getResources().getDimension(R.dimen.active_dot_height);
            } else {
                params.width = (int) getResources().getDimension(R.dimen.inactive_dot_width);
                params.height = (int) getResources().getDimension(R.dimen.inactive_dot_height);
            }

            params.setMargins(4, 0, 4, 0);
            dots[i].setLayoutParams(params);
        }
    }


    private void setupViewPager(ViewPager viewPager, Recipe recipe) {
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, recipe.getImageUrls());
        viewPager.setAdapter(adapter);
    }


    private Recipe getRecipeDetails() {

        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("2 tablespoons Olive oil", "https://media.nedigital.sg/fairprice/fpol/media/images/product/XL/11449276_XL1_20210825.jpg"),
                new Ingredient("1 large Onion, finely chopped", "https://www.shutterstock.com/image-photo/red-whole-sliced-onion-isolated-260nw-1684863088.jpg"),
                new Ingredient("3 medium Carrots, peeled and diced", "https://blog-images-1.pharmeasy.in/blog/production/wp-content/uploads/2021/04/23175719/shutterstock_440493100-1.jpg"),
                new Ingredient("500g Ground beef", "https://www.allrecipes.com/thmb/tQq1D3TigZEeysde4qL0LZ0N9D4=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/229112-ground-beef-with-homemade-taco-seasoning-mix-DDMFS-4x3-719013d51e844a948c0b39cccb5ed908.jpg"),
                new Ingredient("250g Ground pork", "https://www.olgainthekitchen.com/wp-content/uploads/2017/06/DSC_0896.jpg"),
                new Ingredient("4 large Tomatoes, diced", "https://media.post.rvohealth.io/wp-content/uploads/2020/09/AN313-Tomatoes-732x549-Thumb.jpg"),
                new Ingredient("A handful of Basil leaves, chopped", "https://m.media-amazon.com/images/I/51W8xfdp-iL._AC_UF1000,1000_QL80_.jpg"),
                new Ingredient("1 teaspoon Sugar", "https://www.hsph.harvard.edu/nutritionsource/wp-content/uploads/sites/30/2022/04/sugar-g963832288_1280.jpg"),
                new Ingredient("1 teaspoon Kosher salt", "https://www.eatingwell.com/thmb/QQYn36IO-f9sDHIaf-s0xuIFS40=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/what-is-kosher-salt-2000-7f0f88e664bb45db9836066b047cceed.jpg")
        );

        List<Instruction> instructions = Arrays.asList(
                new Instruction("Boil water in a large pot.", Arrays.asList(
                        "https://www.seriouseats.com/thmb/7lo_xXS8AYRZoM6u46qthnqxlWQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/boliingwater-Amandasuarez-hero-15c969283eec4a34b7a38874cfc85f45.jpg",
                        "https://www.kingarthurbaking.com/sites/default/files/styles/featured_image/public/2021-08/Salt%20in%20baking%202.jpeg?itok=knozvPyj",
                        "https://www.mashed.com/img/gallery/why-its-a-mistake-to-break-long-pasta-before-putting-it-in-a-pot/intro-1672856363.jpg"
                )),
                new Instruction("Cook the spaghetti until al dente.", Arrays.asList(
                        "https://www.thechunkychef.com/wp-content/uploads/2019/09/One-Pot-Spaghetti-feat.jpg",
                        "https://www.tastingtable.com/img/gallery/the-most-important-time-to-stir-pasta-while-cooking/l-intro-1651506580.jpg",
                        "https://goop-img.com/wp-content/uploads/2019/09/TRU2454022.jpg"
                )),
                new Instruction("Drain the pasta and set aside.", Arrays.asList(
                        "https://imageresizer.static9.net.au/X8a0xH-Lf_BLhqkYjVvWKUNpWOM=/1200x900/https%3A%2F%2Fprod.static9.net.au%2Ffs%2F74c2cc7b-8bcf-4416-b04b-02300c3c382b",
                        "https://www.thespruceeats.com/thmb/Fc5BvKueeav_Pm7JtCudwrSmEsU=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Colander-Blow-Cuisine-56a11c255f9b58b7d0bbcd2b.jpg",
                        "https://res.cloudinary.com/hksqkdlah/image/upload/ar_16:9,c_fill,dpr_2.0,f_auto,fl_lossy.progressive.strip_profile,g_faces:auto,q_auto:low,w_400/v1/ATK%20Reviews/2021/6_CIND_Italian%20Pasta/STP_PastaAllAmatriciana_3473"
                )),
                new Instruction("Prepare the tomato sauce by sautéing onions and garlic.", Arrays.asList(
                        "https://www.acouplecooks.com/wp-content/uploads/2021/01/Sauteed-Onions-006.jpg",
                        "https://bossthekitchen.com/wp-content/uploads/2022/10/adding-garlic.webp",
                        "https://previews.123rf.com/images/windnarsil/windnarsil1905/windnarsil190502301/122678419-young-woman-adding-cherry-tomatoes-into-salad.jpg"
                )),
                new Instruction("Combine pasta with tomato sauce and serve.", Arrays.asList(
                        "https://www.seriouseats.com/thmb/3yX5n2twa57jXB86ZzrbLxgEta8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__images__2016__02__20160218-finishin-pasta-sauce-1-d1709732a0734803a36e34610aff94db.jpg",
                        "https://c8.alamy.com/comp/FB1NGA/plate-of-spaghetti-bolognese-with-basil-garnish-closeup-shot-with-FB1NGA.jpg",
                        "https://i.shgcdn.com/f4732c84-5a59-4337-b3fa-b865c279c4fd/-/format/auto/-/preview/3000x3000/-/quality/lighter/"
                ))
        );

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        List<Comment> comments = Arrays.asList(
                new Comment("This recipe was fantastic! The sauce had just the right amount of herbs.", "Jane Doe", "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cmFuZG9tJTIwcGVvcGxlfGVufDB8fDB8fHww", 10, parseDate("2023-05-21", dateFormat)),
                new Comment("Truly easy to make and so delicious. My go-to recipe for a quick meal now.", "Mike Smith", "https://i.pinimg.com/736x/39/34/1a/39341ac32898c0a5d0d3fc189cda0f79.jpg", 5, parseDate("2023-05-18", dateFormat)),
                new Comment("The rich flavors of the sauce perfectly complemented the al dente pasta. Will make again!", "Emily Johnson", "https://i.pinimg.com/736x/af/7b/df/af7bdf886b4fc82926f2712016035af5.jpg", 8, parseDate("2023-05-15", dateFormat)),
                new Comment("A hit at our family dinner table. Even the kids asked for seconds!", "John Brown", "https://i.pinimg.com/236x/3c/c7/c4/3cc7c481aa319d5427b3cf0b8ec89105.jpg", 12, parseDate("2023-05-12", dateFormat)),
                new Comment("I appreciated the simplicity of the recipe. Added a personal touch with some extra garlic.", "Chris Lee", "https://img.freepik.com/free-photo/portrait-young-beautiful-woman-with-smoky-eyes-makeup-pretty-young-adult-girl-posing-studio-closeup-attractive-female-face_186202-4439.jpg?size=626&ext=jpg&ga=GA1.1.44546679.1705276800&semt=ais", 15, parseDate("2023-05-09", dateFormat)),
                new Comment("Perfect for family dinners. It's been added to our weekly rotation of dishes.", "Jessica Davis", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQc6cef90BvUhsR2u4mXmuDfpMR32BDtc0jJg&usqp=CAU", 23, parseDate("2023-05-06", dateFormat)),
                new Comment("The search for the ultimate spaghetti recipe is over. This is the one!", "Sarah Wilson", "https://i.pinimg.com/474x/af/6d/78/af6d78cd740d72aabac5b6b5b5b2842b.jpg", 7, parseDate("2023-05-03", dateFormat))
        );

        return new Recipe(
                Arrays.asList("https://www.cookingclassy.com/wp-content/uploads/2012/11/spaghetti+with+meat+sauce11.jpg", "https://www.christinascucina.com/wp-content/uploads/2021/04/fullsizeoutput_f176-e1618706152425.jpeg", "https://staticcookist.akamaized.net/wp-content/uploads/sites/22/2021/06/THUMB-LINK-2020-2-1200x675.jpg"),
                "Italian Spaghetti",
                "John Doe",
                "Experience the essence of Italy with our Classic Italian Pasta Dish, featuring perfectly cooked spaghetti enveloped in a rich, homemade tomato sauce. This sauce is the star of the dish, crafted with sun-ripened tomatoes, a hint of aromatic basil, and a whisper of garlic. It's a simple yet elegant testament to traditional Italian cooking, where the quality of ingredients shines.",
                "30 mins",
                4,
                "Italy",
                ingredients,
                instructions,
                comments,
                4.5
        );
    }

    private Date parseDate(String dateStr, SimpleDateFormat dateFormat) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    private String convertCommentsListToJson(List<Comment> comments) {
        Gson gson = new Gson();
        return gson.toJson(comments);
    }
}