// 政策招标管理核心逻辑
document.addEventListener('DOMContentLoaded', function() {
    // 初始化配置：默认页码、每页条数
    const DEFAULT_PAGE_INDEX = 1;
    const DEFAULT_PAGE_SIZE = 10;

    // 页面加载时默认加载第一页数据
    loadPolicyBiddingList(DEFAULT_PAGE_INDEX, DEFAULT_PAGE_SIZE);

    // 绑定搜索事件（搜索框+搜索按钮）
    const searchInput = document.getElementById('bidding-search');
    const searchBtn = document.querySelector('.search-btn');

    // 搜索按钮点击事件
    searchBtn.addEventListener('click', function() {
        loadPolicyBiddingList(DEFAULT_PAGE_INDEX, DEFAULT_PAGE_SIZE);
    });

    // 搜索框回车事件
    searchInput.addEventListener('keyup', function(e) {
        if (e.key === 'Enter') {
            loadPolicyBiddingList(DEFAULT_PAGE_INDEX, DEFAULT_PAGE_SIZE);
        }
    });
});

/**
 * 加载政策招标列表（分页+搜索）
 * @param {number} pageIndex - 当前页码
 * @param {number} pageSize - 每页条数
 */
function loadPolicyBiddingList(pageIndex, pageSize) {
    // 获取搜索关键词
    const keyword = document.getElementById('bidding-search').value.trim();

    // 发起请求：调用后端分页查询接口
    fetch(`/api/policyBidding/list?pageIndex=${pageIndex}&pageSize=${pageSize}&keyword=${encodeURIComponent(keyword)}`)
        .then(response => {
            if (!response.ok) throw new Error('网络请求失败');
            return response.json();
        })
        .then(data => {
            if (data.success && data.data) {
                // 渲染表格数据
                renderBiddingTable(data.data.records);
                // 渲染分页组件
                renderBiddingPagination(
                    data.data.total,    // 总条数
                    pageIndex,          // 当前页码
                    pageSize            // 每页条数
                );
            } else {
                alert('加载政策招标数据失败：' + (data.message || '未知错误'));
                // 清空表格（避免旧数据残留）
                document.getElementById('bidding-table-body').innerHTML = '<tr><td colspan="8" style="text-align:center;">暂无数据</td></tr>';
            }
        })
        .catch(error => {
            console.error('加载失败：', error);
            document.getElementById('bidding-table-body').innerHTML = '<tr><td colspan="8" style="text-align:center;">加载失败，请刷新重试</td></tr>';
        });
}

/**
 * 渲染政策招标表格
 * @param {Array} biddingList - 政策招标数据数组（PolicyBidding实体列表）
 */
