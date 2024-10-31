document.addEventListener('DOMContentLoaded', () => {
	const list = document.querySelector(".list-group");

	// メッセージ削除ボタンのセットアップ
	const setupDeleteButtons = () => {
		document.querySelectorAll('.deleteOneButton').forEach(button => {
			button.addEventListener('click', function() {
				const messageId = this.getAttribute('data-id');
				axios.delete(`/delete/${messageId}`)
					.then(response => {
						console.log("成功しました");
						this.closest("li").remove();
					})
					.catch(error => {
						console.log("エラー", error);
					});
			});
		});
	};

	// メッセージ編集ボタンのセットアップ
	const setupEditButtons = () => {
		document.querySelectorAll('.editButton').forEach(button => {
			button.addEventListener('click', function() {
				const messageId = this.getAttribute('data-id');
				const messageContent = this.closest('li').querySelector('span:nth-child(2)').textContent;
				document.getElementById('editMessageId').value = messageId;
				document.getElementById('editMessageContent').value = messageContent;
				new bootstrap.Modal(document.getElementById('editModal')).show();
			});
		});
	};

	// 全削除ボタンのセットアップ
	const setupDeleteAllButton = () => {
		document.getElementById('deleteAllButton').addEventListener('click', function() {
			axios.post(`/delete`)
				.then(resoponse => {
					console.log("全削除成功")
					const items = document.querySelectorAll('.list-group li');
					items.forEach(item => item.remove());
				})
				.catch(errer => { console.log("エラー", errer) });
		});
	};

	// 編集内容保存ボタンのセットアップ
	const setupSaveEditButton = () => {
		document.getElementById('saveEditButton').addEventListener('click', function() {
			const messageId = document.getElementById('editMessageId').value;
			const updatedContent = document.getElementById('editMessageContent').value;
			axios.post(`/update/${messageId}`, { content: updatedContent })
				.then(response => { location.reload })
				.catch(errer => { console.log("エラーが発生しました", errer) });
		});
	};

	const insertList = () => {
		document.getElementsByClassName("insertListButton")[0].addEventListener("click", function() {
			fetch(`/save`, {
				method: `POST`
			})
				.then(response => {
					if (response.ok) {
						window.location.href = "/";
					}
				});

		});
	};

	const nextPage = () => {
		document.getElementsByClassName("recruitListButton")[0].addEventListener('click', function() {
			window.location.href = "/recruitList";
		});
	};

	// 初期化関数
	const initialize = () => {
		setupDeleteButtons();
		setupEditButtons();
		setupDeleteAllButton();
		setupSaveEditButton();
		nextPage();
		insertList();
	};

	// 初期化を実行
	initialize();

});
