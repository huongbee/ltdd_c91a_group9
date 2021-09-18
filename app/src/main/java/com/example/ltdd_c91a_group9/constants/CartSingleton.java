package com.example.ltdd_c91a_group9.constants;

import com.example.ltdd_c91a_group9.models.Product;
import com.example.ltdd_c91a_group9.models.Response;
import com.example.ltdd_c91a_group9.models.SaleOff;
import com.example.ltdd_c91a_group9.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartSingleton {
    private List<Product> mList;
    private static CartSingleton mInstance = null;

    private CartSingleton() {
        mList = new ArrayList<>();
    }

    public static CartSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new CartSingleton();
        }
        return mInstance;
    }

    public void pushProduct(Product product) {
        boolean instance = false;
        if (mList.size() == 0) {
            mList.add(product);
        } else {
            for (int i = 0; i < mList.size(); i++) {
                if (product.getId() == mList.get(i).getId()) {
                    mList.get(i).setCount(mList.get(i).getCount() + 1);
                    instance = true;
                    break;
                } else {
                    instance = false;
                }
            }
            if (!instance) {
                mList.add(product);
            }
        }
    }

    public void addAllCarts(List<Product> products) {
        this.mList = products;
    }

    public void clearCart() {
        mList.clear();
    }

    public List<Product> getCart() {
        return mList;
    }

    public long priceProductSales(long price, double percent) {
        return (long) (price * ((100 - percent) / 100));
    }

    public JSONObject createJson(User user, List<Product> products) {
        JSONObject jsonObjectResult = new JSONObject();
        try {

            // object user
            JSONObject jsonUserObject = new JSONObject();
            jsonUserObject.put("email", user.getEmail());
            jsonUserObject.put("password", user.getPassword());
            jsonUserObject.put("isLogin", user.isLogin());

            // arrayCart
            JSONArray jsonCartArray = new JSONArray();

            if (products == null || products.size() == 0){
                jsonObjectResult.put("user", jsonUserObject);
                jsonObjectResult.put("cart", "[]");
                return jsonObjectResult;
            }

            for (int i = 0; i < products.size(); i++) {

                JSONObject jsonObjectSale = new JSONObject();
                jsonObjectSale.put("id", products.get(i).getSaleOff().getId());
                jsonObjectSale.put("title", products.get(i).getSaleOff().getTitle());
                jsonObjectSale.put("percent", products.get(i).getSaleOff().getPercent());

                JSONObject jsonObjectProduct = new JSONObject();
                jsonObjectProduct.put("id", products.get(i).getId());
                jsonObjectProduct.put("name", products.get(i).getName());
                jsonObjectProduct.put("price", products.get(i).getPrice());
                jsonObjectProduct.put("saleOff", jsonObjectSale);
                jsonObjectProduct.put("count", products.get(i).getCount());
                jsonObjectProduct.put("image", products.get(i).getImage());

                jsonCartArray.put(jsonObjectProduct);

            }

            jsonObjectResult.put("user", jsonUserObject);
            jsonObjectResult.put("cart", jsonCartArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObjectResult;
    }

    public Response tranFormStringToResponseData(String data) {
        Response response = new Response();

        List<Product> products = new ArrayList<>();
        try {
            JSONObject jsonObjectData = new JSONObject(data);

            // get User
            JSONObject jsonObjectUser = jsonObjectData.getJSONObject("user");
            String email = jsonObjectUser.getString("email");
            String password = jsonObjectUser.getString("password");
            boolean isLogin = jsonObjectUser.getBoolean("isLogin");
            response.setUser(new User(email, password, isLogin));


            // get Cart
            JSONArray jsonArray = jsonObjectData.getJSONArray("cart");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectProduct = (JSONObject) jsonArray.get(i);
                long id = jsonObjectProduct.getLong("id");
                String name = jsonObjectProduct.getString("name");
                double price = jsonObjectProduct.getDouble("price");
                int count = jsonObjectProduct.getInt("count");
                int image = jsonObjectProduct.getInt("image");

                JSONObject jsonObjectSale = jsonObjectProduct.getJSONObject("saleOff");
                int idSale = jsonObjectSale.getInt("id");
                String title = jsonObjectSale.getString("title");
                double percent = jsonObjectSale.getDouble("percent");

                SaleOff saleOff = new SaleOff(idSale, title, percent);
                Product product = new Product(id, name, image, price, saleOff, count);

                products.add(product);
            }
            response.setProducts(products);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

}