function renderBiddingTable(biddingList) {
    const tbody = document.getElementById('bidding-table-body');
    tbody.innerHTML = ''; // 清空表格

    if (!biddingList || biddingList.length === 0) {
        tbody.innerHTML = '<tr><td colspan="8" style="text-align:center;">暂无匹配数据</td></tr>';
        return;
    }

    // 遍历数据生成表格行
    biddingList.forEach(item => {
        const tr = document.createElement('tr');

        // 状态映射：1=有效，0=无效（根据实际业务调整）
        const statusText = item.status === 1 ? '<span style="color:green;">有效</span>' : '<span style="color:red;">无效</span>';
        // 是否正向政策映射：1=是，0=否
        const positiveText = item.isPositive === 1 ? '是' : '否';

        // 表格内容（与PolicyBidding实体字段对应）
        tr.innerHTML = `
            <td>${item.id || '-'}</td>
            <td style="max-width:200px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" title="${item.title || ''}">${item.title || '-'}</td>
            <td>${formatDate(item.publishTime) || '-'}</td>
            <td>${item.sourceName || '-'}</td>
            <td>${item.category || '-'}</td>
            <td>${statusText}</td>
            <td>${positiveText}</td>
            <td>
                <button class="btn btn-sm btn-edit" onclick="viewBiddingDetail(${item.id})">查看</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

/**
 * 渲染分页组件
 * @param {number} total - 总数据条数
 * @param {number} currentPage - 当前页码
 * @param {number} pageSize - 每页条数
 */
function renderBiddingPagination(total, currentPage, pageSize) {
    const paginationContainer = document.getElementById('bidding-pagination');
    paginationContainer.innerHTML = ''; // 清空分页

    if (total === 0) return; // 无数据时隐藏分页

    const totalPages = Math.ceil(total / pageSize); // 总页数

    // 分页按钮：首页
    const firstBtn = createPageBtn('首页', 1, currentPage === 1);
    paginationContainer.appendChild(firstBtn);

    // 分页按钮：上一页
    const prevBtn = createPageBtn('上一页', currentPage - 1, currentPage === 1);
    paginationContainer.appendChild(prevBtn);

    // 分页按钮：页码（只显示当前页前后2页，避免页码过多）
    const startPage = Math.max(1, currentPage - 2);
    const endPage = Math.min(totalPages, currentPage + 2);

    for (let i = startPage; i <= endPage; i++) {
        const pageBtn = createPageBtn(i.toString(), i, false, i === currentPage);
        paginationContainer.appendChild(pageBtn);
    }

    // 分页按钮：下一页
    const nextBtn = createPageBtn('下一页', currentPage + 1, currentPage === totalPages);
    paginationContainer.appendChild(nextBtn);

    // 分页按钮：末页
    const lastBtn = createPageBtn('末页', totalPages, currentPage === totalPages);
    paginationContainer.appendChild(lastBtn);

    // 分页信息：显示总条数和当前页
    const infoSpan = document.createElement('span');
    infoSpan.style.marginLeft = '15px';
    infoSpan.textContent = `共 ${total} 条数据，当前第 ${currentPage}/${totalPages} 页`;
    paginationContainer.appendChild(infoSpan);
}

/**
 * 创建分页按钮（通用工具函数）
 * @param {string} text - 按钮文本
 * @param {number} pageIndex - 按钮对应的页码
 * @param {boolean} isDisabled - 是否禁用
 * @param {boolean} isActive - 是否为当前页
 * @returns {HTMLButtonElement} 分页按钮元素
 */
function createPageBtn(text, pageIndex, isDisabled, isActive = false) {
    const btn = document.createElement('button');
    btn.textContent = text;
    btn.disabled = isDisabled;

    // 样式类：当前页高亮
    if (isActive) {
        btn.classList.add('active');
    }

    // 绑定点击事件：切换页码
    btn.addEventListener('click', function() {
        if (!isDisabled) {
            loadPolicyBiddingList(pageIndex, 10); // 固定每页10条，可扩展为动态配置
        }
    });

    return btn;
}

/**
 * 日期格式化工具（将ISO格式时间转为YYYY-MM-DD HH:mm）
 * @param {string} dateStr - ISO格式时间字符串（如：2025-11-30T12:00:00）
 * @returns {string} 格式化后的日期
 */
function formatDate(dateStr) {
    if (!dateStr) return '';
    const date = new Date(dateStr);
    return date.toLocaleString('zh-CN', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit'
    }).replace(/\//g, '-');
}

/**
 * 查看政策招标详情（可扩展为弹窗或跳转页面）
 * @param {number} id - 政策招标ID
 */
function viewBiddingDetail(id) {
    // 方案1：跳转至详情页（推荐，需创建 policy-bidding-detail.html）
    window.location.href = `/static/policy-bidding-detail.html?id=${id}`;

    // 方案2：弹窗展示详情（如需弹窗，可替换为以下逻辑）
    // fetch(`/api/policyBidding/get/${id}`)
    //     .then(response => response.json())
    //     .then(data => {
    //         if (data.success) {
    //             const bidding = data.data;
    //             // 弹窗渲染详情（需配合模态框HTML）
    //             showDetailModal(bidding);
    //         }
    //     });
}
