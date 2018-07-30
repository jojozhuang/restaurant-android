using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace Johnny.Portfolio.Restaurant.DataServer.Models
{
    //[DataContractAttribute]
    public class Comment
    {
        public Comment()
        {

        }
        public Comment(int id, String content, int restid, String restname, int userid, string username)
        {
            this.Id = id;
            this.Content = content;
            this.RestId = restid;
            this.RestName = restname;
            this.UserId = userid;
            this.UserName = username;
        }

        public int Id { get; set; }
        public string Content { get; set; }
        public int RestId { get; set; }
        public string RestName { get; set; }
        public int UserId { get; set; }
        public string UserName { get; set; }
    }
}