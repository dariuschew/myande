package com.example.ande_ca2.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ande_ca2.adapters.ProfileRecipesAdapter;
import com.example.ande_ca2.R;
import com.example.ande_ca2.models.Comment;
import com.example.ande_ca2.models.Ingredient;
import com.example.ande_ca2.models.Instruction;
import com.example.ande_ca2.models.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private RecyclerView recipesRecyclerView;
    private ProfileRecipesAdapter recipesAdapter;
    private List<Recipe> recipesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to your profile.xml layout
        setContentView(R.layout.profile);

        // Find your ImageView by id
        ImageView ivProfile = findViewById(R.id.ivProfile);

        // Get the original bitmap from drawable resource
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic);

        // Get the circular bitmap
        Bitmap circularBitmap = getCircularBitmap(originalBitmap);

        // Set the circular bitmap to the ImageView
        ivProfile.setImageBitmap(circularBitmap);


        TextView textView = findViewById(R.id.tvRecipesListTitle);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml("<u>Recipes</u>", Html.FROM_HTML_MODE_LEGACY));
        } else {
            textView.setText(Html.fromHtml("<u>Recipes</u>"));
        }

        // Initialize recipesList with actual data here
        recipesList = new ArrayList<>();
        // First dummy recipe
        Recipe recipe1 = new Recipe(
                Arrays.asList("https://www.cookingclassy.com/wp-content/uploads/2012/11/spaghetti+with+meat+sauce11.jpg", "url_to_image1_2"),
                "Italian Spaghetti",
                "John Doe",
                "A classic Italian pasta dish with tomato sauce.",
                "30 mins",
                4,
                "Italy",
                Arrays.asList(
                        new Ingredient("Olive oil", "https://media.nedigital.sg/fairprice/fpol/media/images/product/XL/11449276_XL1_20210825.jpg"),
                        new Ingredient("Onion", "https://www.shutterstock.com/image-photo/red-whole-sliced-onion-isolated-260nw-1684863088.jpg"),
                        new Ingredient("Carrots", "https://blog-images-1.pharmeasy.in/blog/production/wp-content/uploads/2021/04/23175719/shutterstock_440493100-1.jpg"),
                        new Ingredient("Ground beef", "https://www.allrecipes.com/thmb/tQq1D3TigZEeysde4qL0LZ0N9D4=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/229112-ground-beef-with-homemade-taco-seasoning-mix-DDMFS-4x3-719013d51e844a948c0b39cccb5ed908.jpg"),
                        new Ingredient("Ground pork", "https://www.olgainthekitchen.com/wp-content/uploads/2017/06/DSC_0896.jpg"),
                        new Ingredient("Tomatoes", "https://media.post.rvohealth.io/wp-content/uploads/2020/09/AN313-Tomatoes-732x549-Thumb.jpg"),
                        new Ingredient("Basil leaves", "https://m.media-amazon.com/images/I/51W8xfdp-iL._AC_UF1000,1000_QL80_.jpg"),
                        new Ingredient("Sugar", "https://www.hsph.harvard.edu/nutritionsource/wp-content/uploads/sites/30/2022/04/sugar-g963832288_1280.jpg"),
                        new Ingredient("Kosher salt", "https://www.eatingwell.com/thmb/QQYn36IO-f9sDHIaf-s0xuIFS40=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/what-is-kosher-salt-2000-7f0f88e664bb45db9836066b047cceed.jpg"))
                ,
                Arrays.asList(
                        new Instruction("Boil water in a large pot.", Arrays.asList(
                                "https://www.seriouseats.com/thmb/7lo_xXS8AYRZoM6u46qthnqxlWQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/boliingwater-Amandasuarez-hero-15c969283eec4a34b7a38874cfc85f45.jpg",
                                "https://www.kingarthurbaking.com/sites/default/files/styles/featured_image/public/2021-08/Salt%20in%20baking%202.jpeg?itok=knozvPyj",
                                "https://www.mashed.com/img/gallery/why-its-a-mistake-to-break-long-pasta-before-putting-it-in-a-pot/intro-1672856363.jpg"
                        )),
                        new Instruction("Cook the spaghetti until al dente.", Arrays.asList(
                                "https://www.thechunkychef.com/wp-content/uploads/2019/09/One-Pot-Spaghetti-feat.jpg",
                                "https://example.com/images/stirring-pasta.jpg",
                                "https://example.com/images/tasting-pasta.jpg"
                        )),
                        new Instruction("Drain the pasta and set aside.", Arrays.asList(
                                "https://example.com/images/draining-pasta.jpg",
                                "https://example.com/images/shaking-colander.jpg",
                                "https://example.com/images/pasta-finished-draining.jpg"
                        )),
                        new Instruction("Prepare the tomato sauce by sautéing onions and garlic.", Arrays.asList(
                                "https://example.com/images/sauteing-onions.jpg",
                                "https://example.com/images/adding-garlic.jpg",
                                "https://example.com/images/adding-tomatoes.jpg"
                        )),
                        new Instruction("Combine pasta with tomato sauce and serve.", Arrays.asList(
                                "https://example.com/images/mixing-pasta-sauce.jpg",
                                "https://example.com/images/garnishing-with-basil.jpg",
                                "https://example.com/images/serving-pasta.jpg"
                        ))
                ),
                Arrays.asList(
                        new Comment("Delicious recipe!", "Jane Doe", "url_to_profile1_image", 10, new Date()),
                        new Comment("Easy to make and tasty.", "Mike Smith", "url_to_profile1_image", 5, new Date())
                ),
                4.5
        );

        // Second dummy recipe
        Recipe recipe2 = new Recipe(
                Arrays.asList("https://cdn.loveandlemons.com/wp-content/uploads/2023/02/vegetarian-pizza.jpg", "url_to_image2_2"),
                "Vegetarian Pizza",
                "Alice Johnson",
                "Homemade pizza with bell peppers, olives, and mushrooms.",
                "45 mins",
                2,
                "Italy",
                Arrays.asList(
                        new Ingredient("Olive oil", "https://media.nedigital.sg/fairprice/fpol/media/images/product/XL/11449276_XL1_20210825.jpg"),
                        new Ingredient("Onion", "https://www.shutterstock.com/image-photo/red-whole-sliced-onion-isolated-260nw-1684863088.jpg"),
                        new Ingredient("Carrots", "https://blog-images-1.pharmeasy.in/blog/production/wp-content/uploads/2021/04/23175719/shutterstock_440493100-1.jpg"),
                        new Ingredient("Ground beef", "https://www.allrecipes.com/thmb/tQq1D3TigZEeysde4qL0LZ0N9D4=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/229112-ground-beef-with-homemade-taco-seasoning-mix-DDMFS-4x3-719013d51e844a948c0b39cccb5ed908.jpg"),
                        new Ingredient("Ground pork", "https://www.olgainthekitchen.com/wp-content/uploads/2017/06/DSC_0896.jpg"),
                        new Ingredient("Tomatoes", "https://media.post.rvohealth.io/wp-content/uploads/2020/09/AN313-Tomatoes-732x549-Thumb.jpg"),
                        new Ingredient("Basil leaves", "https://m.media-amazon.com/images/I/51W8xfdp-iL._AC_UF1000,1000_QL80_.jpg"),
                        new Ingredient("Sugar", "https://www.hsph.harvard.edu/nutritionsource/wp-content/uploads/sites/30/2022/04/sugar-g963832288_1280.jpg"),
                        new Ingredient("Kosher salt", "https://www.eatingwell.com/thmb/QQYn36IO-f9sDHIaf-s0xuIFS40=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/what-is-kosher-salt-2000-7f0f88e664bb45db9836066b047cceed.jpg")),
                Arrays.asList(
                        new Instruction("Boil water in a large pot.", Arrays.asList(
                                "https://www.seriouseats.com/thmb/7lo_xXS8AYRZoM6u46qthnqxlWQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/boliingwater-Amandasuarez-hero-15c969283eec4a34b7a38874cfc85f45.jpg",
                                "https://www.kingarthurbaking.com/sites/default/files/styles/featured_image/public/2021-08/Salt%20in%20baking%202.jpeg?itok=knozvPyj",
                                "https://www.mashed.com/img/gallery/why-its-a-mistake-to-break-long-pasta-before-putting-it-in-a-pot/intro-1672856363.jpg"
                        )),
                        new Instruction("Cook the spaghetti until al dente.", Arrays.asList(
                                "https://www.thechunkychef.com/wp-content/uploads/2019/09/One-Pot-Spaghetti-feat.jpg",
                                "https://example.com/images/stirring-pasta.jpg",
                                "https://example.com/images/tasting-pasta.jpg"
                        )),
                        new Instruction("Drain the pasta and set aside.", Arrays.asList(
                                "https://example.com/images/draining-pasta.jpg",
                                "https://example.com/images/shaking-colander.jpg",
                                "https://example.com/images/pasta-finished-draining.jpg"
                        )),
                        new Instruction("Prepare the tomato sauce by sautéing onions and garlic.", Arrays.asList(
                                "https://example.com/images/sauteing-onions.jpg",
                                "https://example.com/images/adding-garlic.jpg",
                                "https://example.com/images/adding-tomatoes.jpg"
                        )),
                        new Instruction("Combine pasta with tomato sauce and serve.", Arrays.asList(
                                "https://example.com/images/mixing-pasta-sauce.jpg",
                                "https://example.com/images/garnishing-with-basil.jpg",
                                "https://example.com/images/serving-pasta.jpg"
                        ))
                ),
                Arrays.asList(
                        new Comment("Loved this pizza recipe!", "Sam Lee", "url_to_profile2_image", 8, new Date()),
                        new Comment("My kids enjoyed it so much.", "Emily Wang", "url_to_profile2_image", 7, new Date())
                ),
                4.8
        );

        // Third dummy recipe
        Recipe recipe3 = new Recipe(
                Arrays.asList("https://www.unileverfoodsolutions.com.sg/dam/global-ufs/mcos/SEA/calcmenu/recipes/SG-recipes/vegetables-&-vegetable-dishes/%E7%BB%8F%E5%85%B8%E8%8A%9D%E5%A3%AB%E6%B1%89%E5%A0%A1/main-header.jpg", "url_to_image3_2"),
                "Classic Cheeseburger",
                "Bob Brown",
                "Juicy beef burger with cheese and lettuce.",
                "25 mins",
                1,
                "USA",
                Arrays.asList(
                        new Ingredient("Olive oil", "https://media.nedigital.sg/fairprice/fpol/media/images/product/XL/11449276_XL1_20210825.jpg"),
                        new Ingredient("Onion", "https://www.shutterstock.com/image-photo/red-whole-sliced-onion-isolated-260nw-1684863088.jpg"),
                        new Ingredient("Carrots", "https://blog-images-1.pharmeasy.in/blog/production/wp-content/uploads/2021/04/23175719/shutterstock_440493100-1.jpg"),
                        new Ingredient("Ground beef", "https://www.allrecipes.com/thmb/tQq1D3TigZEeysde4qL0LZ0N9D4=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/229112-ground-beef-with-homemade-taco-seasoning-mix-DDMFS-4x3-719013d51e844a948c0b39cccb5ed908.jpg"),
                        new Ingredient("Ground pork", "https://www.olgainthekitchen.com/wp-content/uploads/2017/06/DSC_0896.jpg"),
                        new Ingredient("Tomatoes", "https://media.post.rvohealth.io/wp-content/uploads/2020/09/AN313-Tomatoes-732x549-Thumb.jpg"),
                        new Ingredient("Basil leaves", "https://m.media-amazon.com/images/I/51W8xfdp-iL._AC_UF1000,1000_QL80_.jpg"),
                        new Ingredient("Sugar", "https://www.hsph.harvard.edu/nutritionsource/wp-content/uploads/sites/30/2022/04/sugar-g963832288_1280.jpg"),
                        new Ingredient("Kosher salt", "https://www.eatingwell.com/thmb/QQYn36IO-f9sDHIaf-s0xuIFS40=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/what-is-kosher-salt-2000-7f0f88e664bb45db9836066b047cceed.jpg")),
                Arrays.asList(
                        new Instruction("Boil water in a large pot.", Arrays.asList(
                                "https://www.seriouseats.com/thmb/7lo_xXS8AYRZoM6u46qthnqxlWQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/boliingwater-Amandasuarez-hero-15c969283eec4a34b7a38874cfc85f45.jpg",
                                "https://www.kingarthurbaking.com/sites/default/files/styles/featured_image/public/2021-08/Salt%20in%20baking%202.jpeg?itok=knozvPyj",
                                "https://www.mashed.com/img/gallery/why-its-a-mistake-to-break-long-pasta-before-putting-it-in-a-pot/intro-1672856363.jpg"
                        )),
                        new Instruction("Cook the spaghetti until al dente.", Arrays.asList(
                                "https://www.thechunkychef.com/wp-content/uploads/2019/09/One-Pot-Spaghetti-feat.jpg",
                                "https://example.com/images/stirring-pasta.jpg",
                                "https://example.com/images/tasting-pasta.jpg"
                        )),
                        new Instruction("Drain the pasta and set aside.", Arrays.asList(
                                "https://example.com/images/draining-pasta.jpg",
                                "https://example.com/images/shaking-colander.jpg",
                                "https://example.com/images/pasta-finished-draining.jpg"
                        )),
                        new Instruction("Prepare the tomato sauce by sautéing onions and garlic.", Arrays.asList(
                                "https://example.com/images/sauteing-onions.jpg",
                                "https://example.com/images/adding-garlic.jpg",
                                "https://example.com/images/adding-tomatoes.jpg"
                        )),
                        new Instruction("Combine pasta with tomato sauce and serve.", Arrays.asList(
                                "https://example.com/images/mixing-pasta-sauce.jpg",
                                "https://example.com/images/garnishing-with-basil.jpg",
                                "https://example.com/images/serving-pasta.jpg"
                        ))
                ),
                Arrays.asList(
                        new Comment("This is the best burger recipe!", "Gary Stewart", "url_to_profile3_image", 12, new Date()),
                        new Comment("Tastes just like restaurant quality.", "Nancy Drew", "url_to_profile3_image", 9, new Date())
                ),
                5.0
        );

        // Add the dummy recipes to the list
        recipesList.add(recipe1);
        recipesList.add(recipe2);
        recipesList.add(recipe3);
        // Set up RecyclerView
        recipesRecyclerView = findViewById(R.id.recipeRecyclerView);
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipesAdapter = new ProfileRecipesAdapter(recipesList);
        recipesRecyclerView.setAdapter(recipesAdapter);


        // Find the "Edit Profile" button by id
        Button btnEditProfile = findViewById(R.id.btnEditProfile);

        // Set an OnClickListener on the button
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start EditProfileActivity
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);

                // Start EditProfileActivity
                startActivity(intent);
            }

        });
    }

    // Method to convert a bitmap into a circular bitmap
    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int size = Math.min(width, height);

        Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        float radius = size / 2f;
        canvas.drawCircle(radius, radius, radius, paint);

        if (bitmap != output) {
            bitmap.recycle();
        }

        return output;
    }
}
