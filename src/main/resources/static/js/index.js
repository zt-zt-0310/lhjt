// 菜单点击事件处理
document.querySelectorAll('.sidebar a').forEach(link => {
    link.addEventListener('click', function (e) {
        e.preventDefault();
        const target = this.dataset.target;
        document.querySelectorAll('.page-content').forEach(content => {
            content.style.display = 'none';
        });
        document.getElementById(target).style.display = 'block';

        if (target === 'dashboard') {
            // 当点击数据报表菜单时，发起后端请求
            fetchData();
        }
    });
});

// 从后端获取数据
function fetchData() {
    // 这里需要替换为实际的后端 API 地址
    const apiUrl = 'your_backend_api_url';

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // 渲染数据到表格中
            renderDataTable(data);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}

// 渲染数据到表格中
function renderDataTable(data) {
    const tableBody = document.getElementById('dataTableBody');
    tableBody.innerHTML = '';

    data.forEach(item => {
        const row = document.createElement('tr');

        // 这里需要根据实际的数据结构和表格列进行调整
        const cell1 = document.createElement('td');
        cell1.textContent = item.someField;
        row.appendChild(cell1);

        // 处理营业收入列
        const cell2 = document.createElement('td');
        cell2.textContent = item.revenue1;
        row.appendChild(cell2);
        const cell3 = document.createElement('td');
        cell3.textContent = item.revenue2;
        row.appendChild(cell3);

        // 处理利润总额列
        const cell4 = document.createElement('td');
        cell4.textContent = item.profit1;
        row.appendChild(cell4);
        const cell5 = document.createElement('td');
        cell5.textContent = item.profit2;
        row.appendChild(cell5);

        // 处理实现税金列
        const cell6 = document.createElement('td');
        cell6.textContent = item.tax1;
        row.appendChild(cell6);
        const cell7 = document.createElement('td');
        cell7.textContent = item.tax2;
        row.appendChild(cell7);

        // 处理入库税金列
        const cell8 = document.createElement('td');
        cell8.textContent = item.paidTax1;
        row.appendChild(cell8);
        const cell9 = document.createElement('td');
        cell9.textContent = item.paidTax2;
        row.appendChild(cell9);

        tableBody.appendChild(row);
    });
}
