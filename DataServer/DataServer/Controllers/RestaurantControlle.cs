using System.Collections.Generic;
using System.Web.Http;
using Johnny.Portfolio.Restaurant.DataServer.Models;

namespace Johnny.Portfolio.Restaurant.DataServer.Controllers
{
    public class RestaurantController : ApiController
    {
        private RestaurantRespository repo = RestaurantRespository.Current;

        public IEnumerable<RestaurantInfo> GetAll()
        {
            return repo.GetList();
        }

        public IEnumerable<RestaurantInfo> GetAll(string keyword)
        {
            return repo.GetList(keyword);
        }

        public RestaurantInfo Get(int id)
        {
            return repo.Get(id);
        }

        [HttpPost]
        public RestaurantInfo Create(RestaurantInfo item)
        {
            return repo.Add(item);
        }

        [HttpPut]
        public bool Update(RestaurantInfo item)
        {
            return repo.Update(item);
        }

        public void Delete(int id)
        {
            repo.Remove(id);
        }
        
        [HttpPost]
        public void SetRate(RatingInfo rate)
        {
            repo.SetRate(rate.RestId, rate.Rating);
        }
	}
}