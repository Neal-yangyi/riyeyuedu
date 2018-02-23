function login() {
    var reader_name = $("#reader_name").val();
    var reader_password = $("#reader_password").val();

    if (reader_name === "" || reader_name == null) {
        alert("账号不能为空");
        return;
    }
    if (reader_password === "" || reader_password == null) {
        alert("密码不能为空");
        return;
    }

    $.ajax({
        type: "post",
        url: "/login",
        dataType: "text",
        data: {readerName: reader_name, password: reader_password},
        success: function (data) {
            if ("1" === data) {
                alert("登录成功");
                window.location.reload();
            } else if ("0" === data) {
                alert("用户名或密码错误");
            }
        },
        error: function (xhr, status, error) {
            alert(error);
        }
    })
}

function logout() {
    $.ajax({
        type: "post",
        url: "/user/logout",
        dataType: "json",
        data: {},
        success: function () {
            alert("你已登出");
            window.location.href = "/";
        },
        error: function (xhr, status, error) {
            alert(status);
        }
    })
}

function register() {
    var reader_name = $("#username").val();
    var password = $("#password").val();
    var password2 = $("#password2").val();

    if (reader_name.toString().trim() ==="" || reader_name.toString().trim() === null) {
        alert("用户名不能为空");
        return;
    }

    if (password === "" || password === null) {
        alert("密码不能为空");
        return;
    }

    if (password2 === "" || password2 === null) {
        alert("请重复密码");
        return;
    }

    if (password !== password2) {
        alert("两次密码不一致，请重新输入");
        return;
    }

    $.ajax({
        type: "post",
        url: "/register",
        dataType: "text",
        data: {readerName: reader_name, password: password},
        success: function (data) {
            if ("1" === data) {
                alert("注册成功");
                window.location.href = "/home";
            } else if ("0" === data) {
                alert("用户名已被注册");
            }
        },
        error: function (xhr, status, error) {
            alert(status);
        }
    })
}

function registerAuthor() {
    var author_name = $("#author_name").val();
    var author_password = $("#author_password").val();
    var author_password2 = $("#author_password2").val();

    if (author_name.toString().trim() ==="" || author_name.toString().trim() === null) {
        alert("笔名不能为空");
        return;
    }

    if (author_password === "" || author_password === null) {
        alert("密码不能为空");
        return;
    }

    if (author_password2 === "" || author_password2 === null) {
        alert("请重复密码");
        return;
    }

    if (author_password !== author_password2) {
        alert("两次密码不一致，请重新输入");
        return;
    }

    $.ajax({
        type: "post",
        url: "/registerAuthor",
        dataType: "text",
        data: {readerName: author_name, password: author_password},
        success: function (data) {
            if ("1" === data) {
                alert("注册成功");
                window.location.href = "/toAuthorHome";
            } else if ("0" === data) {
                alert("改笔名已被注册");
            }
        },
        error: function (xhr, status, error) {
            alert(status);
        }
    })
}