/**
 * 功能
 * 1.全部查詢
 * 2.單筆查詢
 * 3.新增
 * 4.修改
 * 5.刪除
 * a.顯示資訊
 * b.顯示表格 
 */

// WebApi 路徑
const API_BASE_URL = 'http://localhost:8080/book';

// 取得 UI<視圖> 元素
const messageBox = document.getElementById('messageBox'); 
const searchId   = document.getElementById('searchId');
const findOneBtn = document.getElementById('findOneBtn');
const findAllBtn = document.getElementById('findAllBtn');

// 按鈕事件監聽
findOneBtn.addEventListener('click', findOneBook);
findAllBtn.addEventListener('click', findAllBooks);

// 查詢單筆書籍
function findOneBook() {
	messageBox.innerHTML = '按下[查詢單筆]鈕';
}

// 查詢所有書籍
function findAllBooks() {
	messageBox.innerHTML = '按下[查詢全部]鈕';
}



