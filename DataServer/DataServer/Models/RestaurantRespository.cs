using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Johnny.Portfolio.Restaurant.DataServer.Models
{
    public class RestaurantRespository
    {
        private static RestaurantRespository repo = new RestaurantRespository();

        public static RestaurantRespository Current
        {
            get
            {
                return repo;
            }
        }

        private List<RestaurantInfo> restaurants = new List<RestaurantInfo> {
            new RestaurantInfo(1,"Bavette's Bar and Boeuf(Server)",
                    RestaurantInfo.CategoryType.Restaurant,
                    "218 W Kinzie, Chicago, IL 60654 (Wells/Franklin)",
                    5.0f,
                    3,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/03/0b/9d/de/bavette-s-bar-and-boeuf.jpg",
                    "http://media-cdn.tripadvisor.com/media/daodao/photo-s/09/27/2b/71/caption.jpg",
                    "http://media-cdn.tripadvisor.com/media/daodao/photo-s/09/27/2b/70/caption.jpg"),
            new RestaurantInfo(2,"Swirlz Cupcakes(Server)",
                    RestaurantInfo.CategoryType.Dessert,
                    "705 W Belden Ave, Chicago, IL 60614-3301",
                    5.0f,
                    2,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/02/f8/84/b1/swirlz-cupcakes.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/29/28/70/photo0jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/08/a7/08/d8/swirlz.jpg"),
            new RestaurantInfo(3,"Jeni's Splendid Ice Creams(Server)",
                    RestaurantInfo.CategoryType.IceCream,
                    "3404 N. Southport Ave, Chicago, IL 60657",
                    4.0f,
                    1,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/05/21/50/16/jeni-s-splendid-ice-creams.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/08/28/4c/83/photo0jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/05/21/50/22/jeni-s-splendid-ice-creams.jpg"),
            new RestaurantInfo(4,"Intelligentsia Coffee",
                    RestaurantInfo.CategoryType.CoffeeTea,
                    "53 West Jackson Boulevard, Chicago, IL 60612-2512",
                    4.0f,
                    1,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/01/47/29/ee/great-coffee-intelligenista.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/08/a5/e6/6b/intelligentsia-coffee.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/43/ff/3f/intelligentsia-coffee.jpg"),
            new RestaurantInfo(5,"Firecakes Donuts",
                    RestaurantInfo.CategoryType.Bakeries,
                    "30 E Hubbard, Chicago, IL 60611",
                    3.0f,
                    1,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-o/08/26/79/dc/firecakes-donuts.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/03/b4/af/1c/firecakes-donuts.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/04/18/ac/11/firecakes-donuts.jpg"),
            new RestaurantInfo(6,"Polo Cafe & Catering Bridgeport",
                    RestaurantInfo.CategoryType.Restaurant,
                    "3322 S Morgan St, Chicago, IL 60608",
                    1.0f,
                    1,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-o/03/ee/e3/c0/polo-cafe-catering-bridgeport.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/41/5e/27/photo2jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/09/1c/71/51/polo-cafe-catering-bridgeport.jpg"),
            new RestaurantInfo(7,"Garrett Popcorn Shops",
                    RestaurantInfo.CategoryType.Dessert,
                    "625 N Michigan Ave, Chicago, IL 60611",
                    3.0f,
                    1,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-o/09/2e/ef/00/photo0jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/02/95/f5/51/filename-garretts1-jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/08/7d/5e/86/garrett-popcorn-shops.jpg"),
            new RestaurantInfo(8,"Lickity Split Frozen Custard & Sweets",
                    RestaurantInfo.CategoryType.IceCream,
                    "6056 N. Broadway, Chicago, IL 60660",
                    2.0f,
                    1,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/04/71/88/30/amazing.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/08/98/07/5f/photo0jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/08/12/43/ab/what-a-wonderful-place.jpg"),
            new RestaurantInfo(9,"Wildberry Pancakes and Cafe",
                    RestaurantInfo.CategoryType.CoffeeTea,
                    "130 E Randolph St, Chicago, IL 60601-6207",
                    1.0f,
                    1,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/02/76/f2/5a/filename-photo-35-jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/30/e6/98/super.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/03/01/2d/02/wildberry-pancakes-and.jpg"),
            new RestaurantInfo(10,"Doughnut Vault",
                    RestaurantInfo.CategoryType.Bakeries,
                    "401 1/2 N. Franklin St., Chicago, IL 60654",
                    4.0f,
                    1,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-w/08/9c/00/80/doughnut-vault.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/1a/6c/c0/doughnut-vault.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-w/06/99/ca/7b/doughnut-vault.jpg"),
            new RestaurantInfo(11,"Glazed and Infused",
                    RestaurantInfo.CategoryType.Dessert,
                    "30 E Hubbard, Chicago, IL 60611",
                    4.0f,
                    1,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-o/08/8f/b3/41/glazed-and-infused.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/05/0d/eb/photo2jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/07/e8/90/f7/glazed-and-infused.jpg"),
            new RestaurantInfo(12,"Alinea",
                    RestaurantInfo.CategoryType.Restaurant,
                    "1723 North Halsted St., Chicago, IL 60614-5501",
                    4.0f,
                    1,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-o/09/29/0a/2a/alinea.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/3a/a2/3c/photo1jpg.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/09/29/0a/0e/alinea.jpg"
            ),
            new RestaurantInfo(13,"Ghirardelli Ice Cream & Chocolate Shop",
                    RestaurantInfo.CategoryType.IceCream,
                    "830 N Michigan Ave, Chicago, IL 60611-2078",
                    5.0f,
                    1,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/03/73/c2/24/ghirardelli-ice-cream.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-s/09/3d/c6/b0/homemade-whipped-cream.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-o/07/1e/bb/77/ghirardelli-ice-cream.jpg"),
            new RestaurantInfo(14, "Do - Rite Donuts",
                    RestaurantInfo.CategoryType.Bakeries,
                    "50 W Randolph St, Chicago, IL 60601",
                    1.0f,
                    1,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/05/a4/ea/c4/do-rite-donuts-coffee.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-w/07/ec/aa/52/do-rite-donuts.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-w/07/cf/35/79/photo0jpg.jpg"),
            new RestaurantInfo(15,"West Egg Cafe",
                    RestaurantInfo.CategoryType.CoffeeTea,
                    "620 N Fairbanks Ct, Chicago, IL 60611-3011",
                    5.0f,
                    1,
                    "",
                    "http://media-cdn.tripadvisor.com/media/photo-s/01/6b/b9/e7/hubby-s-meal.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-w/08/e8/d8/96/west-egg-cafe.jpg",
                    "http://media-cdn.tripadvisor.com/media/photo-w/08/96/a7/5a/west-egg-cafe.jpg"),
        };

        public IEnumerable<RestaurantInfo> GetList()
        {
            return restaurants;
        }

        public IEnumerable<RestaurantInfo> GetList(string keyword)
        {
            return restaurants.Where(r => r.Name.ToLower().Contains(keyword.ToLower()));
        }

        public RestaurantInfo Get(int id)
        {
            return restaurants.Where(r => r.Id==id).FirstOrDefault();
        }

        public RestaurantInfo Add(RestaurantInfo item)
        {
            restaurants.Add(item);
            return item;
        }

        public void SetRate(int id, int rating)
        {
            RestaurantInfo item = Get(id);
            if (item != null)
            {
                if (rating<1){
                    rating = 1;
                }
                else if (rating > 5)
                {
                    rating = 5;
                }
                item.Rating = rating;
            }
        }

        public void IncrementReview(int id)
        {
            RestaurantInfo item = Get(id);
            if (item != null)
            {
                item.Reviews += 1;
            }
        }

        public void Remove(int id)
        {
            RestaurantInfo item = Get(id);
            if (item != null)
            {
                restaurants.Remove(item);
            }
        }

        public bool Update(RestaurantInfo item)
        {
            RestaurantInfo udpItem = Get(item.Id);
            if (udpItem != null)
            {
                udpItem.Location = item.Location;
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}