package com.example.ltdd_c91a_group9;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.ltdd_c91a_group9.adapter.ProductAdapter;
import com.example.ltdd_c91a_group9.constants.AppCache;
import com.example.ltdd_c91a_group9.constants.CartSingleton;
import com.example.ltdd_c91a_group9.interfaces.OnItemClickProduct;
import com.example.ltdd_c91a_group9.models.Product;
import com.example.ltdd_c91a_group9.models.Response;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    List<Product> mListProduct;
    RecyclerView mRcvProduct;
    ProductAdapter mProductAdapter;
    Toolbar mToolbar;
    SearchView mSearchView;
    TextView mTvBadgeCart;
    Response mResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        mRcvProduct = findViewById(R.id.recyclerViewProduct);
        mToolbar = findViewById(R.id.toolbarProduct);
        setSupportActionBar(mToolbar);

        mListProduct = Product.getDataMock();


        mProductAdapter = new ProductAdapter(mListProduct);

        mRcvProduct.setHasFixedSize(true);
        mRcvProduct.setAdapter(mProductAdapter);

        // event

        if (AppCache.checkFileExists(ProductListActivity.this)) {
            mResponse = CartSingleton.getInstance().tranFormStringToResponseData(AppCache.readFile(ProductListActivity.this));
            List<Product> listProducts = mResponse.getProducts();
            if (listProducts != null && listProducts.size() > 0) {
                CartSingleton.getInstance().addAllCarts(listProducts);
            }
        }

        mProductAdapter.setOnItemClickProduct(new OnItemClickProduct() {
            @Override
            public void onClick(int index) {
                CartSingleton.getInstance().pushProduct(mListProduct.get(index));
                setupBadge();
                if (AppCache.checkFileExists(ProductListActivity.this)) {
                    mResponse.setProducts(CartSingleton.getInstance().getCart());
                    AppCache.updateFile(ProductListActivity.this,CartSingleton.getInstance().createJson(mResponse.getUser(),mResponse.getProducts()).toString());
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        //Hiển thị searchview full chiêu ngang của toolbar
//        mSearchView.setMaxWidth(Integer.MAX_VALUE);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mProductAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mProductAdapter.getFilter().filter(newText);
                return true;
            }
        });
        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = menuItem.getActionView();
        mTvBadgeCart = actionView.findViewById(R.id.textCartbage);
        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                Intent intent = new Intent(ProductListActivity.this, CartActivity.class);
                startActivityForResult(intent,123);
                break;
        }
        return true;
    }

    public void setupBadge() {
        if (CartSingleton.getInstance().getCart().size() == 0) {
            if (mTvBadgeCart != null) {
                mTvBadgeCart.setVisibility(View.GONE);
            }
        } else {
            if (mTvBadgeCart != null) {
                mTvBadgeCart.setVisibility(View.VISIBLE);
                int count = 0;
                for (Product product : CartSingleton.getInstance().getCart()){
                    count += product.getCount();
                }
                mTvBadgeCart.setText(count + "");
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setupBadge();
    }
}