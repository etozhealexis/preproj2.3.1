window.onload = function () {

}

function createUser() {
    let user = {
        name: document.getElementById("creating-name").value,
        age: document.getElementById("creating-age").value,
        email: document.getElementById("creating-email").value,
        password: document.getElementById("creating-password").value
    }
    let userjson = JSON.stringify(user);
    $.ajax({
        method: "POST",
        url: "/admin/new",
        data: userjson,
        contentType: "application/json; charset=utf8",
        success: function () {
            // document.getElementById("users-table-shower").click();
            window.location.replace("/admin");
        },
        error: function () {
            alert('Bad credentials');
        }
    })
}

function deleteUser() {
    let user = {}

    $.ajax({
        method: "GET",
        url: "admin/delete/" + data.id,
        success: function () {
            window.location.replace("/admin");
        }
    })
}

function editUser() {
    let user = {}

    $.ajax({
        method: "GET",
        url: "admin/edit" + id,
        contentType: "application/json",
        success: function (data) {
            document.getElementById().value = data.name;
            document.getElementById().value = data.age;
            document.getElementById().value = data.name;
        }
    })
}