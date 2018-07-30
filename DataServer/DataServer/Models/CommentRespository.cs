using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Johnny.Portfolio.Restaurant.DataServer.Models
{
    public class CommentRespository
    {
        private static CommentRespository repo = new CommentRespository();

        public static CommentRespository Current
        {
            get
            {
                return repo;
            }
        }

        private List<Comment> comments = new List<Comment> {
           new Comment(1, 
                    "The food and the service was excellent as well as unique. ",
                    1,
                    "Bavette's Bar and Boeuf(Server)",
                    1,
                    "Johnny"),
           new Comment(16, 
                    "Fantastic ",
                    1,
                    "Bavette's Bar and Boeuf(Server)",
                    2,
                    "Marlon"),
           new Comment(17, 
                    "I love it. ",
                    1,
                    "Bavette's Bar and Boeuf(Server)",
                    3,
                    "Divid"),
            new Comment(2,
                    "Completely changed how I think about food.",
                    2,
                    "Swirlz Cupcakes(Server)",
                    1,
                    "Johnny"),
            new Comment(18,
                    "Exceed my expectation",
                    2,
                    "Swirlz Cupcakes(Server)",
                    2,
                    "Marlon"),
            new Comment(3,
                    "I thoroughly enjoyed my meal.",
                    3,
                    "Jeni's Splendid Ice Creams(Server)",
                    1,
                    "Johnny"),
            new Comment(4,
                    "The service was great.",
                    4,
                    "Intelligentsia Coffee",
                    1,
                    "Johnny"),
            new Comment(5,
                    "The overall food itself was probably better than Fat Duck",
                    5,
                    "Firecakes Donuts",
                    1,
                    "Johnny"),
            new Comment(6,
                    "good food",
                    6,
                    "Polo Cafe & Catering Bridgeport",
                    1,
                    "Johnny"),
            new Comment(7,
                    "Easily one of the best meals of my life!",
                    7,
                    "Garrett Popcorn Shops",
                    1,
                    "Johnny"),
            new Comment(8,
                    "We were happily seated at our table and the courses begins.",
                    8,
                    "Lickity Split Frozen Custard & Sweets",
                    1,
                    "Johnny"),
            new Comment(9,
                    "The service was impeccable and the whole experience was definitely to remember for a lifetime!",
                    9,
                    "Wildberry Pancakes and Cafe",
                    2,
                    "Marlon"),
            new Comment(10,
                    "Found flavors and service lacking compared to other Michelin starred restaurants.",
                    10,
                    "Doughnut Vault",
                    2,
                    "Marlon"),
            new Comment(11,
                    "Definitely met but did not exceed expectations.",
                    11,
                    "Glazed and Infused",
                    2,
                    "Marlon"),
            new Comment(12,
                    "It is an extraordinary experience for sure",
                    12,
                    "Alinea",
                    2,
                    "Marlon"),
            new Comment(13,
                    "The actual dining room is elegant but still simplistic.",
                    13,
                    "Ghirardelli Ice Cream & Chocolate Shop",
                    3,
                    "David"),
            new Comment(14,
                    "One of the most impressive dishes was also the one that made me the saddest though.",
                    14,
                    "Do - Rite Donuts",
                    3,
                    "David"),
            new Comment(15,
                    "What a treat! This is an experience that everyone should be able to do once. ",
                    15,
                    "West Egg Cafe",
                    3,
                    "David"),
        };

        public IEnumerable<Comment> GetList()
        {
            return comments;
        }

        public IEnumerable<Comment> GetListByRestaurant(int restid)
        {
            return comments.Where(r => r.RestId==restid);
        }

        public IEnumerable<Comment> GetListByUser(int userid)
        {
            return comments.Where(r => r.UserId == userid);
        }

        public Comment Get(int id)
        {
            return comments.Where(r => r.UserId.Equals(id)).FirstOrDefault();
        }

        public bool Add(CommentInfo item)
        {
            bool isadded = false;
            try
            {
                String restname = RestaurantRespository.Current.GetList().Where(r => r.Id == item.RestId).FirstOrDefault().Name;
                int userid = UserRespository.Current.GetByName(item.UserName).UserId;
                Comment newitem = new Comment(comments.Count + 1, item.Content, item.RestId, restname, userid, item.UserName);
                comments.Add(newitem);
                isadded = true;
            }
            catch (Exception ex)
            {
                Console.Write(ex);
            }
            return isadded;            
        }

        public void Remove(int id)
        {
            Comment item = Get(id);
            if (item != null)
            {
                comments.Remove(item);
            }
        }        
    }
}