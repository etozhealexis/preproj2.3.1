window.onload = function () {
    refreshTable()
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

function refreshTable() {
    $("#users-table td").remove();
    $.ajax({
        method: 'GET',
        url: '/admin/users',
        contentType: 'application/json',
        success: function (response) {
            drawTable(response)
        },
        error: function (error) {
            console.log(error);
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
    data.roles.forEach(function (item) {
        roles += item.role + " ";
    });

    insertTd(data.id, tr)
    insertTd(data.name, tr)
    insertTd(data.age, tr)
    insertTd(data.email, tr)
    insertTd(roles, tr)
    insertEditBtn(data, tr)
    insertDelBtn(data, tr)
}

function insertTd(value, parent) {
    let element = document.createElement("td");
    element.scope = "row";
    element.innerText = value;
    parent.insertAdjacentElement("beforeend", element);
}

function insertDelBtn(data, parent) {
    let td = document.createElement("td");
    td.scope = "row";
    let element = document.createElement("button");
    element.innerText = "Delete";
    element.type = "submit";
    element.className = "btn btn-danger"
    element.addEventListener('click', () => {
        drawDeleteModal(data);
        $('#delete-user-modal').modal('show');
    })
    td.appendChild(element);
    parent.insertAdjacentElement("beforeend", td);
}

function insertEditBtn(data, parent) {
    let td = document.createElement("td");
    td.scope = "row";
    let element = document.createElement("button");
    element.innerHTML = "<button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#edit-user-modal\">"
    element.innerText = "Edit";
    element.className = "btn btn-info";
    element.addEventListener('click', () => {
        drawEditModal(data)
        $('#edit-user-modal').modal('show');
    })
    td.appendChild(element);
    parent.insertAdjacentElement("beforeend", td);
}

function drawEditModal(data) {
    $.ajax({
        method: 'GET',
        url: '/admin/edit/' + data.id.toString(),
        contentType: 'application/json',
        success: function (data) {
            // let hasUser = false;
            // let hasAdmin = false;
            document.getElementById('edit-name').value = data.name;
            document.getElementById('edit-age').value = data.age;
            document.getElementById('edit-email').value = data.email;
            document.getElementById('edit-password').value = data.password;
            document.getElementById('edit-role-user').checked = data.roles[0].role.includes('USER') || data.roles[1].role.includes('USER');
            document.getElementById('edit-role-admin').checked = data.roles[0].role.includes('ADMIN') || data.roles[1].role.includes('ADMIN');
            document.getElementById('edit-button').onclick = (function () {
                // hasUser = document.getElementById('edit-role-user').checked === true;
                // hasAdmin = document.getElementById('edit-role-admin').checked === true;
                let user = {
                    id: data.id,
                    name: document.getElementById('edit-name').value,
                    age: document.getElementById('edit-age').value,
                    email: document.getElementById('edit-email').value,
                    password: document.getElementById('edit-password').value,
                    // hasUser: hasUser,
                    // hasAdmin: hasAdmin
                }
                let userjson = JSON.stringify(user)
                $.ajax({
                    method: 'POST',
                    url: "/admin/edit/" + data.id.toString(),
                    data: userjson,
                    contentType: "application/json; charset=utf8",
                    success: function () {
                        document.getElementById('close-edit').click();
                        refreshTable();
                    },
                    error: function () {
                        alert('Bad Credentials. Please try again');
                    }
                });
            });
        }
    });
}

function drawDeleteModal(data) {
    document.getElementById('delete-name').value = data.name;
    document.getElementById('delete-age').value = data.age;
    document.getElementById('delete-email').value = data.email;
    document.getElementById('delete-role-user').checked = !!data.roles.includes('ROLE_USER');
    document.getElementById('delete-role-admin').checked = !!data.roles.includes('ROLES_ADMIN');
    document.getElementById('delete-button').onclick = (function () {
        $.ajax({
            method: 'GET',
            url: "/admin/delete/" + data.id,
            success: function () {
                document.getElementById('close-delete').click()
                refreshTable()
            }
        })
    })
}
