// 用户管理相关操作
document.addEventListener('DOMContentLoaded', function() {
    // 加载用户列表
    loadUserList(1, 10);

    // 绑定事件
    document.getElementById('add-user-btn').addEventListener('click', showAddUserModal);
    document.getElementById('save-user').addEventListener('click', saveUser);
    document.querySelectorAll('.close-modal').forEach(btn => {
        btn.addEventListener('click', closeUserModal);
    });
});

// 加载用户列表
function loadUserList(pageIndex, pageSize) {
    fetch(`/api/user/list?pageIndex=${pageIndex}&pageSize=${pageSize}`)
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                renderUserTable(data.data.records);
                renderPagination(data.data.total, pageIndex, pageSize);
            } else {
                alert('加载用户列表失败');
            }
        })
        .catch(error => console.error('Error loading users:', error));
}

// 渲染用户表格
function renderUserTable(users) {
    const tbody = document.getElementById('user-table-body');
    tbody.innerHTML = '';

    users.forEach(user => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.loginName}</td>
            <td>${user.phone || '-'}</td>
            <td>${user.deptId || '-'}</td>
            <td>${user.equipmentNo || '-'}</td>
            <td>
                <button class="btn btn-sm btn-edit" onclick="editUser(${user.id})">编辑</button>
                <button class="btn btn-sm btn-delete" onclick="deleteUser(${user.id})">删除</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

// 显示新增用户模态框
function showAddUserModal() {
    document.getElementById('user-modal-title').textContent = '新增用户';
    document.getElementById('user-form').reset();
    document.getElementById('user-id').value = '';
    document.getElementById('user-modal').classList.remove('hidden');
}

// 保存用户
function saveUser() {
    const userId = document.getElementById('user-id').value;
    const userData = {
        name: document.getElementById('name').value,
        loginName: document.getElementById('loginName').value,
        password: document.getElementById('password').value,
        phone: document.getElementById('phone').value,
        deptId: document.getElementById('deptId').value,
        equipmentNo: document.getElementById('equipmentNo').value
    };

    const url = userId ? `/api/user/update` : `/api/user/add`;
    const method = userId ? 'PUT' : 'POST';

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                closeUserModal();
                loadUserList(1, 10);
                alert(userId ? '用户更新成功' : '用户添加成功');
            } else {
                alert('操作失败: ' + (data.message || '未知错误'));
            }
        })
        .catch(error => console.error('Error saving user:', error));
}

// 关闭模态框
function closeUserModal() {
    document.getElementById('user-modal').classList.add('hidden');
}

// 编辑用户
function editUser(id) {
    fetch(`/api/user/get/${id}`)
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                const user = data.data;
                document.getElementById('user-modal-title').textContent = '编辑用户';
                document.getElementById('user-id').value = user.id;
                document.getElementById('name').value = user.name;
                document.getElementById('loginName').value = user.loginName;
                document.getElementById('phone').value = user.phone || '';
                document.getElementById('deptId').value = user.deptId || '';
                document.getElementById('equipmentNo').value = user.equipmentNo || '';
                // 密码不显示
                document.getElementById('password').value = '';
                document.getElementById('user-modal').classList.remove('hidden');
            }
        });
}

// 删除用户
function deleteUser(id) {
    if (confirm('确定要删除这个用户吗？')) {
        fetch(`/api/user/delete/${id}`, { method: 'DELETE' })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    loadUserList(1, 10);
                    alert('删除成功');
                } else {
                    alert('删除失败');
                }
            });
    }
}

// 渲染分页
function renderPagination(total, pageIndex, pageSize) {
    // 实现分页逻辑
}
