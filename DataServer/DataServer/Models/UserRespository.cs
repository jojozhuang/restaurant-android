using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Johnny.Portfolio.Restaurant.DataServer.Models
{
    public class UserRespository
    {
        private static UserRespository repo = new UserRespository();

        public static UserRespository Current
        {
            get
            {
                return repo;
            }
        }

        private List<User> users = new List<User> {
            new User { 
                UserId = 1, UserName = "Johnny", Email = "jojozhuang@gmail.com", Password = "123"},
            new User { 
                UserId = 2, UserName = "Marlon", Email = "marlon@gmail.com", Password = "123"},
            new User { 
                UserId = 3, UserName = "David", Email = "david@gmail.com", Password = "123"},
        };

        public IEnumerable<User> GetList()
        {
            return users;
        }

        public User Get(int id)
        {
            return users.Where(r => r.UserId == id).FirstOrDefault();
        }

        public User GetByName(string name)
        {
            return users.Where(r => r.UserName == name).FirstOrDefault();
        }

        public bool Exists(string name)
        {
            User user = users.Where(r => r.UserName == name).FirstOrDefault();
            return (user == null) ? false : true;
        }

        public User Add(User item)
        {
            item.UserId = users.Count + 1;
            users.Add(item);
            return item;
        }

        public void Remove(int id)
        {
            User item = Get(id);
            if (item != null)
            {
                users.Remove(item);
            }
        }

        public bool Update(User item)
        {
            User udpItem = Get(item.UserId);
            if (udpItem != null)
            {
                udpItem.UserName = item.UserName;
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}