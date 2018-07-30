using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Johnny.Portfolio.Restaurant.DataServer.Models
{
    public class HttpResult
    {
        public int RetCode { get; set; } //0: success, 1: error
        public string Message { get; set; }

    }
}