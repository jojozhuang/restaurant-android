using System.Collections.Generic;
using System.Web.Http;
using Johnny.Portfolio.Restaurant.DataServer.Models;

namespace Johnny.Portfolio.Restaurant.DataServer.Controllers
{
    public class CommentController : ApiController
    {
        private CommentRespository repo = CommentRespository.Current;
        private RestaurantRespository repoRest = RestaurantRespository.Current;

        public IEnumerable<Comment> GetAll()
        {
            return repo.GetList();
        }

        public IEnumerable<Comment> GetListByRestaurant(int restid)
        {
            return repo.GetListByRestaurant(restid);
        }

        public IEnumerable<Comment> GetListByUser(int id)
        {
            return repo.GetListByUser(id);
        }

        public Comment Get(int id)
        {
            return repo.Get(id);
        }

        [HttpPost]
        public HttpResult Create(CommentInfo item)
        {
            bool ret = repo.Add(item);
            if (ret)
            {
                repoRest.IncrementReview(item.RestId);
                return new HttpResult { RetCode = 0, Message = "Succeed to submit comment." };
            }
            else
            {
                return new HttpResult { RetCode = 1, Message = "Fail to submit comment." };
            }            
        }

        public void Delete(int id)
        {
            repo.Remove(id);
        }
	}
}