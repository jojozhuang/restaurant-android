using System.Collections.Generic;
using System.Web.Http;
using Johnny.Portfolio.Restaurant.DataServer.Models;

namespace Johnny.Portfolio.Restaurant.DataServer.Controllers
{
    public class UserController : ApiController
    {
        private UserRespository repo = UserRespository.Current;

        public IEnumerable<User> GetAll()
        {
            return repo.GetList();
        }

        public User Get(int id)
        {
            return repo.Get(id);
        }

        [HttpPost]
        public HttpResult Register(User item)
        {
            if (item == null || string.IsNullOrEmpty(item.UserName) || string.IsNullOrEmpty(item.Password) || string.IsNullOrEmpty(item.Email))
            {
                return new HttpResult { RetCode = 1, Message = "User Name, Email or Password can't be empty!" };
            }
            else if (repo.Exists(item.UserName))
            {
                return new HttpResult { RetCode = 1, Message = string.Format("User with name [{0}] is already existing, choose another name.", item.UserName) };
            }
            else
            {
                var foo = new System.ComponentModel.DataAnnotations.EmailAddressAttribute();
                if (!foo.IsValid(item.Email))
                {
                    return new HttpResult { RetCode = 1, Message = "The Email address is not valid!" };
                }
                else
                {
                    repo.Add(item);
                    return new HttpResult { RetCode = 0, Message = "Registration succeed!" };
                }
            }
        }

        [HttpPost]
        public HttpResult Login(LoginInfo item)
        {
            if (item == null || string.IsNullOrEmpty(item.UserName) || string.IsNullOrEmpty(item.Password))
            {
                return new HttpResult { RetCode = 1, Message = "User Name or Password can't be empty!" };
            }

            User user = repo.GetByName(item.UserName);
            if (user == null)
            {
                return new HttpResult { RetCode = 1, Message = "Login failed, no such user!" };
            }
            else if (!user.Password.Equals(item.Password))
            {
                return new HttpResult { RetCode = 1, Message = "Login failed, password is incorrect!" };                
            }
            else
            {
                return new HttpResult { RetCode = 0, Message = "Login succeed!" };
            }
        }

        [HttpPost]
        public HttpResult Logout(LogoutInfo logout)
        {
            if (logout== null ||string.IsNullOrEmpty(logout.UserName))
            {
                return new HttpResult { RetCode = 1, Message = "Fail to logout, provide your user name!" };
            }
            else
            {
                return new HttpResult { RetCode = 0, Message = logout.UserName + ": You are now logout!" };
            }
        }

        [HttpPut]
        public bool Update(User item)
        {
            return repo.Update(item);
        }

        public void Delete(int id)
        {
            repo.Remove(id);
        }
	}
}