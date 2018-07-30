package johnny.portfolio.mobile.restaurant.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Johnny on 10/28/2015.
 */
public class CommentList {
    /**
     * An array of sample (dummy) items.
     */
    public static List<Comment> Comments = new ArrayList<Comment>();

    /**
     * A map of sample (dummy) items, by restaurant name
     */
    public static Map<String, Comment> Comment_Map = new HashMap<String, Comment>();

    private static void addItem(Comment comment) {
        Comments.add(comment);
        Comment_Map.put(comment.restname, comment);
    }

    public static final Comment[] Comment_Array = new Comment[]{};
    /*
    public static final Restaurant[] Restaurant_Array = {
            new Restaurant("Bavette's Bar and Boeuf",
                    Restaurant.Category.Restaurant,
                    "218 W Kinzie, Chicago, IL 60654 (Wells/Franklin)",
                    5.0f,
                    12,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/03/0b/9d/de/bavette-s-bar-and-boeuf.jpg",
                    "http://media-cdn.tripadvisor.com/media/daodao/photo-s/09/27/2b/71/caption.jpg",
                    "http://media-cdn.tripadvisor.com/media/daodao/photo-s/09/27/2b/70/caption.jpg"),
            new Restaurant("Swirlz Cupcakes",
                    Restaurant.Category.Dessert,
                    "705 W Belden Ave, Chicago, IL 60614-3301",
                    5.0f,
                    76,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/02/f8/84/b1/swirlz-cupcakes.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/29/28/70/photo0jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/08/a7/08/d8/swirlz.jpg"),
            new Restaurant("Jeni's Splendid Ice Creams",
                    Restaurant.Category.IceCream,
                    "3404 N. Southport Ave, Chicago, IL 60657",
                    4.0f,
                    69,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/05/21/50/16/jeni-s-splendid-ice-creams.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/08/28/4c/83/photo0jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/05/21/50/22/jeni-s-splendid-ice-creams.jpg"),
            new Restaurant("Intelligentsia Coffee",
                    Restaurant.Category.CoffeeTea,
                    "53 West Jackson Boulevard, Chicago, IL 60612-2512",
                    4.0f,
                    12,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/01/47/29/ee/great-coffee-intelligenista.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/08/a5/e6/6b/intelligentsia-coffee.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/43/ff/3f/intelligentsia-coffee.jpg"),
            new Restaurant("Firecakes Donuts",
                    Restaurant.Category.Bakeries,
                    "30 E Hubbard, Chicago, IL 60611",
                    3.0f,
                    19,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-o/08/26/79/dc/firecakes-donuts.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/03/b4/af/1c/firecakes-donuts.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/04/18/ac/11/firecakes-donuts.jpg"),
            new Restaurant("Polo Cafe & Catering Bridgeport",
                    Restaurant.Category.Restaurant,
                    "3322 S Morgan St, Chicago, IL 60608",
                    1.0f,
                    56,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-o/03/ee/e3/c0/polo-cafe-catering-bridgeport.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/41/5e/27/photo2jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/09/1c/71/51/polo-cafe-catering-bridgeport.jpg"),
            new Restaurant("Garrett Popcorn Shops",
                    Restaurant.Category.Dessert,
                    "625 N Michigan Ave, Chicago, IL 60611",
                    3.0f,
                    132,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-o/09/2e/ef/00/photo0jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/02/95/f5/51/filename-garretts1-jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/08/7d/5e/86/garrett-popcorn-shops.jpg"),
            new Restaurant("Lickity Split Frozen Custard & Sweets",
                    Restaurant.Category.IceCream,
                    "6056 N. Broadway, Chicago, IL 60660",
                    2.0f,
                    23,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/04/71/88/30/amazing.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/08/98/07/5f/photo0jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/08/12/43/ab/what-a-wonderful-place.jpg"),
            new Restaurant("Wildberry Pancakes and Cafe",
                    Restaurant.Category.CoffeeTea,
                    "130 E Randolph St, Chicago, IL 60601-6207",
                    1.0f,
                    33,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/02/76/f2/5a/filename-photo-35-jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/30/e6/98/super.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/03/01/2d/02/wildberry-pancakes-and.jpg"),
            new Restaurant("Doughnut Vault",
                    Restaurant.Category.Bakeries,
                    "401 1/2 N. Franklin St., Chicago, IL 60654",
                    4.0f,
                    16,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-w/08/9c/00/80/doughnut-vault.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/1a/6c/c0/doughnut-vault.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-w/06/99/ca/7b/doughnut-vault.jpg"),
            new Restaurant("Glazed and Infused",
                    Restaurant.Category.Dessert,
                    "30 E Hubbard, Chicago, IL 60611",
                    4.0f,
                    94,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-o/08/8f/b3/41/glazed-and-infused.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/05/0d/eb/photo2jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/07/e8/90/f7/glazed-and-infused.jpg"),
            new Restaurant("Alinea",
                    Restaurant.Category.Restaurant,
                    "1723 North Halsted St., Chicago, IL 60614-5501",
                    4.0f,
                    88,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-o/09/29/0a/2a/alinea.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/3a/a2/3c/photo1jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/09/29/0a/0e/alinea.jpg"
            ),
            new Restaurant("Ghirardelli Ice Cream & Chocolate Shop",
                    Restaurant.Category.IceCream,
                    "830 N Michigan Ave, Chicago, IL 60611-2078",
                    5.0f,
                    21,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/03/73/c2/24/ghirardelli-ice-cream.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/3d/c6/b0/homemade-whipped-cream.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/07/1e/bb/77/ghirardelli-ice-cream.jpg"),
            new Restaurant("Do - Rite Donuts",
                    Restaurant.Category.Bakeries,
                    "50 W Randolph St, Chicago, IL 60601",
                    1.0f,
                    72,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/05/a4/ea/c4/do-rite-donuts-coffee.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-w/07/ec/aa/52/do-rite-donuts.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-w/07/cf/35/79/photo0jpg.jpg"),
            new Restaurant("West Egg Cafe",
                    Restaurant.Category.CoffeeTea,
                    "620 N Fairbanks Ct, Chicago, IL 60611-3011",
                    5.0f,
                    45,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/01/6b/b9/e7/hubby-s-meal.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-w/08/e8/d8/96/west-egg-cafe.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-w/08/96/a7/5a/west-egg-cafe.jpg"),

    };
    */

    static {
        for (Comment comment : Comment_Array) {
            addItem(comment);
        }
    }

    public static void getCommentsByRest(String restname) {
        Comments.clear();
        Comment_Map.clear();

        if (restname==null||restname.equals("")){
            for (Comment comment : Comment_Array) {
                addItem(comment);
            }
        }
        else {
            ArrayList<Comment> commentList = new ArrayList<Comment>();
            for (Comment item : Comment_Array) {
                if (item.getRestName().toLowerCase().contains(restname.toLowerCase())) {
                    commentList.add(item);
                }
            }

            for (Comment comment : commentList) {
                addItem(comment);
            }
        }

        Log.d("CommentListByRestaurant", "Result:" + Comments.size());

    }

    public static void updateList(ArrayList<Comment> list) {
        Comments.clear();
        Comment_Map.clear();
        if (list!=null) {
            for (Comment comment : list) {
                addItem(comment);
            }
        }
    }



}
