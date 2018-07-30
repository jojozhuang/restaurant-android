function ViewModel() {
    var self = this;

    self.users = ko.observableArray();
    self.displayUserSummary = ko.observable(true);

    self.code = ko.observable();
    self.message = ko.observable();
    
    self.registerUserName = ko.observable();
    self.registerEmail = ko.observable();
    self.registerPassword = ko.observable();

    self.loginUserName = ko.observable();
    self.loginPassword = ko.observable();

    function showResult(result) {
        //var ret = JSON.parse(result);
        self.code(result.RetCode);
        self.message(result.Message);
    }

    function sendAjaxRequest(httpMethod, url, params, callback, reqData) {
        $.ajax(url + (params ? "/" + params : ""), {
            type: httpMethod,
            success: callback,
            data: reqData
        });
    }

    self.getusers = function () {
        sendAjaxRequest("GET", 'api/User/GetAll', null, function (data) {
            self.users.removeAll();
            for (var i = 0; i < data.length; i++) {
                self.users.push(data[i]);
            }
        });
    }

    self.register = function () {
        self.code('');
        self.message('');

        var rgstData = {
            UserId: 0,
            UserName: self.registerUserName(),
            Email: self.registerEmail(),
            Password: self.registerPassword()
        };

        $.ajax({
            type: 'POST',
            url: 'api/User/Register',
            //contentType: 'application/json; charset=utf-8',
            data: rgstData
        }).done(function (data) {
            if (data.RetCode == 0) {
                rgstData.UserId = self.users().length + 1;
                self.users.push(rgstData);
            }
            showResult(data)
        }).fail(showResult);
    }

    self.login = function () {
        self.code('');
        self.message('');

        var loginData = {
            UserName: self.loginUserName(),
            Password: self.loginPassword()
        };

        $.ajax({
            type: 'POST',
            url: 'api/User/Login',
            data: loginData
        }).done(function (data) {
            showResult(data);
        }).fail(showResult);
    }

    self.logout = function () {
        $.ajax({
            type: 'POST',
            url: 'api/User/Logout'
        }).done(function (data) {
            showResult(data);
        }).fail(showResult);
    }
}

var app = new ViewModel();
app.getusers();
ko.applyBindings(app);