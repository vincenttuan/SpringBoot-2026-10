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
const messageBox    = document.getElementById('messageBox'); 
const searchId      = document.getElementById('searchId');
const findOneBtn    = document.getElementById('findOneBtn');
const findAllBtn    = document.getElementById('findAllBtn');
const bookTableBody = document.getElementById('bookTableBody');
const bookForm      = document.getElementById('bookForm'); 
const bookId        = document.getElementById('bookId'); 
const bookName      = document.getElementById('bookName'); 
const bookPrice     = document.getElementById('bookPrice'); 
const bookAmount    = document.getElementById('bookAmount'); 
const bookPub       = document.getElementById('bookPub'); 
const addBtn        = document.getElementById('addBtn'); 
const updateBtn     = document.getElementById('updateBtn'); 
const resetBtn      = document.getElementById('resetBtn'); 
const singleResult  = document.getElementById('singleResult');

// 紀錄目前表單模式
// create = 新增模式(預設)
// update = 修改模式
let formMode = 'create';

// 按鈕事件監聽
findOneBtn.addEventListener('click', () => {
	const id = searchId.value;
	findOneBook(id);
});
findAllBtn.addEventListener('click', findAllBooks);
addBtn.addEventListener('click', addBook);
updateBtn.addEventListener('click', updateBook);
resetBtn.addEventListener('click', () => {
	bookForm.reset(); // 表單清除;
	updateFormMode('create');// 切換模式
});


// 視窗監聽事件
window.addEventListener('DOMContentLoaded', () => {
	// 查詢所有書籍
	findAllBooks();
	// 切換模式
	updateFormMode('create');
});

// 模式切換邏輯
function updateFormMode(mode) {
	if(mode === 'create') {
		addBtn.disabled = false;
		updateBtn.disabled = true;
		messageBox.innerHTML = `新增模式 [${mode}]`;
	} else {
		addBtn.disabled = true;
		updateBtn.disabled = false;
		messageBox.innerHTML = `修改模式 [${mode}]`;
	}
	formMode = mode;
}

// 新增書籍
async function addBook() {
	// 建立 book json
	const book = {
		name: bookName.value.trim(),
		price: bookPrice.value ? Number(bookPrice.value) : null,
		amount: bookAmount.value ? Number(bookAmount.value) : null,
		pub: bookPub.checked
	};
	
	console.log(book);
	messageBox.innerHTML = JSON.stringify(book);
	
	// 透過 WebApi 查詢書籍
	// 得到回應
	const response = await fetch(API_BASE_URL, {
		method: "POST",
		headers: {"Content-Type": "application/json"},
		body: JSON.stringify(book)
	});
	
	// 分析結果
	const result = await response.json(); 
	console.log('result:', result);
	messageBox.innerHTML = JSON.stringify(result.data);
	
	// 清空表單
	bookForm.reset();
	
	// 重新查詢
	findAllBooks(); 
}

// 修改書籍
async function updateBook() {
	messageBox.innerHTML = '按下[修改單筆]鈕';
	console.log('按下[修改單筆]鈕');
	try {
		// 建立要修改書籍的 book 物件(json 物件格式)
		const book = {
			id: bookId.value ? Number(bookId.value) : 0,
			name: bookName.value.trim(),
			price: bookPrice.value ? Number(bookPrice.value) : 0,
			amount: bookAmount.value ? Number(bookAmount.value) : 0,
			pub: bookPub.checked
		};
		console.log('修改 book:', book);
		
		// 檢查資料是否有內容
		if(book.id === 0) {
			messageBox.innerHTML = '沒有 id 無法修改';
			return;
		}
		
		if(!book.name) {
			messageBox.innerHTML = '沒有書名無法修改';
			return;
		}
		
		// 得到回應
		console.log('修改 url:', `${API_BASE_URL}/${book.id}`);
		
		const response = await fetch(`${API_BASE_URL}/${book.id}`, {
			method: "PUT",
			headers: {"Content-Type": "application/json"},
			body: JSON.stringify(book)
		});
		
		// 得到結果
		const result = await response.json();
		console.log('修改 result:', result);
		console.log('修改 result.message:', result.message);

		messageBox.innerHTML = result.message;
		
		findAllBooks(); // 重新查詢
		bookForm.reset(); // 清空表單
		updateFormMode('create'); // 變更模式
		
	} catch(e) {
		messageBox.innerHTML = e;
		console.log(e);
	}
}

// 查詢單筆書籍
async function findOneBook(id) {
	messageBox.innerHTML = '按下[查詢單筆]鈕';
	try {
		// 透過 WebApi 查詢書籍
		const url = `${API_BASE_URL}/${id}`;
		// 得到回應
		const response = await fetch(url);
		// 取得結果
		const result = await response.json();
		// 取得 json 資料
		const data = result.data;
		console.log("data:", data);
		// 顯示結果
		messageBox.innerHTML = result.message;
		singleResult.innerHTML = JSON.stringify(data);
		// 將單筆資料顯示在表單上
		fillForm(data);
		// 變更模式
		updateFormMode('update');
		
	} catch(e) {
		messageBox.innerHTML = e;
		console.log(e);
	}
}

// 查詢所有書籍
async function findAllBooks() {
	messageBox.innerHTML = '按下[查詢全部]鈕';
	
	try {
		// 透過 WebApi 查詢書籍
		// 得到回應
		const response = await fetch(API_BASE_URL);
		console.log('response:', response);
		
		// 分析結果
		const result = await response.json();
		// stringify 將 json 物件結構轉字串結構, 便於讓人可以看懂
		messageBox.innerHTML = JSON.stringify(result.data);
		console.log('result:', result, result.data, JSON.stringify(result.data));
		
		// 資料渲染
		const data = result.data;
		renderBookTable(data);
		
		
	} catch(e) {
		messageBox.innerHTML = e;
		console.log('err:', e);
	}
	
}

// 表單渲染
// 假設: book = {"id":1, name":"Python","price":13.5,"amount":15,"pub":true}
function fillForm(book) {
	bookId.value = book.id ?? "0";
	bookName.value = book.name ?? "";
	bookPrice.value = book.price ?? "";
	bookAmount.value = book.amount ?? "";
	// 單選盒
	bookPub.checked = book.pub ?? false;
}

// 表格渲染
function renderBookTable(data) {
	const books = data;
	
	let html = "";
	
	books.forEach(book => {
		console.log('book:', book);
		html += `
			<tr>
				<td>${book.id}</td>
				<td>${book.name}</td>
				<td>${book.price}</td>
				<td>${book.amount}</td>
				<td>${book.pub}</td>
				<td>
					<button onclick="findOneBook(${book.id})">修改</button>
					<button onclick="deleteOneBook(${book.id})" class="danger">刪除</button>
				</td>
			</tr>
		`;
	});
	
	// 替換資料
	bookTableBody.innerHTML = html; 
	
}


