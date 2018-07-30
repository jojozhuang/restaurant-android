using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Johnny.Portfolio.Restaurant.DataServer.Models
{
    public class CommentInfo
    {
        public int RestId { get; set; }
        public string UserName { get; set; }
        public string Content { get; set; }
    }
}