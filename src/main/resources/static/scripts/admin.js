window.onload = function () {

}

function showTable() {
    $.ajax({
        method: "GET",
        url: "/admin/users",
        contentType: "application/json",
        success: function (response) {
            drawTable(response)
        },
        error: function (error) {
            console.log(error)
        }
    })
}

function drawTable(data) {
    for (let i = 0; i < data.length; i++) {
        addRow(data[i]);
    }
}

function addRow(data) {
    let table = document.getElementById("users-table").getElementsByTagName("tbody")[0];
    let tr = table.insertRow(table.rows.length);
    let roles = "";

    insertTd(data.id, tr);
    insertTd(data.name, tr);
    insertTd(data.age, tr);
    insertTd(data.email, tr);
    insertTd(data.roles, tr);
    insertEditBtn(data.id, roles, tr);
    insertDelBtn(data, roles, tr);
}

function insertTd(value, tr) {
    let td = document.createElement("td");
    td.scope = "row";
    td.innerText = value;
    tr.insertAdjacentElement("beforeend", td);
}

// toDO
function insertEditBtn(value, roles, tr) {

}

function insertDelBtn(value, roles, tr) {
    let td = document.createElement("td");
    td.scope = "row";
    let btn = document.createElement("button");
    btn.innerText = "Delete";
    btn.className = "btn btn-outline-dark";
    btn.addEventListener('click', () => {
        showDeleteModal(value, roles);
    })
    td.appendChild(btn);
    parent.insertAdjacentElement("beforeend", td);
}

function showDeleteModal(value, roles) {
    drawDeleteModal(value, roles);
    $('#deleteUser').modal.show();
}

function drawDeleteModal(value, roles) {

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
            document.getElementById("users-table-shower").click();
        },
        error: function () {
            alert('Bad credentials');
        }
    })
}