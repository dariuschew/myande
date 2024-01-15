package com.example.ande_ca2.activities;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.ande_ca2.adapters.ImagePagerAdapter;
import com.example.ande_ca2.R;
import com.example.ande_ca2.adapters.IngredientsAdapter;
import com.example.ande_ca2.models.Comment;
import com.example.ande_ca2.models.Ingredient;
import com.example.ande_ca2.models.Instruction;
import com.example.ande_ca2.models.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

        Recipe recipe = getRecipeDetails();

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
                // This method can be left empty
            }

            @Override
            public void onPageSelected(int position) {
                selectDot(position); // Update the dots on page change
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // This method can be left empty
            }
        });

        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(recipe.getIngredients());
        ingredientsRecyclerView.setAdapter(ingredientsAdapter);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setupPaginationDots(int numImages) {
        dots = new TextView[numImages];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 0, 8, 0); // Adjust the margin as needed

        for (int i = 0; i < numImages; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;")); // HTML code for bullet symbol
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

            params.setMargins(4, 0, 4, 0); // Adjust the margin as needed
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

        return new Recipe(
                Arrays.asList("https://www.cookingclassy.com/wp-content/uploads/2012/11/spaghetti+with+meat+sauce11.jpg", "https://www.christinascucina.com/wp-content/uploads/2021/04/fullsizeoutput_f176-e1618706152425.jpeg", "https://staticcookist.akamaized.net/wp-content/uploads/sites/22/2021/06/THUMB-LINK-2020-2-1200x675.jpg"),
                "Italian Spaghetti",
                "John Doe",
                "Experience the essence of Italy with our Classic Italian Pasta Dish, featuring perfectly cooked spaghetti enveloped in a rich, homemade tomato sauce. This sauce is the star of the dish, crafted with sun-ripened tomatoes, a hint of aromatic basil, and a whisper of garlic. It's a simple yet elegant testament to traditional Italian cooking, where the quality of ingredients shines.",
                "30 mins",
                4,
                "Italy",
                ingredients,
                Arrays.asList(
                        new Instruction("url_to_instruction1_image1", "Boil water and add salt."),
                        new Instruction("url_to_instruction1_image2", "Cook spaghetti for 10 minutes.")
                ),
                Arrays.asList(
                        new Comment("Delicious recipe!", "Jane D    oe", "url_to_profile1_image", 10, new Date()),
                        new Comment("Easy to make and tasty.", "Mike Smith", "url_to_profile1_image", 5, new Date())
                ),
                4.5
        );
    }
}