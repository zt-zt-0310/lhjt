// 获取所有侧边栏菜单项
const menuItems = document.querySelectorAll('.sidebar a');

// 为每个菜单项添加点击事件监听器
menuItems.forEach(item => {
    item.addEventListener('click', function (e) {
        e.preventDefault();
        // 获取目标页面的 ID
        const targetPageId = this.getAttribute('data-target');
        // 获取所有页面内容元素
        const allPages = document.querySelectorAll('.page-content');
        // 隐藏所有页面内容
        allPages.forEach(page => {
            page.style.display = 'none';
        });
        // 显示目标页面内容
        const targetPage = document.getElementById(targetPageId);
            // window.location.href = targetPageId+".html";
        if (targetPage) {
            targetPage.style.display = 'block';
        }
    });
});

// 初始显示仪表盘页面
document.getElementById('dashboard').style.display = 'block';

// 模拟用户数据
const users = [
    { id: 1, username: 'user1222', email: 'user1@example.com' },
    { id: 2, username: 'user2', email: 'user2@example.com' },
    { id: 3, username: 'user3', email: 'user3@example.com' }
];

// 更新仪表盘统计信息
function updateDashboardStats() {
    const userCountElement = document.getElementById('user-count');
    const activeUserCountElement = document.getElementById('active-user-count');

    userCountElement.textContent = users.length;
    // 这里简单假设所有用户都是活跃用户
    activeUserCountElement.textContent = users.length;
}

// 显示用户列表
function displayUserList() {
    const userTable = document.getElementById('user-table').getElementsByTagName('tbody')[0];
    userTable.innerHTML = '';

    users.forEach(user => {
        const newRow = userTable.insertRow();
        const idCell = newRow.insertCell(0);
        const usernameCell = newRow.insertCell(1);
        const emailCell = newRow.insertCell(2);
        const actionCell = newRow.insertCell(3);

        idCell.textContent = user.id;
        usernameCell.textContent = user.username;
        emailCell.textContent = user.email;

        const deleteButton = document.createElement('button');
        deleteButton.textContent = '删除';
        deleteButton.addEventListener('click', function () {
            const index = users.findIndex(u => u.id === user.id);
            if (index > -1) {
                users.splice(index, 1);
                displayUserList();
                updateDashboardStats();
            }
        });
        actionCell.appendChild(deleteButton);
    });
}

// 监听设置表单提交事件
const settingsForm = document.getElementById('settings-form');
settingsForm.addEventListener('submit', function (e) {
    e.preventDefault();
    const theme = document.getElementById('theme').value;
    const notifications = document.getElementById('notifications').checked;
    console.log(`主题设置为: ${theme}`);
    console.log(`通知开关状态: ${notifications ? '开启' : '关闭'}`);
    alert('设置已保存');
});

// 初始化页面数据
updateDashboardStats();
displayUserList();
